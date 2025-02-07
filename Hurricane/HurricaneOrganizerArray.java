import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data.
 * 
 * Data came from http://www.aoml.noaa.gov/hrd/tcfaq/E23.html except for 2018.
 * 2018 data came from https://en.wikipedia.org/wiki/2018_Atlantic_hurricane_season.
 *
 * @author Susan King 
 * @version January 17, 2019
 * @version February 10, 2020 Polished code via variable names
 */
public class HurricaneOrganizerArray
{
    private Hurricane [] hurricanes;

    /**
     * Comment this constructor even though you did not write it.
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public HurricaneOrganizerArray(String filename)throws IOException
    {
        readFile(filename);   
    }

    /**
     * Comment this method even though you did not write it.
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    private static int determineFileLength(String filename) throws IOException
    {
        Scanner inFile = new Scanner(new File(filename));
        int counter = 0;

        while(inFile.hasNextLine())
        {
            counter++;
            inFile.nextLine();
        }
        inFile.close();
        return counter;
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new Hurricane [determineFileLength(filename)];
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        for(int i = 0; i < hurricanes.length; i++)
        {
            hurYear = inFile.nextInt();
            hurMonth = inFile.next();
            hurPressure = inFile.nextInt();
            hurSpeed = inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if(('a' <= c && c <= 'z') || ('A' <= c && c <='Z'))
                    hurName += c;
            }
            Hurricane h = new Hurricane(hurYear, hurMonth, hurPressure, hurSpeed, hurName);
            hurricanes [i] = h;
        }
        inFile.close();
    }

    /**
     * Comment this method.
     */
    public int findMaxWindSpeed( )
    {
      int ret = Integer.MIN_VALUE;
      for (Hurricane h : hurricanes) {
        ret = Math.max(h.getSpeed(), ret); 
      }
      return ret;
    }

    /**
     * Comment this method.
     */
    public int findMaxPressure( )
    {
      int ret = Integer.MIN_VALUE;
      for (Hurricane h : hurricanes) {
        ret = Math.max(h.getPressure(), ret); 
      }
      return ret;
    }

    /**
     * Comment this method.
     */
    public int findMinWindSpeed( )
    {
      int ret = Integer.MAX_VALUE;
      for (Hurricane h : hurricanes) {
        ret = Math.min(h.getSpeed(), ret); 
      }
      return ret;
    }

    /**
     * Comment this method.
     */
    public int findMinPressure( )
    {
      int ret = Integer.MAX_VALUE;
      for (Hurricane h : hurricanes) {
        ret = Math.min(h.getPressure(), ret); 
      }
      return ret;
    }

    /**
     * Comment this method.
     */
    public double calculateAverageWindSpeed( )
    {
      double sum = 0;
      for (Hurricane h : hurricanes) {
        sum += h.getSpeed();
      }
      return sum / hurricanes.length;
    }

    /**
     * Comment this method.
     */
    public double calculateAveragePressure( )
    {
      double sum = 0;
      for (Hurricane h : hurricanes) {
        sum += h.getPressure();
      }
      return sum / hurricanes.length;
    }

    /**
     * Comment this method.
     */
    public double calculateAverageCategory( )
    {
      double sum = 0;
      for (Hurricane h : hurricanes) {
        sum += h.getCategory();
      }
      return sum / hurricanes.length;
    }

    public void swp(Hurricane[] a, int ii, int jj) {
      Hurricane tmp = a[jj];
      a[jj] = a[ii];
      a[ii] = tmp;
    }

    public int indexOfMin(Hurricane[] a, int startIndex, int endIndex, int type, boolean whichOrder)
    {
      int minI = startIndex;
      Hurricane min = a[startIndex];

      for (int i = startIndex; i < ((endIndex == -1) ? a.length : endIndex); i++) {
        switch (type) {
          case 0: // year
            if ((min.getYear() > a[i].getYear()) == whichOrder) {
              min = a[i];
              minI = i;
            }
            break;
          case 1: // category
            if ((min.getCategory() > a[i].getCategory()) == whichOrder) {
              min = a[i];
              minI = i;
            }
            break;
        }
      }

      return minI;
    }


