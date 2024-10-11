public class dnode {
  private prev;
  private next;
  private val;

  public dnode(v, p, n) {
    val = v;
    prev = p;
    next = n;
  }

  public getPrev() {
    return prev;
  }

  public getNext() {
    return next;
  }

  public getVal() {
    return val;
  }

  public setPrev(p) {
    prev = p;
  }

  public setNext(n) {
    next = n;
  }

  public setVal(v) {
    val = v;
  }
}
