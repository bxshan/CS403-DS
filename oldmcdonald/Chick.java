/** 
 * A concrete Chick class
 * @author Boxuan Shan 
 * @version 09242024
*/
public class Chick extends Chicken {
  /**
   * Constructor, creates a default chick
  */
  public Chick() {
    super("chick");
  }

  /**
   * Returns the chick's sound 
   * @return the chick's sound
  */
  @Override
  public String speak() {
    return "peep";
  }
}
