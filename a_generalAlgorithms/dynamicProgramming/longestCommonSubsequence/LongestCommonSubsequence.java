package a_generalAlgorithms.dynamicProgramming.longestCommonSubsequence;

/**
 * Following is similar to Leetcode Problem 1143.
 *
 * <p>It about find the longest common substring from two strings. e.g. when we have "abcde" and
 * "ace" as substrings then longest common substring is "ace"
 */
public class LongestCommonSubsequence {
  public static void main(String[] args) {
    System.out.println(LCSLength("abcde", "ace"));
    System.out.println(LCSString("abcde", "ace"));
    System.out.println(LCSUsingDP("abcde", "ace"));
    System.out.println(LCSUsingDP("stone", "longest"));
  }

  private static int LCSLength(String x, String y) {
    int lengthX = x.length();
    int lengthY = y.length();

    if (lengthX == 0 || lengthY == 0) return 0;
    else if (x.charAt(lengthX - 1) == y.charAt(lengthY - 1))
      return LCSLength(x.substring(0, lengthX - 1), y.substring(0, lengthY - 1)) + 1;
    else
      return Math.max(
          LCSLength(x, y.substring(0, lengthY - 1)), LCSLength(x.substring(0, lengthX - 1), y));
  }

  /**
   * Using Recursion to solve the problem.
   *
   * <p>The time complexity of following solution: If String x length is m If String y length is n
   * and n < m -> O(2 raise to the power n)
   *
   * @param x String
   * @param y String
   * @return String
   */
  private static String LCSString(String x, String y) {
    int lengthX = x.length();
    int lengthY = y.length();
    if (lengthX == 0 || lengthY == 0) return "";
    if (x.charAt(lengthX - 1) == y.charAt(lengthY - 1)) {
      return LCSString(x.substring(0, lengthX - 1), y.substring(0, lengthY - 1))
          + x.charAt(lengthX - 1);
    }
    String subsequenceXString = LCSString(x, y.substring(0, lengthY - 1));
    String subsequenceYString = LCSString(x.substring(0, lengthX - 1), y);
    return (subsequenceXString.length() > subsequenceYString.length())
        ? subsequenceXString
        : subsequenceYString;
  }

  /**
   * Now Using DP to solve the problem.
   *
   * <p>The time complexity of following solution: If String x length is and If String y length is
   * n, time complexity is O(m * n)
   */
  private static String LCSUsingDP(String x, String y) {
    int lengthX = x.length();
    int lengthY = y.length();
    int[][] table = new int[lengthX + 1][lengthY + 1];
    for (int i = 0; i < x.length(); i++) {
      for (int j = 0; j < y.length(); j++) {
        if (x.charAt(i) == y.charAt(j)) {
          table[i + 1][j + 1] = table[i][j] + 1;
        } else {
          table[i + 1][j + 1] = Math.max(table[i][j + 1], table[i + 1][j]);
        }
      }
    }
    // read the string
    StringBuilder sb = new StringBuilder();
    for (int i = lengthX, j = lengthY; i != 0 && j != 0; ) {
      if (table[i][j] == table[i - 1][j]) i--;
      else if (table[i][j] == table[i][j - 1]) j--;
      else {
        assert x.charAt(i - 1) == y.charAt(j - 1);
        sb.append(x.charAt(i - 1));
        i--;
        j--;
      }
    }
    return sb.reverse().toString();
  }
}
