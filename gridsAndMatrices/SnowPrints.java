package gridsAndMatrices;

import java.util.*;

class SnowPrints {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  private boolean hasFootPrints(int[][] field, int r, int c) {
    return 0 <= r && r < field.length && 0 <= c && c < field[0].length && field[r][c] == 1;
  }

  public int solve(int[][] field) {
    final int R = field.length;
    final int C = field[0].length;

    int r = 0;
    while (r < R && field[r][0] != 1) {
      r++;
    }

    int closest = r;
    int c = 0;

    while (c < C - 1) {
      boolean found = false;
      for (int dirR : new int[] {-1, 0, 1}) {
        int newR = r + dirR;
        int newC = c + 1;
        if (hasFootPrints(field, newR, newC)) {
          r = newR;
          c = newC;
          closest = Math.min(closest, r);
          found = true;
          break;
        }
      }

      if (!found) {
        break;
      }
    }

    return closest;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {
        new int[][] {
          {0, 0, 0, 0, 0, 0},
          {0, 0, 1, 0, 0, 0},
          {1, 1, 0, 1, 0, 0},
          {0, 0, 0, 0, 1, 1}
        },
        1
      },
      {
        new int[][] {
          {0, 0, 0, 1, 0, 0},
          {0, 0, 1, 0, 1, 0},
          {1, 1, 0, 0, 0, 1},
          {0, 0, 0, 0, 0, 0}
        },
        0
      },
      {
        new int[][] {
          {0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0},
          {1, 1, 1, 1, 1, 1}
        },
        3
      },
      {new int[][] {{0}, {1}}, 1},
      {new int[][] {{1, 1, 1}}, 0},
      {
        new int[][] {
          {0, 0, 0},
          {1, 0, 0},
          {0, 1, 0},
          {0, 0, 1}
        },
        1
      },
    };

    SnowPrints solution = new SnowPrints();
    for (Object[] test : tests) {
      int[][] field = (int[][]) test[0];
      int want = (int) test[1];
      int got = solution.solve(field);
      if (got != want) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %d, want: %d\n", Arrays.deepToString(field), got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
