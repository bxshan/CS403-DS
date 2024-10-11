import java.util.Iterator;
import java.util.ListIterator;

public class MyArrayList<E> {
  private int size;
  private Object[] values; // (Java doesn't let us make an array of type E)

  public MyArrayList() {
    size = 0;
    values = new Object[1];
  }

  public String toString() {
    if (size == 0)
      return "[]";

    String s = "[";
    for (int i = 0; i < size - 1; i++)
      s += values[i] + ", ";
    return s + values[size - 1] + "]";
  }

  /**
   * @postcondition replaces the array with one that is
   *                twice as long, and copies all of the
   *                old elements into it
   */
  private void doubleCapacity() {
    if (size == 0) {
      size = 1;
    }
    Object[] tmp = new Object[size * 2];
    for (int i = 0; i < size; i++) {
      tmp[i] = values[i];
    }
    // size *= 2;
    this.values = tmp;
  }

  /**
   * @postcondition returns the length of the array
   */
  public int getCapacity() {
    return values.length;
  }

  public int size() {
    return this.size;
  }

  public Object get(int index) {
    return values[index];
    // (You will need to promise the return value is of type E.)
  }

  /**
   * @postcondition replaces the element at position index with obj
   *                returns the element formerly at the specified position
   */
  public Object set(int index, E obj) {
    Object tmp = values[index];
    values[index] = obj;
    return tmp;
    // (You will need to promise the return value is of type E.)
  }

  /**
   * @postcondition appends obj to end of list; returns true
   */
  public boolean add(E obj) {
    // System.out.println(size);
    // System.out.println(values.length);
    /* if values is already full, call doubleCapacity before adding */
    if (size == values.length) {
      doubleCapacity();
      // System.out.println("!!!");
    }
    // System.out.println("!!!");
    // System.out.println(size);
    // System.out.println(values.length);
    values[size++] = obj;
    return true;
  }

  /**
   * @postcondition removes element from position index, moving elements
   *                at position index + 1 and higher to the left
   *                (subtracts 1 from their indices) and adjusts size
   *                returns the element formerly at the specified position
   */
  public Object remove(int index) {
    Object removed = values[index];

    if (size == 1 && index == 0) {
      values[index] = null;
      size--;
      System.out.println(size);
      return removed;
    }

    for (int i = index; i < size - 1; i++) {
      values[i] = values[i + 1];
    }
    values[size - 1] = null;
    size--;
    System.out.println(size);
    return removed;
  }

  public Iterator<E> iterator() {
    return new MyArrayListIterator();
  }

  /**
   * @precondition 0 <= index <= size
   * @postcondition inserts obj at position index,
   *                moving elements at position index and higher
   *                to the right (adds 1 to their indices) and adjusts size
   */
  public void add(int index, E obj) {
    if (size == values.length) {
      doubleCapacity();
    }

    for (int i = size + 1; i > index; i--) {
      values[i] = values[i - 1];
    }

    values[index] = obj;
    size++;
  }

  private class MyArrayListIterator implements Iterator<E> {
    // the index of the value that will be returned by next()
    private int nextIndex;

    /**
     * @postcondition constructs an iterator for the given list
     */
    public MyArrayListIterator() {
    }

    /**
     * @postcondition returns true if there are more elements
     *                to return
     */
    public boolean hasNext() {
      return nextIndex < size;
    }

    /**
     * @postcondition returns the next element in the iteration
     */
    public E next() {
      return (E) values[nextIndex++];
      // (You will need to promise the return value is of type E.)
    }

    // @postcondition removes the last element that was returned by next
    public void remove() {
    }
  }
}
