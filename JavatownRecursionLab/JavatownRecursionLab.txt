// @author Boxuan Shan 
// @version 09022024
// @description work for the JavaTown Recursion Lab

// @description FancyCalc class, 
//        with all the methods for the Lab
public class FancyCalc {

  // @description takes the power of a number
  // @param b base number to take exponent of
  // @param e power raised to
  // @return b raised to the power e, also b^e
  public pow(b, e) {
    if (e == 0) {
      return 1;
    } else {
      return b * this.pow(b, e - 1);
    }
  }

  // @description takes the square of a number
  // @param n number to square
  // @return n squared, also n^2
  private square(n) {
    return n * n;
  }

  // @description takes the power of a number, faster
  // @param b base number to take exponent of
  // @param e power raised to
  // @return b raised to the power e, also b^e
  public fastPow(b, e) {
    if (e == 0) {
      return 1;
    } 

    if (e % 2 == 0) {
      return this.square(this.fastPow(b, e/2));
    } 

    return b * this.fastPow(b, e-1);
  }

  // @description finds the greatest common divisor of two integers
  // @param a one of the two numbers to find gcd of
  // @param b one of the two numbers to find gcd of
  // @return the gcd of a and b
  public gcd(a, b) {
    if (b == 0) {
      return a;
    } else {
      return(this.gcd(b, a % b));
    }
  }

  // @description helper function to determine
  // whether a number is prime
  // @param n number to determine is prime or not
  // @param d variable to continuously check if n can evenly divide
  // @return true if n is prime
  private helpPrime(n, d) {
    if (n == d) {
      return true;
    }

    if (n % d == 0) {
      return false;
    }

    return this.helpPrime(n, d + 1);
  }

  // @description driver function to determine
  // whether a number is prime
  // @param n number to determine is prime or not
  // @return true if n is prime
  public isPrime(n) {
    return this.helpPrime(n, 2);
  }

  // @description helper function to find
  // the factorial of a number
  // @param n number to find the factorial of
  // @param res variable to continuously
  // store the increment results
  // @return the factorial of n
  private factHelp(n, res) {
    if (n == 0) {
      return res;
    } else {
      return this.factHelp(n - 1, res * n);
    }
  }

  // @description driver function to find
  // the factorial of a number
  // @param n number to find the factorial of
  // @return the factorial of n
  public fact(n) {
    return this.factHelp(n, 1);
  }

  // @description slow recursive function to
  // find the nth fibonacci number
  // @param n which fibonacci number to find
  // @return the nth fibonacci number
  public fib(n) {
    if (n == 1 || n == 2) {
      return 1;
    } else {
      return this.fib(n-1) + this.fib(n-2);
    }
  }

  // @description find the average of two numbers
  // @param n one of two numbers to find the average of
  // @param m one of two numbers to find the average of
  // @return the average of n and m
  private avg(n, m) {
    return (n + m) / 2;
  }

  // @description determines whether g and n/g are close enough
  // @param g the current guess for the sqrt
  // @param n the number to take the sqrt of
  // @return true if g is a good enough guess
  private shouldReturn(g, n) {
    return (g == n/g) || (g == n/g + 1) || (g == n/g - 1);
  }

  // @description helper function to find the sqrt of a number
  // @param g guess for the sqrt of n
  // @param n number to find the sqrt of
  // @return the sqrt of n
  private sqrtHelp(g, n) {
    if (this.shouldReturn(g, n)) {
      return g;
    } else {
      return(this.sqrtHelp(this.avg(g, n/g), n));
    }
  }

  // @description driver function to find the sqrt of a number
  // @param n number to find the sqrt of
  // @return the sqrt of n
  public sqrt(n) {
    return this.sqrtHelp(1, n);
  }

  // @description ackermann function, a much slower recursive function
  // @param n holds no significance, just an input
  // @param m holds no significance, just an input
  // @return ack(n, m)
  public ack(n, m) {
    if (n == 0) {
      return m + 1;
    }
    if ((n > 0) && (m == 0)) {
      return this.ack(n - 1, 1);
    }
    if ((n > 0) && (m > 0)) {
      return this.ack(n - 1, this.ack(n, m - 1));
    }
    return m + 1;
  }


  // @description a terminating recursive function without using if
  // @param n number of times to call itself
  // @return 0
  // public noIfTernary(n) {
  //   so javatown doesnt allow ternary...
  //   return (n == 0) ? 0 : this.noIfTernary(n-1);
  // }
}
