/**
 * TreeUtil.java
 * @author Boxuan Shan
 * @version 11012024
 */
public class TreeUtil {
  /**
   * static object to get the value of the leftmost node of a given tree
   * @param root root of tree
   * @return value of leftmost node of given tree
   */
  public static Object leftMost(TreeNode root) {
    while (root.getLeft() != null) {
      root = root.getLeft();
    }
    return root.getVal();
  }
}
