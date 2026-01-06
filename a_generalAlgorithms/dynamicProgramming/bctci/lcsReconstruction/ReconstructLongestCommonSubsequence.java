package a_generalAlgorithms.dynamicProgramming.bctci.lcsReconstruction;

import java.util.*;

/*
 Given two strings, s1 and s2, return the longest subsequence that is common to both s1 and s2. A
 subsequence of a string s is a sequence of characters that appears in s in the same relative
 order but not necessarily consecutively. In case of a tie, return any common subsequence of
 maximum length. The two strings consist of uppercase English letters only.

 <p>Example 1: s1 = "HAHAH" s2 = "AAAAHH" Output: "AAH". The other valid output is "AHH".

 <p>Example 2: s1 = "" s2 = "AA" Output: ""

 <p>Example 3: s1 = "ABCD" s2 = "ACBAD" Output: "ACD". The other valid output is "ABD".
*/

/*
 Solution _____________________________________________

 <p>We'll solve this problem with DP. Let n1 and n2 be the lengths of s1 and s2, respectively. We
 can start with the recurrence relation for the problem of finding the length of the LCS:

 <p>Subproblem signature: lcs(i1, i2), where i1 identifies a suffix of s1, and i2 identifies a
 suffix of s2.

 <p>Description: lcs(i1, i2) is the longest common subsequence of the two suffixes.

 <p>Base cases: lcs(n1, i2) = 0 (the first suffix is empty). lcs(i1, n2) = 0 (the second suffix is
 empty).

 <p>General case: If s1[i1] == s2[i2], lcs(i1, i2) = 1 + lcs(i1 + 1, i2 + 1). If not, our options
 are to discard s1[i1] or discard s2[i2].

 <p>Aggregation logic: We pick the maximum: lcs(i1, i2) = max(lcs(i1 + 1, i2), lcs(i1, i2 + 1))

 <p>Original problem: lcs(0, 0).
*/

/**
 * The downside to this approach is that it increases the time and space per subproblem:
 *
 * <p>Subproblems: n1 * n2, where n1 is the length of s1 and n2 is the length of s2. Non-recursive
 * work: O(n1 + n2). Time: O(n1 * n2 * (n1 + n2)). Extra space: O(n1 * n2 * (n1 + n2)). There is a
 * technique we can use to avoid the extra time and space. We call it solution reconstruction.
 */
public class ReconstructLongestCommonSubsequence {
  private String s1, s2;
  private Map<String, String> memo;

  public static void main(String[] args) {
    ReconstructLongestCommonSubsequence lcs = new ReconstructLongestCommonSubsequence();
    System.out.println(lcs.solve("HAHAH", "AAAAHH"));
    System.out.println(lcs.solve("ABCDEFGHIJ", "ACBDEFGHIK"));
  }

  public String solve(String x, String y) {
    s1 = x;
    s2 = y;
    memo = new HashMap<>();
    return visit(0, 0);
  }

  private String visit(int i1, int i2) {
    if (i1 == s1.length() || i2 == s2.length()) return "";
    String key = i1 + "," + i2;
    if (memo.containsKey(key)) return memo.get(key);
    if (s1.charAt(i1) == s2.charAt(i2)) {
      memo.put(key, s1.charAt(i1) + visit(i1 + 1, i2 + 1));
    } else {
      String opt1 = visit(i1 + 1, i2);
      String opt2 = visit(i1, i2 + 1);
      memo.put(key, opt1.length() >= opt2.length() ? opt1 : opt2);
    }
    return memo.get(key);
  }
}
