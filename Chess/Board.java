import java.awt.*;
import java.util.*;

// Represesents a rectangular game board, containing Piece objects.
public class Board extends BoundedGrid<Piece>
{
	// Constructs a new Board with the given dimensions
	public Board()
	{
		super(8, 8);
	}

	// Precondition:  move has already been made on the board
	// Postcondition: piece has moved back to its source,
	//                and any captured piece is returned to its location
	public void undoMove(Move move)
	{
    if (move == null) return;

		Piece piece = move.getPiece();
		Location source = move.getSource();
		Location dest = move.getDestination();
		Piece victim = move.getVictim();

		piece.moveTo(source);

		if (victim != null)
			victim.putSelfInGrid(piece.getBoard(), dest);
	}

  // new methods

  public ArrayList<Move> allMoves(Color c) {
    ArrayList<Move> moves = new ArrayList<Move>();
    ArrayList<Location> toCheck = this.getOccupiedLocations();

    for (Location l : toCheck) {
      Piece p = this.get(l);
      if (p.getColor().equals(c)) {
        ArrayList<Location> d = p.destinations();
        for (Location ld : d) {
          moves.add(new Move(p, ld));
        }
      }
    }

    return moves;
  }

  public void executeMove(Move m) {
    m.getPiece().moveTo(m.getDestination());
  }

  public boolean inCheck(Color c) {
    ArrayList<Location> ls = this.getOccupiedLocations();

    for (Location l : ls) {
      Piece p = this.get(l);
      if (p instanceof King) continue;
      ArrayList<Location> hits = p.destinations();
      for (Location hit : hits) {
        if (this.get(hit) instanceof King) {
          return true;
        }
      }
    }

    return false;
  }

  public String getFen(boolean c) {
    String fen = "";

    // field 1

    for (int i = 0; i < 8; i++) {
      int empty = 0;
      for (int j = 0; j < 8; j++) {
        Location l = new Location(i, j);
        Piece p = this.get(l);
        if (p == null) {
          empty++;
        } else {
          if (empty > 0) {
            fen += empty;
            empty = 0;
          }
          fen += p.getFenName();
        }
      }
      if (empty > 0) {
        fen += empty;
      }
      if (i < 7) {
        fen += "/";
      }
    }

    // field 2
    
    if (c) {
      fen += " w ";
    } else {
      fen += " b ";
    }

    // field 3
    // no castling implemented yet
    fen += "- ";

    // field 4
    // no en passant implemented yet
    fen += "- ";

    // field 5 & 6
    fen += "0 1";

    return fen;
  }
}
