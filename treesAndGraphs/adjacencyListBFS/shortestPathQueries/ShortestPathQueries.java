package treesAndGraphs.adjacencyListBFS.shortestPathQueries;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathQueries {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  List<List<Integer>> solve(List<List<Integer>> graph, int start, List<Integer> queries) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);
    Map<Integer, Integer> nodeToPredecessors = new HashMap<>();
    nodeToPredecessors.put(start, null);
    while (!queue.isEmpty()) {
      int node = queue.remove();
      for (int nbr : graph.get(node)) {
        if (!nodeToPredecessors.containsKey(nbr)) {
          nodeToPredecessors.put(nbr, node);
          queue.add(nbr);
        }
      }
    }
    List<List<Integer>> res = new ArrayList<>();
    for (int node : queries) {
      if (!nodeToPredecessors.containsKey(node)) {
        res.add(new ArrayList<>());
      } else {
        List<Integer> path = new ArrayList<>();
        path.add(node);
        while (path.getLast() != start) {
          path.add(nodeToPredecessors.get(path.getLast()));
        }
        List<Integer> reversePath = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
          reversePath.add(path.get(i));
        }
        res.add(reversePath);
      }
    }
    return res;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example
      {
        new int[][] {{1}, {0, 2, 5, 4}, {1, 4, 5}, {}, {5, 2, 1}, {1, 2, 4}},
        0,
        new int[] {1, 0, 3, 4},
        new int[][] {
          {0, 1},
          {0},
          {},
          {0, 1, 4}
        }
      },
      // Simple line graph
      {
        new int[][] {{1}, {0, 2}, {1}},
        0,
        new int[] {1, 2},
        new int[][] {
          {0, 1},
          {0, 1, 2}
        }
      },
      // Disconnected components
      {
        new int[][] {{1}, {0}, {3}, {2}},
        0,
        new int[] {1, 2, 3},
        new int[][] {
          {0, 1},
          {},
          {}
        }
      },
      // Complete graph
      {
        new int[][] {
          {1, 2},
          {0, 2},
          {0, 1}
        },
        0,
        new int[] {1, 2},
        new int[][] {
          {0, 1},
          {0, 2}
        }
      },
      // Single node
      {new int[][] {{}}, 0, new int[] {0}, new int[][] {{0}}},
      // Empty queries
      {new int[][] {{1}, {0}}, 0, new int[] {}, new int[][] {}}
    };

    ShortestPathQueries solution = new ShortestPathQueries();
    for (Object[] test : tests) {
      int[][] graphArray = (int[][]) test[0];
      int start = (int) test[1];
      int[] queriesArray = (int[]) test[2];
      int[][] wantArray = (int[][]) test[3];

      List<List<Integer>> graph =
          Arrays.stream(graphArray)
              .map(row -> Arrays.stream(row).boxed().collect(Collectors.toList()))
              .collect(Collectors.toList());
      List<Integer> queries = Arrays.stream(queriesArray).boxed().collect(Collectors.toList());
      List<List<Integer>> want =
          Arrays.stream(wantArray)
              .map(row -> Arrays.stream(row).boxed().collect(Collectors.toList()))
              .toList();

      List<List<Integer>> got = solution.solve(graph, start, queries);
      if (!got.equals(want)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %d, %s): got: %s, want: %s\n", graph, start, queries, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
