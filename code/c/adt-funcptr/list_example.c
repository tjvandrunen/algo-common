#include "list.h"
#include "array_list.h"
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
  
  struct list *library_records;// = make_array_list(100);
  struct book aeneid;
  strcpy(aeneid.title, "The Aeneid");
  strcpy(aeneid.author, "Virgil");
  aeneid.pages = 267;

  printf("%d\n", sizeof(struct list));
  printf("%d\n", sizeof(library_records->add));

  int i;
  for (i = 0; i < 2; i++) 
    {
      if (i % 2 == 0)
        library_records = make_array_list(100);
      else
        library_records = make_linked_list();
      printf("%lu\n", (unsigned long) &(library_records->add));
      printf("%lu\n", (unsigned long) &(library_records->set));
      printf("%lu\n", (unsigned long) &(library_records->get));
      printf("%lu\n", (unsigned long) &(library_records->insert));
      printf("%lu\n", (unsigned long) &(library_records->size));
      printf("%lu\n", (unsigned long) &(library_records->data));

      library_records->add(library_records, &aeneid);
      printf("%s\n", ((struct book*) library_records->get(library_records, 0))->title);
    }
  return 0;
}
  
