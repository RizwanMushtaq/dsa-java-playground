package slidingWindows.mostSalesInKDays;

import java.util.Arrays;

public class MostSalesInKDays {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int solve(int[] sales, int k) {
    int l = 0, r = 0;
    int windowSales = 0;
    int mostSales = 0;
    int bestWindowStartIndex = 0;

    while (r < sales.length) {
      windowSales += sales[r];
      r++;
      if (r - l == k) {
        if (windowSales > mostSales) {
          mostSales = windowSales;
          bestWindowStartIndex = l;
        }
        windowSales -= sales[l];
        l++;
      }
    }
    return bestWindowStartIndex;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example from the book
      {new int[] {8, 1, 3, 7}, 2, 2},
      // Edge case - k=1
      {new int[] {5, 10, 15, 5}, 1, 2},
      // Edge case - k=len(sales)
      {new int[] {1, 2, 3}, 3, 0},
      // Edge case - multiple valid answers, return first
      {new int[] {10, 5, 10}, 2, 0},
    };

    MostSalesInKDays solution = new MostSalesInKDays();
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
