/* It takes an input array of characters, sorts the elements in non-decreasing order, and then outputs the sorted array. The Counting Sort algorithm works by counting the occurrences of each element in the input array, determining the correct positions of the elements in the sorted array, and then constructing the sorted array accordingly. 
The code includes the main method to test the countingSort method on a given input array and prints both the input array and the resulting sorted array.
*/
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
