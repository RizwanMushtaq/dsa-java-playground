package ctci.ch1_arraysAndStrings.p1_2_checkPermutation;

import java.util.Arrays;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 *
 * <p>We should clarify first following questions:
 *
 * <p>If the permutation comparison case-sensitive? Is God is permutation of dog?
 *
 * <p>If white space is significant? Is "god " permutation of "dog"?
 *
 * <p>We will assume for this problem that the comparison is case-sensitive and whitespace is
 * significant.
 */
public class CheckPermutation {
  public static void main() {
    System.out.println("check permutation");
    CheckPermutation checkPermutation = new CheckPermutation();
    System.out.println(checkPermutation.solve("hello", "olleh"));
    System.out.println(checkPermutation.solve("ehloo", "helo"));
  }

  String sort(String str) {
    char[] charArray = str.toCharArray();
    Arrays.sort(charArray);
    return Arrays.toString(charArray);
  }

  /**
   * The Time Complexity for this depends in Arrays.sort() method which is on Average case O(n log
   * n).
   */
  boolean solve(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }
    return sort(s1).equals(sort(s2));
  }
}
