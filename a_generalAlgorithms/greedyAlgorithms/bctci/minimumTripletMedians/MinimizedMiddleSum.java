package a_generalAlgorithms.greedyAlgorithms.bctci.minimumTripletMedians;

import java.util.Arrays;

/**
 * You are given a non-empty list of distinct integers, arr, where the length of arr is guaranteed
 * to be a multiple of three. Your task is to group the numbers into triplets such that the sum of
 * the medians of each triplet (the middle value in sorted order) is minimized. Return the sum of
 * the medians.
 *
 * <p>Example 1: arr = [6, 5, 8, 2, 1, 9]
 *
 * <p>Output: 8 One optimal grouping is [1, 2, 8], [5, 6, 9] The sum of the medians is 2 + 6 = 8
 * Another optimal grouping is [1, 2, 9], [5, 6, 8]
 *
 * <p>Example 2: arr = [6, 5, 8, 2, 1, 9, 12, 15, 14]
 *
 * <p>Output: 17 One optimal grouping is [5, 6, 14], [1, 2, 12], [8, 9, 15] The sum of the medians
 * is 6 + 2 + 9 = 17
 *
 * <p>__________________________________________________
 *
 * <p>Solution: We don't need to consider all the ways to partition arr into triplets. We can
 * greedily form triplets with the smallest possible middle value.
 *
 * <p>Consider the smallest number. It cannot be the middle element of a triplet, but it has to go
 * in some triplet. We should put the second-smallest value in the same triplet as the smallest
 * value. If we didn't it would end up as the smallest element of a different triplet, which would
 * be suboptimal. The third element doesn't affect the contribution of this group to the overall
 * sum, so we may as well pick the largest number. It's a "safe bet." We can repeat this logic for
 * the remaining elements.
 *
 * <p>Time & Space Analysis n: the number of elements in arr
 *
 * <p>Time: O(n log n) - for sorting.
 *
 * <p>Extra space: O(n) - for sorting.
 *
 * <p>Even if we sort arr in place, most built-in sorting algorithms use O(n) extra space.
 */
public class MinimizedMiddleSum {
  public static void main(String[] args) {
    MinimizedMiddleSum minimizedMiddleSum = new MinimizedMiddleSum();
    System.out.println(minimizedMiddleSum.solve(new int[] {6, 5, 8, 2, 1, 9}));
    System.out.println(minimizedMiddleSum.solve(new int[] {6, 5, 8, 2, 1, 9, 12, 15, 14}));
  }

  public int solve(int[] arr) {
    Arrays.sort(arr);
    int middleSum = 0;
    for (int i = 0; i < arr.length / 3; i++) {
      middleSum += arr[i * 2 + 1];
    }
    return middleSum;
  }
}
