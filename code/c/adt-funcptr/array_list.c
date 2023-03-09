/* array_list.c */
#include "list.h"
#include <stdlib.h>
#include <stdio.h>


struct array_list
{
  int capacity;
  int size;
  void** internal;

};

void grow(struct list* this) 
{
  struct array_list * this_al  = (struct array_list *) this->data;
  void **new_array = calloc(sizeof(void*), this_al->capacity*2);
  int i;
  for (i = 0; i < this_al->capacity; i++)
    new_array[i] = this_al->internal[i];
  free(this_al->internal);
  this_al->internal = new_array;
  this_al->capacity *= 2;
}

void add_al(struct list* this, void* item) 
{
  struct array_list * this_al  = (struct array_list *) this->data;
  if (this_al->size == this_al->capacity) 
      grow(this);
    this_al->internal[this_al->size++] = item;
}

void set_al(struct list* this, int index, void* item) 
{
   struct array_list * this_al  = (struct array_list *) this->data;
   this_al->internal[index] = item;
}

void* get_al(struct list* this, int index) 
{
  struct array_list * this_al  = (struct array_list *) this->data;
  return this_al->internal[index];
}


void* remove_al(struct list* this, int index) 
{
  struct array_list * this_al  = (struct array_list *) this->data;
  void* to_return  = this_al->internal[index];
  int i;
  for (i = index; i < this_al->size-1; i++) 
      this_al->internal[i] = this_al->internal[i+1];
  this_al->size--;
  return to_return;
}

void insert_al(struct list* this, int index, void* item) 
{
  struct array_list * this_al  = (struct array_list *) this->data;
  int i;
  if (this_al->size == this_al->capacity) 
    grow(this);
  for (i = this_al->size; i > index; i--)
    this_al->internal[i] = this_al->internal[i-1];
  this_al->internal[i] = item;
  this_al->size++;
}

int size_al(struct list* this) 
{
  return ((struct array_list *) this->data)->size;
}

struct list * make_array_list(int init_cap) 
{
  struct array_list * this_al = 
    (struct array_list *) malloc(sizeof(struct array_list));  
  this_al->internal = (void**) calloc(sizeof(void*),  init_cap);
   this_al->size = 0;
  this_al->capacity = init_cap;
  struct list* this = (struct list*) malloc(sizeof(struct list));
  this->data = this_al;
  this->add = add_al;
  this->set = set_al;
  this->get = get_al;
  //this->remove = remove_al;
  this->insert = insert_al;
  this->size = size_al;
  return this;
}

void destroy_array_list(struct list* this) {
    struct array_list* this_al = (struct array_list*) this->data;
    free(this_al->internal);
    free(this_al);
    free(this);
}

