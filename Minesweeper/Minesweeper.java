
/**
 * Minesweeper.java 
 *
 * Minesweeper encapsulates the Minesweeper game.
 * 
 * This is where student code goes
 * 
 * @author Mr. Page
 * @author Alexandra Michael modified for Checkstyle
 * @author Susan King modified for comments and moves Main class to main method
 *                    in this class
 * @author Boxuan Shan
 * @version May 23, 2022
 * @version 02212025 
 */
public class Minesweeper
{
  // constants for the size of the field, can be deleted if using default constructor
  private static final int GRID_ROWS = 8;
  private static final int GRID_COLS = 10;
  private static final int NUM_MINES = 10;

  // references to the model and the view
  private MinefieldDisplay theDisplay;
  private Minefield theField;

  private boolean timerStarted;

  /**
   * Initializes the default Minesweeper game.
   */
  public Minesweeper()
  {
    // construct the grid
    theField = new Minefield(GRID_ROWS, GRID_COLS);
    // set up the mines
    setMines(NUM_MINES);

    for(int i = 0; i < theField.numRows(); i++) {
      for(int j = 0; j < theField.numCols(); j++) {
        if(theField.isMine(i, j)) {
          System.out.print("X\t");
        } else {
          System.out.print(countMines(i, j) + "\t");
        }
      }
      System.out.println();
      System.out.println();
    } 

    // construct the display 
    theDisplay = new MinefieldDisplay(this, theField);

    timerStarted = false;
    theDisplay.startTimer();
  }

  /**
   * Handles button presses.  This method is called whenever the user 
   * selects and clicks on a location within the mine field 
   * that has not been previously selected.
   * 
   * The view actionPerformed method calls this method and 
   * passes the row and col information as int values
   * 
   * @param row   the row the cursor was on when a button was pressed
   * @param col   the column the cursor was on when a button was pressed
   * @param rightButton   true if the right button is pressed; otherwise, 
   *                      false
   */
  public void pressed(int row, int col, boolean rightButton)
  {
    System.out.println("row # " + row + " col # " + col + " isRightButton? " + rightButton);
    timerStarted = true;
    if (!rightButton) {
      if (theField.isMine(row, col)) {
        System.out.println("You lose!");
        theDisplay.showAll();
        theDisplay.stopTimer();
        return;
      } else {
        this.scanField(row, col);
        theField.markVisited(row, col);
        if (theField.foundAll()) {
          System.out.println("You win!");
          theDisplay.update();
          theDisplay.stopTimer();
          theDisplay.win();
          return;
        }
        theDisplay.update();
        return;
      }
    } else {
      theDisplay.flag(row, col);
    }
  }

  /**
   * Recursively scans the field and reveals all squares that do not contain mines.
   * Additionally, it labels the squares adjacent to mines with the number of mines 
   * adjacent.
   * 
   * @param row   the row of the cell to be processed
   * @param col   the column of the cell to be processed
   */
  public void scanField(int row, int col)
  {
    if (theField.isValid(row, col) && !theField.isVisited(row, col)) {
      theField.markVisited(row, col);
      if (this.countMines(row, col) > 0) {
        return;
      }

      int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
      int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
      for (int i = 0; i < 8; i++) {
        scanField(row + dx[i], col + dy[i]);
      }
    }
  }

  /**
   * Sets up the mine field in a random pattern.
   * 
   * @param numMines  the number of mines that the user wants set up.
   */
  public void setMines(int numMines)
  {
    while (numMines > 0) {
      int rloc = (int) (Math.random() * theField.numRows());
      int cloc = (int) (Math.random() * theField.numCols());
      while (theField.isMine(rloc, cloc)) {
        rloc = (int) (Math.random() * theField.numRows());
        cloc = (int) (Math.random() * theField.numCols());
      }

      theField.add(rloc, cloc);
      numMines--;
    }
  }

  /**
   * Counts the number of mines adjacent to a given location.
   * 
   * @param row   the row of the cell in which adjacent mines are being counted
   * @param col   the column of the cell in which adjacent mines are being counted
   * @return the number of mines adjacent to the given location
   */
  public int countMines(int row, int col)
  {
    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
    int cnt = 0;

    for (int i = 0; i < 8; i++) {
      if (theField.isValid(row + dx[i], col + dy[i]) && theField.isMine(row + dx[i], col + dy[i])) {
        cnt++;
      }
    }

    return cnt;
  }  

  /**
   * Starts the game Minesweeper.
   * 
   * @param args  information from the command line
   */
  public static void main(String[] args)
  {
    Minesweeper gameMaster = new Minesweeper();
  }
}
