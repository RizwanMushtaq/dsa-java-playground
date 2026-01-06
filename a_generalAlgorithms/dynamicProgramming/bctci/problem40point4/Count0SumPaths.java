package a_generalAlgorithms.dynamicProgramming.bctci.problem40point4;

import java.util.HashMap;
import java.util.Map;

public class Count0SumPaths {
  private int rows, cols;
  private int[][] grid;
  private Map<String, Integer> memo;

  public static void main(String[] args) {
    int[][] grid = {
      {0, 1, 1},
      {0, 0, 0},
      {1, 0, 0},
    };
    Count0SumPaths count0SumPaths = new Count0SumPaths();
    System.out.println(count0SumPaths.solve(grid));
  }

  public int solve(int[][] inputGrid) {
    rows = inputGrid.length;
    cols = inputGrid[0].length;
    grid = inputGrid;
    memo = new HashMap<>();
    return visit(0, 0);
  }

  private int visit(int row, int col) {
    if (grid[row][col] == 1) return 0;

    String key = row + "," + col;
    if (memo.containsKey(key)) return memo.get(key);

    if (row == rows - 1 && col == cols - 1) return 1;

    if (row == rows - 1) {
      memo.put(key, visit(row, col + 1));
      return memo.get(key);
    } else if (col == cols - 1) {
      memo.put(key, visit(row + 1, col));
      return memo.get(key);
    } else {
      memo.put(key, visit(row + 1, col) + visit(row, col + 1) + visit(row + 1, col + 1));
      return memo.get(key);
    }
  }
}
