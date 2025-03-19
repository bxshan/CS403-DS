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

}
