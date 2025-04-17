public class Bit extends BitAbsClass {
  public boolean bit;

  public Bit() {
    this.bit = false;
  }

  public Bit(int bit) {
    this.bit = (bit == 1) ? true : false;
  }

  public String getVal() {
    return String.valueOf(bit ? 1 : 0);
  }
}
