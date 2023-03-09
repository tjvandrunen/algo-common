#include "list.h"
#include <stdlib.h>

struct linked_list *make_linked_list()
{
  struct linked_list * new_list = (struct linked_list*) malloc(sizeof(struct linked_list));
  new_list->head = new_list->tail = NULL;
  new_list->size = 0;
  return new_list;
}

void add(struct linked_list *list, struct list_node *element)
{
  if (list->size == 0) 
      list->head = list->tail = element;
  else 
    {
      list->tail->next = element;
      list->tail = element;
    }
      element->next = NULL;
      list->size++;
}

void set(struct linked_list *list, int index, struct list_node *element)
{
  // assuming index < list->size
  if (index == 0) 
    {
      element->next = list->head->next;
      list->head = element;
    }
  else {
    int i;
    struct list_node *current = list->head;
    for (i = 0; i < index-1; i++)
      current = current->next;
    element->next = current->next->next;
    current->next = element;
  }
}


struct list_node * get(struct linked_list *list, int index)
{
  int i;
  struct list_node *current = list->head;
  for (i = 0; i < index; i++) 
    {
      current = current-> next;
    }
  return current;
}

void insert(struct linked_list *list, int index, struct list_node *element)
{
    // assuming index <= list->size
  if (index == 0) 
    {
      element->next = list->head;
      list->head = element;
    }
  else {
    int i;
    struct list_node *current = list->head;
    for (i = 0; i < index-1; i++)
      current = current->next;
    element->next = current->next;
    current->next = element;
    if (i == list->size-1)
      list->tail = element;
  }
  list->size++;
}


int size(struct linked_list *list)
{
  return list->size;
}
