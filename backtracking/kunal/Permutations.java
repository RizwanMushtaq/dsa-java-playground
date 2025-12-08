package backtracking.kunal;

public class Permutations {
  public static void main(String[] args) {
    printPermutations("abc");
  }

  private static void printPermutations(String str) {
    printPermutationsHelper("", str);
  }

  private static void printPermutationsHelper(String p, String up) {
    if (up.isEmpty()) {
      System.out.println(p);
      return;
    }

    char ch = up.charAt(0);
    for (int i = 0; i <= p.length(); i++) {
      String first = p.substring(0, i);
      String second = p.substring(i, p.length());
      printPermutationsHelper(first + ch + second, up.substring(1));
    }
  }
}
