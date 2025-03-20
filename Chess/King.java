import java.awt.*;
import java.util.*;

public class King extends Piece {
  public static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
  public static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

  public King(Color c, String fn) {
    super(c, fn, 1000);
  }

  public ArrayList<Location> destinations() {
    ArrayList<Location> d = new ArrayList<Location>();

    for (int i = 0; i < 8; i++) {
      Location l = new Location(this.getLocation().getRow() + dx[i], this.getLocation().getCol() + dy[i]);

      if (this.isValidDestination(l)) {
        Move m = new Move(this, l);
        this.getBoard().executeMove(m);
        if (this.getBoard().inCheck(this.getColor())) {
          this.getBoard().undoMove(m);
          continue;
        }
        this.getBoard().undoMove(m);
        d.add(l);
      }
    }

    return d;
  }
}
