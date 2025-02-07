/**
 * Sorter.java
 *
 * @author Boxuan Shan
 * @version 02062025
 */
public class Sorter
{
  private SortDisplay display;

  /**
   * main method instantiates a sorter instance
   * Usage: called directly by the IDE or when Java is launched 
   * ------------------------------------------
   * Creates a Sorter object, but calls no methods from Sorter 
   * because the GUI SortDisplay calls sort methods in Sorter
   * 
   * @params args an array of arguments for legacy command line
   *              the values are not used
   */
  public static void main(String[] args)
  {
    Sorter sorter = new Sorter();
  }

  /**
   * Constructor: Sorter()
   * Useage:  Sorter aSorter = new Sorter()
   * ________________________________________
   * constructor for Sorter objects.  Creates a new display, which controls
   * all of the sorting by means of call-backs to this class.
   */
  public Sorter()
  {
    display = new SortDisplay(this);
  }

  /**
   * swaps two values in a given array
   * @param a array to swap in 
   * @param ii index of first value to swap 
   * @param jj index of second value to swap 
   */
  public void swp(Comparable[] a, int ii, int jj) {
    Comparable tmp = a[jj];
    a[jj] = a[ii];
    a[ii] = tmp;
  }

  /**
   * returns the index of the min value, starting at some index
   * @param a array to search in
   * @param startIndex index to start searching at
   * @return the index of the min value, starting at some index
   */
  public int indexOfMin(Comparable[] a, int startIndex)
  {
    int minI = startIndex;
    Comparable min = a[startIndex];

    for (int i = startIndex; i < a.length; i++) {
      if (min.compareTo(a[i]) > 0) {
        min = a[i];
        minI = i;
      }
    }

    return minI;
  }

  /**
   * implementation of selection sort
   * @param a array to sort
   * @postcondition array a is in increasing order
   */
  public void selectionSort(Comparable[] a)
  {
    int ii = 0;
    while (ii < a.length) {
      int jj = indexOfMin(a, ii);

      swp(a, ii, jj);

      ii++; display.update();
    }
  }

  /**
   * inserts the value at nextIndex+1 to sorted subarray [0, nextIndex] in a
   * @param a array to modify
   * @param nextIndex see previous
   */
  public void insert(Comparable[] a, int nextIndex) {
    int ii = nextIndex - 1;
    int jj = nextIndex;

    while (ii > -1 && jj > -1 && a[ii].compareTo(a[jj]) > 0) {
      swp(a, ii, jj);
      jj--;
      ii = jj - 1;
    }
  }

  /**
   * implementation of insertion sort
   * @param a array to sort
   * @postcondition array a is in increasing order
   */
  public void insertionSort(Comparable[] a)
  {
    int ii = -1;
    while (ii < a.length) {
      insert(a, ii);
      ii++;
      display.update();
    }
  }

  /**
   * implementation of merge sort
   * main method
   * @param a array to sort
   * @postcondition array a is in increasing order
   */
  public void mergesort(Comparable[] a)
  {
    mergesortHelp(a, 0, a.length-1);
  }

  /**
   * recursive helper method for merge sort
   * @param a array to sort
   * @param l lower bound, inclusive
   * @param r upper bound, inclusive
   * @postcondition values with indices [l, r] in a are in increasing order
   */
  private void mergesortHelp(Comparable[] a, int l, int r)
  {   
    if (l >= r) {
      return;
    }
    int m = (l + r) / 2;
    mergesortHelp(a, l, m);
    mergesortHelp(a, m+1, r);
    merge(a, l, m, r);
  }

  /**
   * method merge()
   * Useage: merge(inputArray, lowIndex, midIndex, highIndex)
   *_______________________________________________
   * Merges the two halves of the input array into one.  The method assumes
   * that each half of the input array is sorted as follows:
   * 
   *                a[lowIndex] to a[midIndex] are in increasing order.
   *                a[midIndex + 1] to a[highIndex] are in increasing order.
   * The method creates an array to hold the output.  It then establishes two pointers into the two halves of the input array.  The values at the pointer locations are compared, and the smallest is added to the output
   * array.  The corresponding pointer is then increased by one.  In the event
   * either half becomes empty, the remaining values are copied to the output
   * array.
   * Postcondition: a[lowIndex] to a[highIndex] are in increasing order.
   *
   * @param a is the input array of Comparable values
   * @param l is the index into the array a corresponding to the beginning
   *        of the first half of the array to merge
   * @param m is the index of the last value in the first half of the array
   * @param r is the index of the last value in the second half of the array
   */
  private void merge(Comparable[] a, int l, int m, int r)
  {
    Comparable[] ll = new Comparable[m-l+1];
    Comparable[] rr = new Comparable[r-m];

    for (int k = l; k < r+1; k++) {
      if (k < m+1) {
        ll[k-l] = a[k];
      } else {
        rr[k-m-1] = a[k];
      }
    }

    int i = 0; int j = 0;
    int idx = l;

    while (i < ll.length && j < rr.length) {
      display.update();
      if (ll[i].compareTo(rr[j]) < 0) {
        a[idx] = ll[i];
        idx++; i++;
      } else {
        a[idx] = rr[j];
        idx++; j++;
      } 
    }

    while(i < ll.length) {
      display.update();
      a[idx] = ll[i];
      i++; idx++;
    }

    while(j < rr.length) {
      display.update();
      a[idx] = rr[j];
      j++; idx++;
    }
    
    display.update();
  }
}
