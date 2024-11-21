/**
 * BSTUtilities.java
 * @author Boxuan Shan
 * @version 11202024
 */
public abstract class BSTUtilities
{
  /**
   * Checks if a value of x should go left for a treenode t
   * @param t treenode to check
   * @param x value to compare
   * @return true if should left 
   */
  private static boolean mvL(TreeNode t, Comparable x) {
    return x.compareTo(t.getValue()) < 0;
  }

  /**
   * Checks if a value of x should go right for a treenode t
   * @param t treenode to check
   * @param x value to compare
   * @return true if should go right 
   */
  private static boolean mvR(TreeNode t, Comparable x) {
    return x.compareTo(t.getValue()) > 0;
  }

	//precondition:  t is a binary search tree in ascending order
	//postcondition: returns true if t contains the value x;
	//               otherwise, returns false
	public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
	{
    if (t == null) {
      return false;
    } else if (mvL(t, x)) {
      return contains(t.getLeft(), x, display);
    } else if (mvR(t, x)) {
      return contains(t.getRight(), x, display);
    } else {
      return true;
    }
	}



	//precondition:  t is a binary search tree in ascending order
	//postcondition: if t is empty, returns a new tree containing x;
	//               otherwise, returns t, with x having been inserted
	//               at the appropriate position to maintain the binary
	//               search tree property; x is ignored if it is a
	//               duplicate of an element already in t; only one new
	//               TreeNode is created in the course of the traversal
	public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
	{
    if (t == null) {
      return new TreeNode(x);
    } else if (mvL(t, x)) {
      t.setLeft(insert(t.getLeft(), x, display));
    } else if (mvR(t, x)) {
      t.setRight(insert(t.getRight(), x, display));
    } else {
      return t;
    }

    return t;
  }

	//precondition:  t is a binary search tree in ascending order
	//postcondition: returns a pointer to a binary search tree,
	//               in which the value at node t has been deleted
	//               (and no new TreeNodes have been created)
	private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
  {
    int s = (int) TreeUtil.leftmost(t.getRight()); // get inorder successor 
    t.setValue(s);
    return delete(t.getRight(), (Comparable) s, display);
  }

	//precondition:  t is a binary search tree in ascending order
	//postcondition: returns a pointer to a binary search tree,
	//               in which the value x has been deleted (if present)
	//               (and no new TreeNodes have been created)
	public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
	{
    if (t == null) {
      return t;
    } else if (mvL(t, x)) {
      t.setLeft(delete(t.getLeft(), x, display));
    } else if (mvR(t, x)) {
      t.setRight(delete(t.getRight(), x, display));
    } else { // found node to remove
      if (t.getLeft() != null && t.getRight() != null) { // case 0: 2 child nodes
        t.setRight(deleteNode(t, display));
      } else if (t.getLeft() == null) { // case 1: 1 child to right
        return t.getRight();
      } else if (t.getRight() == null) { // case 2: 1 child to left
        return t.getLeft();
      } else { // case 3: leaf node
        return null;
      }
    }

    return t;
	}

  /**
   * counts how many nodes are in a given range
   * @param t BST to count
   * @param l lower bound
   * @param h upper bound
   * @return number of nodes in [l, h]
   */
  public static int countNodesInRange(TreeNode t, int l, int h) {
    if (t == null) {
      return 0;
    }

    int v = (int) t.getValue();

    if (v < l) {
      return countNodesInRange(t.getRight(), l, h);
    } else if (v > h) {
      return countNodesInRange(t.getLeft(), l, h);
    } else {
      return 1 + countNodesInRange(t.getLeft(), l, h) + countNodesInRange(t.getRight(), l, h);
    }
  }

  /**
   * Checks if given tree is a valid BST
   * @param t tree to check
   * @return true if t is a valid BST
   */
  public static boolean isValidBST(TreeNode t) {
    if (t == null || t.getLeft() == null && t.getRight() == null) {
      return true;
    } else {
      return BSTHelper(t.getLeft(), (int) t.getValue(), 0) && BSTHelper(t.getRight(), (int) t.getValue(), 1);
    }
  }
  
  /**
   * Helper method for isValidBST, checks if a given subtree has nodes all smaller or bigger than a given value
   * @param t subtree to check
   * @param c number to compare to
   * @param d whether to check for greater than (0) or less than (1)
   */
  public static boolean BSTHelper(TreeNode t, int c, int d) {
    if (t == null) {
      return true;
    }
    if (t.getLeft() == null && t.getRight() == null) {
      int v = (int) t.getValue();
      if (d == 0) {
        return v < c; 
      } else {
        return v > c; 
      }
    }

    return BSTHelper(t.getLeft(), c, d) && BSTHelper(t.getRight(), c, d);
  }
}
