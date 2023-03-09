#include "list.h"
#include <string.h>
#include <stdio.h>
#include<stddef.h>

struct book
{
  char title[50];
  char author[20];
  int pages;  
  struct list_node node;
};

#define as_node(bk) &((bk)->node)
#define as_book(nd) (struct book *) ((char *) nd - offsetof(struct book, node))

int main ()
{
  struct linked_list *library_records = make_linked_list();
  struct book aeneid;
  strcpy(aeneid.title, "The Aeneid");
  strcpy(aeneid.author, "Virgil");
  aeneid.pages = 267;

  /*
  struct book *bptr = &aeneid;

  printf("%p\n", bptr);
  struct list_node *nptr = as_node(bptr);
  printf("%p\n", nptr);
  struct book *bptr2 = as_book(nptr);
  printf("%p\n", bptr2);
  */

  printf("%d\n", sizeof(struct book));
  printf("%lu\n", (unsigned long) &(aeneid.title));
  printf("%lu\n", (unsigned long) &(aeneid.author));
  printf("%lu\n", (unsigned long) &(aeneid.pages));
  printf("%lu\n", (unsigned long) &(aeneid.node));
  printf("%lu\n", (unsigned long) &(aeneid.node.next));



  add(library_records, as_node(&aeneid));
  printf("%s\n", (as_book(get(library_records, 0)))->title);
  return 0;
}
