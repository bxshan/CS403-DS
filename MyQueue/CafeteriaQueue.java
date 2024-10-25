/**
 * CafeteriaQueue.java
 * @author Boxuan Shan
 * @version 10252024
 */
public class CafeteriaQueue {
  private ArrayQueue<Student> q;

  /**
   * Constructor for CafeteriaQueue class
   */
  public CafeteriaQueue() {
    q = new ArrayQueue<Student>();
  }

  /**
   * Adds a student to queue
   * @param n name of student
   * @param sID ID of student
   * @param gL student's grade level
   */
  public void addStudent(String n, int sID, String gL) {
    q.enqueue(new Student(n, sID, gL));
  }

  /**
   * Serves a student, removes them from queue
   * @return student served
   */
  public Student serveStudent() {
    return q.dequeue();
  }

  /**
   * peeks the student to be served
   * @return student to be served
   */
  public Student peekNextStudent() {
    return q.peek();
  }

  /**
   * gets the size of queue
   * @return queue size
   */
  public int getQueueSize() {
    return q.size();
  }
}
