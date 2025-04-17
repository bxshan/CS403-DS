public class Word {
  private Char[] chararr;

  public Word(Bit[][] bitarr) {
    chararr = new Char[bitarr.length];

    for (int i = 0; i < bitarr.length; i++) {
      Char c = new Char(bitarr[i]);
      this.chararr[i] = c;
    }
  }

  public String getVal() {
    StringBuilder ret = new StringBuilder();
    for (Char c : chararr) {
      ret.append(c.getVal());
    }
    return ret.toString().toLowerCase();
  }

  public boolean equals(Word other) {
    return this.getVal().equals(other.getVal());
  }

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

  @Override
  public String toString() {
    return getVal();
  }
}

