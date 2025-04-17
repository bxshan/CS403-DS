/**
 *  an interface that represents a bit
 */
public class Bit extends BitAbsClass {
  public boolean bit;

  /**
   * instantiates a bit object to a zero
   */
  public Bit() {
    this.bit = false;
  }

  /**
   * instantiates a bit objec to the object that is passed as parameter
   * @param bit
   */
  public Bit(int bit) {
    this.bit = (bit == 1) ? true : false;
  }

  /**
   * gets the value of the bit
   * @return the value of the bit
   */
  public String getVal() {
    return String.valueOf(bit ? 1 : 0);
  }
}
