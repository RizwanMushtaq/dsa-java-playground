package TwoPointers.inPlaceDuplicateRemoval;

import java.util.*;

class InPlaceDuplicateRemoval {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public int solve(int[] arr) {
    int w = 0, s = 0;

    while (s < arr.length) {
      boolean mustKeep = s == 0 || arr[s] != arr[s - 1];
      if (mustKeep) {
        arr[w] = arr[s];
        w++;
      }
      s++;
    }

    return w;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {1, 2, 2, 3, 3, 3, 5}, 4, new int[] {1, 2, 3, 5}},
      {new int[] {}, 0, new int[] {}},
      {new int[] {1}, 1, new int[] {1}},
      {new int[] {1, 1}, 1, new int[] {1}},
      {new int[] {1, 2}, 2, new int[] {1, 2}},
      {new int[] {1, 1, 1}, 1, new int[] {1}},
      {new int[] {1, 2, 2, 2, 3}, 3, new int[] {1, 2, 3}},
    };

    InPlaceDuplicateRemoval solution = new InPlaceDuplicateRemoval();
    for (Object[] test : tests) {
      int[] arr = Arrays.copyOf((int[]) test[0], ((int[]) test[0]).length);
      int wantLen = (int) test[1];
      int[] wantPrefix = (int[]) test[2];
      int gotLen = solution.solve(arr);
      if (gotLen != wantLen) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got length: %d, want length: %d\n",
                Arrays.toString((int[]) test[0]), gotLen, wantLen));
      }
      if (!Arrays.equals(Arrays.copyOf(arr, wantLen), wantPrefix)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got prefix: %s, want prefix: %s\n",
                Arrays.toString((int[]) test[0]),
                Arrays.toString(Arrays.copyOf(arr, wantLen)),
                Arrays.toString(wantPrefix)));
      } else {
        System.out.print(".");
      }
    }
  }
}
