package graphs.adjacencyList;

import java.util.*;

public class AdjacencyListGraphExample {
  private final Map<Vertex, Set<Vertex>> adjVertices;

  private AdjacencyListGraphExample() {
    adjVertices = new HashMap<>();
  }

  public static void main(String[] args) {
    AdjacencyListGraphExample myGraph = new AdjacencyListGraphExample();
    myGraph.addVertex("a");
    myGraph.addVertex("b");
    myGraph.addVertex("c");
    myGraph.addEdge("a", "b");
    myGraph.addEdge("a", "c");
    myGraph.addEdge("b", "c");
    myGraph.printGraph();
    System.out.println("number of nodes are " + myGraph.getNumberOfNodes());
    System.out.println("number of edges are " + myGraph.getNumberOfEdges());
    System.out.println("degree of node a is " + myGraph.getDegreeOfNode("a"));
    System.out.println("degree of node r is " + myGraph.getDegreeOfNode("r"));
    myGraph.printNeighborsOfNode("c");
    myGraph.printNeighborsOfNode("r");
  }

  private void addVertex(String label) {
    adjVertices.putIfAbsent(new Vertex(label), new HashSet<>());
  }

  private void addEdge(String label1, String label2) {
    Vertex v1 = new Vertex(label1);
    Vertex v2 = new Vertex(label2);
    adjVertices.get(v1).add(v2);
    adjVertices.get(v2).add(v1);
  }

  private int getNumberOfNodes() {
    return adjVertices.size();
  }

  private int getNumberOfEdges() {
    int count = 0;
    for (Vertex vertex : adjVertices.keySet()) {
      count += adjVertices.get(vertex).size();
    }
    return count / 2;
  }

  private int getDegreeOfNode(String label) {
    Vertex vertex = new Vertex(label);
    if (!adjVertices.containsKey(vertex)) return -1;
    return adjVertices.get(vertex).size();
  }

  private void printNeighborsOfNode(String label) {
    Vertex vertex = new Vertex(label);
    if (!adjVertices.containsKey(vertex)) {
      System.out.println("node not found: " + vertex);
      return;
    }
    Set<Vertex> neighbors = adjVertices.get(vertex);
    System.out.println("neighbors are " + neighbors);
  }

  private void printGraph() {
    for (Vertex v : adjVertices.keySet()) {
      System.out.println(v + "-" + adjVertices.get(v));
    }
  }
}

class Vertex {
  String label;

  Vertex(String label) {
    this.label = label;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vertex vertex = (Vertex) o;
    return Objects.equals(label, vertex.label);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(label);
  }

  @Override
  public String toString() {
    return label;
  }
}
