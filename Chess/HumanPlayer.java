import java.awt.*;
import java.util.*;

public class HumanPlayer extends Player {

  private BoardDisplay d;

  public HumanPlayer(Board b, String n, Color c, BoardDisplay d) {
    super(b, n, c);
    this.d = d;
  }

  public Move nextMove(boolean inCheck) {
    Board b = this.getBoard();
    Color c = this.getColor();
    ArrayList<Move> possible = b.allMoves(c);

    // check for checkmate
    boolean js = true;
    if (inCheck) {
      for (Move m : possible) {
        b.executeMove(m);
        if (!b.inCheck(this.getColor())) js = false;
        b.undoMove(m);
      }
    }
    if (js && inCheck) {
      return null;
    }

    Move choice = this.d.selectMove();

    while(!possible.contains(choice) && !b.inCheck(c)) {
      possible = b.allMoves(c);
      choice = this.d.selectMove();
    }
    
    return choice;
  }

}
