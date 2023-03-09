#include "btree.h"
#include <stdio.h>
#include <stdlib.h>

char *filename = "surnames";

char *read_string(FILE *file)
{
  char str[100];
  fgets(str, 100, file);
  int str_size = 0;
  while(str[str_size] != '\n') str_size++;
  char *to_return = (char*) calloc(str_size+1, sizeof(char));
  int i;
  for (i = 0; i < str_size; i++) to_return[i] = str[i];
  to_return[i] = '\0';
  return to_return;
}

void populate(int keys, struct btree *map)
{
  FILE *file = fopen(filename, "r");
  char *str;
  int i = 0;
  while (keys == -1 || i < keys) {
      str = read_string(file);
      if (feof(file)) break;
      put(map, str, i++);
  }
}

int check(int keys, struct btree *map, int check_val)
{
  int pass = 1;
  FILE *file = fopen(filename, "r");
  char *str;
  int i = 0;
  while (keys == -1 || i < keys) {
      str = read_string(file);
      if (!pass || feof(file)) break;
      //printf("Looking for %s\n", str);
      if (check_val) pass = (get(map, str) == i);
      else pass = contains_key(map, str);
      free(str);
      i++;
  }
  return pass;
}

int test_one_node_put()
{
  printf("one_node_put:\n");
  struct btree *map = create();
  populate(MAX_KEYS, map);
  printf("PASS\n");
  return 1;
}

int test_one_node_contains_key()
{
  printf("one_node_contains_key:\n");
  struct btree *map = create();
  populate(MAX_KEYS, map);
  int pass = check(MAX_KEYS, map, 0);
  if (pass) { printf("PASS\n"); return 1; }
  else {printf("FAIL\n"); return 0; }
 
}

int test_one_node_get()
{
  printf("one_node_get:\n");
  struct btree *map = create();
  populate(MAX_KEYS, map);
  int pass = check(MAX_KEYS, map, 1);
  if (pass) { printf("PASS\n"); return 1; }
  else {printf("FAIL\n"); return 0; }
  
}

int test_big_put()
{
  printf("big_put:\n");
  struct btree *map = create();
  populate(-1, map);
  printf("PASS\n");
  return 1;
}

int test_big_contains_key()
{
  printf("big_contains_key:\n");
  struct btree *map = create();
  populate(MAX_KEYS, map);
  int pass = check(MAX_KEYS, map, 0);
  if (pass) { printf("PASS\n"); return 1; }
  else {printf("FAIL\n"); return 0; }
}

int test_big_get()
{
  printf("big_get:\n");
  struct btree *map = create();
  populate(MAX_KEYS, map);
  int pass = check(MAX_KEYS, map, 1);
  if (pass) { printf("PASS\n"); return 1; }
  else {printf("FAIL\n"); return 0; }
}

int main()
{
  FILE *file = fopen(filename, "r");
  char *str;

  /*
  while (1) {
      str = read_string(file);
      if (feof(file)) break;
      printf("%s\n", str);
      free(str);
  }
  */

  int failures = 0;
  if (!test_one_node_put()) failures++;
  if (!test_one_node_contains_key()) failures++;
  if (!test_one_node_get()) failures++;
  if (!test_big_put()) failures++;
  if (!test_big_contains_key()) failures++;
  if (!test_big_get()) failures++;
  
  printf("There were %d failure(s).\n", failures);
  
  return 0;
}
