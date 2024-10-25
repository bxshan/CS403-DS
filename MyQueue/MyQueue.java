/**
 * Interface for a queue 
 * @param <E> elements type in queue
 * @author Boxuan Shan
 * @version 10212024
 */
interface MyQueue<E> {
  /**
   * adds an item to the end of the queue
   * @param item item to add
   */
  void enqueue(E item);
  /** 
   * removes the item at the front of the queue
   * @return item removed
   */
  E dequeue();
  /**
   * returns the item at the front of the queue
   * @return item at head
   */  
  E peek();
  /**
   * returns true if the queue is empty
   * @return true if the queue is empty
   */  
  boolean isEmpty();
  /**
   * returns the number of items in the queue
   * @return the size of the array 
   */  
  int size();
}
