// ========================================================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
//
// Coding Quiz 2
//
// =========================== Requirements =============================== 
//
// Please complete the method bodies of addBefore(E e, Node<E> successor), 
// addAfter(E e, Node<E> predecessor), remove(Node<E> node), and remove(E e)
// in the MyDoublyLinkedList class.
//
// Your output should be:
// ------------------------------------------------------------------------ 
// Testing MyDoublyLinkedList...
// (1, 2, 3, 4, 5)
// (-3, -2, -1, 0, 1, 2, 3, 4, 5)
// There are 9 elements in the list.
// 
// Removing the first element: (-2, -1, 0, 1, 2, 3, 4, 5)
// Removing the last element: (-2, -1, 0, 1, 2, 3, 4)
// 
// Trying to remove element 10...
// No element 10 found!
// Trying to remove element 0...
// Element 0 found and removed!
// The updated list is: (-2, -1, 1, 2, 3, 4)
// Now there are only 6 elements in the list.
//
// ============================== Note ====================================
//
// 1. DO NOT MODIFY OR DELETE ANY GIVEN CODE OR COMMENTS!!!
// 2. You ONLY need to write code under each comment "YOUR CODE GOES HERE".
// 3. Modify the file name to "MyDoublyLinkedList.java" to compile and run.
//
// ========================================================================

public class MyDoublyLinkedList<E> {
	//------------------------- Node class -------------------------
	private static class Node<E> {
		private E element; 
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		
		public E getElement() { return element; }
		public Node<E> getPrev() { return prev; }
		public Node<E> getNext() { return next; }
		public void setPrev(Node<E> p) { prev = p; }
		public void setNext(Node<E> n) { next = n; }
	} //-------------------- End of Node class --------------------
	
	//------------------------- Data field -------------------------
	public Node<E> header;  // parent of head, dummy node
	public Node<E> trailer;  // child of tail, dummy node 
	public int size = 0; 
	
	//------------------------- Method field ------------------------- 
	// Constructor:
	public MyDoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null); // trailer is preceded by header
		header.setNext(trailer);  // header is followed by trailer
	}
	
	// returns the number of elements in the list
	public int size() { 
		return size; 
	}
	
	// returns true if list is empty
	public boolean isEmpty() { 
		return size == 0; 
	}
	
	// returns the first element
	public E first() {	
		if (isEmpty()) 
			return null;
		return header.getNext().getElement(); // head.getElement()
	}
	
	// returns the last element
	public E last() {
		if (isEmpty()) 
			return null;
		return trailer.getPrev().getElement(); // tail.getElement()
	}

	// adds element e to front of the list
	public void addFirst(E e) {
		addAfter(e, header);
	}
	
	// adds element e to end of the list
	public void addLast(E e) {
		addBefore(e, trailer);
	}
	
	// removes and returns the first element
	public E removeFirst() {
		if (isEmpty()) 
			return null;
		return remove(header.getNext());
	}
	
	// removes and returns the last element
	public E removeLast() {
		if (isEmpty()) 
			return null;
		return remove(trailer.getPrev()); 
	}
	
	// inserts e before node successor
	private void addBefore(E e, Node<E> successor) {
	   // YOUR CODE GOES HERE --Part 1/4--
		// Initializing node
		Node<E> newNode = new Node<>(e, successor.prev, successor);
		// newNode is located after previous node but before successor node
	    successor.prev.next = newNode;
	    successor.prev = newNode;
	    // size increases 
	    size++;
      
      
      
      
	}
		
	// inserts e after node predecessor
	private void addAfter(E e, Node<E> predecessor) {
		// YOUR CODE GOES HERE --Part 2/4--
		Node<E> newNode = new Node<>(e, predecessor, predecessor.getNext());
		newNode.getNext().setPrev(newNode);
		predecessor.setNext(newNode);
		size++;
      
      
      
      
	}
	
	// removes the given node and returns its element
	private E remove(Node<E> node) {
		// YOUR CODE GOES HERE --Part 3/4--
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		// Update references of predecessor and successor nodes
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement();
      
      
      
      
	}
	
	// removes the node with element value being e
	public E remove(E e) {
		// YOUR CODE GOES HERE --Part 4/4--
	    Node<E> removableNode = header.getNext();
	    // loop to go through the trail
	    while (removableNode != trailer) {
	        if (removableNode.getElement().equals(e)) {
	            remove(removableNode);
	            // output that the element has been found and removed 
	            System.out.println("Element " + e + " found and removed!");
	            return removableNode.getElement();
	        }
	        removableNode = removableNode.getNext();
	    }
	    System.out.println("No element " + e + " found!");
	    return null; // element not found
	}
      
      
      
      
	
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		Node<E> walk = header.getNext();
		while (walk != trailer) {
			sb.append(walk.getElement());
			walk = walk.getNext();
			if (walk != trailer)
				sb.append(", ");
		}
		sb.append(")");
		return sb.toString();
	}
	
   
	//------------------------- Driver -------------------------
	public static void main(String[] args) {
		System.out.println("Testing MyDoublyLinkedList...");
		MyDoublyLinkedList<Integer> list = new MyDoublyLinkedList<Integer>();
		
		// test addLast(E e)
		for (int j = 1; j <= 5; j++)
			list.addLast(j);
		System.out.println(list.toString());
		
		// test addFirst(E e)
		for (int k = 0; k <= 3; k++)
			list.addFirst(-k);
		System.out.println(list.toString());
     
		// test size()
		System.out.println("There are " + list.size() + " elements in the list.");
		System.out.println();
      
		// test removeFirst()
		list.removeFirst();
		System.out.println("Removing the first element: " + list.toString());
		
		// test removeLast()
		list.removeLast();
		System.out.println("Removing the last element: " + list.toString());
		System.out.println();
		
		// test remove(E e)
		int e1 = 10;
		System.out.println("Trying to remove element " + e1 + "...");
		list.remove(e1);
		int e2 = 0;
		System.out.println("Trying to remove element " + e2 + "...");
		list.remove(e2);
		System.out.println("The updated list is: " + list.toString());
		System.out.println("Now there are only " + list.size() + " elements in the list.");
      
	}
   
}