/**
 * TreeUtilTester.java
 * @author Boxuan Shan
 * @version 11012024
 */
public class TreeUtilTester {
  /**
   * main method of tester class
   * @param args cmd-line args
   */
  public static void main(String[] args) {
    TreeNode root = new TreeNode("A");

    root.setLeft(new TreeNode("B"));
    root.setRight(new TreeNode("C"));

    root.getLeft().setLeft(new TreeNode("D"));
    root.getLeft().setRight(new TreeNode("E"));
    root.getRight().setLeft(new TreeNode("F"));
    root.getRight().setRight(new TreeNode("G"));

    /*
     *           A        
     *         /  \
     *        B    C
     *       / \  / \ 
     *     [D] E F  G
     */

    System.out.println("leftmost node: " + TreeUtil.leftMost(root));
  }
}
