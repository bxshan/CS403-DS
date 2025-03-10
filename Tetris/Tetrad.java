import java.util.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class Tetrad {
  private MyBoundedGrid<Block> home;
  private Block[] blocks;
  private int type;
  private Semaphore lock;

  public boolean lost;

  private static final Color[] COLS = {
    Color.RED,     // 0
    Color.GRAY,    // 1 
    Color.CYAN,    // 2
    Color.YELLOW,  // 3
    Color.MAGENTA, // 4
    Color.BLUE,    // 5
    Color.GREEN    // 6
  };

  private static final int[][] DR = {
    {1, 0, 2, 3},  // 0
    {0, 0, 0, 1},  // 1
    {0, 0, 1, 1},  // 2 
    {2, 1, 0, 2},  // 3
    {2, 1, 0, 2},  // 4
    {0, 1, 1, 0},  // 5
    {1, 0, 0, 1}   // 6
  };

  private static final int[][] DC = {
    {0, 0, 0, 0},  // 0
    {0, -1, 1, 0}, // 1
    {0, 1, 1, 0},  // 2
    {0, 0, 0, 1},  // 3
    {1, 1, 1, 0},  // 4
    {0, 0, -1, 1}, // 5
    {0, -1, 0, 1}  // 6
  };

  public Tetrad(MyBoundedGrid<Block> g) {
    this.home = g;
    this.blocks = new Block[4]; 
    this.type = (int) (Math.random() * 7);
    this.lock = new Semaphore(1, true);
    int ctr = (int) (this.home.getNumCols()/2) - 1;

    this.type = 0;

    Location[] ls = new Location[4];

    for(int i = 0; i < 4; i++) {
      blocks[i] = new Block(COLS[type]);
      ls[i] = new Location(DR[type][i], ctr+DC[type][i]);
    }

    this.home = g;

    if (!areEmpty(this.home, ls)) {
      this.lost = true;
      return;
    } else {
      this.lost = false;
    }

    this.addToLocations(this.home, ls);
  }

  private void addToLocations(MyBoundedGrid<Block> g, Location[] locs) {
    for(int i = 0; i < locs.length; i++) {
      blocks[i].putSelfInGrid(g, locs[i]);
    }
  }

  private Location[] removeBlocks() {
    Location locs[] = new Location[4];
    for(int i = 0; i < 4; i++) {
      locs[i] = blocks[i].getLocation();
      blocks[i].removeSelfFromGrid();
    }
    return locs;
  }

  private boolean areEmpty(MyBoundedGrid<Block> g, Location[] L) {
    for(Location l : L) {
      if (g.isValid(l) && g.get(l) == null) continue;
      return false;
    }
    return true;
  }

  public boolean translate(int dr, int dc) {
    try {
      lock.acquire();

      Location[] before = this.removeBlocks();
      Location[] after = new Location[4];

      for(int i = 0; i < 4; i++) {
        if (before[i] == null) return false;
        after[i] = new Location(before[i].getRow() + dr, before[i].getCol() + dc); 
      }

      if (areEmpty(this.home, after)) {
        addToLocations(this.home, after);
        return true;
      } else {
        addToLocations(this.home, before);
        return false;
      }
    } catch (InterruptedException e) {
      return false;
    } finally {
      lock.release();
    }
  }

  public boolean rotate() {
    try {
      lock.acquire();

      Location ctr = blocks[0].getLocation();
      Location[] before = this.removeBlocks();
      Location[] after = new Location[4];

      for(int i = 0; i < 4; i++) {
        if (before[i] == null) return false;
        int rf = ctr.getRow() - ctr.getCol() + before[i].getCol();
        int cf = ctr.getRow() + ctr.getCol() - before[i].getRow();
        after[i] = new Location(rf, cf); 
      }

      if (areEmpty(this.home, after)) {
        addToLocations(this.home, after);
        return true;
      } else {
        addToLocations(this.home, before);
        return false;
      }
    } catch (InterruptedException e) {
      return false;
    } finally {
      lock.release();
    }
  }
}