    /**
     * Sorts ascending based upon the hurricanes' years,
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
      int ii = 0;
      while (ii < hurricanes.length) {
        int jj = indexOfMin(hurricanes, ii, -1, 0, true);

        swp(hurricanes, ii, jj);

        ii++; 
      }
    }

    public void insert(Hurricane[] a, int nextIndex) {
      int ii = nextIndex - 1;
      int jj = nextIndex;

      while (ii > -1 && jj > -1 && a[ii].getName().compareTo(a[jj].getName()) > 0) {
        swp(hurricanes, ii, jj);
        jj--;
        ii = jj - 1;
      }
    }

    /**
     * Lexicographically sorts hurricanes based on the hurricanes' name, 
     * using insertion sort.
     */
    public void sortNames()
    {
      int ii = -1;
      while (ii < hurricanes.length) {
        insert(hurricanes, ii);
        ii++;
      }
    }

    /**
     * Sorts descending based upon the hurricanes' categories,
     * using selection sort.
     */
    public void sortCategories()
    {
      int ii = 0;
      while (ii < hurricanes.length) {
        int jj = indexOfMin(hurricanes, ii, -1, 1, false);

        swp(hurricanes, ii, jj);

        ii++; 
      }
    }  

    /**
     * Sorts descending based upon pressures using a non-recursive merge sort.
     */
    public void sortPressures()
    {
      for (int i = 1; i < hurricanes.length; i *= 2) {
        for (int l = 0; l < hurricanes.length-1; l += 2*i) {
          int m = Math.min(l+i-1, hurricanes.length-1);
          int r = Math.min(l+2*i-1, hurricanes.length-1);
          merge(l, m, r, 0, false);
        }
      }
    }
    
    /**
     * Sorts descending a portion of array based upon pressure, 
     * using selection sort.
     * 
     * @param   start   the first index to start the sort
     * @param   end     one past the last index to sort; hence, end position
     *                  is excluded in the sort
     */
    private void sortPressuresHelper (int start, int end)
    {
      int ii = start;
      while (ii < end) {
        int jj = indexOfMin(hurricanes, ii, end, 1, false);

        swp(hurricanes, ii, jj);

        ii++; 
      }
    }

    /**
     * Sorts ascending based upon wind speeds using a recursive merge sort. 
     */
    public void sortWindSpeeds(int l, int r)
    {
      if (l >= r) {
        return;
      }
      int m = (l + r) / 2;
      sortWindSpeeds(l, m);
      sortWindSpeeds(m+1, r);
      merge(l, m, r, 1, true);
    }

