/**
* comment this class completely, and in accordance with the style guide.
*/
public class Rectangle
{
	private int length;
	private int width;

	public Rectangle(int len, int w)
	{
		length = len;
		width = w;
	}

	public int getLength()
	{
		return length;
	}

	public int getWidth()
	{
		return width;
	}

	public String toString() {
		return length + "x" + width;
	}
  
  public boolean equals(Object obj) {
    Rectangle o = (Rectangle) obj;
    return o.getWidth() == this.width && o.getLength() == this.length;
  }

  public int hashCode() {
    return length + width * 1000696969;
  }
}
