/* linked_list.c */
#include "linked_list.h"
#include <stdlib.h>
#include <stdio.h>


struct linked_list *make_linked_list() 
{

  struct linked_list * list = 
    (struct linked_list *) malloc(sizeof(struct linked_list));
  list->size = 0;
  list->head = NULL;

  return list;
}

void destroy_linked_list(struct linked_list *list)
{
  list->size = 0;
  struct node *current = list->head;
  while (current != NULL)
    {
      struct node *temp = current->next;
      free(current);
      current = temp;
    }
  list->head = NULL;
  free(list);
}


void add(struct linked_list *list, void *element) 
{
  struct node *new_node = (struct node*) malloc(sizeof(struct node));
  new_node->datum = element;
  new_node->next = NULL;
  if (list->head == NULL)
      list->head = list->tail = new_node;
  else {
    list->tail->next = new_node;
    list->tail = new_node;
  }
  list->size++;
}

void set(struct linked_list *list, int index, void *element)
{
  struct node *current = list->head;
  int i;
  for (i = 0; i < index; i++)
    current = current->next;
  current->datum = element;
}

void *get(struct linked_list *list, int index)
{
  struct node *current = list->head;
  int i;
  for (i = 0; i < index; i++)
    current = current->next;
  return current->datum;
}

void insert(struct linked_list *list, int index, void *element)
{
  struct node* temp = (struct node*) malloc(sizeof(struct node));
  temp->datum = element;
  if (index == 0)
    {
      temp->next = list->head;
      list->head = temp;
    }
  else
    {
      struct node *current = list->head;
      int i;
      for (i = 0; i < index-1; i++)
	current = current->next;
      current->next = temp;
      temp->next = current->next;
    }
  list->size++;

}

int size(struct linked_list *list)
{
  return list->size;
}


