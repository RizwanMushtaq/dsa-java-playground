package recursion;

import java.util.HashMap;

public class LegoCastle {
  public static void main(String[] args) {
    new LegoCastleTests().runTests();
  }

  public long solve(int n) {
    return blocksRec(n);
  }

  public long blocksRec(int n) {
    if (n == 1) {
      return 1;
    }

    return (blocksRec(n - 1) * 2) + roof(n);
  }

  public long roof(int i) {
    if (i == 1) {
      return 1;
    }

    return (roof(i - 1) * 2) + 1;
  }
}

class LegoCastleMemoized {
  private HashMap<Integer, Long> memo;

  public long solve(int n) {
    memo = new HashMap<>();
    return blocksRec(n);
  }

  public long blocksRec(int n) {
    if (n == 1) {
      return 1;
    }

    return (blocksRec(n - 1) * 2) + roof(n);
  }

  public long roof(int i) {
    if (i == 1) {
      return 1;
    }

    if (memo.containsKey(i)) {
      return memo.get(i);
    }

    long result = (roof(i - 1) * 2) + 1;
    memo.put(i, result);

    return result;
  }
}

class LegoCastleTests {
  public void runTests() {
    Object[][] tests = {
      {1, 1L},
      {2, 5L},
      {3, 17L},
      {4, 49L},
      {5, 129L},
      {6, 321L},
      {7, 769L},
      {8, 1793L},
      {9, 4097L},
      {10, 9217L},
    };

    LegoCastleMemoized legoCastle = new LegoCastleMemoized();
    LegoCastleMemoized legoCastleMemoized = new LegoCastleMemoized();
    //    BlocksIterative solutionIterative = new BlocksIterative();
    //    BlocksMath solutionMath = new BlocksMath();
    for (Object[] test : tests) {
      int n = (Integer) test[0];
      long want = (Long) test[1];
      long gotRec = legoCastle.solve(n);
      long gotMemoized = legoCastleMemoized.solve(n);
      //      long gotIterative = solutionIterative.solve(n);
      //      long gotMath = solutionMath.solve(n);
      if (gotRec != want) {
        throw new RuntimeException(
            String.format("\nBlocksRec.solve(%d): got: %d, want: %d\n", n, gotRec, want));
      } else {
        System.out.print(".");
      }

      if (gotMemoized != want) {
        throw new RuntimeException(
            String.format("\nBlocksMemoized.solve(%d): got: %d, want: %d\n", n, gotMemoized, want));
      } else {
        System.out.print(".");
      }

      //      if (gotIterative != want) {
      //        throw new RuntimeException(
      //            String.format(
      //                "\nBlocksIterative.solve(%d): got: %d, want: %d\n", n, gotIterative, want));
      //      }
      //      if (gotMath != want) {
      //        throw new RuntimeException(
      //            String.format("\nBlocksMath.solve(%d): got: %d, want: %d\n", n, gotMath, want));
      //      }
    }
  }
}
