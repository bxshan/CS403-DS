/**
 * Node class for linked list for stack implementation
 * @author Boxuan Shan
 * @version 09302024 
 */
public class Node<E> {
  E val;
  Node ptr;

  /**
   * Default constructor
   * @postcondition val and ptr are initialized to null
   */
  public Node() {
    this.val = null;
    this.ptr = null;
  }

  /**
   * Constructor with params
   * @param val value of the node
   * @param ptr pointer to next node
   * @postcondition val and ptr are initialized to given values
   */
  public Node(E val, Node ptr) {
    this.val = val;
    this.ptr = ptr;
  }

  /**
   * Get value of the node
   * @return value of the node
   */
  public E getVal() {
    return val;
  }

  /**
   * Get pointer
   * @return pointer to next node
   */
  public Node getPtr() {
    return ptr;
  }

  /**
   * Set value of the node
   * @param val value of the node to set
   */
  public void setVal(E val) {
    this.val = val;
  }

  /**
   * Set pointer
   * @param ptr pointer to next node to set
   */
  public void setPtr(Node ptr) {
    this.ptr = ptr;
  }
}
