package trees.alignedChain;

public class LongestAlignedChain {
  private int result = 0;

  public static void main(String[] args) {
    new LongestAlignedChainTests().runTests();
  }

  public int visit(Node node, int depth) {
    if (node == null) return 0;
    int leftChain = visit(node.left, depth + 1);
    int rightChain = visit(node.right, depth + 1);
    int currentChain = 0;
    if (node.value == depth) {
      currentChain = 1 + Math.max(leftChain, rightChain);
      result = Math.max(result, currentChain);
    }
    return currentChain;
  }

  public int solve(Node root) {
    result = 0;
    visit(root, 0);
    return result;
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

class LongestAlignedChainTests {
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
    TestCase[] testCases = {
      new TestCase(
          createNode(
              7,
              createNode(
                  1,
                  createNode(2, createNode(4, null, null), createNode(3, null, null)),
                  createNode(8, null, null)),
              createNode(3, createNode(2, createNode(3, null, null), null), null)),
          3),
      new TestCase(
          createNode(
              0,
              createNode(
                  1, createNode(2, createNode(3, null, null), null), createNode(4, null, null)),
              createNode(5, null, null)),
          4),
      // Test 3: Empty tree
      new TestCase(null, 0),
      // Test 4: Single node aligned at root
      new TestCase(createNode(0, null, null), 1),
      // Test 5: Single node not aligned
      new TestCase(createNode(1, null, null), 0),
      // Test 6: Multiple valid chains, should return longest
      new TestCase(
          createNode(
              0,
              createNode(
                  1,
                  createNode(2, createNode(4, null, null), null),
                  createNode(2, createNode(3, null, null), null)),
              null),
          4),
      // Test 7: No aligned nodes
      new TestCase(
          createNode(
              5,
              createNode(4, createNode(3, null, null), createNode(3, null, null)),
              createNode(2, null, null)),
          0),
      // Test 8
      new TestCase(createNode(0, createNode(1, null, null), createNode(1, null, null)), 2),
    };

    LongestAlignedChain solution = new LongestAlignedChain();
    for (TestCase testCase : testCases) {
      int actual = solution.solve(testCase.root);
      if (actual != testCase.expected) {
        throw new RuntimeException(
            String.format("\naligned_chain(tree): got: %d, want: %d\n", actual, testCase.expected));
      } else {
        System.out.print(".");
      }
    }
  }
}

class TestCase {
  final Node root;
  final int expected;

  TestCase(Node root, int expected) {
    this.root = root;
    this.expected = expected;
  }
}
