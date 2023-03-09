bitops_test: bitops_test.o bitops.o
	gcc -o bitops_test bitops_test.o bitops.o

bitops_test.o: bitops_test.c bitops.h
	gcc -c bitops_test.c

bitops.o : bitops.c bitops.h
	gcc -c bitops.c
