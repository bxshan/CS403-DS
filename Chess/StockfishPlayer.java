import java.awt.*;
import java.util.*;

public class StockfishPlayer extends Player {

  private BoardDisplay d;
  private int depth;

  public StockfishPlayer(Board b, String n, Color c, BoardDisplay d, int depth) {
    super(b, n, c);
    this.d = d;
    this.depth = depth;
  }

  public int getDepth() {
    return this.depth;
  }

  private Location locOf(String uci) {
    return new Location((int) (7 - (uci.charAt(1) - '1')), (int) (uci.charAt(0) - 'a'));
  }

  private Piece pieceAt(String uci) {
    return this.getBoard().get(locOf(uci));
  }

  public Move nextMove(boolean inCheck) {
    Board b = this.getBoard();

    String pc = (this.getColor().equals(Color.WHITE)) ? "white" : "black";

    System.out.println(pc + ", for board of " + this.getBoard().getFen(this.getColor().equals(Color.WHITE)));
    d.setTitle(pc + " stockfish evaluating");

    String uci = StockfishEngine.getBestMove(this.getBoard().getFen(this.getColor().equals(Color.WHITE)), this.depth);

    d.setTitle(pc + " stockfish played: " + uci);

    if (uci.length() != 4) {
      System.out.println("bad position");
      return null;
    }

    Piece p = pieceAt(uci.substring(0, 2));
    Location l = locOf(uci.substring(2));

    if (p == null || l == null) {
      System.out.println("bad"); 
    }

    return new Move(p, l);
  }
}
