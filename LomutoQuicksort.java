package quicksort;

import java.security.SecureRandom;
import java.util.Random;

public class LomutoQuicksort {

    private static int keyComparisons = 0;
    private static int swaps = 0;

    public static void lomutoQuicksort(int[] array, int p, int r) {
        if (p < r) {
            int q = lomutoPartition(array, p, r);
            lomutoQuicksort(array, p, q - 1);
            lomutoQuicksort(array, q + 1, r);
        }
    }

    public static int lomutoPartition(int[] array, int p, int r) {
        int x = array[r]; // choose the pivot
        int i = p - 1;

        for (int j = p; j <= r - 1; j++) {
            keyComparisons++;
            if (array[j] <= x) {
                i = i + 1;
                swap(array, i, j);
                swaps++;
            }
        }

        swap(array, i + 1, r);
        swaps++;
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        // Test the algorithms with different input arrays

        // Ascending Order Arrays
        int[] ascendingArray = generateAscendingArray(100);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(ascendingArray, 0, ascendingArray.length - 1);

        System.out.println("Ascending Order:");
        System.out.println("Array Size: " + ascendingArray.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);

        // Ascending Order
        int[] ascendingArray2 = generateAscendingArray(1000);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(ascendingArray2, 0, ascendingArray2.length - 1);

    
        System.out.println("\nArray Size: " + ascendingArray2.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] ascendingArray3 = generateAscendingArray(10000);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(ascendingArray3, 0, ascendingArray3.length - 1);

      
        System.out.println("\nArray Size: " + ascendingArray3.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        // Descending Order
        int[] descendingArray = generateDescendingArray(100);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(descendingArray, 0, descendingArray.length - 1);

        System.out.println("\nDescending Order:");
        System.out.println("Array Size: " + descendingArray.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] descendingArray2 = generateDescendingArray(1000);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(descendingArray2, 0, descendingArray2.length - 1);

        System.out.println("\nArray Size: " + descendingArray2.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] descendingArray3 = generateDescendingArray(10000);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(descendingArray3, 0, descendingArray3.length - 1);

        System.out.println("\nArray Size: " + descendingArray3.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        // All the Same Number
        int[] sameNumberArray = generateSameNumberArray(100, 20); // Replace 42 with any desired number
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(sameNumberArray, 0, sameNumberArray.length - 1);

        System.out.println("\nAll the Same Number:");
        System.out.println("Array Size: " + sameNumberArray.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);

        int[] sameNumberArray2 = generateSameNumberArray(1000, 20); // Replace 42 with any desired number
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(sameNumberArray2, 0, sameNumberArray2.length - 1);

        System.out.println("\nArray Size: " + sameNumberArray2.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] sameNumberArray3 = generateSameNumberArray(10000, 20); // Replace 42 with any desired number
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(sameNumberArray3, 0, sameNumberArray3.length - 1);

        System.out.println("\nArray Size: " + sameNumberArray3.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        // Random Numbers between 1 and 100,000 (inclusive)
        int[] randomArray = generateRandomArray(100, 1, 100000);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(randomArray, 0, randomArray.length - 1);

        System.out.println("\nRandom Numbers:");
        System.out.println("Array Size: " + randomArray.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] randomArray2 = generateRandomArray(1000, 1, 100000);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(randomArray2, 0, randomArray2.length - 1);

        System.out.println("\nRandom Numbers:");
        System.out.println("Array Size: " + randomArray2.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] randomArray3 = generateRandomArray(10000, 1, 100000);
        keyComparisons = 0;
        swaps = 0;
        lomutoQuicksort(randomArray3, 0, randomArray3.length - 1);

        System.out.println("\nRandom Numbers:");
        System.out.println("Array Size: " + randomArray3.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
    }

    private static int[] generateAscendingArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    private static int[] generateDescendingArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    private static int[] generateSameNumberArray(int size, int value) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = value;
        }
        return array;
    }

    private static int[] generateRandomArray(int size, int minValue, int maxValue) {
        int[] array = new int[size];
        Random rand = new SecureRandom();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(maxValue - minValue + 1) + minValue;
        }
        return array;
    }
}
