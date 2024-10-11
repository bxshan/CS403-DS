/** 
 * A generic Animal abstract class
 * @author Boxuan Shan 
 * @version 09242024
*/
public abstract class Animal implements Comparable {
  /**
   * the latin name for the animal
  */
  private String latinName;
  /**
   * the common name for the animal
  */
  private String commonName;

  /**
   * Constructor, creates an animal with set latin and common names
   * @param lN the animal's latin name
   * @param cN the animal's common name
  */
  public Animal(String lN, String cN) {
    this.latinName = lN;
    this.commonName = cN;
  }

  /** 
   * Gets the animal's latin name
   * @return latinName the animal's latin name
  */
  public String getLatinName() {
    return this.latinName;
  }

  /** 
   * Sets the animal's latin name
   * @param latinName the animal's latin name to set
  */
  public void setLatinName(String lN) {
    this.latinName = lN;
  }

  /** 
   * Gets the animal's common name
   * @return commonName the animal's common name
  */
  public String getCommonName() {
    return this.commonName;
  }

  /** 
   * Sets the animal's common name
   * @param commonName the animal's common name to set
  */
  public void setCommonName(String cN) {
    this.commonName = cN;
  }

  /** 
   * Abstract method for the animal's speech
   * @return String of the sound the animal makes
  */
  public abstract String speak();

  /** 
   * Compares two animals via their common names
   * @param o the object to compare to
   * @return int the comparison result
  */
  public int compareTo(Object o) {
    Animal a = (Animal) o;
    return this.getCommonName().compareTo(a.getCommonName());
  }

  /** 
   * Main method just so i can compile
   * @param args the cmd-line arguments
  */
  public static void main(String[] args) {
  }
}
