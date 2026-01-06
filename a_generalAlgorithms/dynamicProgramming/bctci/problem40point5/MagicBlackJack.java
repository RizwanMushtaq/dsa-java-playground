package a_generalAlgorithms.dynamicProgramming.bctci.problem40point5;

import java.util.*;

/**
 * You're given a magic deck of cards. When one card is removed, an identical card spawns as a
 * replacement. Each card is a number between 1 and 10 (suits do not matter). When a card is drawn,
 * each value from 1 to 10 has a 10% chance of appearing.
 *
 * <p>A dealer repeatedly draws cards until one of two things happen:
 *
 * <p>The sum of the cards is between 16 and 21. The sum of the cards exceeds 21. When this happens,
 * we say the dealer busts. Return the number of different ways the dealer can bust.
 *
 * <p>For instance, if the dealer draws 10, 2, 10, they bust. If they draw 2, 10, 10, that counts as
 * a different way to bust. If the dealer draws 10, 1, 10, they don't bust.
 *
 * <p>Constraints: No input parameters (the problem has fixed parameters)
 *
 * <p>Solution -------------------------------------------------
 *
 * <p>The '10% chance' part is a bit of a red herring that causes many candidates to think they need
 * to use probabilities or start generating random numbers. This is really just a counting problem.
 *
 * <p>There is no input for this problem, which technically means that any algorithm for it takes
 * constant time. However, that doesn't mean we should just use backtracking -- the decision tree
 * has a branching factor of 10 and a maximum depth of 16 (if we repeatedly draw 1's), so it is
 * still unpractically large.
 *
 * <p>We can solve it with DP. We'll have one subproblem, num_ways(i), for each possible sum. Each
 * subproblem denotes the number of ways to bust starting from that sum. Our base cases are i > 21
 * (1 way) and 16 <= i <= 21 (0 ways). The original problem is num_ways(0).
 *
 * <p>We can use the memoization recipe to turn the recurrence relation into efficient code:
 */
public class MagicBlackJack {
  private Map<Integer, Integer> memo;

  public static void main(String[] args) {
    MagicBlackJack magicBlackJack = new MagicBlackJack();
    System.out.println(magicBlackJack.solve());
  }

  public int solve() {
    memo = new HashMap<>();
    return numOfWayRec(0);
  }

  private int numOfWayRec(int i) {
    if (i > 21) return 1;
    if (i >= 16) return 0;
    if (memo.containsKey(i)) return memo.get(i);
    int res = 0;
    for (int card = 1; card <= 10; card++) {
      res += numOfWayRec(i + card);
    }
    memo.put(i, res);
    return res;
  }
}
