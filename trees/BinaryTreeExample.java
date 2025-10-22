package trees;

public class BinaryTreeExample {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(20);
    root.left.left = new TreeNode(-5);
    root.left.right = new TreeNode(0);

    System.out.println(root.left.left.isLeaf());
    System.out.println(root.left.isLeaf());
    printLeafNodes(root);
  }

  public static void printLeafNodes(TreeNode node) {
    if (node == null) {
      return;
    }

    if (node.isLeaf()) {
      System.out.println(node.data + " ");
      return;
    }

    printLeafNodes(node.left);
    printLeafNodes(node.right);
  }
}

class TreeNode {
  int data;
  TreeNode left;
  TreeNode right;

  public TreeNode(int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }

  boolean isLeaf() {
    return this.left == null && this.right == null;
  }
}
