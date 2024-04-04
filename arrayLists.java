import java.util.ArrayList;

public class arrayLists {

	public static void main(String[] args) {
		//Create a list
		ArrayList<String> colorList = new ArrayList<>();
		ArrayList<Integer> intList = new ArrayList<>(1000);
		ArrayList<Object> sumList  = new ArrayList<>();
		// test add(E e)
		colorList.add("Red");
		colorList.add("Blue");
		colorList.add("Green");
		colorList.add("Yellow");
		System.out.println(colorList.toString());
		
		// test size()
		System.out.println("List size is: " + colorList.size());
		
		// test isEmpty()
		System.out.println("Is the list empty? " + intList.isEmpty());
		
		// test set(int i, E e)
		int i = 3;
		colorList.set(i, "orange");
		System.out.println(colorList.toString());
		
		// test get(int i)
		System.out.println("The color at position " + i + " is " + colorList.get(i));
		
		// test indexOf(Object o)
		String c = "Green";
		System.out.println("The color " + c +" is at index " + colorList.indexOf(c));
		
		// test add(int i, E e)
		colorList.add(2, "purple");
		System.out.println(colorList.toString());
		
		// test remove(int i)
		colorList.remove(i);
		System.out.println(colorList.toString());
	}
}
