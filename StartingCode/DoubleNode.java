/**
 * complete commenting of this class is required
 */
public class DoubleNode {
  private Object value;
  private DoubleNode prev;
  private DoubleNode next;

  public DoubleNode(Object v) {
    value = v;
    prev = null;
    next = null;
  }

  public Object getValue() {
    return value;
  }

  public DoubleNode getPrev() {
    return prev;
  }

  public DoubleNode getNext() {
    return next;
  }

  public void setValue(Object v) {
    value = v;
  }

  public void setPrev(DoubleNode p) {
    prev = p;
  }

  public void setNext(DoubleNode n) {
    next = n;
  }
}
