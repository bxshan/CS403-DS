import java.util.*;
/**
 * HeapUtil.java
 * @author Boxuan Shan
 * @version 12022024
 */
public class HeapUtil {
  private int heapSize = 0;

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
  //public HeapUtil() {
  //  this(0);
  //}

  /**
   * gets the size of the heap
   * @return size of heap
   */
  //public int getHeapSize() {
  //  return this.heapSize;
  //}

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
    if (heapSize >= 2*n && h[2*n] != null) {
      if (h[2*n].compareTo(h[n]) < 0) {
        swp(h, 2*n, n);
        heapify(h, 2*n);
      }
    }
    if (heapSize >= (2*n+1) && h[2*n] != null) {
      if (h[2*n+1].compareTo(h[n]) < 0) {
        swp(h, 2*n+1, n);
        heapify(h, 2*n+1);
      }
    }
  }
  

  /**
   * rearranges a given array to satisfy the conditions for a heap
   * @param heap heap to rearrange
   */
  public void buildHeap(Comparable[] h) {
    for (int i = heapSize/2; i > 0; i--) {
      heapify(h, i);
    }
  }

  /**
   * removes and returns the root value of a given heap
   * @param heap heap to remove from
   * @return value of root 
   */
  public Comparable remove(Comparable[] h) {
    Comparable ret = h[1];
    h[1] = h[heapSize];
    h[heapSize] = null;
    heapSize--;
    heapify(h, 1);
    return ret;
  }


  /**
   * heapifies a given heap, sifting up
   * @param heap heap to heapify 
   * @param index index of heap array to start at
   */
  public void heapifyUp(Comparable[] h, int n) {
    int p = (n - 1) / 2;
    if (h[n].compareTo(h[p]) > 0) { 
      swp(h, n, p); 
      heapifyUp(h, p); 
    } 
  }
  


  /**
   * inserts an item into heap, keeping the heap property
   * @param heap heap to insert into
   * @param item item to insert
   * @return heap in the form of an array
   */
  public Comparable[] insert(Comparable[] h, Comparable n) {
    heapSize++;
    if (heapSize >= h.length) {
      Comparable hnew[] = new Comparable[h.length*2];
      for (int i = 0; i < h.length; i++) {
        hnew[i] = h[i];
      }
      h = hnew;
    }
    h[heapSize] = n;
    //heapifyUp(h, heapSize);
    buildHeap(h);
    return h;
  }


  /**
   * sorts an array using heap methods
   * @param heap heap array to sort
   * @return sorted array
   */
  public Comparable[] heapSort(Comparable[] h) {
    buildHeap(h);
    Comparable ret[] = new Comparable[h.length];

    for (int i = 0; i < h.length; i++) {
      ret[i] = remove(h);
    }

    return ret;
  }
}
