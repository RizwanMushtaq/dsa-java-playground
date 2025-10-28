package trees.breadthFirstSearch.mostProlificLevel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MostProlificLevel {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  private Map<Integer, Integer> getLevelToNodes(Node root) {
    Queue<Map.Entry<Node, Integer>> nodes = new LinkedList<>();
    nodes.add(Map.entry(root, 0));
    Map<Integer, Integer> levelToNodes = new HashMap<>();
    while (!nodes.isEmpty()) {
      Map.Entry<Node, Integer> entry = nodes.remove();
      Node n = entry.getKey();
      Integer level = entry.getValue();
      levelToNodes.put(level, levelToNodes.getOrDefault(level, 0) + 1);
      if (n.left != null) nodes.add(Map.entry(n.left, level + 1));
      if (n.right != null) nodes.add(Map.entry(n.right, level + 1));
    }
    return levelToNodes;
  }

  int solve(Node root) {
    if (root == null) return -1;
    Map<Integer, Integer> levelToNodes = getLevelToNodes(root);
    int result = -1;
    double maxProlificness = -1;
    for (Map.Entry<Integer, Integer> entry : levelToNodes.entrySet()) {
      int level = entry.getKey();
      int noOfNodes = entry.getValue();
      if (!levelToNodes.containsKey(level + 1)) continue;
      double prolificness = (double) levelToNodes.get(level + 1) / noOfNodes;
      if (prolificness > maxProlificness || (prolificness == maxProlificness && level < result)) {
        maxProlificness = prolificness;
        result = level;
      }
    }
    System.out.println("level: " + result + " " + "prolificness: " + maxProlificness);
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
              5,
              createNode(2, null, createNode(6, null, null)),
              createNode(
                  9, createNode(9, null, createNode(1, null, null)), createNode(8, null, null))),
          0),
      // Test 2: Empty tree
      new TestCase(null, -1),
      // Test 3: Single node
      new TestCase(createNode(1, null, null), -1),
      // Test 4: Perfect binary tree
      new TestCase(
          createNode(
              1,
              createNode(2, createNode(4, null, null), createNode(5, null, null)),
              createNode(3, createNode(6, null, null), createNode(7, null, null))),
          0),
      // Test 5: Unbalanced tree
      new TestCase(
          createNode(
              1,
              createNode(
                  2,
                  createNode(4, createNode(8, null, null), createNode(9, null, null)),
                  createNode(5, null, null)),
              createNode(3, null, null)),
          0),
      // Test 6: Example from the book
      new TestCase(
          createNode(
              1,
              createNode(
                  2,
                  createNode(4, createNode(8, null, null), createNode(9, null, null)),
                  createNode(5, null, createNode(11, null, null))),
              null),
          1),
      // Test 7
      new TestCase(
          createNode(
              1,
              createNode(
                  2, createNode(4, createNode(8, null, null), createNode(9, null, null)), null),
              null),
          2),
    };

    MostProlificLevel solution = new MostProlificLevel();
    for (TestCase testCase : testCases) {
      int actual = solution.solve(testCase.root());
      if (actual != testCase.expected()) {
        throw new RuntimeException(
            String.format(
                "\nmost_prolific_level(tree): got: %d, want: %d\n", actual, testCase.expected()));
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, int expected) {}
