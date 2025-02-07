import java.io.*;

/**
 * Models hurricane information, including categories.  
 * Works with HurricaneOrganizer, provides object and comparison skeletons.
 * 
 * @author Susan King
 * @version January 17, 2019
 */
public class Hurricane
{
  private int year;
  private String month;
  private int pressure;
  private int speed;
  private String name;
  private int category;

  /**
   * Initializes a Hurricane object with no information.
   */
  public Hurricane()
  {
    this.year = -1;
    this.month = "";
    this.pressure = -1;
    this.speed = -1;
    this.name = "";
  }

  /**
   * Initializes a Hurricane object with historical information.
   * 
   * @param year      year the hurricane took place
   * @param month     month in String format
   * @param pressure  hurricane's pressure
   * @param speed     hurricane's speed in knots
   * @param name      hurricane's name
   */
  public Hurricane(int year, String month, 
      int pressure, int speed, String name)
  {
    this.year = year;
    this.month = month;
    this.pressure = pressure;
    this.speed = speed;
    this.name = name;
    this.category = determineCategory(speed);
  }

  /**
   * Based upon Saffir/Simpson Hurricane Scale, figures out
   * the category using wind speed in knots.
   * 
   * Use https://en.wikipedia.org/wiki/Saffir%E2%80%93Simpson_scale.
   * 
   * @param knots     wind speed in knots
   * @return Saffir/Simpson Hurricane Scale category
   */
  public int determineCategory(int knots)
  {
    // replace the following line with code
    // that determines the category from speed
    if (knots >= 137) {
      return 5;
    } else if (knots >= 113) {
      return 4;
    } else if (knots >= 96) {
      return 3;
    } else if (knots >= 83) {
      return 2;
    } else if (knots >= 64) {
      return 1;
    } else if (knots >= 34) {
      return 0;
    } else {
      return -1;
    }
  }

  //Getters

  /**
   * Comment this method.
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Comment this method.
   */
  public String getMonth()
  {
    return this.month;
  }

  /**
   * Comment this method.
   */
  public int getPressure()
  {
    return this.pressure;
  }

  /**
   * Comment this method.
   */
  public int getSpeed()
  {
    return this.speed;
  }

  /**
   * Comment this method.
   */
  public int getYear()
  {
    return this.year;
  }

  /**
   * Comment this method.
   */
  public int getCategory()
  {
    return this.category;
  }

  /**
   * Comment this method even though you did not write it.
   */
  public void print()
  {
    System.out.println(toString( ));
  }

  /**
   * Alter code a bit then comment this method even though you did not write it.
   */
  public String toString()
  {
    return String.format("%-4d %-5s %-15s %-5d %5d %5d ", 
           year, month, name, category, speed, pressure);
  }

  /**
   * Comment this method.
   */
  public int compareYearTo(Hurricane h)
  {
    return this.year > h.getYear() ? 1 : this.year < h.getYear() ? -1 : 0;
  }

  /**
   * Comment this method.
   */
  public int compareNameTo(Hurricane h)
  {
    return this.name.compareTo(h.getName());
  }

  /**
   * Comment this method.
   */
  public int comparePressureTo(Hurricane h)
  {
    return this.pressure > h.getYear() ? 1 : this.pressure < h.getYear() ? -1 : 0;
  }

  /**
   * Comment this method.
   */
  public int compareSpeedTo(Hurricane h)
  {
    return this.speed > h.getYear() ? 1 : this.speed < h.getYear() ? -1 : 0;
  }

  /**
   * Comment this method.
   */
  public int compareCategoryTo(Hurricane h)
  {
    return this.category > h.getYear() ? 1 : this.category < h.getYear() ? -1 : 0;
  }
}
