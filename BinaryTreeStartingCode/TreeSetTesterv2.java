/**
 * Treeset Tester for testing iterator next and remove
 * Modified the tester vastly to account for duplicate values
 * issues with remove and iteration. Based on the work of Varun T.
 * Fixed a minor typo.
 * @author Anu Datar
 * @author Varun T
 * @version 01/19/2023
 */
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class TreeSetTesterv2
{

    public static void main(String[] args)
    {
        boolean err = false;
        // Create a TreeSet
        TreeSet<Integer> orig = new TreeSet<>();
        MyTreeSet<Integer> treeSet = new MyTreeSet<>();
        Random rand = new Random();

        // Add random number of elements to the TreeSet
        int numElements = rand.nextInt(10) + 5; 
        // generates a random number between 5 and 15
        System.out.println("Testing add. Adding "  + numElements + " elements");
        for (int i = 0; i < numElements; i++)
        {
            Integer i1 = rand.nextInt(100);
            treeSet.add(i1);
            orig.add(i1);
            System.out.print(i1 + ",");
        }
        //Updating in case there are duplicates that get rejected.
        numElements = treeSet.size();
        System.out.println();
        // Get the iterator for the TreeSet
        Iterator<Integer> origI = orig.iterator();
        Iterator<Integer> fakeI = treeSet.iterator();

        System.out.println("Orig: " + orig);
        System.out.println("Fake: [" + treeSet + "]");
        System.out.println();

        // Test hasNext() function
        System.out.println("Testing the iterator, hasNext() and next() methods: ");
        int count = 0;
        String op="";
        int sumO = 0;
        int sumF = 0;
        while (origI.hasNext())
        {
            count++;
            Integer o = origI.next();
            sumO += o;
            Integer f = fakeI.next();
            sumF += f;
            op += o + " ";
            if (o!=f)
            {
                err = true;
                System.out.print("Something wrong with your iterator. Expected " + o + " got " + f + " instead.");
            }
        }   
        if (sumO != sumF)
        {
            err = true;
            System.out.print("Something wrong with your iterator. Wrong count of elements returned");
        }
        System.out.println(op);
        System.out.println("Expected: " + numElements + " | Found: " + count);
        System.out.println();

        System.out.println("Testing the iterator remove() function");
        int countR = 0;
        fakeI = treeSet.iterator();
        while (fakeI.hasNext())
        {
            Integer n = fakeI.next();
            int randO = (int)(Math.random()*2);
            if (randO == 1)
            {
                fakeI.remove();          
                countR++;
                System.out.print("Removed " + n + "\n");    
            }
        }
        if (treeSet.size() + countR != numElements)
        {
            System.out.println("Something wrong with your remove() ");
            System.out.println(treeSet.size() + "-" + countR + "-" + numElements);
            System.out.println(treeSet);
            err = true;
        }

        if (!err)
            System.out.println("Congratulations! Passed all tests successfully!\nHave a nice day!");
        else
            System.out.println("Fix your issues and try again.");
    }
}
