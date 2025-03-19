import java.awt.*;
import java.util.*;

public class RandomPlayer extends Player {
  public RandomPlayer(Board b, String n, Color c) {
    super(b, n, c);
  }

  public Move nextMove() {
    ArrayList<Move> am = this.getBoard().allMoves(this.getColor());
    return am.get((int) (Math.random() * am.size()));
  }
}
