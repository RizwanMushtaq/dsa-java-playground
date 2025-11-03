package graphs.stronglyConnectedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StronglyConnectedGraph {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(List<List<Integer>> graph) {
    int V = graph.size();
    Set<Integer> visited = new HashSet<>();
    visit(graph, visited, 0);
    if (visited.size() != V) {
      return false;
    }

    List<List<Integer>> reverseGraph = new ArrayList<>();
    for (int i = 0; i < V; i++) reverseGraph.add(new ArrayList<>());
    for (int node = 0; node < V; node++) {
      for (int neighbor : graph.get(node)) {
        reverseGraph.get(neighbor).add(node);
      }
    }
    System.out.println("graph: " + graph + " reverseGraph: " + reverseGraph);
    Set<Integer> reverseVisited = new HashSet<>();
    visit(reverseGraph, reverseVisited, 0);
    return reverseVisited.size() == V;
  }

  void visit(List<List<Integer>> graph, Set<Integer> visited, int node) {
    visited.add(node);
    for (int neighbor : graph.get(node)) {
      if (!visited.contains(neighbor)) {
        visit(graph, visited, neighbor);
      }
    }
  }
}

record TestCase(List<List<Integer>> graph, boolean want) {}

class RunTests {
  public void runTests() {
    TestCase[] tests = {
      new TestCase(List.of(List.of(1, 3), List.of(2), List.of(0), List.of(2)), true),
      // Example strongly connected
      new TestCase(List.of(List.of(1), List.of(2), List.of(0)), true),
      // Example not strongly connected
      new TestCase(List.of(List.of(1), List.of(2), List.of()), false),
      // Single node
      new TestCase(List.of(List.of()), true),
      // Two nodes, strongly connected
      new TestCase(List.of(List.of(1), List.of(0)), true),
      // Two nodes, not strongly connected
      new TestCase(List.of(List.of(1), List.of()), false),
      // Cycle of 4 nodes
      new TestCase(List.of(List.of(1), List.of(2), List.of(3), List.of(0)), true),
      // Almost cycle of 4 nodes, missing one edge
      new TestCase(List.of(List.of(1), List.of(2), List.of(3), List.of()), false),
      // Complete graph
      new TestCase(List.of(List.of(1, 2), List.of(0, 2), List.of(0, 1)), true)
    };

    StronglyConnectedGraph solution = new StronglyConnectedGraph();
    for (TestCase test : tests) {
      boolean got = solution.solve(test.graph());
      if (got != test.want()) {
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %b, want: %b\n", test.graph(), got, test.want()));
      } else {
        System.out.print(".");
      }
    }
  }
}
