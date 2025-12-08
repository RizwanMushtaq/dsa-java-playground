package backtracking.kunal;

public class StringSubsequence {
  public static void main(String[] args) {
    printSubsequence("abc");
    printSubsequence("ab");
  }

  private static void printSubsequence(String str) {
    printSubsequenceHelper("", str);
  }

  // p -> processed and up -> unprocessed
  private static void printSubsequenceHelper(String p, String up) {
    if (up.isEmpty()) {
      System.out.println(p);
      return;
    }
    printSubsequenceHelper(p + up.charAt(0), up.substring(1));
    printSubsequenceHelper(p, up.substring(1));
  }
}
