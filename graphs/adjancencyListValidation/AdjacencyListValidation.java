package graphs.adjancencyListValidation;

import java.util.HashSet;
import java.util.Set;

public class AdjacencyListValidation {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(int[][] graph) {
    int V = graph.length;
    for (int node = 0; node < V; node++) {
      Set<Integer> seen = new HashSet<>();
      for (int neighbor : graph[node]) {
        if (neighbor < 0 || neighbor >= V) return false; // Invalid node index
        if (neighbor == node) return false; // self-loop
        if (seen.contains(neighbor)) return false; // Parallel edge
        seen.add(neighbor);
      }
    }

    Set<String> edges = new HashSet<>();
    for (int node1 = 0; node1 < V; node1++) {
      for (int node2 : graph[node1]) {
        String edge = Math.min(node1, node2) + "," + Math.max(node1, node2);
        if (edges.contains(edge)) {
          edges.remove(edge);
        } else {
          edges.add(edge);
        }
      }
    }
    // If node1 appears in graph[node1], then node2 also appear in graph[node1]
    return edges.isEmpty(); //
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Valid cases
      {new int[][] {{1}, {0}}, true}, // Simple valid graph
      {new int[][] {{1, 2}, {0, 2}, {0, 1}}, true}, // Triangle
      // graph
      {new int[][] {}, true}, // Empty graph
      {new int[][] {{}}, true}, // Single isolated node

      // Invalid node index cases
      {new int[][] {{2}, {0}}, false}, // Node index too large
      {new int[][] {{-1}, {}}, false}, // Negative node index

      // Self-loop cases
      {new int[][] {{0}, {}}, false}, // Self loop
      {new int[][] {{1}, {1}}, false}, // Self loop in second node

      // Parallel edge cases
      {new int[][] {{1, 1}, {0, 0}}, false}, // Same edge twice from
      // first node
      {new int[][] {{1}, {0, 2, 0}, {1}}, false}, // Same edge twice
      // from second
      // node

      // Unmatched edge cases
      {new int[][] {{1}, {}}, false}, // Edge only in one direction
      {new int[][] {{1, 2}, {0}, {}}, false}, // Some edges missing
      // their pairs
      {new int[][] {{1}, {2}, {0}}, false}, // Cycle with unmatched
      // edges
    };

    for (Object[] test : tests) {
      int[][] graph = (int[][]) test[0];
      boolean want = (boolean) test[1];
      AdjacencyListValidation solution = new AdjacencyListValidation();
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
