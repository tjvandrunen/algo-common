#include "btree.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>


struct btree_node *create_node() 
{
  struct btree_node *to_return = 
    (struct btree_node*) malloc(sizeof(struct btree_node));
  to_return->deg = 0;
  int i;
  for (i = 0; i < MAX_DEG; i++) to_return->children[i] = NULL;
  return to_return;
}


struct btree *create()
{
  /*
  struct btree *to_return = (struct btree *) malloc(sizeof (struct btree));
  to_return->max_deg = max_deg;
  to_return->root = make_node(max_deg);
  return to_return;
  */
  struct btree *to_return = (struct btree*) malloc(sizeof(struct btree));
  to_return->root = NULL; //create_node();
  return to_return;
}

struct btree_place 
{
  struct btree_node *node;  /* Node where a key is or would go */
  int pos;                  /* Index of least upper bound for a key at this node */
  int found;                /* Is the key there? */
  struct btree_place *prev; /* The previous step in this search, 
                               or NULL if node is the root */
};

struct btree_place *find(struct btree_node *root, char *key)
{
  if (root == NULL) return NULL; /* empty tree yields an empty search */
  
  /* the search result in the parent node */
  struct btree_place * trace = NULL;  
  /* the current node being searched */
  struct btree_node *current = root;
  
  do  /* search each node until we find the key or hit a leaf */
    {
      /* perform binary search for the key at the current node */
      int start = 0, stop = current->deg-1;
      while (stop-start > 0)
        {
	  int mid = (start + stop) / 2;
          int compare = strcmp(key, current->keys[mid]);
          if (compare < 0) stop = mid;
          else if (compare == 0) { start = mid; stop = mid; }
          else // compare > 0
            start = mid+1;
        }
      /* postcondition: start is the least upper bound of key */
      
      /* encapsulate the results of searching at this node
         in a new btree_place */
      struct btree_place * step = (struct btree_place*) malloc(sizeof(struct btree_place));
      step->node = current;
      step->pos = start;
      step->prev = trace;
      step->found = (start < current->deg - 1) 
        && (strcmp(key, current->keys[start]) == 0);
      
      /* prepare for next iteration */
      trace = step;
      current = current->children[start];
    } while(!trace->found && current != NULL);
  /* postcondition: 
     (a) trace->node->keys[trace->pos] is the least upper bound of
         key in trace->node, and similarly for every btree_place
         in the list starting at trace.
     (b) If key is anywhere in this btree, then it is equal to
         trace->node->keys[trace->pos].
     (c) If key is not in this btree, then trace->node is a leaf.  */

  return trace;
}

void destroy_trace(struct btree_place *trace)
{
  while (trace != NULL)
    {
      struct btree_place *prev = trace->prev;
      trace->node = NULL;
      trace->prev = NULL;
      free(trace);
      trace = prev;
    }
}

struct btree_shards
{
  char *key;
  int value;
  struct btree_node *left;
  struct btree_node *right;
};


void grow(struct btree *map, struct btree_shards shards)
{
  map->root = create_node();
  map->root->children[0] = shards.left;
  map->root->children[1] = shards.right;
  map->root->keys[0] = shards.key;
  map->root->values[0] = shards.value;
  map->root->deg = 2;
}

void insert(struct btree_place *trace, struct btree_shards shards)
{
  int i;
  for (i = trace->node->deg - 1; i > trace->pos; i--)
    {
      trace->node->children[i+1] = trace->node->children[i];
      trace->node->keys[i] = trace->node->keys[i-1];
      trace->node->values[i] = trace->node->values[i-1];
    }
  trace->node->children[i+1] = shards.right;
  trace->node->children[i] = shards.left;
  trace->node->keys[i] = shards.key;
  trace->node->values[i] = shards.value;
  trace->node->deg++;
}

struct btree_shards split_key_below_half(struct btree_node *node, int pos, 
                                         struct btree_shards shards)
{
  /* the node to be the left child of the next shards */
  struct btree_node *split_left_node = create_node();

  int i = 0, half = MAX_DEG / 2;

  /* copy the keys, values, and children that come before the
     shard key into the new left node */
  while(i < pos)
    {
      split_left_node->keys[i] = node->keys[i];
      split_left_node->values[i] = node->values[i];
      split_left_node->children[i] = node->children[i];
      i++;
    }

  /* put the shards into the new left node */
  split_left_node->keys[i] = shards.key;
  split_left_node->values[i] = shards.value;
  split_left_node->children[i] = shards.left;
  i++;
  split_left_node->children[i] = shards.right;
  while(i < half)
    {
      split_left_node->keys[i] = node->keys[i];
      split_left_node->values[i] = node->values[i];
      split_left_node->children[i+1] = node->children[i+1];      
      i++;
    }
  split_left_node->deg = i + 1;

  /* grab the next key and value, which are the key and value
     for the next shards */
  char* mid_key = node->keys[i];
  int mid_value = node->values[i];
  i++;

  /* the node to be the right shild of the next shards */
  struct btree_node *split_right_node = create_node();
  
