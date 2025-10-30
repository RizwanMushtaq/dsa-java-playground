package trees.binarySearchTree.bstKthElement;

public class BstKthElement {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int solve(Node root, int k) {
    State state = new State();
    visit(root, k, state);
    return state.result;
  }

  private void visit(Node node, int k, State state) {
    if (node == null || state.result != -1) return;
    visit(node.left, k, state);
    if (state.steps == k) {
      state.result = node.value;
    }
    state.steps++;
    visit(node.right, k, state);
  }

  private static class State {
    int steps;
    int result;

    State() {
      steps = 0;
      result = -1;
    }
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
  private static Node createNode(int value, Node left, Node right) {
    Node node = new Node(value);
    node.left = left;
    node.right = right;
    return node;
  }

  public void runTests() {
    Node root =
        createNode(
            5,
            createNode(2, createNode(1, null, null), createNode(4, null, null)),
            createNode(8, createNode(6, null, null), createNode(9, null, null)));

    TestCase[] testCases = {
      new TestCase(
          createNode(
              5,
              createNode(2, null, createNode(4, null, null)),
              createNode(9, createNode(9, null, null), createNode(11, null, null))),
          4,
          9),
      new TestCase(createNode(1, null, null), 0, 1), // Single node
      new TestCase(root, 0, 1),
      new TestCase(root, 1, 2),
      new TestCase(root, 2, 4),
      new TestCase(root, 3, 5),
      new TestCase(root, 4, 6),
      new TestCase(root, 5, 8),
      new TestCase(root, 6, 9),
    };

    BstKthElement solution = new BstKthElement();
    for (TestCase testCase : testCases) {
      int actual = solution.solve(testCase.root(), testCase.k());
      if (actual != testCase.expected()) {
        throw new RuntimeException(
            String.format(
                "\nkth_element(root, %d): got: %d, want: %d\n",
                testCase.k(), actual, testCase.expected()));
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, int k, int expected) {}
