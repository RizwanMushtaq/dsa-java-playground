package slidingWindows.minimumWindows.shortestPeriodWithOver20Sales;

import java.util.*;

public class ShortestPeriod {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int solve(int[] sales) {
    int l = 0, r = 0;
    int windowSum = 0;
    int curMin = Integer.MAX_VALUE;
    while (true) {
      boolean mustGrow = windowSum <= 20;
      if (mustGrow) {
        if (r == sales.length) break;
        windowSum += sales[r];
        r++;
      } else {
        curMin = Math.min(curMin, r - l);
        windowSum -= sales[l];
        l++;
      }
    }
    return curMin != Integer.MAX_VALUE ? curMin : -1;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {5, 10, 15, 5, 10}, 2},
      {new int[] {5, 10, 4, 5, 10}, 4},
      {new int[] {5, 5, 5, 5}, -1},
      // Edge case - empty array
      {new int[] {}, -1},
      // Edge case - single element over 20
      {new int[] {21}, 1},
      // Edge case - exactly 20 sales not enough
      {new int[] {10, 10}, -1},
    };

    ShortestPeriod solution = new ShortestPeriod();
    for (Object[] test : tests) {
      int[] sales = (int[]) test[0];
      int want = (int) test[1];
      int got = solution.solve(sales);
      if (got != want) {
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %d, want: %d\n", Arrays.toString(sales), got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
