LOAD_PATH=/usr/include/gsl/
CFLAGS=-lm -lgsl -lgslcblas -lplot -ansi -Wall -pedantic -g
CC=gcc
OUTPUT=aaa
all:
	$(CC) trivial_sa.c -I$(LOAD_PATH) $(CFLAGS) -o $(OUTPUT)
