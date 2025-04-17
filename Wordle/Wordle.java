/**
 * a class that represents a game of wordle
 */
public class Wordle {
  private Word answer;
  private Word[] guesses;
  private int guesscnt;

  /**
   * instantiates a class of wordle type with a word as an answer
   * @param answer the answer word to be set
   */
  public Wordle(Word answer) {
    this.answer = answer;
    this.guesses = new Word[6];
    this.guesscnt = 0;
  }

  /**
   * adds a guess to the guess array
   * @param guess the guess to add
   * @return false if the game is over, true if the guess is sucessfully added
   */
  public boolean addGuess(Word guess) {
    if (guesscnt >= 6 || isGameOver()) {
      return false;
    }

    guesses[guesscnt] = guess;
    guesscnt++;
    return true;
  }

  /**
   * returns whether or not the game is over
   * @return if the game is over or not
   */
  public boolean isGameOver() {
    return isWin() || guesscnt == 6;
  }

  /**
   * checks if the player has won by correctly guessing the word
   * @return if the answer is contained within one of the guesses
   */
  public boolean isWin() {
    for (Word guess : guesses) {
      if (guess != null && guess.equals(answer)) {
        return true;
      }
    }
    return false;
  }

  /**
   * prints the feedback on a given guess based on the feedback method
   */
  public void printFeedback() {
    for (int i = 0; i < guesscnt; i++) {
      Word guess = guesses[i];
      String feedback = guess.getFeedback(answer);
      System.out.println(guess + " → " + feedback);
    }
  }

  /**
   * gets the summary of the game after the game is over
   */
  public void playSummary() {
    printFeedback();
    if (isWin()) {
      System.out.println("win");
    } else if (guesscnt == 6) {
      System.out.println("lose");
    }
  }
}

