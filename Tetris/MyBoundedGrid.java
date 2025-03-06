import java.util.*;
/**
 * MyBoundedGrid.java
 * @author Boxuan Shan
 * @version 03052025
 */
public class MyBoundedGrid<E> {
  private int r, c;
  private Object[][] g;

  /**
   * Constructor for MyBoundedGrid
   * @param r number of rows 
   * @param c number of columns
   */
  public MyBoundedGrid(int r, int c) {
    this.r = r;
    this.c = c;
    g = new Object[r][c];
  }

  /**
   * gets number of rows
   * @return this.r
   */
  public int getNumRows() {
    return this.r;
  }

  /**
   * gets number of columns
   * @return this.c
   */
  public int getNumCols() {
    return this.c;
  }

  /**
   * returns if location is valid
   * @param l location to check
   * @return if location is valid
   */
  public boolean isValid(Location l) {
    return l.getRow() > -1 && l.getRow() < this.r && 
           l.getCol() > -1 && l.getCol() < this.c;
  }

  /**
   * puts object at location
   * @param l location to put object
   * @param obj object to put
   * @return object replaced
   */
  public E put(Location l, E obj) {
    if (!isValid(l)) return null;

    int rr = l.getRow();
    int cc = l.getCol();
    E ret = (E) g[rr][cc];

    this.g[rr][cc] = obj;

    return ret;
  }

  /**
   * removes object at location
   * @param l location to remove object
   * @return object removed
   */
  public E remove(Location l) {
    return put(l, null);
  }

  /**
   * gets object at location
   * @param l location to search for object
   * @return object at location
   */ 
  public E get(Location l) {
    if (!isValid(l)) return null;
    return (E) g[l.getRow()][l.getCol()];
  }

  /**
   * returns all occupied locations
   * @return all occupied locations
   */
  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> ret = new ArrayList<Location>();

    for(int rr = 0; rr < this.r; rr++) {
      for(int cc = 0; cc < this.c; cc++) {
        if (g[rr][cc] != null) {
          ret.add(new Location(rr, cc));
        }
      }
    }

    return ret;
  }
}
