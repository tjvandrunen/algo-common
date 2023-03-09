test: test.o btree.o
	gcc -o test test.o btree.o

test.o: test.c btree.h
	gcc -c test.c

btree.o: btree.c btree.h
	gcc -c btree.c
