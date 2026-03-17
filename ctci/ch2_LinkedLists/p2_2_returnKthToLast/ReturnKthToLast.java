package ctci.ch2_LinkedLists.p2_2_returnKthToLast;

import java.util.Objects;

/** Implement an algorithm to find the kth to the last element of a singly linked list. */
public class ReturnKthToLast {
  public static void main() {
    Node head = new Node(5);
    head.next = new Node(10);
    head.next.next = new Node(15);
    head.next.next.next = new Node(51);
    head.next.next.next.next = new Node(2);

    ReturnKthToLast returnKthToLast = new ReturnKthToLast();
    System.out.println(returnKthToLast.solve(head, 2));
    System.out.println(returnKthToLast.solve(head, 3));
    System.out.println("********************************");
    System.out.println(returnKthToLast.solve2(head, 2));
    System.out.println(returnKthToLast.solve2(head, 3));
  }

  /**
   * Approach 1 -> get the size of the Linked list. desired location index -> Size -K
   *
   * <p>Run time -> O(n) Space -> O(1)
   *
   * <p>This solution is so trivial that Interviewer might expect something else.
   */
  Node solve(Node head, int k) {
    Node node = head;
    int size = 0;
    while (node != null) {
      size++;
      node = node.next;
    }
    int index = size - k;
    Node current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current;
  }

  /**
   * Approach 2 -> Using recursion -> first go to end of the linked list and then start counting
   * elements backward and get desired results.
   *
   * <p>runTime -> O(n) space -> O(n)
   */
  Node solve2(Node head, int k) {
    Index index = new Index();
    return helper(head, k, index);
  }

  Node helper(Node head, int k, Index index) {
    if (head == null) return null;
    Node node = helper(head.next, k, index);
    index.idx++;
    if (k == index.idx) {
      return head;
    }
    return node;
  }
}

class Index {
  public int idx = 0;
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
