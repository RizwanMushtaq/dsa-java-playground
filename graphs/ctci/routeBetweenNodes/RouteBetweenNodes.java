package graphs.ctci.routeBetweenNodes;

import java.util.*;

/**
 * CTCI Book -> 4.1 - Route Between Nodes
 *
 * <p>Given a directed Graph, design an algorithm to find out whether there is a route between two
 * nodes.
 */
public class RouteBetweenNodes {
  private boolean result = false;

  public static void main(String[] args) {
    Node node0 = new Node("0");
    Node node1 = new Node("1");
    Node node2 = new Node("2");
    Node node3 = new Node("3");
    node0.neighbours.add(node1);
    node1.neighbours.add(node2);
    node2.neighbours.add(node0);
    node3.neighbours.add(node2);

    System.out.println(new RouteBetweenNodes().solveDFS(node0, node2));
    System.out.println(new RouteBetweenNodes().solveBFS(node0, node3));
  }

  private boolean solveBFS(Node start, Node end) {
    if (start.equals(end)) return true;
    Queue<Node> queue = new LinkedList<>();
    queue.add(start);
    while (!queue.isEmpty()) {
      Node node = queue.remove();
      if (node.equals(end)) {
        return true;
      }
      node.visited = true;
      for (Node nbr : node.neighbours) {
        if (!nbr.visited) {
          queue.add(nbr);
        }
      }
    }
    return false;
  }

  private boolean solveDFS(Node start, Node end) {
    visit(start, end);
    return result;
  }

  private void visit(Node start, Node end) {
    if (start.equals(end)) {
      result = true;
    }
    start.visited = true;
    for (Node nbr : start.neighbours) {
      if (!nbr.visited) {
        visit(nbr, end);
      }
    }
  }

  static class Node {
    public String label;
    public boolean visited;
    public List<Node> neighbours;

    public Node(String label) {
      this.label = label;
      visited = false;
      neighbours = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return Objects.equals(label, node.label);
    }

    @Override
    public int hashCode() {
      return Objects.hash(label);
    }
  }
}
