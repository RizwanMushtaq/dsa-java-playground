package slidingWindows.fixedLengthWindows.enduringBestSellerStreak;

import java.util.*;

public class EnduringBestSellingStreak {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  boolean solve(String[] bestseller, int k) {
    int l = 0, r = 0;
    Map<String, Integer> windowCounts = new HashMap<>();
    while (r < bestseller.length) {
      windowCounts.merge(bestseller[r], 1, Integer::sum);
      r++;
      if (r - l == k) {
        if (windowCounts.size() == 1) return true;
        windowCounts.merge(
            bestseller[l], -1, (count, desc) -> count + desc == 0 ? null : count + desc);
        l++;
      }
    }
    return false;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {new String[] {"book3", "book1", "book3", "book3", "book2"}, 3, false},
      {new String[] {"book3", "book1", "book3", "book3", "book2"}, 2, true},
      // Example with mix of values
      {new String[] {"book1", "book1", "book2", "book1"}, 2, true},
      // Edge case - k=1
      {new String[] {"book1", "book2"}, 1, true},
      // Edge case - k=len(bestSeller)
      {new String[] {"book1", "book1", "book1"}, 3, true},
      // no same sequence possible
      {new String[] {"book1", "book2", "book1"}, 2, false},
    };

    EnduringBestSellingStreak solution = new EnduringBestSellingStreak();

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
