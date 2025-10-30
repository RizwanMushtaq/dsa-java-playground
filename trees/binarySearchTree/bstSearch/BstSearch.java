package trees.binarySearchTree.bstSearch;

public class BstSearch {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(Node root, int target) {
    Node currNode = root;
    while (currNode != null) {
      if (currNode.value == target) return true;
      else if (currNode.value > target) currNode = currNode.left;
      else currNode = currNode.right;
    }
    return false;
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
    // Test 1
    Node root1 =
        createNode(
            5,
            createNode(2, null, createNode(4, null, null)),
            createNode(
                9, createNode(9, null, createNode(9, null, null)), createNode(11, null, null)));

    // Test 3: Single node
    Node root3 = createNode(1, null, null);

    // Test 4: Perfect BST
    Node root4 =
        createNode(
            4,
            createNode(2, createNode(1, null, null), createNode(3, null, null)),
            createNode(6, createNode(5, null, null), createNode(7, null, null)));

    // Test 5: Unbalanced BST
    Node root5 =
        createNode(
            5,
            createNode(
                3, createNode(2, createNode(1, null, null), null), createNode(4, null, null)),
            null);

    TestCase[] testCases = {
      new TestCase(root1, 6, false),
      new TestCase(root1, 9, true),
      new TestCase(root1, 3, false),
      new TestCase(root1, 4, true),
      new TestCase(null, 1, false), // Empty tree
      new TestCase(root3, 1, true), // Single node, target exists
      new TestCase(root3, 2, false), // Single node, target doesn't exist
      new TestCase(root4, 5, true), // Perfect BST, target exists
      new TestCase(root4, 8, false), // Perfect BST, target doesn't exist
      new TestCase(root5, 1, true), // Unbalanced BST, target exists at leaf
      new TestCase(root5, 5, true), // Unbalanced BST, target exists at root
      new TestCase(root5, 6, false) // Unbalanced BST, target doesn't exist
    };

    BstSearch solution = new BstSearch();
    for (TestCase testCase : testCases) {
      boolean actual = solution.solve(testCase.root(), testCase.target());
      if (actual != testCase.expected()) {
        throw new RuntimeException(
            String.format(
                "\nfind(root, %d): got: %b, want: %b\n",
                testCase.target(), actual, testCase.expected()));
      } else {
        System.out.print(".");
      }
    }
  }
}

record TestCase(Node root, int target, boolean expected) {}
