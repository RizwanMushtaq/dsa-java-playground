package a_generalAlgorithms.dynamicProgramming.bctci.problem40point8;

import java.util.*;

/**
 * There is a technique we can use to avoid the extra time and space. We call it solution
 * reconstruction. We start by implementing the normal recurrence relation with memoization. The
 * difference is that, instead of returning the original problem at the end (with return lcs_rec(0,
 * 0)), that's where we start reconstructing the solution.
 *
 * <p>We build it one character at a time with the "parallel pointers" pattern from the Two Pointers
 * chapter. We use two pointers, i1 and i2, initialized to 0. If s1[i1] and s2[i2] match, we can add
 * that character to the solution and advance both pointers. Otherwise, we know that we need to
 * advance one of the two pointers without adding anything to the solution. Which one? We use
 * lcs_rec() to answer that. If lcs_rec(i1 + 1, i2) is higher than lcs_rec(i1, i2 + 1), it is better
 * to advance i1. Otherwise, it's better to advance i2. We keep going until either pointer reaches
 * the end of the string, which means we are done.
 *
 * <p>Time & Space Analysis n1: the length of s1 n2: the length of s2 As mentioned, the
 * "straightforward" solution takes O(n1 * n2 * (n1 + n2)) time and space, that is implemented in
 * class ReconstructLongestCommonSubsequence.
 *
 * <p>For the optimal solution, the "memoization" step where we populate the memo table takes:
 *
 * <p>Subproblems: n1 * n2. Non-recursive work: O(1). Time: O(n1 * n2). Extra space: O(n1 * n2). The
 * reconstruction step takes O(n1 + n2) time since we advance either i1 or i2 in each iteration, and
 * both pointers start at 0 and can only advance up to the length of their respective strings.
 *
 * <p>Therefore, the overall time and space complexity remains O(n1 * n2), dominated by the
 * memoization step.
 */
public class LcsReconstructionOptimal {
  private String s1, s2;
  private Map<String, Integer> memo;

  public static void main(String[] args) {
    LcsReconstructionOptimal lcs = new LcsReconstructionOptimal();
    System.out.println(lcs.solve("HAHAH", "AAAAHH"));
    System.out.println(lcs.solve("ABCDEFGHIJ", "ACBDEFGHIK"));
  }

  public String solve(String x, String y) {
    s1 = x;
    s2 = y;
    memo = new HashMap<>();

    int i1 = 0, i2 = 0;
    StringBuilder res = new StringBuilder();
    while (i1 < s1.length() && i2 < s2.length()) {
      if (s1.charAt(i1) == s2.charAt(i2)) {
        res.append(s1.charAt(i1));
        i1++;
        i2++;
      } else if (visit(i1 + 1, i2) >= visit(i1, i2 + 1)) {
        i1++;
      } else {
        i2++;
      }
    }
    return res.toString();
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
    return result;
  }
}
