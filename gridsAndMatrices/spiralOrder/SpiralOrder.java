package gridsAndMatrices.spiralOrder;

import java.util.*;

class SpiralOrder {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public boolean isValid(int[][] grid, int r, int c) {
    return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 0;
  }

  public int[][] solve(int n) {
    int val = n * n - 1;
    int[][] res = new int[n][n];
    int r = n - 1, c = n - 1;

    int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int dir = 0;

    while (val > 0) {
      res[r][c] = val;
      val--;
      if (!isValid(res, r + directions[dir][0], c + directions[dir][1])) {
        dir = (dir + 1) % 4;
      }

      r += directions[dir][0];
      c += directions[dir][1];
    }

    return res;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {
        5,
        new int[][] {
          {16, 17, 18, 19, 20},
          {15, 4, 5, 6, 21},
          {14, 3, 0, 7, 22},
          {13, 2, 1, 8, 23},
          {12, 11, 10, 9, 24}
        }
      },
      {1, new int[][] {{0}}},
      {
        3,
        new int[][] {
          {4, 5, 6},
          {3, 0, 7},
          {2, 1, 8}
        }
      },
    };

    SpiralOrder solution = new SpiralOrder();
    for (Object[] test : tests) {
      int n = (int) test[0];
      int[][] want = (int[][]) test[1];
      int[][] got = solution.solve(n);
      if (!Arrays.deepEquals(got, want)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%d): got: %s, want: %s\n",
                n, Arrays.deepToString(got), Arrays.deepToString(want)));
      } else {
        System.out.print(".");
      }
    }
  }
}
