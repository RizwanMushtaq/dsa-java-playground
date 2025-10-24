package trees.alignedPath;

public class AlignedPath {
  private int result;

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  private int visit(Node node, int depth) {
    if (node == null) return 0;
    int leftChain = visit(node.left, depth + 1);
    int rightChain = visit(node.right, depth + 1);
    int currentChain = 0;
    if (node.value == depth) {
      currentChain = 1 + Math.max(leftChain, rightChain);
      result = Math.max(result, leftChain + rightChain + 1);
    }
    return currentChain;
  }

  public int solve(Node root) {
    result = 0;
    visit(root, 0);
    System.out.println("result : " + result);
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
    TestCase[] testCases = {
      // Test 1: Example from the book
      new TestCase(
          createNode(
              7,
              createNode(
                  1,
                  createNode(2, createNode(4, null, null), createNode(3, null, null)),
                  createNode(8, null, null)),
              createNode(
                  3, createNode(2, createNode(3, null, null), createNode(3, null, null)), null)),
          3),
      // Variation 1
      new TestCase(
          createNode(
              7,
              createNode(
                  1,
                  createNode(20, createNode(4, null, null), createNode(3, null, null)),
                  createNode(8, null, null)),
              createNode(
                  3, createNode(2, createNode(3, null, null), createNode(3, null, null)), null)),
          3),
      // Variation 2
      new TestCase(
          createNode(
              7,
              createNode(
                  1,
                  createNode(2, createNode(4, null, null), createNode(3, null, null)),
                  createNode(8, null, null)),
              createNode(
                  3, createNode(20, createNode(3, null, null), createNode(3, null, null)), null)),
          3),
      // Variation 3
      new TestCase(
          createNode(
              7,
              createNode(
                  1,
                  createNode(20, createNode(4, null, null), createNode(3, null, null)),
                  createNode(8, null, null)),
              createNode(
                  3, createNode(20, createNode(3, null, null), createNode(3, null, null)), null)),
          1),
      // Test 2: Empty tree
      new TestCase(null, 0),
      // Test 3: Single aligned node
      new TestCase(createNode(0, null, null), 1),
      // Test 4: Single unaligned node
      new TestCase(createNode(1, null, null), 0),
      // Test 5: Path through root
      new TestCase(createNode(0, createNode(1, null, null), createNode(1, null, null)), 3),
      // Test 6: No aligned nodes
      new TestCase(createNode(5, createNode(4, null, null), createNode(2, null, null)), 0),
      // Test 7
      new TestCase(
          createNode(
              0,
              createNode(1, createNode(2, null, null), createNode(2, null, null)),
              createNode(1, null, null)),
          4),
    };

    AlignedPath solution = new AlignedPath();
    for (TestCase testCase : testCases) {
      int actual = solution.solve(testCase.root());
      if (actual != testCase.expected()) {
        throw new RuntimeException(
            String.format(
                "\naligned_path(root): got: %d, want: %d\n", actual, testCase.expected()));
      }
    }
  }
}

record TestCase(Node root, int expected) {}
