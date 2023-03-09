list_example: list_example.o array_list.o linked_list.o
	gcc list_example.o array_list.o linked_list.o -o list_example

list_example.o: list_example.c list.h array_list.h linked_list.h
	gcc -c list_example.c

array_list.o: array_list.c array_list.h list.h
	gcc -c array_list.c

linked_list.o: linked_list.c linked_list.h list.h
	gcc -c linked_list.c
