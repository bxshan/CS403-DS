import java.util.ArrayList;
/**
 * Prints out the old mac donalds farm song with different animals
 * @author Boxuan Shan 
 * @version 09242024
*/
public class OldMacDonaldsFarm {
  /**
   * the name of the farmer
  */
  private String farmerName;
  /**
   * the name of the farm animals, in an ArrayList
  */
  private ArrayList<Animal> farmAnimals;

  /**
   * Constructor, creates a default farm 
   * @precondition farmer, farm created
  */
  public OldMacDonaldsFarm() {
    farmerName = "Old MacDonald";
    farmAnimals = new ArrayList<Animal>();
  }

  /**
   * Prints out a verse of the song 
   * @precondition verse sung
  */
  public void singVerse() {
    String p1 = farmerName + " had a farm,";
    String p2 = " E-I-E-I-O";
    String p3 = " and on his farm he had some ";
    String f = p1 + p2 + p3;

    System.out.println(f + 
        farmAnimals.get(farmAnimals.size() - 1).getCommonName() + 
        "s," + 
        p2 + 
        "."
    );

    for (int i = farmAnimals.size() - 1; i > -1; i--) {
      Animal a = farmAnimals.get(i);
      String spoken = a.speak();

      System.out.println("With a " + 
          spoken + "-" + spoken + 
          " here, and a " + 
          spoken + "-" + spoken + 
          " there,"
      );

      System.out.println("Here a " +
          spoken + ", there a " + 
          spoken + ", every where a " + 
          spoken + "-" + spoken + ", " 
      ); 
    }

    System.out.println(p1 + p2 + ".\n");
  }

  /**
   * Main method, creates a farm and sings the song with different animals
   * @param args cmd-line arguments
  */
  public static void main (String [] args) {
    OldMacDonaldsFarm singer = new OldMacDonaldsFarm();
    singer.farmAnimals.add(new Chicken());
    singer.singVerse();
    singer.farmAnimals.add(new Chick());
    singer.singVerse();
    singer.farmAnimals.add(new Rooster());
    singer.singVerse();
    singer.farmAnimals.add(new Pig());
    singer.singVerse();
  }
}
