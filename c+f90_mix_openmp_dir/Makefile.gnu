CC=gcc
FC=gfortran

COPT=-O3 
FOPT=-O3 -fopenmp

pi: pi.o pi_mytime.o
	$(FC) -o pi $(FOPT) pi.o pi_mytime.o

pi.o: pi.f90
	$(FC) $(FOPT) -c pi.f90

pi_mytime.o: pi_mytime.c
	$(CC) $(COPT) -c pi_mytime.c

clean:
	rm -f *.o pi
