import java.awt.*;
import java.util.*;

public class Game {

  private static Player wp;
  private static Player bp;

  private static final Color HIGHLIGHT = new Color(241, 245, 2);
  private static final Color CHECK = new Color(255, 51, 51);
  private static final Color CHECKMATE = new Color(255, 255, 255);

  private static final int backRank = 7;
  private static final int frontRank = 6;

  private static final int[] kdy = {4};
  private static final int[] qdy = {3};
  private static final int[] rdy = {0, 7};
  private static final int[] bdy = {2, 5};
  private static final int[] ndy = {1, 6};
  private static final int[] pdy = {0, 1, 2, 3, 4, 5, 6, 7};

  private static boolean nextTurn(Board b, BoardDisplay d, Player p, boolean inCheck) {
    if (!(wp instanceof StockfishPlayer || bp instanceof StockfishPlayer)) d.setTitle(p.getName());
    Move m = p.nextMove(inCheck);

    if (m == null) {
      System.out.println("checkmate");
      checkmate(b, d);
      return false;
    }

    if(inCheck) d.setTitle("CHECK");
    b.executeMove(m);

    d.clearColors();

    // check
    if (b.inCheck(p.getColor())) {
      d.setColor(m.getSource(), HIGHLIGHT);
      d.setColor(m.getDestination(), CHECK);
      try {Thread.sleep(10);} catch (Exception e) {}
      return true;
    } else {
      d.setColor(m.getSource(), HIGHLIGHT);
      d.setColor(m.getDestination(), HIGHLIGHT);
      try {Thread.sleep(10);} catch (Exception e) {}
      return false;
    }
  }

  public static void play(Board b, BoardDisplay d, Player p, boolean inCheck) {
    boolean next = p.getColor().equals(Color.BLACK);

    play(b, d, ((next) ? wp : bp), nextTurn(b, d, p, inCheck));
  }

  public static void checkmate(Board b, BoardDisplay d) {
    d.setTitle("CHECKMATE");

    ArrayList<Location> ls = b.getOccupiedLocations();
    for (Location l : ls) {
      if (b.get(l) instanceof King) {
        d.setColor(l, CHECKMATE);
      }
    }

    try {Thread.sleep(10000);} catch (Exception e) {}
    System.exit(0);
  }

  public static void main(String[] args) {
    Board b = new Board();

    // KINGS
    for (int i = 0; i < 1; i++) {
      Piece wk = new King(Color.WHITE, "white_king.gif");
      wk.putSelfInGrid(b, new Location(backRank, kdy[i]));

      Piece bk = new King(Color.BLACK, "black_king.gif");
      bk.putSelfInGrid(b, new Location(7-backRank, kdy[i]));
    }

    // QUEENS
    for (int i = 0; i < 1; i++) {
      Piece wq = new Queen(Color.WHITE, "white_queen.gif");
      wq.putSelfInGrid(b, new Location(backRank, qdy[i]));

      Piece bq = new Queen(Color.BLACK, "black_queen.gif");
      bq.putSelfInGrid(b, new Location(7-backRank, qdy[i]));
    }
    
    // ROOKS
    for (int i = 0; i < 2; i++) {
      Piece wr = new Rook(Color.WHITE, "white_rook.gif");
      wr.putSelfInGrid(b, new Location(backRank, rdy[i]));

      Piece br = new Rook(Color.BLACK, "black_rook.gif");
      br.putSelfInGrid(b, new Location(7-backRank, rdy[i]));
    }

    // BISHOPS 
    for (int i = 0; i < 2; i++) {
      Piece wb = new Bishop(Color.WHITE, "white_bishop.gif");
      wb.putSelfInGrid(b, new Location(backRank, bdy[i]));

      Piece bb = new Bishop(Color.BLACK, "black_bishop.gif");
      bb.putSelfInGrid(b, new Location(7-backRank, bdy[i]));
    }

    // KNIGHTS
    for (int i = 0; i < 2; i++) {
      Piece wn = new Knight(Color.WHITE, "white_knight.gif");
      wn.putSelfInGrid(b, new Location(backRank, ndy[i]));

      Piece bn = new Knight(Color.BLACK, "black_knight.gif");
      bn.putSelfInGrid(b, new Location(7-backRank, ndy[i]));
    }

    // PAWNS 
    for (int i = 0; i < 8; i++) {
      Piece wp = new Pawn(Color.WHITE, "white_pawn.gif");
      wp.putSelfInGrid(b, new Location(frontRank, pdy[i]));

      Piece bp = new Pawn(Color.BLACK, "black_pawn.gif");
      bp.putSelfInGrid(b, new Location(7-frontRank, pdy[i]));
    }

    // DISPLAY
    BoardDisplay d = new BoardDisplay(b);
    
    wp = new StockfishPlayer(b, "box", Color.WHITE, d, 40);
    bp = new StockfishPlayer(b, "xob", Color.BLACK, d, 40);
    //bp = new StockfishPlayer(b, "xob", Color.BLACK, d, 25);
    // last param of StockfishPlayer constructor the max search depth
    // <30 for performance, >20 for decently smart play

    // stockfish message
    if (wp instanceof StockfishPlayer && bp instanceof StockfishPlayer) {
      d.setTitle("white stockfish depth " + ((StockfishPlayer) wp).getDepth() + 
          "; black stockfish depth " + ((StockfishPlayer) bp).getDepth());
    } else if (wp instanceof StockfishPlayer) {
      d.setTitle("white stockfish depth " + ((StockfishPlayer) wp).getDepth());
    } else if (bp instanceof StockfishPlayer) {
      d.setTitle("black stockfish depth " + ((StockfishPlayer) bp).getDepth());
    }

    play(b, d, wp, false);
  }
}
