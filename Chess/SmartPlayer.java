import java.awt.*;
import java.util.*;

public class SmartPlayer extends Player{
  public SmartPlayer(Board b, String n, Color c) {
    super(b, n, c);
  }

  public int score() {
    int s = 0;
    ArrayList<Location> ls = this.getBoard().getOccupiedLocations();

    for (Location l : ls) {
      Piece p = this.getBoard().get(l);
      if (p.getColor().equals(this.getColor())) s += p.getValue();
      else s -= p.getValue();
    }

    return s;
  }

  public Move nextMove() {
    Board b = this.getBoard();
    ArrayList<Move> am = b.allMoves(this.getColor());
    Move mm = new Move(); // initially all null; SURELY it is assigned to a legal move...
    int ms = -1001; // intitialize to 1 score smaller than the worst move possible

    for (Move cm : am) {
      b.executeMove(cm);
      int cs = this.score();
      if (cs > ms) {
        mm = cm;
        ms = cs;
      }
      b.undoMove(cm);
    }

    return mm;
  }
}
