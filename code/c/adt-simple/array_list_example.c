#include "array_list.h"
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
  struct array_list *library_records = make_array_list(100);
  struct book aeneid;
  strcpy(aeneid.title, "The Aeneid");
  strcpy(aeneid.author, "Virgil");
  aeneid.pages = 267;

  printf("%d\n", sizeof(struct array_list));
  printf("%d\n", sizeof(int));
  printf("%d\n", sizeof(void**));
  printf("%d\n", sizeof(struct book));

  printf("%lu %lu\n", (unsigned long) &aeneid, (unsigned long) &(aeneid.pages));

  add(library_records, &aeneid);
  printf("%s\n", ((struct book*) get(library_records, 0))->title);
  return 0;
}
  
