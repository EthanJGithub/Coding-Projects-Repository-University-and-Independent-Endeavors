#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX_THREADS 6

// Struct to pass arguments to thread function
struct ThreadArgs {
    int start;
    int end;
};

// Global variables
int A_count = 0, B_count = 0, C_count = 0;
pthread_mutex_t mutex;

// Function to compute Collatz sequence
int collatz(int x) {
    return (x % 2 == 0) ? (x / 2) : (3 * x + 1) / 2;
}

// Thread function to compute Collatz categories
void *computeCategories(void *args) {
    struct ThreadArgs *threadArgs = (struct ThreadArgs *)args;

    for (int i = threadArgs->start; i <= threadArgs->end; i++) {
        int var = i;
        int less_than_var = 0, greater_than_var = 0;
        while (var != 1) {
            var = collatz(var);
            if (var < i) {
                less_than_var++;
            } else if (var > i) {
                greater_than_var++;
            }
        }
        pthread_mutex_lock(&mutex);
        if (less_than_var < greater_than_var) {
            A_count++;
        } else if (less_than_var == greater_than_var) {
            B_count++;
        } else {
            C_count++;
        }
        pthread_mutex_unlock(&mutex);
    }

    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Usage: %s a b n\n", argv[0]);
        return 1;
    }

    int a = atoi(argv[1]);
    int b = atoi(argv[2]);
    int n = atoi(argv[3]);
    pthread_t threads[MAX_THREADS];
    struct ThreadArgs threadArgs[MAX_THREADS];
    int totalIntegers = b - a + 1;
    int integersPerThread = totalIntegers / n;
    int remainingIntegers = totalIntegers % n;

    if (a >= b || n < 2 || n > MAX_THREADS) {
        printf("Error: invalid arguments.\n");
        return 1;
    }

    printf("Using %d threads to handle %d Collatz lists from %d to %d inclusive.\n", n, totalIntegers, a, b);

    pthread_mutex_init(&mutex, NULL);

    // Create threads
    int index = 0;
    for (int i = 0; i < n; i++) {
        threadArgs[i].start = a + index;
        threadArgs[i].end = threadArgs[i].start + integersPerThread - 1;
        if (remainingIntegers > 0) {
            threadArgs[i].end++;
            remainingIntegers--;
        }
        index = threadArgs[i].end - a + 1;
        pthread_create(&threads[i], NULL, computeCategories, (void *)&threadArgs[i]);
    }

    // Join threads
    for (int i = 0; i < n; i++) {
        pthread_join(threads[i], NULL);
    }

    pthread_mutex_destroy(&mutex);

    printf("Category (A) count is %d.\n", A_count);
    printf("Category (B) count is %d.\n", B_count);
    printf("Category (C) count is %d.\n", C_count);

    return 0;
}
