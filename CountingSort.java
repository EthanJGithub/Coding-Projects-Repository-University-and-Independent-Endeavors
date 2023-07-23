// ========================================================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
//
// Coding Quiz 8
//
// =========================== Requirements =============================== 
// Implement the counting sort algorithm to sort elements of an array
// into non-decreasing order.
//
// Please use appropriate data types and design appropriate output to
// demonstrate the correctness of your code.
//
// Your output may look as follows:
// ------------------------------------------------------------------------
// Input array: e a b a c k i k s f o c g 
// Sorted array: a a b c c e f g i k k o s 
//
// ============================== Note ====================================
//
// 1. DO NOT MODIFY OR DELETE ANY GIVEN CODE OR COMMENTS!!!
// 2. You ONLY need to write code under each comment "YOUR CODE GOES HERE".
// 3. Modify the file name to "CountingSort.java" to compile and run.
//
// ========================================================================

public class CountingSort {

	public static char[] countingSort(char arr[]) {
		// YOUR CODE GOES HERE --Part 1/2--
		
			// Find the max value in the array
			char max = arr[0];
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > max) {
					max = arr[i];
				}
			}
			// Purpose of count array is to store the number of times an element appears
			int count[] = new int[max + 1];
			for (int i = 0; i < arr.length; i++) {
				++count[arr[i]];
			}

			// Find correct position of each element based on count
			/* Change count[i] so that count[i] will contain
	         the actual position of this character in output array */
			for (int i = 1; i <= max; i++) {
				count[i] += count[i - 1];
			}

			// Sorted array initialization 
			char output[] = new char[arr.length];

			// Builds the sorted array, operates in reverse order to maintain stability
			for (int i = arr.length - 1; i >= 0; i--) {
				output[count[arr[i]] - 1] = arr[i];
				count[arr[i]]--;
			}

			return output;
		}








		public static void main(String[] args) {
			// Test the countingSort method on the following char array
			char arr[] = {'e', 'a', 'b', 'a', 'c', 'k', 'i', 'k', 's', 'f', 'o', 'c', 'g'};

			// YOUR CODE GOES HERE --Part 2/2--

			// Print input array
			System.out.print("Input array: ");
			for (char c : arr) {
				System.out.print(c + " ");
			}
			System.out.println();

			// Sorts the array using counting sort
			char sortedArr[] = countingSort(arr);

			// Printing the sorted array
			System.out.print("Sorted array: ");
			for (char c : sortedArr) {
				System.out.print(c + " ");
			}
			System.out.println();







		}
	}
