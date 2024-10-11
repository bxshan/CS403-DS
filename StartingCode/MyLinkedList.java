import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList<E> {
  private DoubleNode first;
  private DoubleNode last;
  private int size;

  public MyLinkedList() {
    first = null;
    last = null;
    size = 0;
  }

  public String toString() {
    DoubleNode node = first;
    if (node == null)
      return "[]";
    String s = "[";
    while (node.getNext() != null) {
      s += node.getValue() + ", ";
      node = node.getNext();
    }
    return s + node.getValue() + "]";
  }

  /**
   * @precondition 0 <= index <= size / 2
   * @postcondition starting from first, returns the node
   *                with given index (where index 0 returns first)
   **/

  private DoubleNode getNodeFromFirst(int index, DoubleNode first) {
    if (index == 0) {
      return first;
    } else {
      first = first.getNext();
      return this.getNodeFromFirst(index - 1, first);
    }
  }

  /**
   * @precondition size / 2 <= index < size
   * @postcondition starting from last, returns the node
   *                with given index (where index size-1 returns last)
   **/

  private DoubleNode getNodeFromLast(int index, DoubleNode last) {
    if (index == size - 1) {
      return last;
    } else {
      last = last.getPrev();
      return this.getNodeFromLast(index + 1, last);
    }
  }

  /**
   * @precondition 0 <= index < size
   * @postcondition starting from first or last (whichever
   *                is closer), returns the node with given
   */
  private DoubleNode getNode(int index) {
    DoubleNode tmpFirst = first;
    DoubleNode tmpLast = last;
    if (index < size / 2) {
      return this.getNodeFromFirst(index, tmpFirst);
    } else {
      return this.getNodeFromLast(index, tmpLast);
    }
  }

  /**
   * @postcondition returns the number of elements in this list
   */
  public int size() {
    return size;
  }

  /**
   * @postcondition appends obj to end of list; increases size
   */
  public Object get(int index) {
    return this.getNode(index).getValue();
    // (You will need to promise the return value is of type E.)
  }

  /**
   * @postcondition replaces the element at position index with obj
   *                returns the element formerly at the specified position
   */
  public Object set(int index, E obj) {
    DoubleNode v = this.getNode(index);
    Object tmp = v.getValue();
    v.setValue(obj);
    return tmp;
    // (You will need to promise the return value is of type E.)
  }

  /**
   * @postcondition appends obj to end of list; returns true
   */
  public boolean add(E obj) {
    if (size == 0) {
      first = new DoubleNode(obj);
      last = first;
    } else {
      DoubleNode n = new DoubleNode(obj);
      last.setNext(n);
      n.setPrev(last);
      last = n;
    }
    size++;
    return true;
  }

  /**
   * @postcondition removes element from position index, moving elements
   *                at position index + 1 and higher to the left
   *                (subtracts 1 from their indices) and adjusts size
   *                returns the element formerly at the specified position
   */
  public Object remove(int index) {
    DoubleNode v = this.getNode(index);

    if (index == 0) {
      first = v.getNext();
      if (first != null) {
        first.setPrev(null);
      }
    } else {
      v.getPrev().setNext(v.getNext());
      if (v.getNext() != null) {
        v.getNext().setPrev(v.getPrev());
      }
    }

    if (index == size - 1) {
      last = v.getPrev();
      if (last != null) {
        last.setNext(null);
      }
    }

    size--;
    return v.getValue(); 
    // (You will need to promise the return value is of type E.)
  }

  /**
   * @precondition 0 <= index <= size
   * @postcondition inserts obj at position index,
   *                moving elements at position index and higher
   *                to the right (adds 1 to their indices) and adjusts size
   */
  public void add(int index, E obj) {
    if (index == 0) {
      DoubleNode n = new DoubleNode(obj);
      n.setNext(first);
      if (first != null) {
        first.setPrev(n);
      }
      first = n;
      if (size == 0) {
        last = first;
      }
    } else if (index == size) {
      DoubleNode n = new DoubleNode(obj);
      last.setNext(n);
      n.setPrev(last);
      last = n;
    } else {
      DoubleNode v = this.getNode(index);
      DoubleNode n = new DoubleNode(obj);
      n.setNext(v);
      n.setPrev(v.getPrev());
      v.setPrev(n);
      n.getPrev().setNext(n);
    }
    size++;
  }

  public void addFirst(E obj) {

  }

  public void addLast(E obj) {
    throw new RuntimeException("INSERT MISSING CODE HERE");
  }

  public E getFirst() {
    throw new RuntimeException("INSERT MISSING CODE HERE");

    // (You will need to promise the return value is of type E.)
  }

  public E getLast() {
    throw new RuntimeException("INSERT MISSING CODE HERE");

    // (You will need to promise the return value is of type E.)
  }

  public E removeFirst() {
    throw new RuntimeException("INSERT MISSING CODE HERE");

    // (You will need to promise the return value is of type E.)
  }

  public E removeLast() {
    throw new RuntimeException("INSERT MISSING CODE HERE");

    // (You will need to promise the return value is of type E.)
  }

  public Iterator<E> iterator() {
    return new MyLinkedListIterator();
  }

  private class MyLinkedListIterator implements Iterator<E> {
    private DoubleNode nextNode;

    public MyLinkedListIterator() {
      throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    public boolean hasNext() {
      throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    public E next() {
      throw new RuntimeException("INSERT MISSING CODE HERE");

      // (You will need to promise the return value is of type E.)
    }

    // @postcondition removes the last element that was returned by next
    public void remove() {
      throw new RuntimeException("INSERT MISSING CODE HERE");
    }
  }
}
