/**
 * the main class that is a tester
 */
public class Main {
  /*
  tests the classes by having it print hello
   */
  public static void main(String[] args) {
    // 01101000 01100101 01101100 01101100 01101111 00001010
    Bit[][] bitarr = {
      {new Bit(0), new Bit(1),  new Bit(1),  new Bit(0), new Bit(1),  new Bit(0), new Bit(0), new Bit(0)}, // h
      {new Bit(0), new Bit(1),  new Bit(1),  new Bit(0), new Bit(0), new Bit(1),  new Bit(0), new Bit(1)},  // e 
      {new Bit(0), new Bit(1),  new Bit(1),  new Bit(0), new Bit(1),  new Bit(1),  new Bit(0), new Bit(0)}, // l
      {new Bit(0), new Bit(1),  new Bit(1),  new Bit(0), new Bit(1),  new Bit(1),  new Bit(0), new Bit(0)}, // l
      {new Bit(0), new Bit(1),  new Bit(1),  new Bit(0), new Bit(1),  new Bit(1),  new Bit(1),  new Bit(1)},  // o
      {new Bit(0), new Bit(0), new Bit(0), new Bit(0), new Bit(1),  new Bit(0), new Bit(1),  new Bit(0)}  // newline
    };

    //Bit[] o = {new Bit(0), new Bit(1),  new Bit(1),  new Bit(0), new Bit(1),  new Bit(1),  new Bit(1),  new Bit(1)};  // 'o' (01101111)

    Word ans = new Word(bitarr);

    System.out.println(ans);
  }
}
