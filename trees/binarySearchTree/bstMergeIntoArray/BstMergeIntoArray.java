package trees.binarySearchTree.bstMergeIntoArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BstMergeIntoArray {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  List<Integer> solve(Node root1, Node root2) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    inorderTraverse(root1, list1);
    inorderTraverse(root2, list2);

    List<Integer> result = new ArrayList<>();
    int p1 = 0, p2 = 0;
    while (p1 < list1.size() && p2 < list2.size()) {
      if (list1.get(p1) < list2.get(p2)) {
        result.add(list1.get(p1));
        p1++;
      } else {
        result.add(list2.get(p2));
        p2++;
      }
    }
    while (p1 < list1.size()) {
      result.add(list1.get(p1));
      p1++;
    }
    while (p2 < list2.size()) {
      result.add(list2.get(p2));
      p2++;
    }
    return result;
  }

  private void inorderTraverse(Node node, List<Integer> list) {
    if (node == null) return;
    inorderTraverse(node.left, list);
    list.add(node.value);
    inorderTraverse(node.right, list);
  }
}

class Node {
  int value;
  Node left, right;

  Node(int value) {
    this.value = value;
    left = right = null;
  }
}

class RunTests {
  private Node createNode(int value) {
    return new Node(value);
  }

  private Node createNode(int value, Node left, Node right) {
    Node node = new Node(value);
    node.left = left;
    node.right = right;
    return node;
  }

  public void runTests() {
    // Create test trees
    Node root1 =
        createNode(
            5, createNode(2, null, createNode(4)), createNode(9, createNode(9), createNode(11)));

    Node root2 =
        createNode(
            3, createNode(2, createNode(1), null), createNode(7, createNode(6), createNode(8)));

    Node root3 = createNode(2, createNode(2), createNode(2));

    Node root4 = createNode(2, createNode(2), createNode(2));

    TestCase[] testCases = {
      // Example 1 from the book
      new TestCase(root1, root2, Arrays.asList(1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 9, 11)),
      // Example 2 from the book
      new TestCase(root3, root4, Arrays.asList(2, 2, 2, 2, 2, 2)),
      // Edge cases
      new TestCase(null, null, Collections.emptyList()),
      new TestCase(createNode(1), null, List.of(1)),
      new TestCase(null, createNode(1), List.of(1)),
      new TestCase(createNode(1), createNode(2), Arrays.asList(1, 2)),
    };

    BstMergeIntoArray solution = new BstMergeIntoArray();
    for (TestCase testCase : testCases) {
      List<Integer> actual = solution.solve(testCase.root1(), testCase.root2());
      if (!actual.equals(testCase.expected())) {
        throw new RuntimeException(
            String.format(
                "\nmerge_into_array(tree1, tree2): got: %s, want: %s\n",
                actual, testCase.expected()));
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root1, Node root2, List<Integer> expected) {
  TestCase(Node root1, Node root2, List<Integer> expected) {
    this.root1 = root1;
    this.root2 = root2;
    this.expected = List.copyOf(expected);
  }
}
