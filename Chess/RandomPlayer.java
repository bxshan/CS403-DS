import java.awt.*;
import java.util.*;

public class RandomPlayer extends Player {
  public RandomPlayer(Board b, String n, Color c) {
    super(b, n, c);
  }

  public Move nextMove(boolean inCheck) {
    Board b = this.getBoard();
    ArrayList<Move> am = this.getBoard().allMoves(this.getColor());

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

    if (inCheck) {
      Move m = null;

      do {
        b.undoMove(m); // m is null skips this
        m = am.get((int) (Math.random() * am.size()));
        b.executeMove(m);
      } while (b.inCheck(this.getColor()));

      return m;
    } else {
      return am.get((int) (Math.random() * am.size()));
    }
  }
}
