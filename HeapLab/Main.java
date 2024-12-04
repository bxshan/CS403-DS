import java.util.*;
/**
 * Main.java
 * @author Boxuan Shan
 * @version 12022024
 */
public class Main {
  private static int heapSize = 5;
  private static HeapUtil hp = new HeapUtil(heapSize);
  private static Random random = new Random();
  /**
   * main method
   * @param args cmd-line args
   */
  public static void main(String[] args) {
    Integer[] ha = new Integer[heapSize + 1];
    for (int i = 1; i < heapSize + 1; i++) {
      //ha[i] = random.nextInt(100); 
      ha[i] = 11*(heapSize - i + 1);
    }
    HeapDisplay d = new HeapDisplay();

    hp.heapify(ha, 1);
    d.displayHeap((Comparable[]) ha, heapSize);
  }
}
