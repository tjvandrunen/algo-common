
/* array_list.h */
#ifndef ARRAY_LIST
#define ARRAY_LIST

struct array_list
{
  int capacity;
  int size;
  void **internal;
};

struct array_list *make_array_list(int init_cap);
void destroy_array_list(struct array_list *list);
void add(struct array_list *list, void *element);
void set(struct array_list *list, int index, void *element);
void *get(struct array_list *list, int index);
/*void *remove(struct array_list *list, int index);*/
void insert(struct array_list *list, int index, void *element);
int size(struct array_list *list);

#endif

