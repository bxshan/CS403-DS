public class CafeteriaQueue {
  private ArrayQueue<Student> q;

  public CafeteriaQueue() {
    q = new ArrayQueue<Student>();
  }

  public void addStudent(String n, int sID, String gL) {
    q.enqueue(new Student(n, sID, gL));
  }

  public Student serveStudent() {
    return q.dequeue();
  }

  public Student peekNextStudent() {
    return q.peek();
  }

  public int getQueueSize() {
    return q.size();
  }
}
