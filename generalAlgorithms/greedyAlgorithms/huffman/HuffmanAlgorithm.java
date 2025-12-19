package generalAlgorithms.greedyAlgorithms.huffman;

import java.util.*;

/*
 * In following the feeder string taken as All ASCII character from 32 to 126.
 * It takes the feeder and creates encoder and decoder Hashmaps.
 * So any message coded encoded using encode method and decoded using decode
 * method.
 *
 */
public class HuffmanAlgorithm {
  HashMap<Character, String> encoder;
  HashMap<String, Character> decoder;

  public HuffmanAlgorithm() {
    String feeder = getAsciiCharacters();
    System.out.println(feeder);
    HashMap<Character, Integer> freqMap = new HashMap<>();
    for (int i = 0; i < feeder.length(); i++) {
      Character currentCharacter = feeder.charAt(i);
      freqMap.computeIfPresent(currentCharacter, (character, cost) -> cost + 1);
      freqMap.putIfAbsent(currentCharacter, 1);
    }
    PriorityQueue<Node> minHeap = new PriorityQueue<>();
    Set<Map.Entry<Character, Integer>> entrySet = freqMap.entrySet();
    for (Map.Entry<Character, Integer> entry : entrySet) {
      minHeap.add(new Node(entry.getKey(), entry.getValue()));
    }
    while (minHeap.size() != 1) {
      Node first = minHeap.remove();
      Node second = minHeap.remove();
      Node newNode = new Node('\0', first.cost + second.cost);
      newNode.left = first;
      newNode.right = second;
      minHeap.add(newNode);
    }
    Node fullTree = minHeap.remove();
    this.encoder = new HashMap<>();
    this.decoder = new HashMap<>();
    this.initEncoderDecoder(fullTree, "");
  }

  private String getAsciiCharacters() {
    StringBuilder sb = new StringBuilder();
    for (char c = 32; c <= 126; c++) {
      sb.append(c);
    }
    return sb.toString();
  }

  private void initEncoderDecoder(Node node, String code) {
    if (node == null) return;
    if (node.left == null || node.right == null) {
      this.encoder.put(node.data, code);
      this.decoder.put(code, node.data);
    }
    initEncoderDecoder(node.left, code + "0");
    initEncoderDecoder(node.right, code + "1");
  }

  public String encode(String source) {
    StringBuilder resultBuilder = new StringBuilder();
    for (int i = 0; i < source.length(); i++) {
      resultBuilder.append(encoder.get(source.charAt(i)));
    }
    return resultBuilder.toString();
  }

  public String decode(String codedString) {
    StringBuilder resultBuilder = new StringBuilder();
    StringBuilder keyBuilder = new StringBuilder();
    for (int i = 0; i < codedString.length(); i++) {
      keyBuilder.append(codedString.charAt(i));
      if (decoder.containsKey(keyBuilder.toString())) {
        resultBuilder.append(decoder.get(keyBuilder.toString()));
        keyBuilder.setLength(0);
      }
    }
    return resultBuilder.toString();
  }

  private static class Node implements Comparable<Node> {
    Character data;
    int cost;
    Node left;
    Node right;

    public Node(Character data, int cost) {
      this.data = data;
      this.cost = cost;
      this.left = null;
      this.right = null;
    }

    @Override
    public int compareTo(Node o) {
      return this.cost - o.cost;
    }
  }
}
