CFLAGS= -ggdb -Wall -Wextra 
sort_driver: sort_driver.o sorts.o array_util.o
	gcc $(CFLAGS) sort_driver.o sorts.o array_util.o -o sort_driver

sort_driver.o: sort_driver.c sorts.h array_util.h
	gcc $(CFLAGS) -c sort_driver.c

exper_selection_comparisons : exper_selection_comparisons.o sorts.o array_util.o
	gcc $(CFLAGS)  exper_selection_comparisons.o sorts.o array_util.o -o exper_selection_comparisons

exper_selection_comparisons.o: exper_selection_comparisons.c sorts.h array_util.h
	gcc $(CFLAGS) -c exper_selection_comparisons.c

exper_allsort_runtime : exper_allsort_runtime.o sorts.o array_util.o
	gcc $(CFLAGS)  -lm exper_allsort_runtime.o sorts.o array_util.o -o exper_allsort_runtime

exper_allsort_runtime.o: exper_allsort_runtime.c sorts.h array_util.h
	gcc $(CFLAGS) -c exper_allsort_runtime.c

exper_goodsort_runtime : exper_goodsort_runtime.o sorts.o array_util.o
	gcc $(CFLAGS)  -lm exper_goodsort_runtime.o sorts.o array_util.o -o exper_goodsort_runtime

exper_goodsort_runtime.o: exper_goodsort_runtime.c sorts.h array_util.h
	gcc $(CFLAGS) -c exper_goodsort_runtime.c

exper_radix_runtime_linear : exper_radix_runtime_linear.o sorts.o array_util.o
	gcc $(CFLAGS)  -lm exper_radix_runtime_linear.o sorts.o array_util.o -o exper_radix_runtime_linear

exper_radix_runtime_linear.o: exper_radix_runtime_linear.c sorts.h array_util.h
	gcc $(CFLAGS) -c exper_radix_runtime_linear.c

sorts.o: sorts.c 
	gcc $(CFLAGS) -c sorts.c

array_util.o: array_util.c
	gcc $(CFLAGS) -c array_util.c

clean:
	rm sort_driver;
	rm *.o;

