package a_generalAlgorithms.dynamicProgramming.bctci.problem40point7;

import java.util.*;

/**
 * Given two strings, s1 and s2, return the length of the longest common subsequence that is common
 * to s1 and s2. A subsequence of a string s is a sequence of characters that appears in s in the
 * same relative order but not necessarily consecutively. The two strings consist of uppercase
 * English letters only.
 *
 * <p>Example 1: s1 = "HAHAH" s2 = "AAAAHH" Output: 3. There are two common subsequences of length
 * 3: "AAH" and "AHH".
 *
 * <p>Example 2: s1 = "" s2 = "AA" Output: 0.
 *
 * <p>Example 3: s1 = "ABC" s2 = "BCA" Output: 2. The longest common subsequence is "BC".
 *
 * <p>Solution ________________________________________________________
 *
 * <p>Let's specify a recurrence relation:
 *
 * <p>We'll have one subproblem, lcs(i1, i2), where i1 identifies a suffix of s1, and i2 identifies
 * a suffix of s2.
 *
 * <p>Let's specify a recurrence relation:
 *
 * <p>1. Signature: we'll have subproblems lcs(i1, i2), where i1 identifies a suffix of s1, and i2
 * identifies a suffix of s2.
 *
 * <p>2. Description: lcs(i1, i2) is the longest common subsequence of the two suffixes.
 *
 * <p>3. Base cases: let n1 and n2 be the lengths of s1 and s2, respectively.
 *
 * <p>lcs(n1, i2) = 0 (the first suffix is empty). lcs(i1, n2) = 0 (the second suffix is empty). 4.
 * General case:
 *
 * <p>If s1[i1] == s2[i2], lcs(i1, i2) = 1 + lcs(i1 + 1, i2 + 1). If not, our options are to discard
 * s1[i1] or discard s2[i2]. Aggregation logic: We pick the maximum: lcs(i1, i2) = max(lcs(i1 + 1,
 * i2), lcs(i1, i2 + 1))
 *
 * <p>5. Original problem: lcs(0, 0).
 *
 * <p>We can use the memoization recipe to turn the recurrence relation into efficient code:
 *
 * <p>Time & Space Analysis n1: the length of s1 n2: the length of s2 For DP, we can analyze the
 * time complexity as the product of two things: the number of subproblems and the non-recursive
 * work per subproblem (when the subproblem is not cached).
 *
 * <p>For this problem, we have:
 *
 * <p>Subproblems: n1 * n2. Non-recursive work: O(1). Time: O(n1 * n2). Extra space: O(n1 * n2) for
 * the memoization map.
 */
public class LongestCommonSubsequenceLength {
  private Map<String, Integer> memo;
  private String s1, s2;

  public static void main(String[] args) {
    LongestCommonSubsequenceLength lcsLength = new LongestCommonSubsequenceLength();
    System.out.println(lcsLength.solve("HAHAH", "AAAAHH"));
    System.out.println(lcsLength.solve("ABCDEFGHIJ", "ACBDEFGHIK"));
  }

  public int solve(String x, String y) {
    memo = new HashMap<>();
    s1 = x;
    s2 = y;
    return visit(0, 0);
  }

  private int visit(int i1, int i2) {
    if (i1 == s1.length() || i2 == s2.length()) return 0;
    String key = i1 + "," + i2;
    if (memo.containsKey(key)) return memo.get(key);

    int result;
    if (s1.charAt(i1) == s2.charAt(i2)) {
      result = 1 + visit(i1 + 1, i2 + 1);
    } else {
      result = Math.max(visit(i1 + 1, i2), visit(i1, i2 + 1));
    }
    memo.put(key, result);
    return memo.get(key);
  }
}
