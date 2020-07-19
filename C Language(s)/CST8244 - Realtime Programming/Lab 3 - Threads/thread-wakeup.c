#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>

// Pointer to a semaphore
sem_t * semPointer;

int main(void) {

	int numWakeups;
	/* call the function to open a named semaphore; the named semaphore has already been created
		so only 2 params are needed: nameOfYourSemaphore and 0 (zero) */
	semPointer = sem_open("/O", 0);
	// This must be valid semaphore name. Valid semaphore names are path name beginning with a slash (/).

	// Display the PID of the thread-waker process
	printf("PID of thread-waker.c -->  %d\n\n", getpid());

	// Do-while numWakeup != 0:
	do {

		// Prompt User for the number of threads to wake-up
		printf("How many threads would you like to wake up? (enter 0 to quit)\n");

		scanf("%d", &numWakeups);

		//for numWakeup
		for (int i = 0; i < numWakeups; i++) {

			// Call the function to unlock the semaphore
			sem_post(semPointer);
		}
	} while (numWakeups != 0);

	// Call function to close the semaphore
	sem_close(semPointer);
	// call function to destroy the semaphore
	sem_destroy(semPointer);
	return EXIT_SUCCESS;;
}