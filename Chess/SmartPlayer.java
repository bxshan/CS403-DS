import java.awt.*;
import java.util.*;

public class SmartPlayer extends Player{

  private int lookAhead = 2;

  public SmartPlayer(Board b, String n, Color c, int lookAhead) {
    super(b, n, c);
    this.lookAhead = lookAhead;
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

  public int valOfWorstResponse(int lookAhead) {
    if (lookAhead == 0) return this.score();

    Board b = this.getBoard();
    ArrayList<Move> om = b.allMoves((this.getColor().equals(Color.WHITE)) ? Color.BLACK : Color.WHITE);

    int w = Integer.MAX_VALUE; 

    for (Move cm: om) {
      b.executeMove(cm); 
      w = Math.min(w, this.valOfWorstResponse(lookAhead - 1)); 
      b.undoMove(cm); 
    }

    return w;
  }

  public Move nextMove(boolean inCheck) {
    Board b = this.getBoard();
    Move mm = null; // initially all null; SURELY it is assigned to a legal move...
    int ms = -2001; // intitialize to 1 score smaller than the worst move possible

    ArrayList<Move> am = b.allMoves(this.getColor());

    // check for checkmate
    boolean js = true;
    if (inCheck) {
      for (Move m : am) {
        b.executeMove(m);
        if (!b.inCheck(this.getColor())) js = false;
        b.undoMove(m);
      }
    }
    if (js && inCheck) {
      return null;
    }

    for (Move cm : am) {
      b.executeMove(cm);

      if (b.inCheck(this.getColor())) {
        b.undoMove(cm);
        continue;
      }

      int cs = this.score() + this.valOfWorstResponse(this.lookAhead);
      if (cs > ms) {
        ms = cs;
        mm = cm;
      }
      b.undoMove(cm);
    }

    return mm;
  }
}
