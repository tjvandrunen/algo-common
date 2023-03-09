/* array_list.c */
#include "array_list.h"
#include <stdlib.h>


struct array_list *make_array_list(int init_cap) 
{
  struct array_list * list = 
    (struct array_list *) malloc(sizeof(struct array_list));
  list->capacity = init_cap;
  list->size = 0;
  list->internal = calloc(sizeof(void*),  list->capacity);
  return list;
}

void destroy_array_list(struct array_list *list)
{
  list->capacity = 0;
  list->size = 0;
  free(list->internal);
  list->internal = NULL;
  free(list);
}

void grow(struct array_list *list) 
{
  void **new_array = calloc(sizeof(void*), list->capacity*2);
  int i;
  for (i = 0; i < list->capacity; i++)
    new_array[i] = list->internal[i];
  free(list->internal);
  list->internal = new_array;
  list->capacity *= 2;
}


void add(struct array_list *list, void *element) 
{
    if (list->size == list->capacity) 
      grow(list);
    list->internal[list->size++] = element;
}

void set(struct array_list *list, int index, void *element)
{
   list->internal[index] = element;
}

void *get(struct array_list *list, int index)
{
  return list->internal[index];
}

/*
void *remove(struct array_list *list, int index)
{
  void *to_return  = list->internal[index];
  int i;
  for (i = index; i < list->size-1; i++) 
      list->internal[i] = list->internal[i+1];
  list->size--;
  return to_return;
}
*/
void insert(struct array_list *list, int index, void *element)
{
  int i;
  if (list->size == list->capacity) 
    grow(list);
  for (i = list->size; i > index; i--)
    list->internal[i] = list->internal[i-1];
  list->internal[i] = element;
  list->size++;
}

int size(struct array_list *list)
{
  return list->size;
}


