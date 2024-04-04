// ===============================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
// 
// M9 - Linked-List-Based Queue
//      Modified version of the LinkedQueue class
//      from Module 3
// 
// ===============================================
import java.util.LinkedList;

public class LinkedQueue<E> {

  private LinkedList<E> list = new LinkedList<>();  // an empty list

  // Constructor:
  public LinkedQueue() { }
   
  // returns number of elements in the queue
  public int size() { return list.size(); }

  // returns true if the queue is empty, false otherwise
  public boolean isEmpty() { return list.isEmpty(); }

  // inserts an element at the rear of the queue
  public void enqueue(E element) { list.addLast(element); }

  // returns, but does not remove, the first element of the queue
  public E first() { return list.getFirst(); }

  // removes and returns the first element of the queue
  public E dequeue() { return list.removeFirst(); }

  // returns a string representation of the contents of the queue
  // (from front to rear)
  public String toString() { return list.toString(); }
  
}
