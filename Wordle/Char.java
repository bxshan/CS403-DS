/**
 * a class that uses bits to represent a character
 */
public class Char extends Bit {
  private Bit[] bitarr;

  /**
   * creates a charater based on a bit
   */
  public Char() {
    this.bitarr = new Bit[0];
  }

  /**
   * creates a character based on a given array of bits
   * @param bitarr the array of bits to define the character
   */
  public Char(Bit[] bitarr) {
    this.bitarr = new Bit[bitarr.length];
    for (int i = 0; i < bitarr.length; i++) {
      this.bitarr[i] = bitarr[i];
    }
  }

  /**
   * gets the value of the character
   * @return the value of the character
   */
  public String getVal() {
    int B = 0;
    for (int i = 0; i < bitarr.length; i++) {
      B = B * 2 + (bitarr[i].bit ? 1 : 0);  
    }

    char character = (char) B;

    return String.valueOf(character);
  }
}

