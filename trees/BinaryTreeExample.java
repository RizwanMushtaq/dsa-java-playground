package trees;

import java.util.*;

public class BinaryTreeExample {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(20);
    root.left.left = new TreeNode(-5);
    root.left.right = new TreeNode(0);

    System.out.println(root.left.left.isLeaf());
    System.out.println(root.left.isLeaf());

    System.out.println("************************");
    printLeafNodes(root);

    System.out.println("************************");
    Arrays.stream(getChildrenValues(root)).forEach(item -> System.out.println(item));

    System.out.println("************************");
    System.out.println(Arrays.toString(getChildrenValues(root)));
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

  public static int[] getChildrenValues(TreeNode node) {
    if (node == null) {
      return new int[] {};
    }

    List<Integer> values = new ArrayList<>();

    if (node.left != null) {
      values.add(node.left.data);
    }

    if (node.right != null) {
      values.add(node.right.data);
    }

    return values.stream().mapToInt(Integer::intValue).toArray();
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

  public boolean isLeaf() {
    return this.left == null && this.right == null;
  }
}
