#ifndef LIST_INTERFACE
#define LIST_INTERFACE


struct list_node 
{
  struct list_node *next;
};

struct linked_list
{
  struct list_node *head, *tail;
  int size;
};

struct linked_list *make_linked_list();
void add(struct linked_list *list, struct list_node *element);
void set(struct linked_list *list, int index, struct list_node *element);
struct list_node * get(struct linked_list *list, int index);
void insert(struct linked_list *list, int index, struct list_node *element);
int size(struct linked_list *list);
#endif
