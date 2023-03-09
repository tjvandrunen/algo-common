/* btree.h */

#define MAX_DEG 100
#define MAX_KEYS 99

struct btree_node
{
  char *keys[MAX_KEYS];
  int values[MAX_KEYS];
  struct btree_node *children[MAX_DEG];
  int deg;
};

struct btree
{
  struct btree_node *root;
};

struct btree *create();

void put(struct btree *map, char *key, int value);

int contains_key(struct btree *map, char *key);

int get(struct  btree *map, char *key);

void destroy(struct btree *map);
