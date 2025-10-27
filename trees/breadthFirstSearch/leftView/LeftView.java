package trees.breadthFirstSearch.leftView;

import java.util.*;

public class LeftView {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  Integer[] solve(Node root) {
    if (root == null) return new Integer[] {};
    Queue<Map.Entry<Node, Integer>> nodes = new LinkedList<>();
    nodes.add(Map.entry(root, 0));
    Map<Integer, Integer> levelToValue = new HashMap<>();

    while (!nodes.isEmpty()) {
      Map.Entry<Node, Integer> entry = nodes.remove();
      Node n = entry.getKey();
      int level = entry.getValue();
      levelToValue.putIfAbsent(level, n.value);
      if (n.left != null) nodes.add(Map.entry(n.left, level + 1));
      if (n.right != null) nodes.add(Map.entry(n.right, level + 1));
    }
    System.out.println(levelToValue.values());
    return levelToValue.values().toArray(new Integer[0]);
  }
}

class Node {
  public Integer value;
  public Node left, right;

  public Node(Integer value) {
    this.value = value;
    left = right = null;
  }
}

class RunTests {
  private static Node createNode(int value, Node left, Node right) {
    Node node = new Node(value);
    node.left = left;
    node.right = right;
    return node;
  }

  public void runTests() {
    TestCase[] testCases = {
      // Test 1
      new TestCase(
          createNode(
              1,
              createNode(2, createNode(4, null, null), createNode(5, null, null)),
              createNode(3, null, createNode(6, null, null))),
          new Integer[] {1, 2, 4}),
      // Test 2: Empty tree
      new TestCase(null, new Integer[] {}),
      // Test 3: Single node
      new TestCase(createNode(1, null, null), new Integer[] {1}),
      // Test 4: Only right children
      new TestCase(
          createNode(1, null, createNode(2, null, createNode(3, null, null))),
          new Integer[] {1, 2, 3}),
      // Test 5: Only left children
      new TestCase(
          createNode(1, createNode(2, createNode(3, null, null), null), null),
          new Integer[] {1, 2, 3}),
      // Test 6: Example from the book
      new TestCase(
          createNode(
              5,
              createNode(2, null, createNode(6, null, null)),
              createNode(
                  9, createNode(9, null, createNode(1, null, null)), createNode(8, null, null))),
          new Integer[] {5, 2, 6, 1}),
    };

    LeftView solution = new LeftView();
    for (TestCase testCase : testCases) {
      Integer[] actual = solution.solve(testCase.root());
      if (!Arrays.equals(actual, testCase.expected())) {
        throw new RuntimeException(
            "\nleft_view(tree): got: "
                + Arrays.toString(actual)
                + ", want: "
                + Arrays.toString(testCase.expected())
                + "\n");
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, Integer[] expected) {}
