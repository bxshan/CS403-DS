import java.util.*;
import java.awt.*;

public class Tetris implements ArrowListener {
  private MyBoundedGrid<Block> field;
  private BlockDisplay display;
  private Tetrad tet;

  private int nextType;
  
  private int rowsCleared;
  private int lvl;

  public Tetris(int r, int c) {
    if (r < 20) r = 20;
    if (c < 10) c = 10;
    this.field = new MyBoundedGrid<Block>(r, c);
    this.display = new BlockDisplay(this.field);
    this.display.setTitle("Tetbris");
    this.display.setArrowListener(this);
    this.display.showBlocks();

    this.tet = new Tetrad(this.field, (int) (Math.random() * 7));
    this.nextType = (int) (Math.random() * 7);

    this.rowsCleared = 0;
    this.lvl = 1;

    this.play();
  }

  public Tetris() {
    this(20, 10);
  }

  private int determineIncrement(int cr) {
    if (cr == 1) return 40;
    else if (cr == 2) return 100;
    else if (cr == 3) return 300;
    else if (cr == 4) return 1200;
    else return 0;
  }

  private String nextTypeStringFormat(int nt) {
    if (nt == 0) return "I";
    else if (nt == 1) return "T";
    else if (nt == 2) return "O";
    else if (nt == 3) return "L";
    else if (nt == 4) return "J";
    else if (nt == 5) return "S";
    else if (nt == 6) return "Z";
    else return "";
  }

  private void d() {
    int r = this.field.getNumRows();
    int c = this.field.getNumCols();

    ArrayList<Location> l = this.field.getOccupiedLocations();
    char[][] g = new char[r][c];

    for(Location L : l) {
      g[L.getRow()][L.getCol()] = 'X';
    }

    for(int i = 0; i < c+2; i++) System.out.print("-");
    System.out.println();

    for(int rr = 0; rr < r; rr++) {
      System.out.print("|");
      for(int cc = 0; cc < c; cc++) {
        if (g[rr][cc] != 'X') {
          System.out.print(" ");
        } else {
          System.out.print("X");
        }
      }
      System.out.println("|");
    }

    for(int i = 0; i < c+2; i++) System.out.print("-");
  }

  public void upPressed() {
    //this.tet.translate(-1, 0);
    this.tet.rotate();
    this.display.showBlocks();
  }

  public void downPressed() {
    this.tet.translate(1, 0);
    this.display.showBlocks();
  }
  
  public void rightPressed() {
    this.tet.translate(0, 1);
    this.display.showBlocks();
  }

  public void leftPressed() {
    this.tet.translate(0, -1);
    this.display.showBlocks();
  }

  private boolean isCompletedRow(int r) {
    for(int c = 0; c < this.field.getNumCols(); c++) {
      if (this.field.get(new Location(r, c)) == null) return false;
    }

    return true;
  }

  private void clearRow(int r) {
    for(int c = 0; c < this.field.getNumCols(); c++) {
      this.field.get(new Location(r, c)).removeSelfFromGrid();
    }
  }

  private int clearCompletedRows() {
    int cr = 0;

    for (int r = this.field.getNumRows() - 1; r > -1; r--) {
      if (isCompletedRow(r)) {
        clearRow(r);
        cr++; 
        r++;
      }
    }

    for (int r = this.field.getNumRows() - 1; r > -1; r--) {
      for (int c = this.field.getNumCols() - 1; c > -1; c--) {
        Block cb = this.field.get(new Location(r, c));  
        if (cb != null) {
          cb.moveTo(new Location(cb.getLocation().getRow() + cr, cb.getLocation().getCol()));
        }
      }
    }

    return cr;
  }

  public void lose() {
    this.display.setTitle("stupid");
    this.display.setLost();

    // clear field
    for (int r = this.field.getNumRows() - 1; r > -1; r--) {
      for (int c = this.field.getNumCols() - 1; c > -1; c--) {
        Block cb = this.field.get(new Location(r, c));  
        if (cb != null) {
          cb.removeSelfFromGrid();
        }
      }

      try {
        Thread.sleep(500 - (500*r/this.field.getNumRows()));
      } catch(InterruptedException e) {
        //
      }

      this.display.showBlocks();
    }

    // show lost
    // TO DO
  }

  public void play() {
    while(!false) {
      try {
        Thread.sleep((int) (1000/Math.min(this.lvl, 1)));
      } catch(InterruptedException e) {
        //
      }
      if (!this.tet.translate(1, 0)) {
        int cr = clearCompletedRows();

        this.display.incrementScore(determineIncrement(cr) * this.lvl);

        this.rowsCleared += cr;

        this.lvl = rowsCleared/10;
        if (this.lvl == 0) this.lvl = 1;
        this.display.setLvl(this.lvl);

        this.tet = new Tetrad(this.field, this.nextType);

        this.nextType = (int) (Math.random() * 7);
        this.display.setTitle("Next Type: " + nextTypeStringFormat(this.nextType));

        if (this.tet.lost) {
          System.out.println("Game Over");
          this.lose();
          return;
        }
      }
      this.display.showBlocks();
      //this.d();
    }
  }

  public static void main(String[] args) {
    new Tetris();
  }
}
