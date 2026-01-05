package a_generalAlgorithms.dynamicProgramming.maxSumPath;

public class MaxSumPath {
  public static void main(String[] args) {
    int[][] grid = {
      {1, 4, 3},
      {2, 7, 6},
      {5, 8, 9},
    };
    MaxSumPathSol maxSumPathSol = new MaxSumPathSol();
    System.out.println("maxSumPathSol = " + maxSumPathSol.solve(grid));
  }
}

class MaxSumPathSol {
  private int rows, columns;

  public int solve(int[][] grid) {
    rows = grid.length;
    columns = grid[0].length;
    int[][] memo = new int[rows][columns];
    return visit(0, 0, grid, memo);
  }

  private int visit(int r, int c, int[][] grid, int[][] memo) {
    if (r == rows - 1 && c == columns - 1) return grid[r][c];
    if (memo[r][c] != 0) return memo[r][c];
    else if (r == rows - 1) {
      return memo[r][c] = grid[r][c] + visit(r, c + 1, grid, memo);
    } else if (c == columns - 1) {
      return memo[r][c] = grid[r][c] + visit(r + 1, c, grid, memo);
    } else {
      return memo[r][c] =
          grid[r][c] + Math.max(visit(r + 1, c, grid, memo), visit(r, c + 1, grid, memo));
    }
  }
}
