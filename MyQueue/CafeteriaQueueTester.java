/**
 * CafeteriaQueueTester.java
 * @author Boxuan Shan
 * @version 10262024
 */
public class CafeteriaQueueTester {
  /**
   * Main method for CafeteriaQueueTester
   * @param args cmd-line args
   */
  public static void main(String[] args) {
    CafeteriaQueue cq = new CafeteriaQueue();

    cq.addStudent("A", 100001, "frosh");
    cq.addStudent("B", 200002, "soph");
    cq.addStudent("C", 300003, "junior");
    cq.addStudent("E", 400004, "senior");
    cq.addStudent("F", 500005, "senior");
    System.out.println("Added:\n\tA 100001 frosh\n\tB 200002 soph \n\tC 300003 junior \n\tD 400004 senior \n\tE 500005 senior");

    System.out.println("serving: " + cq.serveStudent());
    System.out.println("serving: " + cq.serveStudent());
    System.out.println("at head: " + cq.peekNextStudent());
    System.out.println("queue length " + cq.getQueueSize());

    System.out.println("serving: " + cq.serveStudent());
    System.out.println("at head: " + cq.peekNextStudent());

    System.out.println("serving: " + cq.serveStudent());
    System.out.println("serving: " + cq.serveStudent());
    System.out.println("queue length " + cq.getQueueSize());
  }
}
