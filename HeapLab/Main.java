import java.util.*;
/**
 * Main.java
 * @author Boxuan Shan
 * @version 12022024
 */
public class Main {
  /**
   * main method
   * @param args cmd-line args
   */
  public static void main(String[] args) {
    //private static int heapSize = 5;
    Integer[] ha = new Integer[12];
    Random random = new Random();
    for (int i = 1; i < 12; i++) {
      ha[i] = random.nextInt(100); 
    }
    HeapDisplay d1 = new HeapDisplay();
    HeapDisplay d2 = new HeapDisplay();
    HeapDisplay d3 = new HeapDisplay();
    HeapUtil hp = new HeapUtil(11);

    hp.buildHeap(ha);
    d1.displayHeap(ha, 11);
    hp.remove(ha);
    d2.displayHeap(ha, 10);
    hp.insert(ha, 22);
    d3.displayHeap(ha, 11);
    for (Comparable i: ha) {
      System.out.print(i + " ");
    }
    System.out.println("\n-------\n");
    Comparable sh[] = hp.heapSort(ha);
    for (Comparable i: sh) {
      System.out.print(i + " ");
    }
  }
}
