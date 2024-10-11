public class Memorizer {
  private M;
  private curr;
  
  public Memorizer() {
    M = null;
    curr = null;
  }

  public seen(n) {
    if (M == null) {
      M = new node(n, null);
      curr = M;
      return false;
    }
    if (curr.getVal() == n) {
      curr = M;
      return true;
    } 
    if (curr.getPtr() == null) {
      n = new node(n, null);
      curr.setPtr(n);
      curr = M;
      return false;
    }
    if (curr.getPtr() != null) {
      curr = curr.getPtr();
    } else {
      return false;
    }
    return this.seen(n);
  }
}
