/**
 * Tester for the MyTreeSet add, remove and contains method
 * Tests the iterator implementation of the TreeSet
 * @author Kabir R
 * @author Anu Datar
 * @version 12/16/2014
 */
import java.util.*;

public class TreeSetIteratorTester
{
    private static final boolean DEBUG = true;
    private static final int MAX_VALUE = 100;
    private static final int NUMBER_OF_ELEMENTS = 5;

    public static void main(String[] args)
    {
        Set<Integer> real = new TreeSet<Integer>();
        MyTreeSet<Integer> fake = new MyTreeSet<Integer>();
        while (real.size() < NUMBER_OF_ELEMENTS)
        {
            debug("real:  " + real);
            debug("fake:  " + fake);

            Integer value = new Integer(random(MAX_VALUE));

            boolean realBool = real.contains(value);
            boolean fakeBool = fake.contains(value);
            if (fakeBool != realBool)
                throw new RuntimeException("contains(" + value + ") returned " + fakeBool +
                    " and should return " + realBool);

            //add
            debug("add(" + value + ")");
            realBool = real.add(value);
            fakeBool = fake.add(value);
            if (fakeBool != realBool)
                throw new RuntimeException("add(" + value + ") returned " + fakeBool +
                    " and should return " + realBool);

            int realInt = real.size();
            int fakeInt = fake.size();
            if (realInt != fakeInt)
                throw new RuntimeException("size() returned " + fakeInt + " and should return " +
                    realInt);
        }
       //Iterator<Object> it = fake.iterator();
       Iterator<Integer> it = fake.iterator();
        int i = 0;
        System.out.println("Testing the iterator");
        boolean itF = false;
        while(it.hasNext())
        {
            System.out.print(it.next() + ", ");
            i++;
        }
        if (i != NUMBER_OF_ELEMENTS)
        {
            System.out.println("Something wrong with the iterator");
        }
        else
        {
            itF = true;
            System.out.println("Iterator works well!");
        }    

        while(real.size() > 0)
        {
            debug("real:  " + real);
            debug("fake:  " + fake);

            Integer value = new Integer(random(MAX_VALUE));

            boolean realBool = real.contains(value);
            boolean fakeBool = fake.contains(value);
            if (fakeBool != realBool)
                throw new RuntimeException("contains(" + value + ") returned " + fakeBool +
                    " and should return " + realBool);

            //remove
            debug("remove(" + value + ")");
            realBool = real.remove(value);
            fakeBool = fake.remove(value);
            if (fakeBool != realBool)
                throw new RuntimeException("remove(" + value + ") returned " + fakeBool +
                    " and should return " + realBool);

            int realInt = real.size();
            int fakeInt = fake.size();
            if (realInt != fakeInt)
                throw new RuntimeException("size() returned " + fakeInt + " and should return " +
                    realInt);
        }

        if (itF)
            System.out.println("Awesome! MyTreeSet and the iterator both work well!");
        else
            System.out.println("Iterator does not work.Check again. MyTreeSet is fine");
    }

    private static void debug(String s)
    {
        if (DEBUG)
            System.out.println(s);
    }

    private static int random(int n)
    {
        return (int)(Math.random() * n);
    }
}