import java.awt.*;
import java.util.*;

public class Bishop extends Piece {
  public Bishop(Color c, String fn) {
    super(c, fn, 3);
  }

  public ArrayList<Location> destinations() {
    ArrayList<Location> d = new ArrayList<Location>();

    this.sweep(d, Location.NORTHEAST);
    this.sweep(d, Location.SOUTHEAST);
    this.sweep(d, Location.SOUTHWEST);
    this.sweep(d, Location.NORTHWEST);

    return d;
  }
}
