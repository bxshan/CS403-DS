import java.util.Stack;

/**
 * Write a description of class StringUtil here.
 * 
 * @author Anu Datar
 * @author Boxuan Shan
 * @version 10/27/2017
 * @version 10062024
 */
public class StringUtil
{
    //reverse a string using a Stack and substring not charAt
    public static String reverseString(String str)
    {
      String ret = "";
      for (int i = str.length() - 1; i > -1; i--) {
        ret += str.substring(i, i+1);
      }
      return ret;
    }

    // must use reverse written above
    public static boolean isPalindrome(String s)
    {
      return reverseString(s).equals(s);
    }

    // The tester for checking that reverse and isPalindrome work well.
    public static void main(String[] args)
    {
        String test =  "racecar";
        String test2 = "notapalindrome";

        if ( !("".equalsIgnoreCase(reverseString(""))) )
            System.out.println("An empty string is palindrome");

        if ( !("a".equalsIgnoreCase(reverseString("a"))) )
            System.out.println("\"a\" is a palindrome");

        if ( !("aa".equalsIgnoreCase(reverseString("aa"))) )
            System.out.println("Error: \"aa\" is a palindrome");

        if (!test.equalsIgnoreCase(reverseString(test)))
            System.out.println("Error: " + test + " is a palindrome");
        else
            System.out.println("Success " + test + " matched " + reverseString(test));

        if (test2.equalsIgnoreCase(reverseString(test2)))
            System.out.println("Error: " + test2 + " is a palindrome");

    }
}
