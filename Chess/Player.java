import java.awt.*;

public abstract class Player {
  private Board b;
  private String n;
  private Color c;

  public Player(Board b, String n, Color c) {
    this.b = b;
    this.n = n;
    this.c = c;
  }

  public Board getBoard() {
    return this.b;
  }

  public String getName() {
    return this.n;
  }

  public Color getColor() {
    return this.c;
  }

  public abstract Move nextMove(boolean inCheck);
}