  /* copy all the remaining keys of the node we're splitting
     into the new right node */
  while (i < MAX_KEYS)
    {
      split_right_node->keys[i-half-1] = node->keys[i];
      split_right_node->values[i-half-1] = node->values[i];
      split_right_node->children[i-half-1] = node->children[i];
      i++;
     }
   split_right_node->children[i-half-1] = node->children[i];
   split_right_node->deg = i-half;

   /* finally, set the split variables */
  shards.key = mid_key;
  shards.value = mid_value;
  shards.left = split_left_node;
  shards.right = split_right_node;
  return shards;
}

struct btree_shards split_key_middle(struct btree_node *node, int pos, 
                                         struct btree_shards shards)
{
  struct btree_node *split_left_node = create_node();
  int i = 0, half = MAX_DEG / 2;
  while(i < half)
    {
      split_left_node->keys[i] = node->keys[i];
      split_left_node->values[i] = node->values[i];
      split_left_node->children[i] = node->children[i];
      i++;
    }
  split_left_node->children[i] = shards.left;
  split_left_node->deg = i + 1;

  struct btree_node *split_right_node = create_node();
  split_right_node->children[0] = shards.right;
  while (i < MAX_KEYS)
    {
      split_right_node->keys[i-half] = node->keys[i];
      split_right_node->values[i-half] = node->values[i];
      split_right_node->children[i-half+1] = node->children[i];
      i++;
    }
   split_right_node->deg = i-half+1;

   /* finally set the split variables */
   shards.left = split_left_node;
   shards.right = split_right_node;
  
   return shards;
}


struct btree_shards split_key_above_half(struct btree_node *node, int pos, 
                                         struct btree_shards shards)
{
  struct btree_node *split_left_node = create_node();
  int i = 0, half = MAX_DEG / 2;
  while(i < half)
    {
      split_left_node->keys[i] = node->keys[i];
      split_left_node->values[i] = node->values[i];
      split_left_node->children[i] = node->children[i];
      i++;
    }
  split_left_node->children[i] = node->children[i];
  split_left_node->deg = i + 1;
  
  
  struct btree_node *split_right_node = create_node();
  while (i < pos)
    {
      split_right_node->keys[i-half] = node->keys[i];
      split_right_node->values[i-half] = node->values[i];
      split_right_node->children[i-half] = node->children[i];
      i++;
    }
  split_right_node->keys[i-half] = shards.key;
  split_right_node->values[i-half] = shards.value;
  split_right_node->children[i-half] = shards.left;
  char* mid_key = node->keys[i-half];
  int mid_value = node->values[i-half];
  i++;
  split_right_node->children[i-half] = shards.right;
  while (i < MAX_KEYS)
    {
      split_right_node->keys[i-half] = node->keys[i];
      split_right_node->values[i-half] = node->values[i];
      split_right_node->children[i-half+1] = node->children[i+1];
      i++;
    }
  split_right_node->deg = i-half+1;
  /* finally set the split variables */
  shards.key = mid_key;
  shards.value = mid_value;
  shards.left = split_left_node;
  shards.right = split_right_node;
  
  return shards;
}

struct btree_shards split(struct btree_place *trace, struct btree_shards shards)
{
  int compare = trace->pos - MAX_DEG / 2;
  if (compare < 0) 
    return split_key_below_half(trace->node, trace->pos, shards);
  else if (compare == 0) 
    return split_key_middle(trace->node, trace->pos, shards);
  else /* compare > 0 */ 
    return split_key_above_half(trace->node, trace->pos, shards);
}

void put(struct btree *map, char *key, int value)
{
  /* find the node and pos where this key is or should be */
  struct btree_place *trace = find(map->root, key);
  /* if the key is there, replace its value */
  if (trace != NULL && trace->found) trace->node->values[trace->pos] = value;
  /* otherwise, insert it at a leaf, with possible splits cascading up */
  else
    {
      /* the "shards" are initially the key and value being put
         together with null children */
      struct btree_shards shards = { key, value, NULL, NULL };
      /* as long as we are at a non-full node ...*/
      while (trace != NULL && trace->node->deg == MAX_DEG)
        {
          shards = split(trace, shards);     /* split that node */
          trace = trace->prev;               /* ascend a level */
        }
      /* if we found a non-full node, insert the shards into that node */
      if (trace != NULL) insert(trace, shards);
      /* if we went past the root, make the map grow one level */
      else grow(map, shards);
    }
}



int contains_key(struct btree *map, char *key)
{  
  //return find(map->root, key).found;
  struct btree_place *trace = find(map->root, key);
  int found = trace == NULL? 0 : trace->found;
  destroy_trace(trace);
  return found;
}

int get(struct  btree *map, char *key)
{
  /*
  struct btree_place place = find(map->root, key);
  if (place.found) return place.node->values[place.pos];
  else return -1;
  */
  struct btree_place *trace = find(map->root, key);
  int value;
  if (trace != NULL && trace->found) value = trace->node->values[trace->pos];
  else value = -1;
  destroy_trace(trace);
  return value;
}

void destroy_node(struct btree_node *node)
{
  int i;
  for (i = 0; i < node->deg; i++)
    {
      destroy_node(node->children[i]);
      node->children[i] = NULL;
    }
  free(node);
}

void destroy(struct btree *map) 
{
  destroy_node(map->root);
  map->root = NULL;
  free(map);
}
