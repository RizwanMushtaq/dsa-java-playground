package graphs.adjacencyListDFS.hilliestConnectedComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HilliestConnectedComponent {
  Map<Integer, Integer> nodeToCC;
  List<List<Integer>> graph;

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  double solve(List<List<Integer>> graph, List<Double> heights) {
    this.graph = graph;
    nodeToCC = new HashMap<>();
    labelNodesWithCcIds();
    int V = graph.size();
    Map<Integer, Double> ccToElevationGainSum = new HashMap<>();
    Map<Integer, Integer> ccToNumEdges = new HashMap<>();

    for (int node = 0; node < V; node++) {
      int cc = nodeToCC.get(node);
      ccToElevationGainSum.putIfAbsent(cc, 0.0);
      ccToNumEdges.putIfAbsent(cc, 0);
      for (int nbr : graph.get(node)) {
        if (nbr > node) {
          ccToNumEdges.put(cc, ccToNumEdges.get(cc) + 1);
          ccToElevationGainSum.put(
              cc, ccToElevationGainSum.get(cc) + Math.abs(heights.get(node) - heights.get(nbr)));
        }
      }
    }

    double res = 0;
    for (int cc : ccToNumEdges.keySet()) {
      if (ccToNumEdges.get(cc) > 0) {
        res = Math.max(res, ccToElevationGainSum.get(cc) / ccToNumEdges.get(cc));
      }
    }
    return res;
  }

  void labelNodesWithCcIds() {
    int ccId = 0;
    for (int node = 0; node < graph.size(); node++) {
      if (!nodeToCC.containsKey(node)) {
        visit(node, ccId);
        ccId++;
      }
    }
  }

  void visit(int node, int ccId) {
    if (nodeToCC.containsKey(node)) {
      return;
    }
    nodeToCC.put(node, ccId);
    for (int nbr : graph.get(node)) {
      visit(nbr, ccId);
    }
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example
      {
        new int[][] {
          {1, 3},
          {0, 2},
          {1, 3},
          {0, 2}
        },
        new double[] {4.0, 1.0, 3.0, 2.0},
        2.0
      },
      // Single node component
      {new int[][] {{}}, new double[] {5.0}, 0.0},
      // Two disconnected components
      {new int[][] {{1}, {0}, {3}, {2}}, new double[] {1.5, 5.5, 0.0, 5.0}, 5.0},
      // All nodes same height
      {
        new int[][] {
          {1, 2},
          {0, 2},
          {0, 1}
        },
        new double[] {3.0, 3.0, 3.0},
        0.0
      },
      // Line graph
      {new int[][] {{1}, {0, 2}, {1}}, new double[] {1.0, 5.0, 2.0}, 3.5},
      // Complete graph
      {
        new int[][] {
          {1, 2, 3},
          {0, 2, 3},
          {0, 1, 3},
          {0, 1, 2}
        },
        new double[] {1.0, 4.0, 7.0, 10.0},
        5.0
      }
    };

    HilliestConnectedComponent solution = new HilliestConnectedComponent();
    for (Object[] test : tests) {
      int[][] adjList = (int[][]) test[0];
      Object heightsObj = test[1];
      double want = (double) test[2];

      // Convert adjacency list array to List<List<Integer>>
      List<List<Integer>> graph = new ArrayList<>();
      for (int[] neighbors : adjList) {
        List<Integer> nbrList = new ArrayList<>();
        for (int nbr : neighbors) {
          nbrList.add(nbr);
        }
        graph.add(nbrList);
      }

      // Convert heights array to List<Double>
      List<Double> heightsList = new ArrayList<>();
      if (heightsObj instanceof int[] heights) {
        for (int h : heights) {
          heightsList.add((double) h);
        }
      } else if (heightsObj instanceof double[] heights) {
        for (double h : heights) {
          heightsList.add(h);
        }
      }

      double got = solution.solve(graph, heightsList);
      if (Math.abs(got - want) >= 0.0001) {
        throw new RuntimeException(
            String.format("\nsolve(%s, %s): got: %f, want: %f\n", graph, heightsList, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
