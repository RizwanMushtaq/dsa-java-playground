package binarySearch;

import java.util.*;

class SearchInSortedArray {
  public static void main(String[] main) {
    SearchInSortedArrayTests runTests = new SearchInSortedArrayTests();
    runTests.runTests();
  }

  public int solve(int[] arr, int target) {
    if (arr.length == 0) {
      return -1;
    }

    int l = 0, r = arr.length - 1;

    if (arr[l] >= target || arr[r] < target) {
      if (arr[l] == target) {
        return l;
      }
      return -1;
    }

    while (r - l > 1) {
      int mid = (l + r) / 2;
      if (arr[mid] < target) {
        l = mid;
      } else {
        r = mid;
      }
    }

    if (arr[r] == target) {
      return r;
    }

    return -1;
  }
}

class SearchInSortedArrayTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {-2, 0, 3, 4, 7, 9, 11}, 3, 2},
      {new int[] {-2, 0, 3, 4, 7, 9, 11}, 2, -1},
      {new int[] {-2, 0, 3, 4, 7, 9, 11}, 9, 5},
      // Edge case - empty array
      {new int[] {}, 5, -1},
      // Edge case - target at start
      {new int[] {1, 2, 3}, 1, 0},
      // Edge case - target at end
      {new int[] {1, 2, 3}, 3, 2},
      // Edge case - single element
      {new int[] {5}, 5, 0},
      // Edge case - not found
      {new int[] {1, 3, 5}, 2, -1}
    };

    SearchInSortedArray solution = new SearchInSortedArray();
    for (Object[] test : tests) {
      int[] arr = (int[]) test[0];
      int target = (int) test[1];
      int want = (int) test[2];
      int got = solution.solve(arr, target);
      if (got != want) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %d): got: %d, want: %d\n", Arrays.toString(arr), target, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
