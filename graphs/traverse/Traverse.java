package graphs.traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Traverse {
  public static void main(String[] args) {
    Traverse traverse = new Traverse();
    List<List<Integer>> graph =
        List.of(List.of(1, 3), List.of(0, 3), List.of(3), List.of(0, 1, 2, 4), List.of(3));
    System.out.println("graph: " + graph + " dfs: " + traverse.dfs(graph, 0));
    System.out.println("graph: " + graph + " bfs: " + traverse.bfs(graph, 0));

    List<List<Integer>> graph2 =
        List.of(List.of(1, 2), List.of(0, 3, 4), List.of(0, 3), List.of(1, 2, 4), List.of(1, 3));
    System.out.println("graph: " + graph2 + " bfs: " + traverse.bfs(graph2, 1));
    System.out.println("graph: " + graph2 + " dfs: " + traverse.dfs(graph2, 1));
  }

  private List<Integer> bfs(List<List<Integer>> graph, Integer v) {
    if (v == null) return new ArrayList<>();
    if (v < 0 || v >= graph.size()) return new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();

    List<Integer> visited = new ArrayList<>();
    queue.add(v);
    visited.add(v);
    while (!queue.isEmpty()) {
      int node = queue.remove();
      for (int nbr : graph.get(node)) {
        if (!visited.contains(nbr)) {
          visited.add(nbr);
          queue.add(nbr);
        }
      }
    }
    return visited;
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
