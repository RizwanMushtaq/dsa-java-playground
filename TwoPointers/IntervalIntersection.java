package TwoPointers;

import java.util.*;

class IntervalIntersection {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public int[][] solve(int[][] arr1, int[][] arr2) {
    int p1 = 0, p2 = 0;
    List<int[]> res = new ArrayList<>();

    while (p1 < arr1.length && p2 < arr2.length) {
      int[] interval1 = arr1[p1], interval2 = arr2[p2];

      if (interval1[1] < interval2[0]) {
        p1++;
      } else if (interval2[1] < interval1[0]) {
        p2++;
      } else {
        res.add(new Intersection().solve(interval1, interval2));
        if (interval1[1] < interval2[1]) {
          p1++;
        } else {
          p2++;
        }
      }
    }

    return res.toArray(new int[0][]);
  }
}

class Intersection {
  public int[] solve(int[] arr1, int[] arr2) {
    int OverlapStart = Math.max(arr1[0], arr2[0]);
    int OverlapEnd = Math.min(arr1[1], arr2[1]);
    return new int[] {OverlapStart, OverlapEnd};
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {
        new int[][] {{0, 1}, {4, 6}, {7, 8}},
        new int[][] {{2, 3}, {5, 9}, {10, 11}},
        new int[][] {{5, 6}, {7, 8}}
      },
      {
        new int[][] {{2, 4}, {5, 8}},
        new int[][] {{3, 3}, {4, 7}},
        new int[][] {{3, 3}, {4, 4}, {5, 7}}
      },
      {new int[][] {}, new int[][] {}, new int[][] {}},
      {new int[][] {{1, 2}}, new int[][] {}, new int[][] {}},
      {new int[][] {{1, 3}}, new int[][] {{2, 4}}, new int[][] {{2, 3}}},
      {new int[][] {{1, 5}}, new int[][] {{2, 3}}, new int[][] {{2, 3}}},
      {new int[][] {{1, 2}, {3, 4}}, new int[][] {{2, 3}}, new int[][] {{2, 2}, {3, 3}}},
    };

    IntervalIntersection solution = new IntervalIntersection();
    for (Object[] test : tests) {
      int[][] arr1 = (int[][]) test[0];
      int[][] arr2 = (int[][]) test[1];
      int[][] want = (int[][]) test[2];
      int[][] got = solution.solve(arr1, arr2);
      if (!Arrays.deepEquals(got, want)) {
        throw new RuntimeException(
            String.format(
                "\ninterval_intersection(%s, %s): got: %s, want: %s\n",
                Arrays.deepToString(arr1),
                Arrays.deepToString(arr2),
                Arrays.deepToString(got),
                Arrays.deepToString(want)));
      } else {
        System.out.print(".");
      }
    }
  }
}