    /**
     * Merges two consecutive parts of an array, using wind speed as a criteria
     * and a temporary array.  The merge results in an ascending sort between
     * the two given indices.
     * 
     * @precondition the two parts are sorted ascending based upon wind speed
     * 
     * @param low   the starting index of one part of the array.
     *              This index is included in the first half.
     * @param mid   the starting index of the second part of the array.
     *              This index is included in the second half.
     * @param high  the ending index of the second part of the array.  
     *              This index is included in the merge.
     */
    private void merge(int l, int m, int r, int which, boolean whichOrder)
    {
      Hurricane[] ll = new Hurricane[m-l+1];
      Hurricane[] rr = new Hurricane[r-m];

      for (int k = l; k < r+1; k++) {
        if (k < m+1) {
          ll[k-l] = hurricanes[k];
        } else {
          rr[k-m-1] = hurricanes[k];
        }
      }

      int i = 0; int j = 0;
      int idx = l;

      while (i < ll.length && j < rr.length) {
        switch (which) {
          case 0: // pressure
            if ((ll[i].getPressure() < rr[j].getPressure()) == whichOrder) {
              hurricanes[idx] = ll[i];
              idx++; i++;
            } else {
              hurricanes[idx] = rr[j];
              idx++; j++;
            } 
            break;
          case 1: // speed
            if ((ll[i].getSpeed() < rr[j].getSpeed()) == whichOrder) {
              hurricanes[idx] = ll[i];
              idx++; i++;
            } else {
              hurricanes[idx] = rr[j];
              idx++; j++;
            } 
            break;
        }
      }

      while(i < ll.length) {
        hurricanes[idx] = ll[i];
        i++; idx++;
      }

      while(j < rr.length) {
        hurricanes[idx] = rr[j];
        j++; idx++;
      }
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     * 
     * @param   year
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public Hurricane [] searchYear(int year)
    {
        int counter = 0;
        for (Hurricane h : hurricanes) {
          counter += (h.getYear() == year) ? 1 : 0;
        }

        Hurricane[] matches = new Hurricane[counter];
        int idx = 0;
        
        for (Hurricane h : hurricanes) {
          if (h.getYear() == year) {
            matches[idx] = h;
            idx++;
          }
        }

        return matches;
    }     

    /**
     * Binary search for a hurricane name.
     * 
     * @param  name   hurricane name being search
     * @return a Hurricane array of all objects in hurricanes with specified name. 
     *         Returns null if there are no matches
     */
    public Hurricane[ ] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1);
    }

    /**
     * Recursive binary search for a hurricane name.  This is the helper
     * for searchHurricaneName.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name  hurricane name to search for
     * @param   low   the smallest index that needs to be checked
     * @param   high  the highest index that needs to be checked
     * @return  a Hurricane array of all Hurricane objects with a specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] searchHurricaneNameHelper(String name, int l, int r)
    {
      int m = (l+r)/2;
      // Test for the base case when a match is not found
      if (r - l < 2) {
        return null;
      }

      // Test for match
      // Determine if the potential match is in the 
      // "first half" of the considered items in the array
      // The potential match must be in the
      // "second half" of the considered items in the array

      if (hurricanes[m].getName().compareTo(name) > 0) {
        return searchHurricaneNameHelper(name, l, m);
      } else if (hurricanes[m].getName().compareTo(name) < 0) {
        return searchHurricaneNameHelper(name, m, r);
      } else {
        return retrieveMatchedNames(name, m);
      }
    }

    /**
     * Supports Binary Search method to get the full range of matches.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name hurricane name being search for
     * @param   index  the index where a match was found
     * @return  a Hurricane array with objects from hurricanes with specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] retrieveMatchedNames (String name, int index)
    {
      // Find the start where the matches start:
      while(hurricanes[index].getName().equalsIgnoreCase(name)) {
        index--;
      }
      int strt = ++index;
      
      // Find the end of the matches:
      while(hurricanes[index].getName().equalsIgnoreCase(name)) {
        index++;
      }
      int end = --index;
      
      Hurricane[] ret = new Hurricane[strt-end+1];
      int idx = 0;

      while(strt != end+1) {
        ret[idx] = hurricanes[strt];
        strt++;
      }

      return ret;  // correct this line
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printHurricanes(Hurricane [] hurs)
    {
        if(hurs.length == 0)
        {
            System.out.println("\nVoid of hurricane data.");
            return;
        }
        printHeader();
        for(Hurricane h: hurs)
        {
            System.out.println(h);
        }
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printMenu()
    {
        System.out.println("\n\nEnter option: ");
        System.out.println("\t 1 - Print all hurricane data \n" +
            "\t 2 - Print maximum and minimum data \n" +
            "\t 3 - Print averages \n" +
            "\t 4 - Sort hurricanes by year \n" +
            "\t 5 - Sort hurricanes by name \n" +
            "\t 6 - Sort hurricanes by category, descending \n" +
            "\t 7 - Sort hurricanes by pressure, descending \n" +
            "\t 8 - Sort hurricanes by speed \n" + 
            "\t 9 - Search for hurricanes for a given year \n" +
            "\t10 - Search for a given hurricane by name \n" +
            "\t11 - Quit \n");
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printMaxAndMin( )
    {
        System.out.println("Maximum wind speed is " + 
            findMaxWindSpeed( ) +
            " knots and minimum wind speed is " + 
            findMinWindSpeed( ) + " knots.");
        System.out.println("Maximum pressure is " + 
            findMaxPressure( ) +
            " and minimum pressure is " + 
            findMinPressure( ) + ".");
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printAverages( )
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" , 
            calculateAverageWindSpeed( ));
        System.out.printf("Average pressure is %5.2f. \n" , 
            calculateAveragePressure( ));
        System.out.printf("Average category is %5.2f. \n" , 
            calculateAverageCategory( ));
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public boolean interactWithUser( )
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = in.nextInt();
        // clear the input buffer
        in.nextLine();

        if(choice == 1)
        {
            printHurricanes( ); 
        }
        else if (choice == 2)
        {
            printMaxAndMin( );
        }
        else if (choice == 3)
        {
            printAverages( );
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes( );
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes( );
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes( );
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes( );
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes( );
        }
        else if(choice == 9)
        {
            System.out.print("\n\tWhich year do you want to search for?\n\t");
            int year = in.nextInt();
            printHurricanes(searchYear(year));
        }
        else if(choice == 10)
        {
            System.out.print("\n\tWhich name do you want to search for?\n\t");
            String name = in.next();
            printHurricanes(searchHurricaneName(name));
        }
        else if (choice == 11)
        {
            done = true;
        }  
        return done;
    }

    /**
     * Comment the method even though you did not write it.
     * 
     * @param args  user's information from the command line
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args) throws IOException
    {
        HurricaneOrganizerArray cane = new HurricaneOrganizerArray("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}
