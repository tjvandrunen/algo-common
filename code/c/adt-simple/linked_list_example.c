#include "linked_list.h"
#include <string.h>
#include <stdio.h>

struct book
{
  char title[50];
  char author[20];
  int pages;  
};

int main ()
{
  struct linked_list *library_records = make_linked_list(100);
  struct book aeneid;
  strcpy(aeneid.title, "The Aeneid");
  strcpy(aeneid.author, "Virgil");
  aeneid.pages = 267;

  printf("%d\n", sizeof(struct node));
  printf("%d\n", sizeof(struct node*));
  printf("%d\n", sizeof(void*));
  printf("%d\n", sizeof(struct linked_list));
  printf("%d\n", sizeof(library_records->head));
  printf("%d\n", sizeof(library_records->tail));
  printf("%d\n", sizeof(library_records->size));
  
  printf("%lu %lu %lu %lu\n", library_records, &(library_records->head),
         &(library_records->tail),&(library_records->size));
  add(library_records, &aeneid);
  printf("%s\n", ((struct book*) get(library_records, 0))->title);
  return 0;
}
  
