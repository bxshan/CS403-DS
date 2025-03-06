import java.awt.Color;
/**
 * Block.java
 * @author Boxuan Shan
 * @version 03052025
 */
public class Block
{
  private MyBoundedGrid<Block> grid;
  private Location location;
  private Color color;

  /**
   * constructs a blue block, because blue is the greatest color ever!
   */
  public Block()
  {
    color = Color.BLUE;
    grid = null;
    location = null;
  }

  /**
   * gets color
   * @return this.color
   */
  public Color getColor()
  {
    return this.color;
  }

  /**
   * sets color
   * @param newColor color to set to 
   */
  public void setColor(Color newColor)
  {
    this.color = newColor;
  }

  /**
   * gets grid
   * @return this.grid
   */
  public MyBoundedGrid<Block> getGrid()
  {
    return this.grid;
  }

  /**
   * gets location
   * @return this.location
   */
  public Location getLocation()
  {
    return this.location;
  }

  /**
   * removes self from grid
   */
  public void removeSelfFromGrid()
  {
    if (this.grid == null || this.location == null) return;
    this.grid.remove(this.location);
    this.location = null;
    this.grid = null;
  }

  /**
   * puts self into grid
   * @param gr grid to put self into
   * @param loc location to put self at
   */
  public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
  {
    this.grid = gr;
    this.location = loc;
    Block prev = this.grid.remove(this.location);
    if (prev != null) prev.removeSelfFromGrid();
    this.grid.put(this.location, this);
  }

  /**
   * moves self to new location
   * @param newLocation location to move self to
   */
  public void moveTo(Location newLocation)
  {
    this.grid.remove(this.location);
    if (this.grid.get(newLocation) != null) this.grid.get(newLocation).removeSelfFromGrid();
    this.grid.remove(newLocation);
    this.grid.put(newLocation, this);
    this.location = newLocation;
  }

  /**
   * returns a string with the location and color of this block
   */
  public String toString()
  {
    return "Block[location=" + location + ",color=" + color + "]";
  }
}
