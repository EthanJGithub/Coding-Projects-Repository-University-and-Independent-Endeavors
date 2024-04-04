package quicksort;
import java.util.Random;
public class HoareQuicksort {

    private static int keyComparisons = 0;
    private static int swaps = 0;

    public static void hoareQuicksort(int[] array, int p, int r) {
        if (p < r) {
            int q = hoarePartition(array, p, r);
            hoareQuicksort(array, p, q);
            hoareQuicksort(array, q + 1, r);
        }
    }

    public static int hoarePartition(int[] array, int p, int r) {
        int x = array[p];
        int i = p - 1;
        int j = r + 1;
        while (true) {
            do {
                j = j - 1;
                keyComparisons++;
            } while (array[j] > x);

            do {
                i = i + 1;
                keyComparisons++;
            } while (array[i] < x);

            if (i < j) {
                swap(array, i, j);
                swaps++;
            } else {
                return j;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(maxValue - minValue + 1) + minValue;
        }
        return array;
    }
    public static void main(String[] args) {
        int arraySize = 100;

        // Ascending Order
        int[] ascendingArray = generateAscendingArray(arraySize);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(ascendingArray, 0, arraySize - 1);

        System.out.println("Ascending Order:");
        System.out.println("Array Size: " + arraySize);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);

        int[] ascendingArray2 = generateAscendingArray(1000);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(ascendingArray2, 0, ascendingArray2.length - 1);

    
        System.out.println("\nArray Size: " + ascendingArray2.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] ascendingArray3 = generateAscendingArray(10000);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(ascendingArray3, 0, ascendingArray3.length - 1);

      
        System.out.println("\nArray Size: " + ascendingArray3.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        // Descending Order
        int[] descendingArray = generateDescendingArray(arraySize);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(descendingArray, 0, arraySize - 1);

        System.out.println("\nDescending Order:");
        System.out.println("Array Size: " + arraySize);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] descendingArray2 = generateDescendingArray(1000);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(descendingArray2, 0, descendingArray2.length - 1);

        System.out.println("\nArray Size: " + descendingArray2.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] descendingArray3 = generateDescendingArray(10000);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(descendingArray3, 0, descendingArray3.length - 1);
        
        System.out.println("\nArray Size: " + descendingArray3.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        // All the Same Number
        int[] sameNumberArray = generateSameNumberArray(arraySize, 20); // Replace 42 with any desired number
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(sameNumberArray, 0, arraySize - 1);

        System.out.println("\nAll the Same Number:");
        System.out.println("Array Size: " + arraySize);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);

        int[] sameNumberArray2 = generateSameNumberArray(1000, 20); // Replace 42 with any desired number
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(sameNumberArray2, 0, sameNumberArray2.length - 1);

        System.out.println("\nArray Size: " + sameNumberArray2.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] sameNumberArray3 = generateSameNumberArray(10000, 20); // Replace 42 with any desired number
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(sameNumberArray3, 0, sameNumberArray3.length - 1);

        System.out.println("\nArray Size: " + sameNumberArray3.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        // Random Numbers between 1 and 100,000 (inclusive)
        int[] randomArray = generateRandomArray(arraySize, 1, 100000);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(randomArray, 0, arraySize - 1);

        System.out.println("\nRandom Numbers:");
        System.out.println("Array Size: " + arraySize);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        int[] randomArray2 = generateRandomArray(1000, 1, 100000);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(randomArray2, 0, randomArray2.length - 1);

        System.out.println("\nRandom Numbers:");
        System.out.println("Array Size: " + randomArray2.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
        
        int[] randomArray3 = generateRandomArray(10000, 1, 100000);
        keyComparisons = 0;
        swaps = 0;
        hoareQuicksort(randomArray3, 0, randomArray3.length - 1);

        System.out.println("\nRandom Numbers:");
        System.out.println("Array Size: " + randomArray3.length);
        System.out.println("Key Comparisons: " + keyComparisons);
        System.out.println("Swaps: " + swaps);
    }
}