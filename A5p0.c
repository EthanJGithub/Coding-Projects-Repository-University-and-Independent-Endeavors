#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Function to convert one-third of the string to backward-offset lower case
void convertToBackwardLower(char *str, int length) {
    for (int i = 0; i < length; i++) {
        if (str[i] >= 'A' && str[i] <= 'Z') {
            str[i] = ('z' - ('Z' - str[i] + 1)) % 26 + 'a';
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <length>\n", argv[0]);
        return 1;
    }

    int m = atoi(argv[1]);
    if (m % 60 != 0) {
        printf("Input must be a multiple of 60.\n");
        return 1;
    }

    srand(time(NULL)); // Seed for random number generation
    char *str = (char *)malloc((m + 1) * sizeof(char));
    if (str == NULL) {
        printf("Memory allocation failed.\n");
        return 1;
    }

    // Generate random uppercase English characters
    printf("Generated a string of %d chars.\n", m);
    for (int i = 0; i < m; i++) {
        str[i] = rand() % 26 + 'A';
    }
    str[m] = '\0'; // Null-terminate the string

    // Print original string
    printf("Original random upper case string:\n%s\n", str);

    // Call the function three times to convert the entire string
    for (int i = 0; i < 3; i++) {
        convertToBackwardLower(str + (i * m / 3), m / 3);
    }

    // Print converted string
    printf("Backward-offset lower case string after three function calls:\n%s\n", str);

    free(str); // Free dynamically allocated memory

    return 0;
}
