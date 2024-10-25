public class ArrayQueue<E> implements MyQueue<E> {
  private Object[] mem;
  int size;
  
  public ArrayQueue() {
    mem = new Object[0];
    size = 0;
  }

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

  public E peek() {
    return (E) mem[size];
  }
  
  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }
}
