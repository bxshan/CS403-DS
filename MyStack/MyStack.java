/**
 * MyStack.java
 * Implementation of a stack in a roundabout way
 * @author Boxuan Shan
 * @version 09302024
 */
public class MyStack<E> {
  private Node top;

  /**
   * Default constructor
   * @postcondition top is initialized to a new default node
   */
  public MyStack() {
    top = new Node();
  }

  /**
   * Push a value onto the stack
   * @param v value to push
   * @return value pushed
   */
  public E push(E v) {
    Node n = new Node(v, top);
    top = n;
    return v;
  }

  /**
   * Pop a value off the stack
   * @return value popped
   */
  public E pop() {
    Node n = top; 
    top = top.getPtr();
    return (E) n.getVal();
  }

  /**
   * Peek the topmost value of the stack
   * @return top value
   */
  public E peek() {
    return (E) top.getVal();
  }

  /**
   * Check if the stack is empty
   * @return true if empty
   */
  public boolean isEmpty() {
    return top.getPtr() == null;
  }

  /**
   * Get the size of the stack
   * @return size of the stack
   */
  public int size() {
    if (this.isEmpty()) {
      return 0;
    } else {
      E tmp = this.pop();
      int t = 1 + this.size();
      this.push(tmp);
      return t;
    }
  }

  /**
   * Search for a value in the stack
   * @param o value to search for
   * @return index of the value, -1 if not found
   */
  public int search(Object o) {
    if (this.isEmpty()) {
        return -1; 
    } else if (this.peek().equals(o)) {
        return 0; 
    } else {
        E tmp = this.pop(); 
        int t = this.search(o); 
        this.push(tmp); 
        return (t == -1) ? -1 : t + 1; 
    }
  }
}
