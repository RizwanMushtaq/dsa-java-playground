package binarySearch;

import java.util.function.*;

class CCTVFootage {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public int findBike(int t1, int t2, Function<Integer, Boolean> isStolen) {
    int l = t1, r = t2;
    while (r - l > 1) {
      int mid = (l + r) / 2;

      if (isBefore(mid, isStolen)) {
        l = mid;
      } else {
        r = mid;
      }
    }

    return r;
  }

  private boolean isBefore(int mid, Function<Integer, Boolean> isStolen) {
    return !isStolen.apply(mid);
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example 1 - stolen at t=5
      {1, 10, (Function<Integer, Boolean>) (t -> t >= 5), 5},
      // Example 2 - stolen at start
      {1, 5, (Function<Integer, Boolean>) (t -> t >= 2), 2},
      // Example 3 - stolen at end
      {1, 5, (Function<Integer, Boolean>) (t -> t >= 5), 5},
      // Edge case - two timestamps
      {5, 6, (Function<Integer, Boolean>) (t -> t >= 6), 6}
    };

    CCTVFootage solution = new CCTVFootage();
    for (Object[] test : tests) {
      int t1 = (int) test[0];
      int t2 = (int) test[1];
      @SuppressWarnings("unchecked")
      Function<Integer, Boolean> isStolen = (Function<Integer, Boolean>) test[2];
      int want = (int) test[3];
      int got = solution.findBike(t1, t2, isStolen);
      if (got != want) {
        throw new RuntimeException(
            String.format("\nsolve(%d, %d): got: %d, want: %d\n", t1, t2, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
