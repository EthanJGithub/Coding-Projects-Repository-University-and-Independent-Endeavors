#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <ctype.h>
#include <string.h>

// Structure to pass arguments to thread function
struct ThreadArgs {
    char *str;
    int offset;
    int chunk_size;
};

// Thread function to convert characters to backward-offset lowercase
void *convertToLowerCase(void *args) {
    struct ThreadArgs *t_args = (struct ThreadArgs *)args;
    int i;

    for (i = 0; i < t_args->chunk_size; i++) {
        int index = t_args->offset + i;
        t_args->str[index] = tolower(t_args->str[index]);
    }

    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Usage: %s <m (multiple of 60)> <n (2-6)>\n", argv[0]);
        return 1;
    }

    int m = atoi(argv[1]); // Length of the string
    int n = atoi(argv[2]); // Number of threads

    // Check if m is a multiple of 60 and n is in the range [2, 6]
    if (m % 60 != 0 || n < 2 || n > 6) {
        printf("m should be a multiple of 60 and n should be in the range [2, 6]\n");
        return 1;
    }

    char *str = (char *)malloc((m + 1) * sizeof(char)); // Allocate memory for the string
    if (str == NULL) {
        printf("Memory allocation failed\n");
        return 1;
    }

    // Generate random uppercase English characters
    for (int i = 0; i < m; i++) {
        str[i] = 'A' + rand() % 26;
    }
    str[m] = '\0'; // Null terminate the string

    printf("Using %d threads to handle %d chars.\n", n, m);
    printf("Original random upper case string:\n%s\n", str);

    pthread_t threads[n]; // Array to hold thread IDs
    struct ThreadArgs thread_args[n]; // Array to hold arguments for each thread

    int chunk_size = m / n; // Size of each chunk for a thread
    int remaining_chars = m % n; // Number of remaining characters after dividing equally

    int offset = 0;
    // Create threads
    for (int i = 0; i < n; i++) {
        thread_args[i].str = str;
        thread_args[i].offset = offset;
        thread_args[i].chunk_size = chunk_size + (i < remaining_chars ? 1 : 0);

        pthread_create(&threads[i], NULL, convertToLowerCase, (void *)&thread_args[i]);

        offset += thread_args[i].chunk_size;
    }

    // Wait for threads to finish
    for (int i = 0; i < n; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Backward-offset lower case string after conversion with %d threads:\n%s\n", n, str);

    free(str); // Free allocated memory

    return 0;
}
