package slidingWindows.resettingWindows.maxSubarraySum;

import java.util.Arrays;

public class MaxSubarraySum {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int solve(int[] arr) {
    int maxValue = Arrays.stream(arr).max().orElse(0);
    if (maxValue <= 0) return maxValue; // Edge case without positive number
    int r = 0;
    int windowSum = 0;
    int curMax = 0;
    while (r < arr.length) {
      boolean canGrow = windowSum + arr[r] >= 0;
      if (canGrow) {
        windowSum += arr[r];
        r++;
        curMax = Math.max(curMax, windowSum);
      } else {
        windowSum = 0;
        r++;
      }
    }
    return curMax;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {1, 2, 3, -2, 1}, 6},
      {new int[] {1, 2, 3, -2, 7}, 11},
      {new int[] {1, 2, 3, -8, 7}, 7},
      {new int[] {-2, -3, -4}, -2},
      // Edge case - single element
      {new int[] {5}, 5},
      // Edge case - all positive
      {new int[] {1, 2, 3}, 6},
    };

    MaxSubarraySum solution = new MaxSubarraySum();
    for (Object[] test : tests) {
      int[] arr = (int[]) test[0];
      int want = (int) test[1];
      int got = solution.solve(arr);
      if (got != want) {
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %d, want: %d\n", Arrays.toString(arr), got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
