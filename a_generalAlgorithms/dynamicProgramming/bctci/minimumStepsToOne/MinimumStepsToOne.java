package a_generalAlgorithms.dynamicProgramming.bctci.minimumStepsToOne;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function that accepts a positive integer, n, and returns the minimum number of operations
 * to get to 1, assuming we can choose between the following operations:
 *
 * <p>Subtract 1. Divide by 2. We can only do this if the number is divisible by 2. Divide by 3. We
 * can only do this if the number is divisible by 3.
 *
 * <p>Example 1: n = 10 Output: 3. We can do 10 -> 9 -> 3 -> 1.
 *
 * <p>Example 2: n = 1 Output: 0
 *
 * <p>Example 3: n = 15 Output: 4
 *
 * <p>Solution ______________________________________
 *
 * <p>For DP, we can analyze the time complexity as the product of two things: the number of
 * subproblems and the non-recursive work per subproblem (when the subproblem is not cached).
 *
 * <p>For this problem, we have:
 *
 * <p>Subproblems: n. Non-recursive work: O(1) for each subproblem. Time: O(n). Extra space: O(n) -
 * for the memoization map.
 */
public class MinimumStepsToOne {
  private Map<Integer, Integer> memo;

  public static void main(String[] args) {
    MinimumStepsToOne minimumStepsToOne = new MinimumStepsToOne();
    System.out.println(minimumStepsToOne.solve(15));
    System.out.println(minimumStepsToOne.solve(10));
  }

  public int solve(int n) {
    memo = new HashMap<>();
    return visit(n);
  }

  private int visit(int n) {
    if (n == 1) return 0;

    if (memo.containsKey(n)) return memo.get(n);

    int steps = visit(n - 1);
    if (n % 3 == 0) {
      steps = Math.min(steps, visit(n / 3));
    }
    if (n % 2 == 0) {
      steps = Math.min(steps, visit(n / 2));
    }
    memo.put(n, 1 + steps);
    return memo.get(n);
  }
}
