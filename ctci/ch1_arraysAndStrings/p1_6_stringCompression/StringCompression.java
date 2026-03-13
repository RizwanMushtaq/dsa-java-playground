package ctci.ch1_arraysAndStrings.p1_6_stringCompression;

/**
 * Implement a method to to perform basic string compression using the counts of repeated
 * characters. For example, the string aabcccccaaa would become a2b1c5a3. If the compressed string
 * would not become smaller than the original string, your method should return the original string.
 * You can assume the string has only suppercase and lowercase letters (a-z).
 */
public class StringCompression {
  public static void main() {
    System.out.println("string compressed");
    StringCompression sc = new StringCompression();
    System.out.println(sc.solve("aabcccccaaa"));
    System.out.println(sc.solve("abca"));
  }

  /**
   * The runtime for this solution will be O(n) where n is length of string.
   *
   * <p>The space complexity will be O(size of compressed) -> size of string builder
   */
  String solve(String s) {
    int currentCount = 0;
    StringBuilder compressed = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      currentCount++;
      if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
        compressed.append(s.charAt(i));
        compressed.append(currentCount);
        currentCount = 0;
      }
    }
    return compressed.toString().length() < s.length() ? compressed.toString() : s;
  }
}
