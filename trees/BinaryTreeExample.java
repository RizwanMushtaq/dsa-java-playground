package trees;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryTreeExample {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(20);
    root.left.left = new TreeNode(-5);
    root.left.right = new TreeNode(0);
    root.right.left = new TreeNode(32);
    root.right.right = new TreeNode(100);

    //    System.out.println(root.left.left.isLeaf());
    //    System.out.println(root.left.isLeaf());

    System.out.println("************************");
    printLeafNodes(root);

    //    System.out.println("************************");
    //    Arrays.stream(getChildrenValues(root)).forEach(item -> System.out.println(item));

    System.out.println("************************");
    System.out.println(getChildrenValues(root));

    System.out.println("************************");
    System.out.println(getGrandChildrenValues(root));
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

  public static List<Integer> getChildrenValues(TreeNode node) {
    if (node == null) {
      return new ArrayList<>();
    }

    List<Integer> values = new ArrayList<>();
    if (node.left != null) values.add(node.left.data);
    if (node.right != null) values.add(node.right.data);

    return values;
  }

  public static List<Integer> getGrandChildrenValues(TreeNode node) {
    if (node == null) {
      return new ArrayList<>();
    }

    List<Integer> leftChildren = new ArrayList<>();
    List<Integer> rightChildren = new ArrayList<>();
    if (node.left != null) leftChildren = getChildrenValues(node.left);
    if (node.right != null) rightChildren = getChildrenValues(node.right);

    return Stream.concat(leftChildren.stream(), rightChildren.stream())
        .collect(Collectors.toList());
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
