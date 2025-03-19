import java.awt.*;
import java.util.*;

public class HumanPlayer extends Player {

  private BoardDisplay d;

  public HumanPlayer(Board b, String n, Color c, BoardDisplay d) {
    super(b, n, c);
    this.d = d;
  }

  public Move nextMove() {
    ArrayList<Move> possible = this.getBoard().allMoves(this.getColor());
    Move choice = this.d.selectMove();

    while(!possible.contains(choice)) {
      possible = this.getBoard().allMoves(this.getColor());
      choice = this.d.selectMove();
    }
    
    return choice;
  }

}
