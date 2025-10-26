package trees.invertBinaryTree;

public class InvertBinaryTree {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  Node solve(Node root) {
    if (root == null) return null;
    Node temp = root.left;
    root.left = solve(root.right);
    root.right = solve(temp);
    return root;
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

  private static boolean sameValues(Node t1, Node t2) {
    if (t1 == null && t2 == null) {
      return true;
    }
    if (t1 == null || t2 == null) {
      return false;
    }
    return (t1.value.equals(t2.value)
        && sameValues(t1.left, t2.left)
        && sameValues(t1.right, t2.right));
  }

  public void runTests() {
    TestCase[] testCases = {
      // Test 1: Example from the book - tree with 4 triangles
      new TestCase(
          createNode(
              1,
              createNode(
                  6, createNode(4, null, createNode(5, null, null)), createNode(11, null, null)),
              createNode(7, createNode(2, null, createNode(9, null, null)), null)),
          createNode(
              1,
              createNode(7, null, createNode(2, createNode(9, null, null), null)),
              createNode(
                  6, createNode(11, null, null), createNode(4, createNode(5, null, null), null)))),
      // Test 2: Empty tree
      new TestCase(null, null),
      // Test 3: Single node
      new TestCase(createNode(1, null, null), createNode(1, null, null)),
      // Test 4: Left-heavy to right-heavy transformation
      new TestCase(
          createNode(1, createNode(2, createNode(3, null, null), null), null),
          createNode(1, null, createNode(2, null, createNode(3, null, null)))),
    };

    InvertBinaryTree solution = new InvertBinaryTree();
    for (TestCase testCase : testCases) {
      Node actual = solution.solve(testCase.root());
      if (!sameValues(actual, testCase.expected())) {
        throw new RuntimeException("\ninvert_binary_tree(tree): got != want\n");
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, Node expected) {}
