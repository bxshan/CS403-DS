/**
 * a class representing a word
 */
public class Word {
  private Char[] chararr;

  /**
   * creates an array of characters based on an array of bits
   * @param bitarr
   */
  public Word(Bit[][] bitarr) {
    chararr = new Char[bitarr.length];

    for (int i = 0; i < bitarr.length; i++) {
      Char c = new Char(bitarr[i]);
      this.chararr[i] = c;
    }
  }

  /**
   * gets the value of the string
   * @return the value of the string
   */
  public String getVal() {
    StringBuilder ret = new StringBuilder();
    for (Char c : chararr) {
      ret.append(c.getVal());
    }
    return ret.toString().toLowerCase();
  }

  /**
   * checks if the current word is equal to the other word
   * @param other the word to compare to
   * @return if the current word is equal to the other word
   */
  public boolean equals(Word other) {
    return this.getVal().equals(other.getVal());
  }

  /**
   * gets how close the current word
   * @param answer the answer word
   * @return a string describing each character and how it compares.
   */
  public String getFeedback(Word answer) {
    String guess = this.getVal();
    String target = answer.getVal();

    char[] result = new char[5];
    boolean[] used = new boolean[5];

    for (int i = 0; i < 5; i++) {
      if (guess.charAt(i) == target.charAt(i)) {
        result[i] = 'G'; 
        used[i] = true;
      }
    }

    for (int i = 0; i < 5; i++) {
      if (result[i] == 'G') continue;

      boolean found = false;
      for (int j = 0; j < 5; j++) {
        if (!used[j] && guess.charAt(i) == target.charAt(j)) {
          result[i] = 'Y';
          used[j] = true;
          found = true;
          break;
        }
      }

      if (!found) {
        result[i] = 'X'; 
      }
    }

    return new String(result);
  }

  /**
   * returns a string that contains the value of this word
   * @return a string containing the valuye of this word
   */
  @Override
  public String toString() {
    return getVal();
  }
}

