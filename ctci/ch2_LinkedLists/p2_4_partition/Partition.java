package ctci.ch2_LinkedLists.p2_4_partition;

import java.util.Objects;

/**
 * Write code to partition a linked list around a value x, such that all nodes less than x come
 * before all node greater than or equal to x.
 *
 * <p>If x is contained within the list, the values of x only need to be after the elements less
 * than x.
 *
 * <p>The partition element x can appear anywhere in the right partition, it does not need to appear
 * between the left and right partitions.
 *
 * <p>e.g. 3->5->8->5->10->2->1 [partition = 5]--------- 3->1->2->10->5->5->8
 */
public class Partition {
  public static void main() {
    Node head = new Node(5);
    head.next = new Node(15);
    head.next.next = new Node(10);
    head.next.next.next = new Node(51);
    head.next.next.next.next = new Node(2);
    head.next.next.next.next.next = new Node(1);

    Partition partition = new Partition();
    System.out.println(partition.solve(head, 15));
    System.out.println(partition.solve(head, 5));
  }

  /**
   * The most intuitive way to solve this is to think of its as sorting items into two separate
   * bins:
   *
   * <p>BinA (less) : foll all nodes with val < x BinB (greater): for all nodes with val >=x
   *
   * <p>By traversing the original list once and attaching each node to its respective bin, we can
   * simply stitch the two bins together at the end.
   *
   * <p>Runtime -> O(n) Space -> O(1)
   */
  Node solve(Node head, int x) {
    Node lessDummy = new Node(0), greaterDummy = new Node(0);
    Node less = lessDummy, greater = greaterDummy;
    while (head != null) {
      if (head.data < x) {
        less.next = head;
        less = less.next;
      } else {
        greater.next = head;
        greater = greater.next;
      }
      head = head.next;
    }
    greater.next = null;
    less.next = greaterDummy.next;
    return lessDummy.next;
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
