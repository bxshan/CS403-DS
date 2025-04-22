
/**
 * Write a description of class CardTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreditCardLevel1Tester
{
    private int[] cardNums1 = {4,5,2,3,4,6,1,8,2,7,7,5,2,7,1,2};
    private int[] cardNums2 = {3,6,5,2,6,5,8};
    private int[] cardNums3 = {7,3,9,6,2,3};
    private int[] cardNums4 = {3,7,5,2,7,5,9};
    private int[] cardNums5 = {4,5,2,3,4,6,1,8,2,-1,7,5,2,7,1,2};
    private int[] cardNums6 = {4,5,2,3,4,6,-1,8,2,7,7,5,2,7,1,2};
    
    //private CardNumber cardTwo
    
    public  void print(String header, int[] card)
    {
        String str = "\n" + header + " {";
        for (int i=0; i<card.length; i++)
        {
            str += card[i];
            if (i+1 != card.length)
                str += ", ";
            else
                str += "}";
        }
        
        System.out.println(str);
    }
    
    public  void print(String header, boolean[] missing)
    {
        String str = "\n" + header + " {";
        for (int i=0; i<missing.length; i++)
        {
            str += missing[i];
            if (i+1 != missing.length)
                str += ", ";
            else
                str += "}";
        }
        
        System.out.println(str);
    }
    
    public  void testDoublePattern()
    {
        CardNumberLevel1 card1 = new CardNumberLevel1(cardNums1);
        print("card1",cardNums1);
        int[]cardDouble1 = card1.doublePattern();
        print("CardOne Double",cardDouble1);
        
        CardNumberLevel1 card2 = new CardNumberLevel1(cardNums2);
        print("card2",cardNums2);
        int[]cardDouble2 = card2.doublePattern();
        print("Card2 Double",cardDouble2);
        
        CardNumberLevel1 card3 = new CardNumberLevel1(cardNums3);
        print("card3",cardNums3);
        int[]cardDouble3 = card3.doublePattern();
        print("Card2 Double",cardDouble3);
        
        System.out.println("+++++++++");
    }
    
    public void testVerified()
    {
        CardNumberLevel1 card1 = new CardNumberLevel1(cardNums1);
        print("card1",cardNums1);
        boolean isVerified1  = card1.verified();
        System.out.println("Card1 verified = " + isVerified1  + " => TRUE");
        
        CardNumberLevel1 card3 = new CardNumberLevel1(cardNums3);
        print("card3",cardNums3);
        boolean isVerified3= card3.verified();
        System.out.println("Card3 verified = " + isVerified3 + " => TRUE");
        
        CardNumberLevel1 card4 = new CardNumberLevel1(cardNums4);
        print("card4",cardNums4);
        boolean isVerified4= card4.verified();
        System.out.println("Card4 verified = " + isVerified4 + " => FALSE");
        
        System.out.println("+++++++++");
    }
    
    public static void main(String[] args)
    {
        CreditCardLevel1Tester tester = new CreditCardLevel1Tester();
        tester.testDoublePattern();
        tester.testVerified();
        
    }
}

/*

card1 {4, 5, 2, 3, 4, 6, 1, 8, 2, 7, 7, 5, 2, 7, 1, 2}

CardOne Double {8, 5, 4, 3, 8, 6, 2, 8, 4, 7, 14, 5, 4, 7, 2, 2}

card2 {3, 6, 5, 2, 6, 5, 8}

Card2 Double {3, 12, 5, 4, 6, 10, 8}

card3 {7, 3, 9, 6, 2, 3}

Card2 Double {14, 3, 18, 6, 4, 3}
+++++++++

card1 {4, 5, 2, 3, 4, 6, 1, 8, 2, 7, 7, 5, 2, 7, 1, 2}
Card1 verified = true => TRUE

card3 {7, 3, 9, 6, 2, 3}
Card3 verified = true => TRUE

card4 {3, 7, 5, 2, 7, 5, 9}
Card4 verified = false => FALSE
+++++++++
 */
