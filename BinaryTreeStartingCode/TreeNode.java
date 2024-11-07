/**
 * TreeNode.java
 * @author Boxuan Shan
 * @version 11012024
 */
public class TreeNode {
    public Object val;
    public TreeNode left;
    public TreeNode right;

    /**
     * Constructor for TreeNode
     * @param val value of node
     * @param left left child of node
     * @param right right child of node
     */
    public TreeNode(Object val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    /**
     * Alternate for TreeNode, sets left and right to null
     * @param val value of node
     */
    public TreeNode(Object val) {
      this(val, null, null);
    }
    
    /**
     * sets the value of node
     * @param val value to set
     */
    public void setValue(Object val) {
      this.val = val;
    }

    /**
     * gets the value of node
     * @return val value of node
     */
    public Object getValue() {
      return this.val;
    }

    /**
     * sets the left child of node
     * @param left left child to set to
     */
    public void setLeft(TreeNode left) {
      this.left = left;
    }

    /**
     * gets the left child of node
     * @return left left child of node
     */
    public TreeNode getLeft() {
      return this.left;
    }

    /**
     * sets the right child of node
     * @param right right child to set to
     */
    public void setRight(TreeNode right) {
      this.right = right;
    }

    /**
     * gets the right child of node
     * @return right right child of node
     */
    public TreeNode getRight() {
      return this.right;
    }
}
