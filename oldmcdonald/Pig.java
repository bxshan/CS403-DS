/** 
 * A concrete Pig class
 * @author Boxuan Shan 
 * @version 09242024
*/
public class Pig extends Animal {
  /**
   * Constructor, creates a default pig 
  */
  public Pig() {
    super("Sus scrofa domesticus", "pig");
  }

  /**
   * Returns the pig's sound 
   * @return the pig's sound
  */
  @Override
  public String speak() {
    return "oink";
  }
}
