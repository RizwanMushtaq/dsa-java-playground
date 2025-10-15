package TwoPointers;

import java.util.*;

class ParitySorting {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.solve();
  }

  public void solve(int[] arr) {
    int l = 0, r = arr.length - 1;

    while (l < r) {
      if (arr[l] % 2 == 0) {
        l++;
      } else if (arr[r] % 2 == 1) {
        r--;
      } else {
        swap(arr, l, r);
        l++;
        r--;
      }
    }
  }

  private void swap(int[] arr, int l, int r) {
    int temp = arr[l];
    arr[l] = arr[r];
    arr[r] = temp;
  }
}

class RunTests {
  public void solve() {
    Object[][] tests = {
      {new int[] {1, 2, 3, 4, 5}, new int[] {4, 2, 3, 1, 5}},
      {new int[] {5, 1, 3, 1, 5}, new int[] {5, 1, 3, 1, 5}},
      {new int[] {}, new int[] {}},
      {new int[] {1}, new int[] {1}},
      {new int[] {2}, new int[] {2}},
      {new int[] {1, 2}, new int[] {2, 1}},
      {new int[] {2, 1}, new int[] {2, 1}},
      {new int[] {1, 3, 2, 4}, new int[] {4, 2, 3, 1}},
    };

    ParitySorting sorter = new ParitySorting();

    for (Object[] test : tests) {
      int[] arr = (int[]) test[0];
      int[] want = (int[]) test[1];
      sorter.solve(arr);
      if (!Arrays.equals(want, arr)) {
        System.out.println("test failed ! updated array is: " + Arrays.toString(arr));
      } else {
        System.out.print(".");
      }
    }
  }
}
