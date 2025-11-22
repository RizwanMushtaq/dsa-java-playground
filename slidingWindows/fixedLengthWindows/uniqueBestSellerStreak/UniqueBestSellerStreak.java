package slidingWindows.fixedLengthWindows.uniqueBestSellerStreak;

import java.util.*;

public class UniqueBestSellerStreak {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(String[] bestSeller, int k) {
    int l = 0, r = 0;
    Map<String, Integer> windowCounts = new HashMap<>();

    while (r < bestSeller.length) {
      windowCounts.merge(bestSeller[r], 1, Integer::sum);
      r++;
      if (r - l == k) {
        if (windowCounts.size() == k) return true;
        windowCounts.merge(
            bestSeller[l], -1, (count, dec) -> count + dec == 0 ? null : count + dec);
        l++;
      }
    }
    return false;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {
        new String[] {"book3", "book1", "book3", "book3", "book2", "book3", "book4", "book3"},
        3,
        true
      },
      {
        new String[] {"book3", "book1", "book3", "book3", "book2", "book3", "book4", "book3"},
        4,
        false
      },
      // Edge case - k=1
      {new String[] {"book1", "book2"}, 1, true},
      // Edge case - k=len(bestSeller)
      {new String[] {"book1", "book2", "book3"}, 3, true},
      // no unique sequence possible
      {new String[] {"book1", "book1", "book1"}, 2, false},
    };

    UniqueBestSellerStreak solution = new UniqueBestSellerStreak();
    for (Object[] test : tests) {
      String[] bestSeller = (String[]) test[0];
      int k = (int) test[1];
      boolean want = (boolean) test[2];
      boolean got = solution.solve(bestSeller, k);
      if (got != want) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %d): got: %b, want: %b\n", Arrays.toString(bestSeller), k, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
