package slidingWindows.countingProblems.atMostKCounting;

import java.util.*;

public class SubArraysWithAtMostKBadDays {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int solve(int[] sales, int k) {
    int l = 0, r = 0;
    int windowBadDays = 0;
    int count = 0;
    while (r < sales.length) {
      boolean canGrow = sales[r] >= 10 || windowBadDays < k;
      if (canGrow) {
        if (sales[r] < 10) windowBadDays++;
        r++;
        count += r - l;
      } else {
        if (sales[l] < 10) windowBadDays--;
        l++;
      }
    }
    return count;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {0, 20, 5}, 1, 5},
      {new int[] {}, 1, 0},
      // Edge case - k = 0
      {new int[] {0, 20, 5}, 0, 1},
      // Edge case - all good days
      {new int[] {10, 20, 30}, 1, 6},
      // Edge case - all bad days
      {new int[] {0, 5, 8}, 2, 5},
    };

    SubArraysWithAtMostKBadDays solution = new SubArraysWithAtMostKBadDays();
    for (Object[] test : tests) {
      int[] sales = (int[]) test[0];
      int k = (int) test[1];
      int want = (int) test[2];
      int got = solution.solve(sales, k);
      if (got != want) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %d): got: %d, want: %d\n", Arrays.toString(sales), k, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
