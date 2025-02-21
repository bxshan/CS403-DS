/**
 * Minefield.java
 * 
 * Students write this
 * 
 * This class encapsulates the mine field grid
 * 
 * @author Mr. Page
 * @author Alexandra Michael modified for Checkstyle
 * @author Susan King modified comments
 * @author Boxuan Shan
 * @version May 23, 2022
 * @version 02212025
 */
public class Minefield
{
  // instance variables
  // theField is an array of booleans that indicate the locations of the mines
  // visited is an arrray of booleans indicating wether a location has been visited
  private boolean[][] theField;
  private boolean[][] visited;

  // mineCount keeps track of how many mines are currently in the game
  private int mineCount;

  // constructors
  /**
   * Creates a mine field with the specified number of rows and columns.
   * 
   * @param rows  the number of rows in this minefield
   * @param cols  the number of columns in this minefield
   */
  public Minefield(int rows, int cols)
  {
    theField = new boolean[rows][cols];
    visited = new boolean[rows] [cols];
  }

  /**
   * Creates a default 9 by 9 mine field.
   */
  public Minefield()
  {
    this(9,9);
  }

  /**
   * Returns the number of rows in this minefield. 
   * 
   * @return  row count 
   */
  public int numRows()
  {
    return theField.length;
  }

  /**
   * Returns the number of columns in this minefield.
   * 
   * @return  column count
   */
  public int numCols()
  {
    return theField[0].length;
  }

  /**
   * Determines if the row and col given are within the minefield's grid.
   * 
   * @param row   the row to be checked
   * @param col   the column to be checked
   * 
   * @return true if row is within the number of rows in this field and col
   *              is within the number of columns in this field, otherwise,
   *         false
   */
  public boolean isValid(int row, int col)
  {
    return row > -1 && row < this.numRows() && col > -1 && col < this.numCols();
  }

  /**
   * Determines if a cell in the minefield has a mine.
   * 
   * @param row   the row number of the location
   * @param col   the column number of the location
   * 
   * @return  true    if the row and col given correspond to a valid location 
   *                  within the grid and the location does contain a mine; otherwise,
   *          false
   */
  public boolean isMine(int row, int col)
  {
    return isValid(row,col) && theField[row][col];
  }

  /**
   * Adds a mine to the grid
   * 
   * @param row   the row of the location of the new mine
   * @param col   the column of the location of the new mine
   */
  public void add(int row, int col)
  {
    if(!isMine(row,col))
    {
      theField[row][col] = true;
      mineCount++;
    }
  }

  /**
   * Marks an empty location as a visited location, if it does not have a bomb.
   * Locations containing mines cannot be marked as visited; otherwise,
   * the recursive scan will show the mine locations!  
   * Use the display disable method to display the mine locations.
   * 
   * @param row   the row of the location to be marked
   * @param col   the column of the location to be marked
   */
  public void markVisited(int row,int col)
  {
    // mark a location as visited, if it is not a mine
    if(isValid(row,col) && !theField[row][col]) visited[row][col] = true;        
  }

  /**
   * Determines whether a cell of the minefield has been visited.
   *  
   * @param row   the row of the cell that is in question
   * @param col   the column of the cell that is in question
   * @return true if the location specified has been visited; otherwise,
   *         false
   */
  public boolean isVisited(int row, int col)
  {
    return isValid(row,col) && visited[row][col];
  }

  /**
   * Determines and returns the number of mines in the minefield.
   * 
   * @return the number of mines in this minefield.
   */
  public int numMines()
  {
    return mineCount;
  }

  public boolean foundAll() {
    int ret = 0;
    for (int i = 0; i < numRows(); i++) {
      for (int j = 0; j < numCols(); j++) {
        if (isVisited(i, j)) ret++;
      }
    }
    System.out.println((numRows() * numCols() - ret) + " tiles remaining");
    return ret == numRows() * numCols() - numMines();
  }
}
