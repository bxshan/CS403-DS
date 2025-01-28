/**
 * SearchUtil.java
 *
 * @author Boxuan Shan
 * @version 01282025
 */
public class SearchUtil {
  /**
   * performs a linear search
   * best case complexity O(1)
   * worst case complexity O(n)
   * @param a array to search in
   * @param x element to find
   * @return index of element x
   */
  public static int linearSearch(int[] a, int x) {
    for(int i = 0; i < a.length; i++) {
      if (a[i] == x) {
        return i;
      }
    }

    return -1;
  }

  /**
   * performs a binary search
   * best case complexity O(1)
   * worst case complexity O(log(n)
   * @param a array to search in
   * @param x element to find
   * @return index of element x
   */
  public static int binarySearch(int[] a, int x) {
    int l = 0;
    int r = a.length;

    while (r-l > 1) {
      int m = (l+r)/2;
      if (x < a[m]) {
        r = m;
      } else {
        l = m;
      }
    }

    if (a[l] == x) {
      return l;
    } else {
      return -1;
    }
  }
}
