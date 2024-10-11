import java.util.Stack;

/**
 * Write a description of class StringUtil here.
 * 
 * @author Anu Datar
 * @author Boxuan Shan
 * @version 10/27/2017
 * @version 10062024
 */

public class Expressions
{
  // parenthesis matching : An expression is said to be balanced if
  // every opener has a corresponding closer, in the right order
  // {, [ or ( are the only types of brackets allowed
  // @param   expression containing operands operators 
  //          and any of the 3 supportedbrackets
  // @return  true is the parenthesis are balanced         
  //          false otherwise
  public static boolean matchParenthesis(String exp)
  {
    Stack<String> mem = new Stack<String>();

    for (int i = 0; i < exp.length(); i++) {
      String n = exp.substring(i, i+1);

      if (n.equals("(") || n.equals("[") || n.equals("{")) {
        mem.push(n);
      } else if (!mem.empty() && n.equals(")") && mem.peek().equals("(")) {
        mem.pop();
      } else if (!mem.empty() && n.equals("]") && mem.peek().equals("[")) {
        mem.pop();
      } else if (!mem.empty() && n.equals("}") && mem.peek().equals("{")) {
        mem.pop();
      }
    }
    return mem.empty();
  }


  // returns a string in postfix form 
  // if given an expression in infix form as a parameter
  // does this conversion using a Stack
  // @param expr valid expression in infix form
  // @return equivalent expression in postfix form
  public static String infixToPostfix(String exp)
  {
    Stack<String> mem = new Stack<String>();
    String retpf = "";

    for (int i = 0; i < exp.length(); i++) {
      String n = exp.substring(i, i+1);

      if (n.matches("1|2|3|4|5|6|7|8|9|0") || n.equals("(")) {
        while (i < exp.length() && exp.substring(i, i + 1).matches("1|2|3|4|5|6|7|8|9|0")) {
          retpf += exp.substring(i, i + 1);
          i++;
        }
        retpf += ' '; // Add space to separate operands
        i--; // Adjust the index because the for loop will increment it

      } else if (n.equals("*") || n.equals("/") || n.equals("%")) {
        if (mem.empty() || (!mem.peek().equals("*") && !mem.peek().equals("/") && !mem.peek().equals("%"))) {
          mem.push(n);
        } else {
          retpf += mem.pop();
          mem.push(n);
        }
      } else if (n.equals("+") || n.equals("-")) {
        if (mem.empty() || (!mem.peek().equals("*") && !mem.peek().equals("/") && !mem.peek().equals("%") && 
            !mem.peek().equals("+") && !mem.peek().equals("-"))) {
          mem.push(n);
        } else {
          retpf += mem.pop();
          mem.push(n);
        }
      }
    }

    while (!mem.empty()) {
      retpf += mem.pop();
    }
    return retpf;
  }

  public boolean isInt(String i)
  {
    try {
      Integer.parseInt(i);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  // returns the value of an expression in postfix form
  // does this computation using a Stack
  // @param expr valid expression in postfix form
  // @return value of the expression
  // @precondition postfix expression  
  //               contains numbers and operators + - * / and %
  //               and that operands and operators are separated by spaces
  public static double evalPostfix(String exp)
  {
    Stack<Integer> mem = new Stack<Integer>();
    for (int i = 0; i < exp.length(); i++) {
      String n = exp.substring(i, i+1);
      if (n.matches("1|2|3|4|5|6|7|8|9|0")) {
        while (i < exp.length() && exp.substring(i, i + 1).matches("1|2|3|4|5|6|7|8|9|0")) {
          n += exp.substring(i, i + 1);
          i++;
        }
        i--; // Adjust the index because the for loop will increment it
        n = n.substring(1);
        mem.push(Integer.parseInt(n));
      } else if (n.equals("+")) {
        int N = mem.pop();
        mem.push(mem.pop() + N);
      } else if (n.equals("-")) {
        int N = mem.pop();
        mem.push(mem.pop() - N);
      } else if (n.equals("*")) {
        int N = mem.pop();
        mem.push(mem.pop() * N);
      } else if (n.equals("/")) {
        int N = mem.pop();
        mem.push(mem.pop() / N);
      } else if (n.equals("%")) {
        int N = mem.pop();
        mem.push(mem.pop() % N);
      }
    }
    return mem.peek();
  }

  // Tester to check if infix to postfix and evaluate postfix work well
  public static void main(String[] args)
  {
    String exp = "20 * 40";
    test(exp, 800);

    exp = "3 + 4 * 5 - 6";
    test(exp, 17);

    exp = "5 % 2 + 3 * 2 - 4 / 2";
    test(exp, 5);   

    // test balanced expressions
    testBalanced("{ 2 + 3 } * ( 4 + 3 )", true);
    testBalanced("} 4 + 4 { * ( 4 + 3 )", false);
    testBalanced("[ [ [ ] ]", false);
    testBalanced("{ ( } )", false);
    testBalanced("( ( ( ) ) )", true);
  }

  public static void test(String expr, double expect)
  {
    String post = infixToPostfix(expr);        
    double val = evalPostfix(post);

    System.out.println("Infix: " + expr);
    System.out.println("Postfix: " + post);
    System.out.println("Value: " + val);
    if (val == expect)
    {
      System.out.println("** Success! Great Job **");
    }
    else
    {
      System.out.print("** Oops! Something went wrong. ");
      System.out.println("Check your postfix and eval methods **");
    }
  }

  public static void testBalanced(String ex, boolean expected)
  {
    boolean act = matchParenthesis(ex);
    if (act == expected)
      System.out.println("** Success!: matchParenthesis(" + ex + ") returned " + act);
    else
    {
      System.out.print("** Oops! Something went wrong check : matchParen(" + ex + ")");
      System.out.println(" returned " + act + " but should have returned " + expected);
    }
  }
  }
