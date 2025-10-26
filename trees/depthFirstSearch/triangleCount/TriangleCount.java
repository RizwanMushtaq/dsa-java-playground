package trees.depthFirstSearch.triangleCount;

public class TriangleCount {
  private int res;

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  private TreeSides visit(Node node) {
    if (node == null) return new TreeSides(0, 0);
    TreeSides left = visit(node.left);
    TreeSides right = visit(node.right);
    res += Math.min(left.leftSide, right.rightSide);
    return new TreeSides(left.leftSide + 1, right.rightSide + 1);
  }

  int solve(Node root) {
    res = 0;
    visit(root);
    System.out.println("result: " + res);
    return res;
  }

  private record TreeSides(int leftSide, int rightSide) {}
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
      // Example
      new TestCase(createNode(1, null, null), 0), // Single node
      new TestCase(null, 0), // Empty tree

      // Example from the book
      new TestCase(
          createNode(
              1,
              createNode(2, createNode(4, null, null), createNode(5, null, null)),
              createNode(3, createNode(6, null, null), createNode(7, null, null))),
          4),

      // No triangles - only left children
      new TestCase(createNode(1, createNode(2, createNode(3, null, null), null), null), 0),

      // No triangles - only right children
      new TestCase(createNode(1, null, createNode(2, null, createNode(3, null, null))), 0),

      // Single triangle
      new TestCase(createNode(1, createNode(2, null, null), createNode(3, null, null)), 1),
    };

    TriangleCount solution = new TriangleCount();
    for (TestCase testCase : testCases) {
      int actual = solution.solve(testCase.root());
      if (actual != testCase.expected()) {
        throw new RuntimeException(
            String.format(
                "\ntriangle_count(root): got: %d, want: %d\n", actual, testCase.expected()));
      }
    }
  }

  record TestCase(Node root, int expected) {}
}

class Node {
  public int value;
  public Node left, right;

  public Node(int value) {
    this.value = value;
    left = right = null;
  }
}
