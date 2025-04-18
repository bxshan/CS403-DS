public class CardNumber {
  private int[] cardNum;

  public CardNumber(String cardNum) {
    this.cardNum = new int[cardNum.length()];
    for (int i = 0; i < cardNum.length(); i++) {
      this.cardNum[i] = Character.getNumericValue(cardNum.charAt(i));
    }
  }

  public CardNumber(int[] cardNum) {
    this.cardNum = cardNum;
  }

  // tester method
  public void scramble(int i) {
    this.cardNum[i] = -1;
  }

  public int[] getNum() {
    return cardNum;
  }

  public int[] doublePattern() {
    int[] ret = new int[cardNum.length];
    for (int i = cardNum.length - 1; i >= 0; i--) {
      if ((cardNum.length - 1 - i) % 2 == 1) {
        ret[i] = cardNum[i] * 2; 
      } else {
        ret[i] = cardNum[i];  
      }
    }
    return ret;
  }

  public boolean verified() {
    int[] use = doublePattern();
    int sum = 0;
    for (int n : use) {
      if (n > 9) {
        sum += (n % 10) + (n / 10);
      } else {
        sum += n;
      }
    }
    return sum % 10 == 0;
  }

  /**
   * Nothing would need to be modified for this; 
   *  maybe a scramble method to scramble a digit for testing 
   * Just iterate through all possible values for the missing number;
   *  since it is guaranteed that it is possible to find the missing num, 
   *  there must be only one possible candidate
   */
  public void fillInMissingNumber() {
    for (int i = 0; i < cardNum.length; i++) {
      if (cardNum[i] == -1) {
        for (int j = 0; j < 10; j++) {
          cardNum[i] = j;
          if (verified()) return;
        }
        cardNum[i] = -1;
      }
    }
  }

  // tester
  public static void main(String[] args) {
    CardNumber num = new CardNumber("4523461827752712");
    num.scramble(2); // obscure the 3rd num

    //int[] out = num.doublePattern();
    //for (int i : out) {
    //  System.out.print(i + " ");
    //}

    for (int n : num.getNum()) {
      System.out.print(n + " ");
    } System.out.println();

    num.fillInMissingNumber();
    for (int n : num.getNum()) {
      System.out.print(n + " ");
    } System.out.println();

    System.out.println(num.verified());
  }
}
