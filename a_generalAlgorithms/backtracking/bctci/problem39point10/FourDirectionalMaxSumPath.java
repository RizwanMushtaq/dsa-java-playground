package a_generalAlgorithms.backtracking.bctci.problem39point10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FourDirectionalMaxSumPath {
  private int rows;
  private int columns;
  private int maxSum;
  private List<List<Integer>> maxSumPath;

  public static void main(String[] args) {
    FourDirectionalMaxSumPath fourDirectionalMaxSumPath = new FourDirectionalMaxSumPath();
    int[][] grid = {
      {1, -4, 3},
      {-2, 7, -6},
      {5, -4, 9}
    };
    System.out.println(fourDirectionalMaxSumPath.solve(grid));
    System.out.println(fourDirectionalMaxSumPath.maxSum);
    System.out.println("***********************************");
    int[][] grid2 = {{1, 2, 3}};
    System.out.println(fourDirectionalMaxSumPath.solve(grid2));
    System.out.println(fourDirectionalMaxSumPath.maxSum);
  }

  public List<List<Integer>> solve(int[][] grid) {
    rows = grid.length;
    columns = grid[0].length;
    maxSum = Integer.MIN_VALUE;
    List<List<Integer>> currentSumPath = new ArrayList<>();
    currentSumPath.add(List.of(0, 0));
    boolean[][] memo = new boolean[rows][columns];
    visit(grid, memo, 0, 0, grid[0][0], currentSumPath);
    return maxSumPath;
  }

  private void visit(
      int[][] grid,
      boolean[][] memo,
      int row,
      int column,
      int currentSum,
      List<List<Integer>> currentSumPath) {
    if (row == rows - 1 && column == columns - 1) {
      if (currentSum > maxSum) {
        maxSum = currentSum;
        maxSumPath = currentSumPath.stream().map(ArrayList::new).collect(Collectors.toList());
      }
      return;
    }
    if (memo[row][column]) {
      return;
    }
    memo[row][column] = true;
    if (row > 0) {
      currentSumPath.add(List.of(row - 1, column));
      visit(grid, memo, row - 1, column, currentSum + grid[row - 1][column], currentSumPath);
      currentSumPath.removeLast();
    }
    if (row < rows - 1) {
      currentSumPath.add(List.of(row + 1, column));
      visit(grid, memo, row + 1, column, currentSum + grid[row + 1][column], currentSumPath);
      currentSumPath.removeLast();
    }
    if (column > 0) {
      currentSumPath.add(List.of(row, column - 1));
      visit(grid, memo, row, column - 1, currentSum + grid[row][column - 1], currentSumPath);
      currentSumPath.removeLast();
    }
    if (column < columns - 1) {
      currentSumPath.add(List.of(row, column + 1));
      visit(grid, memo, row, column + 1, currentSum + grid[row][column + 1], currentSumPath);
      currentSumPath.removeLast();
    }
    memo[row][column] = false;
  }
}
