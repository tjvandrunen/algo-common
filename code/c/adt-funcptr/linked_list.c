/* linked_list.c */
#include "linked_list.h"
#include <stdlib.h>
#include <stdio.h>

struct node
{
  void *datum;
  struct node *next;
};

struct linked_list
{
  struct node *head;
  struct node *tail;
  int size;
};



void add_ll(struct list *this, void *element) 
{
  struct linked_list * this_ll = (struct linked_list *) this->data;
  struct node *new_node = (struct node*) malloc(sizeof(struct node));
  new_node->datum = element;
  new_node->next = NULL;
  if (this_ll->head == NULL)
      this_ll->head = this_ll->tail = new_node;
  else {
    this_ll->tail->next = new_node;
    this_ll->tail = new_node;
  }
  this_ll->size++;
}

void set_ll(struct list *this, int index, void *element)
{
  struct linked_list * this_ll = (struct linked_list *) this->data;
  struct node *current = this_ll->head;
  int i;
  for (i = 0; i < index; i++)
    current = current->next;
  current->datum = element;
}

void *get_ll(struct list *this, int index)
{
  struct linked_list * this_ll = (struct linked_list *) this->data;
  struct node *current = this_ll->head;
  int i;
  for (i = 0; i < index; i++)
    current = current->next;
  return current->datum;
}

void insert_ll(struct list *this, int index, void *element)
{
  struct linked_list * this_ll = (struct linked_list *) this->data;
  struct node* temp = (struct node*) malloc(sizeof(struct node));
  temp->datum = element;
  if (index == 0)
    {
      temp->next = this_ll->head;
      this_ll->head = temp;
    }
  else
    {
      struct node *current = this_ll->head;
      int i;
      for (i = 0; i < index-1; i++)
	current = current->next;
      current->next = temp;
      temp->next = current->next;
    }
  this_ll->size++;

}

int size_ll(struct list *this)
{
  struct linked_list * this_ll = (struct linked_list *) this->data;
  return this_ll->size;
}

void* remove_ll(struct list *this, int index)
{
  struct linked_list * this_ll = (struct linked_list *) this->data;
  // maybe later...  
  return NULL;
}


struct list *make_linked_list() 
{

  struct linked_list * this_ll = 
    (struct linked_list *) malloc(sizeof(struct linked_list));
  this_ll->size = 0;
  this_ll->head = NULL;
  struct list* this = (struct list*) malloc(sizeof(struct list));
  this->data = this_ll;
  this->add = add_ll;
  this->set = set_ll;
  this->get = get_ll;
  //this->remove = remove_ll;
  this->insert = insert_ll;
  this->size = size_ll;

  return this;
}

void destroy_linked_list(struct list *this)
{
  struct linked_list * this_ll = (struct linked_list *) this->data;
  this_ll->size = 0;
  struct node *current = this_ll->head;
  while (current != NULL)
    {
      struct node *temp = current->next;
      free(current);
      current = temp;
    }
  this_ll->head = NULL;
  free(this_ll);
  free(this);
}

