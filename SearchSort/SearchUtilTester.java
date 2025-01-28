/**
 * SearchUtilTester.java
 *
 * @author Boxuan Shan
 * @version 01282025
 */
public class SearchUtilTester {
  /**
   * main method tester
   * @param args cmd-line args
   */
  public static void main(String[] args) {
    SearchUtil s = new SearchUtil();
    int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    System.out.print("given a: ");
    for(int i : a) {
      System.out.print(i + " ");
    }
    
    System.out.print("\n\ntesting linear search\n");
    for(int i = 0; i < a.length+1; i++) {
      System.out.print("found " + i + " @ " + SearchUtil.linearSearch(a, i) + "\n");
    }

    System.out.print("\ntesting binary search\n");
    for(int i = 0; i < a.length+1; i++) {
      System.out.print("found " + i + " @ " + SearchUtil.binarySearch(a, i) + "\n");
    }
  }
}
