package trees.binarySearchTree.bstDuplicateDetection;

public class BstDuplicateDetection {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(Node root) {
    State state = new State();
    visit(root, state);
    return state.result;
  }

  private void visit(Node node, State state) {
    if (node == null || state.result) return;
    visit(node.left, state);
    if (node.value == state.preValue) {
      state.result = true;
    } else {
      state.preValue = node.value;
    }
    visit(node.right, state);
  }

  private static class State {
    int preValue;
    boolean result;

    State() {
      preValue = Integer.MIN_VALUE;
      result = false;
    }
  }
}

class Node {
  int value;
  Node left, right;

  public Node(int value) {
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
    // Example 1 - BST with duplicates
    Node root1 =
        createNode(
            5,
            createNode(2, null, createNode(4, null, null)),
            createNode(
                9, createNode(9, null, createNode(9, null, null)), createNode(11, null, null)));

    // Example 3 - single node
    Node root3 = createNode(1, null, null);

    // Example 4 - BST without duplicates
    Node root4 =
        createNode(
            5,
            createNode(2, createNode(1, null, null), createNode(4, null, null)),
            createNode(8, createNode(6, null, null), createNode(9, null, null)));

    TestCase[] testCases = {
      new TestCase(root1, true), // Has duplicates (9s)
      new TestCase(null, false), // Empty tree has no duplicates
      new TestCase(root3, false), // Single node has no duplicates
      new TestCase(root4, false) // No duplicates
    };

    BstDuplicateDetection solution = new BstDuplicateDetection();
    for (TestCase testCase : testCases) {
      boolean actual = solution.solve(testCase.root());
      if (actual != testCase.expected()) {
        throw new RuntimeException(
            String.format(
                "\nhas_duplicate(root): got: %b, want: %b\n", actual, testCase.expected()));
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, boolean expected) {}
