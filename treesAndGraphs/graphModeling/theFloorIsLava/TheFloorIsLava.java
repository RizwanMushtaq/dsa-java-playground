package treesAndGraphs.graphModeling.theFloorIsLava;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TheFloorIsLava {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(List<List<Double>> furniture, double d) {
    int V = furniture.size();
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < V; i++) {
      for (int j = i + 1; j < V; j++) {
        if (distance(furniture.get(i), furniture.get(j)) <= d) {
          graph.get(i).add(j);
          graph.get(j).add(i);
        }
      }
    }
    System.out.println("graph: " + graph);
    Set<Integer> visited = new HashSet<>();
    visited.add(0);
    visit(graph, visited, 0);
    System.out.println("graph: " + graph + " visited: " + visited);
    return visited.contains(V - 1);
  }

  private void visit(List<List<Integer>> graph, Set<Integer> visited, int node) {
    for (int nbr : graph.get(node)) {
      if (!visited.contains(nbr)) {
        visited.add(nbr);
        visit(graph, visited, nbr);
      }
    }
  }

  private double distance(List<Double> furniture1, List<Double> furniture2) {
    double xMin1 = furniture1.get(0);
    double yMin1 = furniture1.get(1);
    double xMax1 = furniture1.get(2);
    double yMax1 = furniture1.get(3);

    double xMin2 = furniture2.get(0);
    double yMin2 = furniture2.get(1);
    double xMax2 = furniture2.get(2);
    double yMax2 = furniture2.get(3);

    double xGap = segmentDistance(xMin1, xMax1, xMin2, xMax2);
    double yGap = segmentDistance(yMin1, yMax1, yMin2, yMax2);

    //    System.out.println("xGap: " + xGap + " yGap: " + yGap + furniture1 + furniture2);

    if (xGap == 0) {
      return yGap;
    } else if (yGap == 0) {
      return xGap;
    } else {
      return Math.sqrt(xGap * xGap + yGap * yGap);
    }
  }

  private double segmentDistance(double min1, double max1, double min2, double max2) {
    return Math.max(0, Math.abs(Math.max(min1, min2) - Math.max(max1, max2)));
  }
}

record TestCase(List<List<Double>> furniture, double d, boolean want) {}

class RunTests {
  public void runTests() {
    TestCase[] tests = {
      // Example 1 from the book:
      new TestCase(
          List.of(
              List.of(1.0, 1.0, 9.0, 5.0),
              List.of(12.0, 9.0, 20.0, 13.0),
              List.of(16.0, 2.0, 22.0, 7.0),
              List.of(24.0, 9.0, 26.0, 11.0),
              List.of(29.0, 1.0, 31.0, 5.0)),
          5.0,
          true),
      //       Example 2 from the book:
      new TestCase(
          List.of(
              List.of(1.0, 1.0, 9.0, 5.0),
              List.of(12.0, 9.0, 20.0, 13.0),
              List.of(16.0, 2.0, 22.0, 7.0),
              List.of(24.0, 9.0, 26.0, 11.0),
              List.of(29.0, 1.0, 31.0, 5.0)),
          4.0,
          false),
      new TestCase(
          List.of(
              List.of(0.0, 0.0, 1.0, 1.0),
              List.of(1.0, 1.0, 2.0, 2.0),
              List.of(2.0, 2.0, 3.0, 3.0),
              List.of(3.0, 3.0, 4.0, 4.0),
              List.of(4.0, 4.0, 5.0, 5.0)),
          0.0,
          true),
      new TestCase(
          List.of(
              List.of(0.0, 0.0, 1.0, 1.0),
              List.of(1.0, 1.0, 2.0, 2.0),
              List.of(2.0, 2.0, 3.0, 3.0),
              List.of(3.0, 3.0, 4.0, 4.0),
              List.of(4.0, 4.0, 5.0, 5.0)),
          1.0,
          true),
      new TestCase(
          List.of(
              List.of(0.0, 0.0, 1.0, 1.0),
              List.of(1.0, 1.0, 2.0, 2.0),
              List.of(3.0, 3.0, 4.0, 4.0),
              List.of(4.0, 4.0, 5.0, 5.0)),
          1.0,
          false),
      new TestCase(
          List.of(
              List.of(0.0, 0.0, 1.0, 1.0),
              List.of(1.0, 1.0, 2.0, 2.0),
              List.of(3.0, 3.0, 4.0, 4.0),
              List.of(4.0, 4.0, 5.0, 5.0)),
          2.0,
          true),
      // Single piece of furniture
      new TestCase(List.of(List.of(0.0, 0.0, 1.0, 1.0)), 5.0, true),
      // Two pieces far apart
      new TestCase(
          List.of(List.of(0.0, 0.0, 1.0, 1.0), List.of(10.0, 10.0, 11.0, 11.0)), 5.0, false),
      // Two pieces just within reach
      new TestCase(List.of(List.of(0.0, 0.0, 1.0, 1.0), List.of(5.0, 5.0, 6.0, 6.0)), 5.7, true),
      // Two pieces just out of reach
      new TestCase(List.of(List.of(0.0, 0.0, 1.0, 1.0), List.of(5.0, 5.0, 6.0, 6.0)), 5.6, false),
      // Pieces in a line
      new TestCase(
          List.of(
              List.of(0.0, 0.0, 1.0, 1.0),
              List.of(1.0, 0.0, 2.0, 1.0),
              List.of(2.0, 0.0, 3.0, 1.0)),
          1.5,
          true)
    };

    TheFloorIsLava solution = new TheFloorIsLava();
    for (TestCase test : tests) {
      boolean got = solution.solve(test.furniture(), test.d());
      if (got != test.want()) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %f): got: %b, want: %b\n",
                test.furniture(), test.d(), got, test.want()));
      } else {
        System.out.print(".");
      }
    }
  }
}
