public class Char extends Bit {
  private Bit[] bitarr;

  public Char() {
    this.bitarr = new Bit[0];
  }

  public Char(Bit[] bitarr) {
    this.bitarr = new Bit[bitarr.length];
    for (int i = 0; i < bitarr.length; i++) {
      this.bitarr[i] = bitarr[i];
    }
  }

  public String getVal() {
    int B = 0;
    for (int i = 0; i < bitarr.length; i++) {
      B = B * 2 + (bitarr[i].bit ? 1 : 0);  
    }

    char character = (char) B;

    return String.valueOf(character);
  }
}

