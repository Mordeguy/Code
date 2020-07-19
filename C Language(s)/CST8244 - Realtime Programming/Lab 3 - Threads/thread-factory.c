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


// Function declaration
void handler(int sig);
void *childThread();

// The number of threads to create.
int nThreads;

// Used to hangle the signal in the lab.
sig_atomic_t usr1Happened;
pthread_attr_t thread;

/*In computer science, a semaphore is a variable or abstract data type used to control access
to a common resource by multiple processes in a concurrent system such as a multitasking operating system. */
sem_t *semPointer;

int main(int argc, char *argv[]) {



	// The number of threads to create.
	int nThreads;
	usr1Happened = 0;
	int rv = -1, counter = 0;
	pid_t pid;



	/*Use  ‘sigemptyset()’ and ’sigaction()’ to register a handler function to
	  intercept SIGUSR1 signals; on error: call perror and exit*/
	struct sigaction sa;
	sa.sa_handler = handler;
	sa.sa_flags = 0;
	sigemptyset(&sa.sa_mask);


	if (sigaction(SIGUSR1, &sa, NULL) == -1) {
		perror("sigaction");
		exit(-1);
	}


	// Prompts user for amount of THREADS to create
	printf("Enter the number of child THREADS to create: ");
	scanf("%d", &nThreads);
	// Get number of threads (local var nThreads)

	// Initializes and opens a named Semaphore declared above.
	semPointer = sem_open("/O", O_CREAT, S_IWOTH, 0);


	// for nThreads
	for (int i = 0; i <= nThreads - 1; i++) {
		// Call the pthread function to initializes attr with all default thread attributes
		pthread_attr_init(&thread);

		// Call the pthread function to create a new thread (params 1 & 4 are NULL)
		pthread_create(NULL, &thread, &childThread, NULL);

		// Call the pthread function to destroy attr
		pthread_attr_destroy(&thread);
	}


	// Loop continuously until integer flag is set to 1 using handler()
	while (usr1Happened == 0) {
	}

	// Call function to close the semaphore
	sem_close(semPointer);

	//Call function to destroy the semaphore
	sem_destroy(semPointer);


	return EXIT_SUCCESS;
}



/* This function is called when process receives SIGUSR1 signal
   (registration was done in main()); set integer flag to 1 */
void handler(int sig) {
	usr1Happened = 1;
}


void* childThread(void *arg) {

	// Print message that thread has been created; display thread ID
	printf("Thread has been created with thread-ID: %d\n", pthread_self());

	// while true
	while (1) {

		// wait on the semaphore
		if (sem_wait(semPointer) >= 0) {

			// print message the thread has unblocked; display thread ID
			printf("Thread has been unblocked with ID: %d\n", pthread_self());

			// sleep for 5 secs (simulates work being done by thread)
			sleep(5);
		}
	}

	// return NULL
	return NULL;
}
