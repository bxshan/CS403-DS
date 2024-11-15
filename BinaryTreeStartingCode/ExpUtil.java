import java.util.*;

public class ExpUtil {
  public static String infixToPostfix(String exp) {
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
          retpf += " ";
          mem.push(n);
        }
      } else if (n.equals("+") || n.equals("-")) {
        if (mem.empty() || (!mem.peek().equals("*") && !mem.peek().equals("/") && !mem.peek().equals("%") && 
            !mem.peek().equals("+") && !mem.peek().equals("-"))) {
          mem.push(n);
        } else {
          retpf += mem.pop();
          retpf += " ";
          mem.push(n);
        }
      }
    }

    while (!mem.empty()) {
      retpf += mem.pop();
      retpf += " ";
    }
    return retpf;
  }

  public static String takeNextItem(String exp) {
    String retpf = "";
    if (exp.substring(0, 1).equals("+") || exp.substring(0, 1).equals("-") || exp.substring(0, 1).equals("*") ||
         exp.substring(0, 1).equals("/") || exp.substring(0, 1).equals("%")) {
      retpf += exp.substring(0, 1);
      exp = exp.substring(2);
      return retpf;
         }
    while (exp.substring(0, 1).matches("1|2|3|4|5|6|7|8|9|0")) {
      retpf += exp.substring(0, 1);
      exp = exp.substring(1);
    }

    exp = exp.substring(1);
    return retpf;
  }

  public static String removeNextItem(String exp) {
    if (exp.substring(0, 1).equals("+") || exp.substring(0, 1).equals("-") || exp.substring(0, 1).equals("*") ||
         exp.substring(0, 1).equals("/") || exp.substring(0, 1).equals("%")) {
      exp = exp.substring(2);
      return exp;
         }
    while (exp.substring(0, 1).matches("1|2|3|4|5|6|7|8|9|0")) {
      exp = exp.substring(1);
    }

    exp = exp.substring(1);
    return exp;
  }

  public static boolean isOp(String exp) {
    return exp.equals("+") || exp.equals("-") || exp.equals("*") || 
      exp.equals("/") || exp.equals("%"); 
  }

  public static TreeNode ihopethisworks(String exp) {
    ArrayList<String> ops = new ArrayList<String>();
    ArrayList<String> nums = new ArrayList<String>();

    exp += " ";

    while (!exp.equals("")) {
      if (isOp(takeNextItem(exp))) {
        ops.add(takeNextItem(exp));
      } else {
        nums.add(takeNextItem(exp));
      }
      exp = removeNextItem(exp);
    }

    System.out.println("!!!");
    for (String i: ops) {
      System.out.println(i);
    }

    TreeNode t = new TreeNode(ops.get(0));
    TreeNode ROOT = t;
    ops.remove(0);

    while (ops.size() > 0) {
      TreeNode n = new TreeNode(ops.get(0));
      t.setRight(n);
      t = n;
      ops.remove(0);
    }

    t = ROOT;

    while (nums.size() > 0) {
      if (nums.size() == 2) {
        t.setLeft(new TreeNode(nums.get(0)));
        t.setRight(new TreeNode(nums.get(1)));
        nums.remove(0);
        nums.remove(0);
      } else {
        TreeNode n = new TreeNode(nums.get(0));
        t.setLeft(n);
        t = t.getRight();
        nums.remove(0);
      }
    }

    return ROOT;
  }

  /**
   * fill a list with the values of a binary tree rooted at t using a 
   * post-order traversal with '$' values added to the list whenever
   * a null pointer is encountered
   * @param t the root of the tree
   * @param list the returned list of values in the tree
   */
  public static String getPO(TreeNode t)
  {
    if (t == null) {
      return " ";
    } else {
      return getPO(t.getLeft()) + getPO(t.getRight()) + String.valueOf(t.getValue());
    }
  }

  // returns the value of an expression in postfix form
  // does this computation using a Stack
  // @param expr valid expression in postfix form
  // @return value of the expression
  // @precondition postfix expression  
  //               contains numbers and operators + - * / and %
  //               and that operands and operators are separated by spaces
  public static int evalPostfix(String exp)
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
    return (int) mem.peek();
  }

  public static int eval(TreeNode t) {
    String postfix = "";
    postfix = getPO(t);
    System.out.println("---" + postfix);
    return evalPostfix(postfix);
  }

  public static void main(String[] args) {
    String in = "11 + 22 * 33";

    TreeNode expTree = ihopethisworks(in);

    TreeDisplay display = new TreeDisplay();
    display.displayTree(expTree);

    System.out.println("eval res " + eval(expTree));
  }
}
