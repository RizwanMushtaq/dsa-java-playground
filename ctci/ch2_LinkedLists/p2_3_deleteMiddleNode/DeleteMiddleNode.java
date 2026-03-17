package ctci.ch2_LinkedLists.p2_3_deleteMiddleNode;

import java.util.Objects;

/**
 * Implement an algorithm to delete the node in the middle of a singly linked list, given only
 * access to that node.
 */
class DeleteMiddleNode {
  public static void main() {
    Node head = new Node(5);
    head.next = new Node(10);
    head.next.next = new Node(15);
    head.next.next.next = new Node(51);
    head.next.next.next.next = new Node(2);
    Node node = head.next.next;
    System.out.println(head);
    DeleteMiddleNode deleteMiddleNode = new DeleteMiddleNode();
    deleteMiddleNode.solve(node);
    System.out.println(head);
  }

  /** The solution is simply to copy the data from the next node and to delete the next node. */
  void solve(Node middle) {
    Node next = middle.next;
    middle.data = next.data;
    middle.next = next.next;
  }
}

class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    next = null;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return data == node.data && Objects.equals(next, node.next);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, next);
  }

  @Override
  public String toString() {
    return "Node{" + "next=" + next + ", data=" + data + '}';
  }
}
