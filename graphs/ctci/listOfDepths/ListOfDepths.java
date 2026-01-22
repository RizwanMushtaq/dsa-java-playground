package graphs.ctci.listOfDepths;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListOfDepths {
  public static void main(String[] args) {
    Node root = new Node(8);
    root.left = new Node(4);
    root.right = new Node(10);
    root.left.left = new Node(13);
    root.right.left = new Node(12);
    root.right.right = new Node(2);
    ListOfDepths listOfDepths = new ListOfDepths();
    ArrayList<LinkedList<Node>> result = listOfDepths.createLevelLinkedList(root);
    for (LinkedList<Node> list : result) {
      list.forEach(System.out::println);
      System.out.println("********************");
    }
  }

  ArrayList<LinkedList<Node>> createLevelLinkedList(Node root) {
    ArrayList<LinkedList<Node>> depthLists = new ArrayList<>();
    LinkedList<Node> current = new LinkedList<>();
    if (root != null) current.add(root);
    while (!current.isEmpty()) {
      depthLists.add(current);
      LinkedList<Node> parents = current;
      current = new LinkedList<>();
      for (Node parent : parents) {
        if (parent.left != null) {
          current.add(parent.left);
        }
        if (parent.right != null) {
          current.add(parent.right);
        }
      }
    }
    return depthLists;
  }

  static class Node {
    int value;
    Node left, right;

    public Node(int value) {
      this.value = value;
      left = right = null;
    }

    @Override
    public String toString() {
      return "Node{" + "value=" + value + ", left=" + left + ", right=" + right + '}';
    }
  }
}
