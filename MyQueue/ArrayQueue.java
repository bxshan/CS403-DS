/**
 * ArrayQueue.java
 * @author Boxuan Shan
 * @version 10252024
 */
public class ArrayQueue<E> implements MyQueue<E> {
  private Object[] mem;
  int size;
  
  /**
   * Constructor for ArrayQueue class
   */
  public ArrayQueue() {
    mem = new Object[0];
    size = 0;
  }

  /**
   * enqueues an item
   * @param n item to add
   */
  public void enqueue(E n) {
    Object[] nmem = new Object[size + 1];
    if (size == 0) {
      nmem[0] = n;
    } else {
      for (int i = 1; i < size + 1; i++) {
        nmem[i] = mem[i - 1];
      }
    }
    nmem[0] = n;
    mem = nmem;
    size++;
  }

  /**
   * dequeues an item
   * @return n item dequeued
   */
  public E dequeue() {
    E ret = (E) mem[size - 1];
    Object[] nmem = new Object[size - 1];
    for (int i = 0; i < size - 1; i++) {
      nmem[i] = mem[i];
    }
    mem = nmem;
    size--;
    return ret;
  }

  /**
   * peeks the front of queue 
   * @return n item at front 
   */
  public E peek() {
    return (E) mem[size - 1];
  }
  
  /**
   * returns if queue is empty
   * @return true if empty
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * returns size of queue
   * @return size of queue
   */
  public int size() {
    return size;
  }
}
