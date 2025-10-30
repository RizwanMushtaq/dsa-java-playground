package trees.binarySearchTree.bstValidation;

public class BstValidation {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(Node root) {
    State state = new State();
    visit(root, state);
    return state.result;
  }

  private void visit(Node node, State state) {
    if (node == null || !state.result) return;
    visit(node.left, state);
    if (node.value < state.preValue) {
      state.result = false;
    } else {
      state.preValue = node.value;
    }
    visit(node.right, state);
  }

  private class State {
    public int preValue;
    public boolean result;

    public State() {
      preValue = Integer.MIN_VALUE;
      result = true;
    }
  }
}

class Node {
  public int value;
  public Node left, right;

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
    // Example 1 - valid BST
    Node root1 =
        createNode(
            5,
            createNode(2, null, createNode(4, null, null)),
            createNode(
                9, createNode(9, null, createNode(9, null, null)), createNode(11, null, null)));

    // Example 3 - single node
    Node root3 = createNode(1, null, null);

    // Example 4 - invalid BST (right child smaller than parent)
    Node root4 = createNode(5, createNode(2, null, null), createNode(4, null, null));

    // Example 5 - invalid BST (left child larger than parent)
    Node root5 = createNode(5, createNode(6, null, null), createNode(7, null, null));

    TestCase[] testCases = {
      new TestCase(root1, true), // Valid BST
      new TestCase(null, true), // Empty tree is valid
      new TestCase(root3, true), // Single node is valid
      new TestCase(root4, false), // Invalid - right child smaller than parent
      new TestCase(root5, false) // Invalid - left child larger than parent
    };

    BstValidation solution = new BstValidation();
    for (TestCase testCase : testCases) {
      boolean actual = solution.solve(testCase.root());
      if (actual != testCase.expected()) {
        throw new RuntimeException(
            String.format("\nis_bst(root): got: %b, want: %b\n", actual, testCase.expected()));
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, boolean expected) {}
