package treesAndGraphs.trees.breadthFirstSearch.zigZagOrder;

import java.util.*;

public class ZigZagOrder {

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  List<Integer> solve(Node root) {
    if (root == null) return Collections.emptyList();
    Queue<Map.Entry<Node, Integer>> nodes = new LinkedList<>();
    List<Integer> result = new ArrayList<>();
    List<Integer> currentLevel = new ArrayList<>();
    int curDepth = 0;

    nodes.add(Map.entry(root, 0));
    while (!nodes.isEmpty()) {
      Map.Entry<Node, Integer> entry = nodes.remove();
      Node node = entry.getKey();
      int depth = entry.getValue();

      if (depth > curDepth) {
        if (curDepth % 2 == 0) {
          result.addAll(currentLevel);
        } else {
          Collections.reverse(currentLevel);
          result.addAll(currentLevel);
        }
        currentLevel = new ArrayList<>();
        curDepth = depth;
      }

      currentLevel.add(node.value);
      if (node.left != null) nodes.add(Map.entry(node.left, depth + 1));
      if (node.right != null) nodes.add(Map.entry(node.right, depth + 1));
    }
    // add the last level
    if (curDepth % 2 == 0) {
      result.addAll(currentLevel);
    } else {
      Collections.reverse(currentLevel);
      result.addAll(currentLevel);
    }
    System.out.println(result);
    return result;
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
  private static Node createNode(int value) {
    return new Node(value);
  }

  private static Node createNode(int value, Node left, Node right) {
    Node node = new Node(value);
    node.left = left;
    node.right = right;
    return node;
  }

  public void runTests() {
    // Create test graphs.trees
    Node root1 =
        createNode(
            1,
            createNode(2, createNode(4, null, null), createNode(5, null, null)),
            createNode(3, createNode(6, null, null), createNode(7, null, null)));

    Node root4 =
        createNode(1, createNode(2, createNode(3, createNode(4, null, null), null), null), null);

    Node root5 =
        createNode(
            1,
            createNode(
                2,
                createNode(4, createNode(8, null, null), createNode(9, null, null)),
                createNode(5, createNode(10, null, null), createNode(11, null, null))),
            createNode(
                3,
                createNode(6, createNode(12, null, null), null),
                createNode(7, createNode(14, null, null), createNode(15, null, null))));

    TestCase[] testCases = {
      // Example 1 - basic tree
      new TestCase(root1, Arrays.asList(1, 3, 2, 4, 5, 6, 7)),
      // Example 2 - empty tree
      new TestCase(null, Collections.emptyList()),
      // Example 3 - single node
      new TestCase(createNode(1), List.of(1)),
      new TestCase(createNode(2), List.of(2)),
      // Example 4 - unbalanced tree
      new TestCase(root4, Arrays.asList(1, 2, 3, 4)),
      // Example 5 - complete binary tree
      new TestCase(root5, Arrays.asList(1, 3, 2, 4, 5, 6, 7, 15, 14, 12, 11, 10, 9, 8)),
    };

    ZigZagOrder solution = new ZigZagOrder();
    for (TestCase testCase : testCases) {
      List<Integer> actual = solution.solve(testCase.root());
      if (!actual.equals(testCase.expected())) {
        throw new RuntimeException(
            String.format(
                "\nzig_zag_order(tree): got: %s, want: %s\n", actual, testCase.expected()));
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, List<Integer> expected) {
  TestCase(Node root, List<Integer> expected) {
    this.root = root;
    this.expected = List.copyOf(expected);
  }
}
