import java.awt.*;
import java.util.*;

public class Knight extends Piece {
  public static final int dx[] = {-2, -2, -1, -1, 1, 1, 2, 2};
  public static final int dy[] = {1, -1, 2, -2, 2, -2, 1, -1};

  public Knight(Color c, String fn) {
    super(c, fn, 3);
  }

  public ArrayList<Location> destinations() {
    ArrayList<Location> d = new ArrayList<Location>();

    for (int i = 0; i < 8; i++) {
      Location l = new Location(
          this.getLocation().getRow() + dx[i],
          this.getLocation().getCol() + dy[i]);

      if (this.isValidDestination(l)) {
        d.add(l);
      }
    }

    return d;
  }
}
