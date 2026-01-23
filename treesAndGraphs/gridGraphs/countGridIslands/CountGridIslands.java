package treesAndGraphs.gridGraphs.countGridIslands;

public class CountGridIslands {
  private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  private int[][] grid;
  private int rows;
  private int columns;
  private boolean[][] visited;

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int solve(int[][] grid) {
    this.grid = grid;
    rows = grid.length;
    columns = grid[0].length;
    visited = new boolean[rows][columns];
    int count = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (grid[r][c] == 1 && !visited[r][c]) {
          visited[r][c] = true;
          visit(r, c);
          count++;
        }
      }
    }
    return count;
  }

  private void visit(int r, int c) {
    for (int[] dir : directions) {
      int nbrR = r + dir[0];
      int nbrC = c + dir[1];
      if (isValid(nbrR, nbrC)) {
        visited[nbrR][nbrC] = true;
        visit(nbrR, nbrC);
      }
    }
  }

  private boolean isValid(int r, int c) {
    return r >= 0 && r < rows && c >= 0 && c < columns && grid[r][c] == 1 && !visited[r][c];
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example 1 from the book
      {
        new int[][] {
          {0, 0, 1, 0},
          {1, 1, 0, 1},
          {0, 0, 1, 1}
        },
        3
      },
      // Example 2 from the book
      {new int[][] {{}}, 0},
      // Edge case - single cell
      {new int[][] {{1}}, 1},
      // Edge case - all water
      {new int[][] {{0, 0}, {0, 0}}, 0},
      // Edge case - all land
      {new int[][] {{1, 1}, {1, 1}}, 1},
      // Multiple islands
      {
        new int[][] {
          {1, 0, 1},
          {0, 0, 0},
          {1, 0, 1}
        },
        4
      }
    };

    // Test CountIslands function
    CountGridIslands solution1 = new CountGridIslands();
    for (Object[] test : tests) {
      int[][] gridTemplate = (int[][]) test[0];
      int[][] grid = deepCopy(gridTemplate);
      int want = (int) test[1];
      int got = solution1.solve(grid);
      if (got != want) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %d, want: %d\n",
                java.util.Arrays.deepToString(gridTemplate), got, want));
      } else {
        System.out.print(".");
      }
    }
  }

  private int[][] deepCopy(int[][] original) {
    if (original.length == 0) return new int[0][0];
    int[][] copy = new int[original.length][original[0].length];
    for (int i = 0; i < original.length; i++) {
      System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
    }
    return copy;
  }
}
