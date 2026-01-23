package treesAndGraphs.ctci.minimalTree;

/**
 * CTCI Book -> 4.2 - Minimum Tree
 *
 * <p>Given a sorted (increasing order) array with unique integer elements, write an algorithm to
 * create a binary search tree with minimal height.
 */
public class MinimalTree {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    Node n = new MinimalTree().solve(arr);
    printInOrderTraversal(n);
  }

  private static void printInOrderTraversal(Node n) {
    if (n == null) return;
    printInOrderTraversal(n.left);
    System.out.println(n.id + " ");
    printInOrderTraversal(n.right);
  }

  private Node solve(int[] arr) {
    return visit(arr, 0, arr.length - 1);
  }

  private Node visit(int[] arr, int start, int end) {
    if (start > end) return null;
    int mid = start + (end - start) / 2;
    Node n = new Node(arr[mid]);
    n.left = visit(arr, start, mid - 1);
    n.right = visit(arr, mid + 1, end);
    return n;
  }

  static class Node {
    int id;
    Node left, right;

    public Node(int id) {
      this.id = id;
      left = null;
      right = null;
    }
  }
}
