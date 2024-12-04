import java.util.*;
/**
 * HeapUtil.java
 * @author Boxuan Shan
 * @version 12022024
 */
public class HeapUtil {
  private int heapSize;

  /**
   * constructor for HeapUtil
   * @param heapSize size of heap
   */
  public HeapUtil(int heapSize) {
    this.heapSize = heapSize;
  }

  /**
   * default constructor for HeapUtil
   */
  public HeapUtil() {
    this(0);
  }

  /**
   * gets the size of the heap
   * @return size of heap
   */
  public int getHeapSize() {
    return this.heapSize;
  }

  private void swp(Comparable[] h, int a, int b) {
    Comparable t = h[a];
    h[a] = h[b];
    h[b] = t;
  }
  
  /**
   * heapifies a given heap
   * @param heap heap to heapify 
   * @param index index of heap array to start at
   */
  public void heapify(Comparable[] h, int n) {
    int l = 2*n;
    int r = 2*n+1;
    //int cl =  // > 0 
    //int cr = h[r].compareTo(h[n]); // > 0

    if (n > heapSize) {
      return;
    }

    if (l < heapSize && h[l].compareTo(h[n]) < 0) {
      swp(h, l, n);
    }

    if (r < heapSize && h[r].compareTo(h[n]) < 0) {
      swp(h, l, n);
    }

    heapify(h, l);
    heapify(h, r);
  }
  

  /**
   * rearranges a given array to satisfy the conditions for a heap
   * @param heap heap to rearrange
   */
  //public void buildHeap(Comparable[] heap) {

  //}

  /**
   * removes and returns the root value of a given heap
   * @param heap heap to remove from
   * @return value of root 
   */
  //public Comparable remove(Comparable[] heap) {

  //}

  /**
   * inserts an item into heap, keeping the heap property
   * @param heap heap to insert into
   * @param item item to insert
   * @return heap in the form of an array
   */
  //public Comparable[] insert(Comparable[] heap, Comparable item) {

  //}










}
