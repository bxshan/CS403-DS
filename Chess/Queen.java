import java.awt.*;
import java.util.*;

public class Queen extends Piece {
  public Queen(Color c, String fn) {
    super(c, fn, 9);
  }

  public ArrayList<Location> destinations() {
    ArrayList<Location> d = new ArrayList<Location>();

    this.sweep(d, Location.NORTH);
    this.sweep(d, Location.NORTHEAST);
    this.sweep(d, Location.EAST);
    this.sweep(d, Location.SOUTHEAST);
    this.sweep(d, Location.SOUTH);
    this.sweep(d, Location.SOUTHWEST);
    this.sweep(d, Location.WEST);
    this.sweep(d, Location.NORTHWEST);

    return d;
  }
}
