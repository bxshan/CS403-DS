import java.awt.*;
import java.util.*;

public class Pawn extends Piece {
  public Pawn(Color c, String fn) {
    super(c, fn, 1);
  }

  @Override
  // only used for walking ahead, not for capturing
  // isValid() of the board class is used instead
  public boolean isValidDestination(Location d) {
    return this.getBoard().isValid(d) && 
      this.getBoard().get(d) == null; 
  }

  public ArrayList<Location> destinations() {
    ArrayList<Location> d = new ArrayList<Location>();

    int heading = (this.getColor().equals(Color.WHITE)) ? Location.NORTH : Location.SOUTH;
    Location f = this.getLocation().getAdjacentLocation(heading);

    if (isValidDestination(f)) d.add(f);

    if ((this.getLocation().getRow() == 6 && this.getColor().equals(Color.WHITE)) || 
        (this.getLocation().getRow() == 1 && this.getColor().equals(Color.BLACK))) {
      Location s = f.getAdjacentLocation(heading);
      if (this.isValidDestination(s)) d.add(s);
    }

    // check for piece captures
    Location ne = this.getLocation().getAdjacentLocation(heading + Location.HALF_RIGHT);
    Location nw = this.getLocation().getAdjacentLocation(heading + Location.HALF_LEFT);
    if (this.getBoard().isValid(ne) && 
        this.getBoard().get(ne) != null && 
        !this.getBoard().get(ne).getColor().equals(this.getColor())) {
      d.add(ne);
        }
    if (this.getBoard().isValid(nw) && 
        this.getBoard().get(nw) != null && 
        !this.getBoard().get(nw).getColor().equals(this.getColor())) d.add(nw);

    return d;
  }
}
