public class ArrayQueueTester<E> {
  public static void main(String[] args) {
    ArrayQueue q = new ArrayQueue();
    q.enqueue(1);
    q.enqueue(2);
    System.out.println("size of 1 2 is " + q.size());
    System.out.println("queue is empty? " + q.isEmpty());
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);
    System.out.println("size of 1 2 3 4 5 is " + q.size());
    System.out.println("queue is empty? " + q.isEmpty());
    for (int i = 0; i < 5; i++) {
      System.out.println("dequeue #" + i + ": " + q.dequeue());
    }
    System.out.println("size of empty queue is " + q.size());
    System.out.println("queue is empty? " + q.isEmpty());
  }
}
