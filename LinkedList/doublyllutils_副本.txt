public class llutils {
  public makeList(n, v) {
    if (n == 0) {
      return null;
    }

    head = new dnode(v, null, null);
    head.setNext(this.makeList(n - 1, v));
    if (head.getNext() != null) {
      head.getNext().setPrev(head);
    }

    return head;
  }

  public sizeHelper(head, tail) {
    if (head == tail) {
      return 1;
    } else {
      return 1 + this.sizeHelper(head.getNext(), tail);
    }
  }

  public size(head) {
    return this.sizeHelper(head.getNext(), head); 
  }

  public insert(head, v, l) {
    if (head.getNext() != null && head.getVal() != l) {
      return this.insert(head.getNext(), v, l);
    } 
    if (head.getVal() == l) {
      n = new dnode(v, head, head.getNext());
      head.getNext().setPrev(n);
      head.setNext(n);
    }
  }

  public remove(head, l) {
    if (head.getNext() != null && head.getVal() != l) {
      return this.remove(head.getNext(), l);
    } 
    if (head.getVal() == l) {
      head = head.getPrev();
      head.setNext(head.getNext().getNext());
      if (head.getNext() != null) {
        head.getNext().setPrev(head);
      }
    }
  }

  public removeCircularHelper(head, l, s) {
    if (s <= 0) {
      return;
    }
    if (s != 0 && head.getVal() != l) {
      return this.removeCircularHelper(head.getNext(), l, s - 1);
    } 
    if (head.getVal() == l) {
      head = head.getPrev();
      head.setNext(head.getNext().getNext());
      if (head.getNext() != null) {
        head.getNext().setPrev(head);
      }
      return this.removeCircularHelper(head.getNext(), l, s - 2);
    }
  }

  public removeCircular(head, l) {
    this.removeCircularHelper(head, l, this.size(head));
  }

  public removeSinglyCircularHelper(head, v, s) {
    if (s > 0) {
      if (head.getNext().getVal() == v) {
        head.setNext(head.getNext().getNext());
        return this.removeSinglyCircularHelper(head.getNext(), v, s - 2);
      }
      return this.removeSinglyCircularHelper(head.getNext(), v, s - 1);
    } 
  }

  public removeSinglyCircular(head, l) {
    this.removeSinglyCircularHelper(head, l, this.size(head));
  }
}
