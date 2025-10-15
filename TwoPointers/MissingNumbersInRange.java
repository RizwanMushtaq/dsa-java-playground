package TwoPointers;

import java.util.*;

class MissingNumbersInRange {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public int[] missingNumbersInRange(int[] arr, int low, int high) {
    int p1 = 0; // pointer for array
    int p2 = low; // pointer for virtual array
    List<Integer> res = new ArrayList<>();

    while (p1 < arr.length && p2 <= high) {
      if (arr[p1] < p2) {
        p1++;
      } else if (arr[p1] == p2) {
        p1++;
        p2++;
      } else {
        res.add(p2);
        p2++;
      }
    }

    if (p2 <= high) {
      for (int i = p2; i <= high; i++) {
        res.add(i);
      }
    }

    return res.stream().mapToInt(Integer::intValue).toArray();
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {6, 9, 12, 15, 18}, 9, 13, new int[] {10, 11, 13}},
      {new int[] {}, 9, 9, new int[] {9}},
      {new int[] {6, 7, 8, 9}, 7, 8, new int[] {}},
      {new int[] {}, 1, 5, new int[] {1, 2, 3, 4, 5}},
      {new int[] {1, 2, 3, 4, 5}, 1, 5, new int[] {}},
      {new int[] {1, 3, 5}, 1, 5, new int[] {2, 4}},
      {new int[] {1}, 1, 1, new int[] {}},
      {new int[] {2}, 1, 3, new int[] {1, 3}},
    };

    MissingNumbersInRange solution = new MissingNumbersInRange();
    for (Object[] test : tests) {
      int[] arr = (int[]) test[0];
      int low = (int) test[1];
      int high = (int) test[2];
      int[] want = (int[]) test[3];

      int[] got = solution.missingNumbersInRange(arr, low, high);
      if (!Arrays.equals(want, got)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %d, %d): got: %s, want: %s\n",
                Arrays.toString(arr), low, high, Arrays.toString(got), Arrays.toString(want)));
      } else {
        System.out.print(".");
      }
    }
  }
}
