/**
 * Card.java
 * @author Boxuan Shan``
 * @version 10112024
 */
public class Card {
  private int rank;
  private String suit;
  private boolean isUp;

  /**
   * Constructor
   * @param rank
   * @param suit
   */
  public Card(int rank, String suit) {
    this.rank = rank;
    this.suit = suit;
    this.isUp = false;
  }

  /**
   * gets rank
   * @return rank
   */
  public int getRank() {
    return this.rank;
  }

  /**
   * gets suit
   * @return suit 
   */
  String getSuit() {
    return this.suit;
  }

  /**
   * determines if red
   * @return true if card is red
   */
  boolean red() {
    return this.suit.equals("h") || this.suit.equals("d");
  }

  /**
   * checks if up
   * @return true if card is up
   */
  boolean isFaceUp() {
    return this.isUp;
  }

  /**
   * turnUp turns the card up
   */
  void turnUp() {
    this.isUp = true;
  }

  /**
   * turnDown turns the card down 
   */
  void turnDown() {
    this.isUp = false;
  }

  /**
   * gets the string name of card
   * @param rank
   * @return string name of the card
   */
  private String getStringName(int rank) {
    if (rank == 1) {
      return "a";
    } else if (rank == 10) {
      return "t";
    } else if (rank == 11) {
      return "j";
    } else if (rank == 12) {
      return "q";
    } else if (rank == 13) {
      return "k";
    } else {
      return Integer.toString(rank);
    }
  }

  /**
   * gets the file name of card
   * @return file name of the card
   */
  String getFileName() {
    if (!this.isUp) {
      return "cards/backapcsds.gif";
    } else {
      return "cards/" + getStringName(this.rank) + this.suit + ".gif";
    }
  }
}
