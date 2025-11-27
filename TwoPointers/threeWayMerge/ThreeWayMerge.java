package TwoPointers.threeWayMerge;

import java.util.*;

class ThreeWayMerge {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public int[] threeWayMerge(int[] arr1, int[] arr2, int[] arr3) {
    int p1 = 0, p2 = 0, p3 = 0;
    List<Integer> result = new ArrayList<>();

    while (p1 < arr1.length || p2 < arr2.length || p3 < arr3.length) {
      int minVal = Integer.MAX_VALUE;

      // Find the smallest value among current positions
      if (p1 < arr1.length) {
        minVal = Math.min(minVal, arr1[p1]);
      }
      if (p2 < arr2.length) {
        minVal = Math.min(minVal, arr2[p2]);
      }
      if (p3 < arr3.length) {
        minVal = Math.min(minVal, arr3[p3]);
      }

      // Skip duplicates of minVal in all arrays
      if (p1 < arr1.length && arr1[p1] == minVal) {
        p1++;
      }
      if (p2 < arr2.length && arr2[p2] == minVal) {
        p2++;
      }
      if (p3 < arr3.length && arr3[p3] == minVal) {
        p3++;
      }

      // Only add if we haven't added this value before
      if (result.isEmpty() || result.get(result.size() - 1) != minVal) {
        result.add(minVal);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {
        new int[] {2, 3, 3, 4, 5, 7},
        new int[] {3, 3, 9},
        new int[] {3, 3, 9},
        new int[] {2, 3, 4, 5, 7, 9}
      },
      {new int[] {}, new int[] {}, new int[] {}, new int[] {}},
      {new int[] {1}, new int[] {}, new int[] {}, new int[] {1}},
      {new int[] {1}, new int[] {1}, new int[] {1}, new int[] {1}},
      {new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {3, 4, 5}, new int[] {1, 2, 3, 4, 5}},
      {new int[] {1, 1, 1}, new int[] {1, 1}, new int[] {1}, new int[] {1}},
      {
        new int[] {1, 2, 3},
        new int[] {4, 5, 6},
        new int[] {7, 8, 9},
        new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}
      },
    };

    ThreeWayMerge solution = new ThreeWayMerge();
    for (Object[] test : tests) {
      int[] arr1 = (int[]) test[0];
      int[] arr2 = (int[]) test[1];
      int[] arr3 = (int[]) test[2];
      int[] want = (int[]) test[3];

      int[] got = solution.threeWayMerge(arr1, arr2, arr3);

      //      System.out.println(Arrays.toString(got));

      if (!Arrays.equals(want, got)) {
        throw new RuntimeException(
            String.format("\nsolve(%s, %s, %s): got: %s, want: %s\n", arr1, arr2, arr3, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
