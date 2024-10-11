/** 
 * A concrete Chicken class
 * @author Boxuan Shan 
 * @version 09242024
*/
public class Chicken extends Animal {
  /**
   * Constructor, creates a default chicken
  */
  public Chicken() {
    this("chicken");
  }

  /**
   * Constructor, creates a chicken with a specific type
   * @param chickenType the type of chicken
  */
  public Chicken(String chickenType) {
    super("Gallus Gallus domesticus", chickenType);
  }

  /**
   * Returns the chicken's sound 
   * @return the chicken's sound
  */
  @Override
  public String speak() {
    return "bawk";
  }
}
