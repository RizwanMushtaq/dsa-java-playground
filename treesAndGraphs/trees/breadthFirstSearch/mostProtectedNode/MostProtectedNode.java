package treesAndGraphs.trees.breadthFirstSearch.mostProtectedNode;

import java.util.*;

public class MostProtectedNode {
  private Map<Node, Integer> protection;
  private Map<Integer, Integer> nodesPerLevel;
  private Map<Node, Integer> nodeToIndexInLevel;

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  private int dfs(Node node, int depth) {
    if (node == null) return -1;
    int height = 1 + Math.max(dfs(node.left, depth + 1), dfs(node.right, depth + 1));
    protection.put(node, Math.min(protection.get(node), height));
    // Update protection with right counts
    protection.put(
        node,
        Math.min(
            protection.get(node), nodesPerLevel.get(depth) - nodeToIndexInLevel.get(node) - 1));
    return height;
  }

  int solve(Node root) {
    if (root == null) return 0;
    protection = new HashMap<>();
    nodesPerLevel = new HashMap<>();
    nodeToIndexInLevel = new HashMap<>();

    Queue<Map.Entry<Node, Integer>> queue = new LinkedList<>();
    queue.add(Map.entry(root, 0));
    while (!queue.isEmpty()) {
      Map.Entry<Node, Integer> entry = queue.remove();
      Node node = entry.getKey();
      Integer depth = entry.getValue();
      if (!protection.containsKey(node)) {
        protection.put(node, Integer.MAX_VALUE);
      }
      nodesPerLevel.merge(depth, 1, Integer::sum);
      nodeToIndexInLevel.put(node, nodesPerLevel.get(depth) - 1);
      protection.put(node, Math.min(protection.get(node), nodeToIndexInLevel.get(node)));
      if (node.left != null) queue.add(Map.entry(node.left, depth + 1));
      if (node.right != null) queue.add(Map.entry(node.right, depth + 1));
    }
    dfs(root, 0);
    System.out.println(protection.values().stream().max(Integer::compareTo).orElse(0));
    return protection.values().stream().max(Integer::compareTo).orElse(0);
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

  private static Node perfectTree(int height) {
    if (height == 1) {
      return createNode(1, null, null);
    }
    return createNode(1, perfectTree(height - 1), perfectTree(height - 1));
  }

  public void runTests() {
    // Example from the book
    Node root1 =
        createNode(
            1,
            createNode(
                2,
                createNode(
                    3,
                    createNode(
                        4,
                        createNode(5, createNode(6, null, null), null),
                        createNode(7, createNode(8, null, null), createNode(9, null, null))),
                    createNode(10, createNode(11, null, null), null)),
                createNode(
                    12,
                    createNode(
                        13,
                        createNode(14, createNode(15, null, null), createNode(16, null, null)),
                        null),
                    null)),
            createNode(
                17,
                createNode(
                    18,
                    createNode(19, createNode(20, createNode(21, null, null), null), null),
                    null),
                createNode(
                    22,
                    createNode(23, createNode(24, createNode(25, null, null), null), null),
                    null)));

    TestCase[] testCases = {
      new TestCase(root1, 2), // Book example (Example 1)
      new TestCase(createNode(1, null, null), 0), // Single node (Example 2)
      // Linear tree (Example 3)
      new TestCase(
          createNode(1, createNode(2, createNode(3, createNode(4, null, null), null), null), null),
          0),
      new TestCase(perfectTree(1), 0),
      new TestCase(perfectTree(2), 0),
      new TestCase(perfectTree(3), 0),
      new TestCase(perfectTree(4), 1),
      new TestCase(perfectTree(5), 1),
      new TestCase(perfectTree(6), 2),
      new TestCase(perfectTree(7), 3),
      new TestCase(perfectTree(8), 3),
      new TestCase(perfectTree(9), 4),
    };

    MostProtectedNode solution = new MostProtectedNode();
    for (TestCase testCase : testCases) {
      int actual = solution.solve(testCase.root());
      if (actual != testCase.expected()) {
        throw new RuntimeException(
            String.format(
                "\nmost_protected_node(tree): got: %d, want: %d\n", actual, testCase.expected()));
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, int expected) {}
