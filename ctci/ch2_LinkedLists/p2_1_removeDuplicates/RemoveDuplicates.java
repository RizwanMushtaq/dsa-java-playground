package ctci.ch2_LinkedLists.p2_1_removeDuplicates;

import java.util.HashSet;

class RemoveDuplicates {
  public static void main() {
    System.out.println("remove duplicates");
    Node head = new Node(5);
    head.next = new Node(5);
    head.next.next = new Node(3);
    head.next.next.next = new Node(5);
    head.next.next.next.next = new Node(2);
    head.next.next.next.next.next = new Node(5);

    RemoveDuplicates rm = new RemoveDuplicates();
    rm.print(head);
    rm.solveWithoutBuffer(head);
    rm.print(head);

    Node head2 = new Node(5);
    head2.next = new Node(5);
    head2.next.next = new Node(3);
    head2.next.next.next = new Node(5);
    head2.next.next.next.next = new Node(2);
    head2.next.next.next.next.next = new Node(5);
    rm.print(head2);
    rm.solveWithBuffer(head2);
    rm.print(head2);
  }

  void solveWithBuffer(Node head) {
    if (head == null) return;
    Node n = head;
    HashSet<Integer> buffer = new HashSet<>();
    buffer.add(n.data);
    while (n.next != null) {
      if (buffer.contains(n.next.data)) {
        n.next = n.next.next;
        continue;
      }
      buffer.add(n.next.data);
      n = n.next;
    }
  }

  void solveWithoutBuffer(Node head) {
    for (Node current = head; current != null; current = current.next) {
      Node runner = current;
      while (runner.next != null) {
        if (current.data == runner.next.data) {
          runner.next = runner.next.next;
        } else {
          runner = runner.next;
        }
      }
    }
  }

  void print(Node head) {
    if (head == null) return;
    Node n = head;
    while (n.next != null) {
      System.out.print(n.data + "->");
      n = n.next;
    }
    System.out.println(n.data);
  }

  static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
      next = null;
    }
  }
}
