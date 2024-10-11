/** 
 * A concrete Rooster class
 * @author Boxuan Shan 
 * @version 09242024
*/
public class Rooster extends Chicken {
  /**
   * Constructor, creates a default rooster
  */
  public Rooster() {
    super("rooster");
  }

  /**
   * Returns the rooster's sound 
   * @return the rooster's sound
  */
  @Override
  public String speak() {
    return "cock-a-doodle-do";
  }
}
