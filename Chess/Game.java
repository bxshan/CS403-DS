import java.awt.*;
import java.util.*;

public class Game {

  private static final Color HIGHLIGHT = new Color(241, 245, 2);

  private static final int[] kdx = {7};
  private static final int[] kdy = {4};

  private static final int[] qdx = {7};
  private static final int[] qdy = {3};

  private static final int[] rdx = {7, 7};
  private static final int[] rdy = {0, 7};

  private static final int[] bdx = {7, 7};
  private static final int[] bdy = {2, 5};

  private static final int[] ndx = {7, 7};
  private static final int[] ndy = {1, 6};

  private static final int[] pdx = {6, 6, 6, 6, 6, 6, 6, 6};
  private static final int[] pdy = {0, 1, 2, 3, 4, 5, 6, 7};

  private static void nextTurn(Board b, BoardDisplay d, Player p) {
    d.setTitle(p.getName());
    Move m = p.nextMove();
    b.executeMove(m);
    d.clearColors();
    d.setColor(m.getSource(), HIGHLIGHT);
    d.setColor(m.getDestination(), HIGHLIGHT);
    try {Thread.sleep(500);} catch (Exception e) {}
  }

  public static void play(Board b, BoardDisplay d, Player wp, Player bp) {
    while (true) {
      nextTurn(b, d, wp);
      nextTurn(b, d, bp);
    }
  }

  public static void main(String[] args) {
    Board b = new Board();

    // KINGS
    for (int i = 0; i < 1; i++) {
      Piece wk = new King(Color.WHITE, "white_king.gif");
      wk.putSelfInGrid(b, new Location(kdx[i], kdy[i]));

      Piece bk = new King(Color.BLACK, "black_king.gif");
      bk.putSelfInGrid(b, new Location(7-kdx[i], kdy[i]));
    }

    // QUEENS
    for (int i = 0; i < 1; i++) {
      Piece wq = new Queen(Color.WHITE, "white_queen.gif");
      wq.putSelfInGrid(b, new Location(qdx[i], qdy[i]));

      Piece bq = new Queen(Color.BLACK, "black_queen.gif");
      bq.putSelfInGrid(b, new Location(7-qdx[i], qdy[i]));
    }
    
    // ROOKS
    for (int i = 0; i < 2; i++) {
      Piece wr = new Rook(Color.WHITE, "white_rook.gif");
      wr.putSelfInGrid(b, new Location(rdx[i], rdy[i]));

      Piece br = new Rook(Color.BLACK, "black_rook.gif");
      br.putSelfInGrid(b, new Location(7-rdx[i], rdy[i]));
    }

    // BISHOPS 
    for (int i = 0; i < 2; i++) {
      Piece wb = new Bishop(Color.WHITE, "white_bishop.gif");
      wb.putSelfInGrid(b, new Location(bdx[i], bdy[i]));

      Piece bb = new Bishop(Color.BLACK, "black_bishop.gif");
      bb.putSelfInGrid(b, new Location(7-bdx[i], bdy[i]));
    }

    // KNIGHTS
    for (int i = 0; i < 2; i++) {
      Piece wn = new Knight(Color.WHITE, "white_knight.gif");
      wn.putSelfInGrid(b, new Location(ndx[i], ndy[i]));

      Piece bn = new Knight(Color.BLACK, "black_knight.gif");
      bn.putSelfInGrid(b, new Location(7-ndx[i], ndy[i]));
    }

    // PAWNS 
    for (int i = 0; i < 8; i++) {
      Piece wp = new Pawn(Color.WHITE, "white_pawn.gif");
      wp.putSelfInGrid(b, new Location(pdx[i], pdy[i]));

      Piece bp = new Pawn(Color.BLACK, "black_pawn.gif");
      bp.putSelfInGrid(b, new Location(7-pdx[i], pdy[i]));
    }

    // DISPLAY
    BoardDisplay d = new BoardDisplay(b);
    
    //for (Location l : bk.destinations()) d.setColor(l, HIGHLIGHT);

    //for (Location l : wk.destinations()) d.setColor(l, HIGHLIGHT);

    //for (Location l : bra.destinations()) d.setColor(l, HIGHLIGHT);
    //for (Location l : brh.destinations()) d.setColor(l, HIGHLIGHT);

    //for (Location l : wra.destinations()) d.setColor(l, HIGHLIGHT);
    //for (Location l : wrh.destinations()) d.setColor(l, HIGHLIGHT);
    
    Player rpw = new RandomPlayer(b, "box", Color.WHITE);
    Player rpb = new SmartPlayer(b, "xob", Color.BLACK);
    play(b, d, rpw, rpb);
    
    //ArrayList<Location> lsw = b.allMoves(Color.WHITE);
    //ArrayList<Location> lsb = b.allMoves(Color.BLACK);
    //for (Location l : lsw) {
    //  d.setColor(l, HIGHLIGHT);
    //}

  }
}
