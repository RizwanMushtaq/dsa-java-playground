package graphs.adjacencyListDFS.treeCheck;

import java.util.HashMap;
import java.util.Map;

public class TreeCheck {
  private final Map<Integer, Integer> predecessors;
  private boolean foundCycle;

  public TreeCheck() {
    predecessors = new HashMap<>();
    this.predecessors.put(0, -1);
    foundCycle = false;
  }

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(int[][] graph) {
    visit(graph, 0);
    boolean connected = predecessors.size() == graph.length;
    return connected && !foundCycle;
  }

  void visit(int[][] graph, int node) {
    if (foundCycle) return;
    for (int neighbor : graph[node]) {
      if (!predecessors.containsKey(neighbor)) {
        predecessors.put(neighbor, node);
        visit(graph, neighbor);
      } else if (neighbor != predecessors.get(node)) {
        foundCycle = true;
      }
    }
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example 1 from the book
      {new int[][] {{2}, {2, 5}, {0, 1, 3, 4}, {2}, {2}, {1}}, true},
      // Example 2 from the book
      {new int[][] {{2}, {5}, {0, 3}, {2}, {}, {1}}, false},
      // Example 3 from the book
      {new int[][] {{1}, {0, 2, 5}, {1, 3, 4}, {2}, {2, 5}, {1, 4}}, false},
      // Single node
      {new int[][] {{}}, true},
      // Two nodes connected
      {new int[][] {{1}, {0}}, true},
      // Two nodes disconnected
      {new int[][] {{}, {}}, false},
      // Line graph (valid tree)
      {new int[][] {{1}, {0, 2}, {1, 3}, {2}}, true},
      // Cycle
      {new int[][] {{1, 3}, {2, 0}, {3, 1}, {0, 2}}, false},
      // Complete graph K4 (not a tree)
      {new int[][] {{1, 2, 3}, {0, 2, 3}, {0, 1, 3}, {0, 1, 2}}, false},
      // Star graph
      {new int[][] {{1, 2, 3, 4}, {0}, {0}, {0}, {0}}, true},
    };

    for (Object[] test : tests) {
      int[][] graph = (int[][]) test[0];
      boolean want = (boolean) test[1];
      TreeCheck solution = new TreeCheck();
      boolean got = solution.solve(graph);
      if (got != want) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %b, want: %b\n",
                java.util.Arrays.deepToString(graph), got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
