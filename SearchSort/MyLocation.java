/**
 * MyLocation.java
 *
 * @author Boxuan Shan
 * @version 02072025
 */
public class MyLocation implements Comparable
{
  private int row;
  private int col;

  /**
   * Constructor: MyLocation()
   * Usage: MyLocation loc = new MyLocation(row, col);
   * -----------------------------
   * creates a MyLocation object with the given row & col
   * 
   * @param r - row of this MyLocation
   * @param c - column of this MyLocaiton
   */
  public MyLocation(int r, int c)
  {
    row = r;
    col = c;
  }

  /**
   * row getter
   * @return row
   */
  public int getRow() {
    return this.row;
  }

  /**
   * column getter
   * @return column
   */
  public int getCol() {
    return this.col;
  }

  /**
   * determines equality to another location
   * @param other location to check equality
   * @return true if this object is equal to other
   */
  public boolean equals(Object other)
  {
    MyLocation o = (MyLocation) other;
    return o.getCol() == col && o.getRow() == row;
  }

  /**
   * returns string representation of this location
   * @return string representation of this location
   */
  public String toString()
  {
    return "(" + row + ", " + col + ")";
  }

  /**
   * compareTo override
   * compares by row first, then col
   * @param in location to compare
   * @return int > 0 if object is larger, < 0 if object is smaller, 0 otherwise
   */
  public int compareTo(Object in)
  {
    MyLocation x = (MyLocation) in;

    if (x.getRow() == row) {
      if (x.getCol() == col) {
        return 0;
      } else {
        return (x.getCol() > col) ? -1 : 1;
      }
    } else {
      return (x.getRow() > row) ? -1 : 1;
    }
  }
}
