#include <stdio.h>
#include <stdlib.h>

// Function to compute Collatz sequence
int collatz(int x) {
    return (x % 2 == 0) ? (x / 2) : (3 * x + 1) / 2;
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Usage: %s a b\n", argv[0]);
        return 1;
    }

    int a = atoi(argv[1]);
    int b = atoi(argv[2]);
    int A_count = 0, B_count = 0, C_count = 0;

    if (a >= b) {
        printf("Error: a must be less than b.\n");
        return 1;
    }

    printf("There are %d integers between %d and %d inclusive.\n", b - a + 1, a, b);

    for (int i = a; i <= b; i++) {
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
        if (less_than_var < greater_than_var) {
            A_count++;
        } else if (less_than_var == greater_than_var) {
            B_count++;
        } else {
            C_count++;
        }
    }

    printf("Category (A) count is %d.\n", A_count);
    printf("Category (B) count is %d.\n", B_count);
    printf("Category (C) count is %d.\n", C_count);

    return 0;
}
