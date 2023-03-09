/* linked_list.h */
#ifndef LINKED_LIST
#define LINKED_LIST
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

struct linked_list *make_linked_list();
void destroy_linked_list(struct linked_list *list);
void add(struct linked_list *list, void *element);
void set(struct linked_list *list, int index, void *element);
void *get(struct linked_list *list, int index);
/*void *remove(struct linked_list *list, int index);*/
void insert(struct linked_list *list, int index, void *element);
int size(struct linked_list *list);

#endif

