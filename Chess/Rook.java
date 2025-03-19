import java.awt.*;
import java.util.*;

public class Rook extends Piece {
  public Rook(Color c, String fn) {
    super(c, fn, 5);
  }

  public ArrayList<Location> destinations() {
    ArrayList<Location> d = new ArrayList<Location>();

    this.sweep(d, Location.NORTH);
    this.sweep(d, Location.EAST);
    this.sweep(d, Location.SOUTH);
    this.sweep(d, Location.WEST);

    return d;
  }
}
