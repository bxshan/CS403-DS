/**
 * MyStackTester.java
 * tester for MyStack.java
 * @author Boxuan Shan
 * @version 09302024
 */
public class MyStackTester {
  /**
   * Main method, tester method
   * @param args cmd-line args
   */
  public static void main(String[] args) {
    MyStack<String> stack = new MyStack<String>();

    System.out.println("pushing elements...");

    stack.push("1"); // 4
    stack.push("2"); // 3 
    stack.push("3"); // 2
    stack.push("4"); // 1
    stack.push("5"); // 0
    
    System.out.println("the stack looks like this: top -> 5 4 3 2 1");
    System.out.println("topmost element is: " + stack.peek());
    System.out.println("is it empty? " + stack.isEmpty());
    System.out.println("it has size " + stack.size());

    System.out.println("value 5 is at location: " + stack.search("5"));
    System.out.println("value 4 is at location: " + stack.search("4"));
    System.out.println("value 3 is at location: " + stack.search("3"));
    System.out.println("value 2 is at location: " + stack.search("2"));
    System.out.println("value 1 is at location: " + stack.search("1"));
    System.out.println("value 0 is at location: " + stack.search("0"));
    System.out.println("value 9 is at location: " + stack.search("9"));

    System.out.println("popping... " + stack.pop());
    System.out.println("popping... " + stack.pop());
    System.out.println("popping... " + stack.pop());
    System.out.println("it now has size " + stack.size());
    System.out.println("popping... " + stack.pop());
    System.out.println("popping... " + stack.pop());

    System.out.println("is it empty now? " + stack.isEmpty());
    System.out.println("it has size " + stack.size());

    System.out.println("Now You Truly Win!");
  }
}
