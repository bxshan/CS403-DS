import java.io.*;
import java.util.*;

/**
 * FileUtil.java
 * @author Boxuan Shan
 * @version 11152024
 */
public class FileUtil
{
  /**
   * loads a file into a list of strings
   * @param fileName name of file to load
   * @return iterator of list of strings from file
   */
	public static Iterator<String> loadFile(String fileName)
	{
		try
		{
			Scanner in = new Scanner(new File(fileName));
			List<String> list = new ArrayList<String>();
			while (in.hasNextLine())
				list.add(in.nextLine());
			in.close();
			return list.iterator();
		}
		catch(FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

  /**
   * saves a list of strings to a file
   * @param fileName name of file to save to
   * @param data iterator of list of strings to save
   */
	public static void saveFile(String fileName, Iterator<String> data)
	{
		try
		{
			PrintWriter out = new PrintWriter(
				new FileWriter(fileName), true);
			while (data.hasNext())
				out.println(data.next());
			out.close();
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
