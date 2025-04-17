public class Wordle {
  private Word answer;
  private Word[] guesses;
  private int guesscnt;

  public Wordle(Word answer) {
    this.answer = answer;
    this.guesses = new Word[6];
    this.guesscnt = 0;
  }

  public boolean addGuess(Word guess) {
    if (guesscnt >= 6 || isGameOver()) {
      return false;
    }

    guesses[guesscnt] = guess;
    guesscnt++;
    return true;
  }

  public boolean isGameOver() {
    return isWin() || guesscnt == 6;
  }

  public boolean isWin() {
    for (Word guess : guesses) {
      if (guess != null && guess.equals(answer)) {
        return true;
      }
    }
    return false;
  }

  public void printFeedback() {
    for (int i = 0; i < guesscnt; i++) {
      Word guess = guesses[i];
      String feedback = guess.getFeedback(answer);
      System.out.println(guess + " → " + feedback);
    }
  }

  public void playSummary() {
    printFeedback();
    if (isWin()) {
      System.out.println("win");
    } else if (guesscnt == 6) {
      System.out.println("lose");
    }
  }
}

