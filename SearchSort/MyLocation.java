/**
 * Write a complete class description of  MyLocation  here.
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
   * Method: getRow()
   * Usage: 
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Method: getCol()
   * Usage: 
   */
  public int getCol() {
    return this.col;
  }

  /**
   * Method: equals()
   * Usage: 
   */
  public boolean equals(Object other)
  {
    MyLocation o = (MyLocation) other;
    return o.getCol() == col && o.getRow() == row;
  }

  /**
   * Method: toString()
   * Usage: 
   */
  public String toString()
  {
    return "(" + row + ", " + col + ")";
  }

  /**
   * Method: compareTo()
   * Usage: 
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
