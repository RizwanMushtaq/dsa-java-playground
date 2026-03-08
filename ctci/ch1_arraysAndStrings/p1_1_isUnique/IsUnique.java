package ctci.ch1_arraysAndStrings.p1_1_isUnique;

/**
 * Implement an algorithm to determine if a string has all unique characters. What if you cannot use
 * additional data structures?
 */
public class IsUnique {
  public static void main() {
    IsUnique isUnique = new IsUnique();
    System.out.println(isUnique.solve("hello"));
    System.out.println(isUnique.solve("helo"));
    System.out.println("*************************");
    System.out.println(isUnique.solveUsingASCII("hello"));
    System.out.println(isUnique.solveUsingASCII("helo"));
  }

  /** Time Complexity -> O(n^2) Space Complexity -> O(1) */
  boolean solve(String str) {
    for (int i = 0; i < str.length(); i++) {
      for (int j = i + 1; j < str.length(); j++) {
        if (str.charAt(i) == str.charAt(j)) return false;
      }
    }
    return true;
  }

  /**
   * We can assume that string is an ASCII string. There are 128 unique characters in ASCII.
   *
   * <p>So you should ask interviewer if the string is ASCII or extended ASCII.
   *
   * <p>We can immediately return false if the string length exceeds the number of unique characters
   * in the alphabet.
   *
   * <p>Time Complexity -> O(n)
   *
   * <p>Space Complexity -> O(c) -> c is size of character set
   */
  boolean solveUsingASCII(String str) {
    if (str.length() > 128) return false;
    boolean[] char_set = new boolean[128];
    for (int i = 0; i < str.length(); i++) {
      if (char_set[str.charAt(i)]) {
        return false;
      }
      char_set[str.charAt(i)] = true;
    }
    return true;
  }
}
