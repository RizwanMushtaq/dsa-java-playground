package greedyAlgorithms.huffman;

public class app {
  public static void main(String[] args) {
    HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();
    String encodedMessage = huffmanAlgorithm.encode("hello rizwan");
    System.out.println("encodedMessage = " + encodedMessage);
    String decodedMessage = huffmanAlgorithm.decode(encodedMessage);
    System.out.println("decodedMessage = " + decodedMessage);
  }
}
