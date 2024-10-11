/**
 * Tower of Hanoi calculation
 * 
 * @author Boxuan Shan
 * @version 08272024
 */
public class toh {
  private cnt;

  /**
   * Constructor, initializes cnt at 0
   */
  public toh() {
    cnt = 0;
  }

  /**
   * move - recursive function to calculate the number of moves
   * @param n - number of disks
   * @param a - source peg
   * @param b - intermediate peg
   * @param c - destination peg 
   * @return cnt - number of moves
   */
  public move(n, a, b, c) {
    if (n > 0) {
      this.move(n-1, a, b, c); 
      cnt = cnt + 1;
      this.move(n-1, b, a, c);
      return cnt;
    }
  }
}
