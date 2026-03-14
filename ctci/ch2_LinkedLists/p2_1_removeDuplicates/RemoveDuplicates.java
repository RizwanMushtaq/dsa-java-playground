package ctci.ch2_LinkedLists.p2_1_removeDuplicates;

import java.util.HashSet;
import java.util.Objects;

class RemoveDuplicates {
  public static void main() {
    Test test = new Test();
    test.test();
  }

  Node solveWithBuffer(Node head) {
    /*
    -> First implementation by me
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
    */
    // Alternative for Duplicate Removal (more standard "prev" pattern)
    Node curr = head;
    Node prev = null;
    HashSet<Integer> buffer = new HashSet<>();
    while (curr != null) {
      if (buffer.contains(curr.data)) {
        prev.next = curr.next;
      } else {
        buffer.add(curr.data);
        prev = curr;
      }
      curr = curr.next;
    }
    return head;
  }

  Node solveWithoutBuffer(Node head) {
    Node current = head;
    while (current != null) {
      Node runner = current;
      while (runner.next != null) {
        if (current.data == runner.next.data) {
          runner.next = runner.next.next;
        } else {
          runner = runner.next;
        }
      }
      current = current.next;
    }
    return head;
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

class TestData {
  Node head, result;

  TestData(Node head, Node result) {
    this.head = head;
    this.result = result;
  }

  @Override
  public String toString() {
    return "TestData{" + "head=" + head + ", result=" + result + '}';
  }
}

class Test {
  void test() {
    Node head = new Node(5);
    head.next = new Node(5);
    head.next.next = new Node(3);
    head.next.next.next = new Node(3);

    Node result = new Node(5);
    result.next = new Node(3);

    Node head2 = new Node(10);
    Node result2 = new Node(10);

    TestData[] testData = {new TestData(head, result), new TestData(head2, result2)};
    RemoveDuplicates removeDuplicates = new RemoveDuplicates();
    System.out.println("solution with buffer");
    for (TestData item : testData) {
      Node output = removeDuplicates.solveWithBuffer(item.head);
      if (areLLIdentical(output, item.result)) {
        System.out.println(".");
      } else {
        throw new RuntimeException("test failed" + item);
      }
    }
    System.out.println("solution without buffer");
    for (TestData item : testData) {
      Node output = removeDuplicates.solveWithoutBuffer(item.head);
      if (areLLIdentical(output, item.result)) {
        System.out.println(".");
      } else {
        throw new RuntimeException("test failed" + item);
      }
    }
  }

  boolean areLLIdentical(Node a, Node b) {
    return a.equals(b);
  }
}
