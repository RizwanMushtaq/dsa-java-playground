package TwoPointers.sortValleyShapedArray;

import java.util.*;

class SortValleyShapedArray {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public int[] sortValleyShapedArray(int[] arr) {
    int n = arr.length;
    if (n == 0) {
      return new int[] {};
    }

    int l = 0, r = n - 1;
    int[] result = new int[n];
    int resultCounter = n - 1;

    while (l <= r) {
      if (arr[l] >= arr[r]) {
        result[resultCounter] = arr[l];
        l++;
        resultCounter--;
      } else {
        result[resultCounter] = arr[r];
        r--;
        resultCounter--;
      }
    }

    return result;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {8, 4, 2, 6}, new int[] {2, 4, 6, 8}},
      {new int[] {1, 2}, new int[] {1, 2}},
      {new int[] {2, 2, 1, 1}, new int[] {1, 1, 2, 2}},
      {new int[] {}, new int[] {}},
      {new int[] {1}, new int[] {1}},
      {new int[] {3, 2, 1, 4}, new int[] {1, 2, 3, 4}},
      {new int[] {5, 4, 3, 2, 1, 2, 3}, new int[] {1, 2, 2, 3, 3, 4, 5}},
      {new int[] {1, 1, 1, 1}, new int[] {1, 1, 1, 1}},
    };

    SortValleyShapedArray solution = new SortValleyShapedArray();
    for (Object[] test : tests) {
      int[] arr = (int[]) test[0];
      int[] want = (int[]) test[1];
      int[] got = solution.sortValleyShapedArray(arr);
      if (!Arrays.equals(got, want)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %s, want: %s\n",
                Arrays.toString(arr), Arrays.toString(got), Arrays.toString(want)));
      } else {
        System.out.print(".");
      }
    }
  }
}
