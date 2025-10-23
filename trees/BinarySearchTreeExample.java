package trees;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinarySearchTreeExample {
  public static void main(String[] args) {
    BinarySearchTree bt = new BinarySearchTree();
    bt.add(100);
    bt.add(50);
    bt.add(40);
    bt.add(10);
    bt.add(25);
    bt.add(109);

    System.out.println("************************");
    bt.printLeafNodes(bt.root);

    System.out.println("************************");
    System.out.println(bt.containNode(100));

    System.out.println("************************");
    System.out.println(bt.root.data);
    System.out.println();
    bt.traverseInOrder(bt.root);
    System.out.println();
    bt.traversePreOrder(bt.root);
    System.out.println();
    bt.traversePostOrder(bt.root);
    System.out.println();
    bt.traverseLevelOrder(bt.root);
    System.out.println();

    //    bt.delete(100);
    //    bt.traverseInOrder(bt.root);
    //    System.out.println();
    //    System.out.println(bt.root.data);

    System.out.println("************************");
    System.out.println(bt.containNode(100));

    System.out.println("************************");
    bt.printLeafNodes(bt.root);

    System.out.println("************************");
    System.out.println(bt.getChildrenValues(bt.root));

    System.out.println("************************");
    System.out.println(bt.getGrandChildrenValues(bt.root));
  }
}

class BinarySearchTree {
  public TreeNode root;

  public BinarySearchTree() {
    this.root = null;
  }

  public void add(int value) {
    root = addRecursive(root, value);
  }

  private TreeNode addRecursive(TreeNode current, int value) {
    if (current == null) {
      return new TreeNode(value);
    }

    if (value < current.data) {
      current.left = addRecursive(current.left, value);
    } else if (value > current.data) {
      current.right = addRecursive(current.right, value);
    } else {
      // value already exists
      return current;
    }

    return current;
  }

  public boolean containNode(int value) {
    return containNodeRecursive(root, value);
  }

  private boolean containNodeRecursive(TreeNode current, int value) {
    if (current == null) {
      return false;
    }

    if (current.data == value) {
      return true;
    }

    return value < current.data
        ? containNodeRecursive(current.left, value)
        : containNodeRecursive(current.right, value);
  }

  public void delete(int value) {
    root = deleteRecursive(root, value);
  }

  private TreeNode deleteRecursive(TreeNode current, int value) {
    if (current == null) {
      return null;
    }

    if (value == current.data) {
      if (current.left == null && current.right == null) {
        return null;
      }

      if (current.right == null) {
        return current.left;
      }

      if (current.left == null) {
        return current.right;
      }

      int smallestValue = findSmallestValue(current.right);
      current.data = smallestValue;
      current.right = deleteRecursive(current.right, smallestValue);
      return current;
    }

    if (value < current.data) {
      current.left = deleteRecursive(current.left, value);
      return current;
    }
    current.right = deleteRecursive(current.right, value);
    return current;
  }

  private int findSmallestValue(TreeNode root) {
    return root.left == null ? root.data : findSmallestValue(root.left);
  }

  /**
   * Depth-first search is a type of traversal that goes deep as much as possible in every child
   * before exploring the next sibling.
   *
   * <p>There are several ways to perform a depth-first search: in-order, pre-order and post-order.
   *
   * <p>The in-order traversal consists of first visiting the left sub-tree, then the root node, and
   * finally the right sub-tree:
   *
   * @param node
   */
  public void traverseInOrder(TreeNode node) {
    if (node != null) {
      traverseInOrder(node.left);
      System.out.print(" " + node.data);
      traverseInOrder(node.right);
    }
  }

  /**
   * Pre-order traversal visits first the root node, then the left sub-tree and finally the right
   * sub-tree.
   *
   * @param node
   */
  public void traversePreOrder(TreeNode node) {
    if (node != null) {
      System.out.print(" " + node.data);
      traversePreOrder(node.left);
      traversePreOrder(node.right);
    }
  }

  /**
   * Post.order traversal visits the left sub-tree, the right sub-tree and the root node at the end.
   *
   * @param node
   */
  public void traversePostOrder(TreeNode node) {
    if (node != null) {
      traversePostOrder(node.left);
      traversePostOrder(node.right);
      System.out.print(" " + node.data);
    }
  }

  /**
   * Breadth-First Search - This is another common type of traversal that visits all the nodes of a
   * level before going to the next level.
   *
   * <p>This kind of traversal is also called level-order, and visits all the levels of the tree
   * starting from the root, and from left to right.
   *
   * <p>For the implementation, we’ll use a Queue to hold the nodes from each level in order. We’ll
   * extract each node from the list, print its values, then add its children to the queue:
   *
   * @param node
   */
  public void traverseLevelOrder(TreeNode node) {
    if (node == null) return;
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(node);
    while (!nodes.isEmpty()) {
      TreeNode n = nodes.remove();
      System.out.print(" " + n.data);
      if (n.left != null) {
        nodes.add(n.left);
      }
      if (n.right != null) {
        nodes.add(n.right);
      }
    }
  }

  public void printLeafNodes(TreeNode node) {
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

  public List<Integer> getChildrenValues(TreeNode node) {
    if (node == null) {
      return new ArrayList<>();
    }

    List<Integer> values = new ArrayList<>();
    if (node.left != null) values.add(node.left.data);
    if (node.right != null) values.add(node.right.data);

    return values;
  }

  public List<Integer> getGrandChildrenValues(TreeNode node) {
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
