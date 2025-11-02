package graphs.traverse;

import java.util.ArrayList;
import java.util.List;

public class Traverse {
  public static void main(String[] args) {
    List<List<Integer>> graph =
        List.of(List.of(1, 3), List.of(0, 3), List.of(3), List.of(0, 1, 2, 4), List.of(3));

    List<Integer> result = new Traverse().dfs(graph, 0);
    System.out.println("graph: " + graph + " dfs: " + result);

    List<List<Integer>> graph2 =
        List.of(List.of(1, 2), List.of(0, 3, 4), List.of(0, 3), List.of(1, 2, 4), List.of(1, 3));

    List<Integer> result2 = new Traverse().dfs(graph2, 0);
    System.out.println("graph: " + graph2 + " dfs: " + result2);
  }

  private List<Integer> dfs(List<List<Integer>> graph, Integer v) {
    if (v == null) return new ArrayList<>();
    if (v < 0 || v >= graph.size()) return new ArrayList<>();
    List<Integer> visited = new ArrayList<>();
    visit(graph, v, visited);
    return visited;
  }

  private void visit(List<List<Integer>> graph, Integer v, List<Integer> visited) {
    visited.add(v);
    for (Integer neighbor : graph.get(v)) {
      if (!visited.contains(neighbor)) {
        visit(graph, neighbor, visited);
      }
    }
  }
}
