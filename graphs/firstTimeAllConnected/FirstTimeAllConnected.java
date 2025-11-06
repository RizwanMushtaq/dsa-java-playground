package graphs.firstTimeAllConnected;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstTimeAllConnected {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int solve(int V, int[][] cables) {
    if (cables.length == 0) return -1;
    int l = 0, r = cables.length - 1;
    if (isBefore(r, V, cables)) {
      return -1;
    }
    while (r - l > 1) {
      int mid = l + (r - l) / 2;
      if (isBefore(mid, V, cables)) {
        l = mid;
      } else {
        r = mid;
      }
    }
    return r;
  }

  private boolean isBefore(int cableIndex, int V, int[][] cables) {
    // Build adjacency list representation using ArrayList
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      graph.add(new ArrayList<>());
    }

    // Fill Lists
    for (int i = 0; i <= cableIndex; i++) {
      int node1 = cables[i][0];
      int node2 = cables[i][1];
      graph.get(node1).add(node2);
      graph.get(node2).add(node1);
    }
    Set<Integer> visited = new HashSet<>();
    visited.add(0);
    visit(graph, visited, 0);
    return visited.size() < V;
  }

  void visit(List<List<Integer>> graph, Set<Integer> visited, int node) {
    for (int nbr : graph.get(node)) {
      if (!visited.contains(nbr)) {
        visited.add(nbr);
        visit(graph, visited, nbr);
      }
    }
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Case from picture - becomes connected after cables[2]
      {4, new int[][] {{0, 2}, {1, 3}, {0, 1}, {1, 2}}, 2},
      // Edge case - never gets fully connected
      {3, new int[][] {{0, 1}}, -1},
      // Edge case - gets connected with final cable
      {3, new int[][] {{0, 1}, {1, 2}}, 1},
      // Larger test case
      {5, new int[][] {{0, 1}, {2, 3}, {1, 2}, {3, 4}, {0, 4}}, 3},
      // Edge case - redundant cables don't affect result
      {4, new int[][] {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 0}}, 3},
      // No edges added
      {4, new int[][] {}, -1},
      // One edge added
      {4, new int[][] {{0, 1}}, -1}
    };

    FirstTimeAllConnected solution1 = new FirstTimeAllConnected();

    for (Object[] test : tests) {
      int V = (int) test[0];
      int[][] cables = (int[][]) test[1];
      int want = (int) test[2];

      int got1 = solution1.solve(V, cables);
      if (got1 != want) {
        throw new RuntimeException(
            String.format(
                "\nFirstTimeAllConnected.solve(%d, %s): got: %d, want: %d\n",
                V, java.util.Arrays.deepToString(cables), got1, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
