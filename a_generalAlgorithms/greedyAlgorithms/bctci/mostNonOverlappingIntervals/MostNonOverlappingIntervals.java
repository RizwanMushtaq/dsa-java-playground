package a_generalAlgorithms.greedyAlgorithms.bctci.mostNonOverlappingIntervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list, intervals, where each element is a pair of integers [l, r], with l ≤ r,
 * representing an interval (with both endpoints included). Return the largest number of
 * non-overlapping intervals.
 *
 * <p>Example 1: intervals = [[2, 3], [1, 4], [2, 3], [3, 6], [8, 9]] Output: 2 For instance, [2, 3]
 * and [8, 9] don't overlap. We can't add [3, 6] because it overlaps with [2, 3] at value 3.
 *
 * <p>Example 2: intervals = [[1, 2], [2, 3], [3, 4]] Output: 2
 *
 * <p>Example 3: intervals = [[1, 10], [8, 9], [2, 3]] Output: 2
 *
 * <p>Solution__________________________________
 *
 * <p>What if we always pick the available interval that ends the earliest instead? Try to find a
 * counterexample.
 *
 * <p>This greedy choice actually works -- there are no counterexamples.
 *
 * <p>If we can't find counterexamples, the next step should be to explain the intuition for why it
 * works to the interviewer (with examples and diagrams, if it helps). Something like this:
 *
 * <p>"Imagine [2, 7] is the interval that ends the earliest. All intervals that overlap with it
 * reach further to the right than 7. So, if we don't pick [2, 7] to pick another interval that
 * overlaps with it, we'd just be restricting our options more than if we picked [2, 7]. So, picking
 * [2, 7] is a safe bet (it might not be better, but it's definitely not worse). We can repeat the
 * same logic for the remaining intervals."
 *
 * <p>Finally, let's talk about the implementation. A very common pattern for Greedy algorithms is
 * using a custom-comparator sort (~pg 365 in BCtCI) to sort the input elements in the order that
 * greedy would consider them. For this problem, that means:
 *
 * <p>Sorting the input intervals based on the right endpoint. Iterating through them, keeping track
 * of which ones we can add. Here is the implementation:
 *
 * <p>Time & Space Analysis n: the number of intervals
 *
 * <p>Time: O(n log n) - for sorting
 *
 * <p>Extra space: O(n) - for sorting
 *
 * <p>The sorting step dominates the runtime and the extra space. Even if we sort the intervals
 * in-place, most in-place sorting algorithms still use O(n) extra space.
 */
public class MostNonOverlappingIntervals {
  public static void main(String[] args) {
    MostNonOverlappingIntervals solution = new MostNonOverlappingIntervals();
    int[][] intervals1 = {
      {2, 3},
      {1, 4},
      {2, 3},
      {3, 6},
      {8, 9},
    };
    System.out.println(solution.solve(intervals1));
    int[][] intervals2 = {
      {1, 2}, {3, 4}, {2, 3},
    };
    System.out.println(solution.solve(intervals2));
  }

  public int solve(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
    int count = 0;
    int prevEnd = Integer.MIN_VALUE;
    for (int[] interval : intervals) {
      if (interval[0] > prevEnd) {
        count++;
        prevEnd = interval[1];
      }
    }
    return count;
  }
}
