package treesAndGraphs.adjacencyListDFS.graphPath;

import java.util.*;

public class GraphPath {
  private Map<Integer, Integer> predecessors;

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  List<Integer> solve(List<List<Integer>> graph, int node1, int node2) {
    predecessors = new HashMap<>();
    predecessors.put(node2, null);
    visit(graph, node2);
    if (!predecessors.containsKey(node1)) return new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    path.add(node1);
    while (path.getLast() != node2) {
      path.add(predecessors.get(path.getLast()));
    }
    System.out.println("result: " + path);
    return path;
  }

  void visit(List<List<Integer>> graph, int node) {
    for (int neighbour : graph.get(node)) {
      if (!predecessors.containsKey(neighbour)) {
        predecessors.put(neighbour, node);
        visit(graph, neighbour);
      }
    }
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example 1 from book - graph from Figure 8
      {
        new int[][] {{1}, {0, 2, 5, 4}, {1, 4, 5}, {}, {5, 2, 1}, {1, 2, 4}},
        0,
        4,
        new int[] {0, 1, 4}
      },
      // Example 2 from book - graph from Figure 8, no path exists
      {new int[][] {{1}, {0, 2, 5, 4}, {1, 4, 5}, {}, {5, 2, 1}, {1, 2, 4}}, 0, 3, new int[] {}},
      // Simple line graph
      {new int[][] {{1}, {0, 2}, {1}}, 0, 2, new int[] {0, 1, 2}},
      // Cycle graph
      {
        new int[][] {
          {1, 3},
          {0, 2},
          {1, 3},
          {0, 2}
        },
        0,
        2,
        new int[] {0, 1, 2}
      },
      // Disconnected graph
      {new int[][] {{1}, {0}, {3}, {2}}, 0, 2, new int[] {}},
      // Complete graph
      {
        new int[][] {
          {1, 2},
          {0, 2},
          {0, 1}
        },
        0,
        2,
        new int[] {0, 2}
      }
    };

    GraphPath solution = new GraphPath();
    for (Object[] test : tests) {
      int[][] adjList = (int[][]) test[0];
      int node1 = (int) test[1];
      int node2 = (int) test[2];
      int[] want = (int[]) test[3];

      // Convert adjacency list array to List<List<Integer>>
      List<List<Integer>> graph = new ArrayList<>();
      for (int[] neighbors : adjList) {
        List<Integer> nbrList = new ArrayList<>();
        for (int nbr : neighbors) {
          nbrList.add(nbr);
        }
        graph.add(nbrList);
      }

      List<Integer> got = solution.solve(graph, node1, node2);

      // Convert want array to List for comparison
      List<Integer> wantList = new ArrayList<>();
      for (int node : want) {
        wantList.add(node);
      }

      // For this problem, there can be multiple valid paths
      // So we need to verify:
      // 1. If want is empty, got should be empty
      // 2. If want is not empty:
      // - got should start with node1 and end with node2
      // - got should be a valid path in the graph
      // - got should not have duplicates
      if (wantList.isEmpty()) {
        if (!got.isEmpty()) {
          throw new RuntimeException(
              String.format(
                  "\nsolve(%s, %d, %d): got: %s, want empty path\n", graph, node1, node2, got));
        }
        continue;
      }

      if (got.getFirst() != node1 || got.getLast() != node2) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %d, %d): path %s should start with %d and end with %d\n",
                graph, node1, node2, got, node1, node2));
      }

      // Verify path is valid
      for (int i = 0; i < got.size() - 1; i++) {
        if (!graph.get(got.get(i)).contains(got.get(i + 1))) {
          throw new RuntimeException(
              String.format(
                  "\nsolve(%s, %d, %d): invalid path %s - no edge between %d and %d\n",
                  graph, node1, node2, got, got.get(i), got.get(i + 1)));
        }
      }

      // Verify no duplicates
      Set<Integer> seen = new HashSet<>(got);
      if (seen.size() != got.size()) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %d, %d): path %s contains duplicates\n", graph, node1, node2, got));
      }

      System.out.print(".");
    }
  }
}
