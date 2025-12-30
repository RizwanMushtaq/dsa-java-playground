package a_generalAlgorithms.backtracking.bctci.problem39point10;

import java.util.*;
import java.util.stream.Collectors;

public class TwoDirectionalMaxSumPath {
  private int rows;
  private int columns;
  private int maxSum;
  private List<List<Integer>> maxSumPath;

  public static void main(String[] args) {
    TwoDirectionalMaxSumPath twoDirectionalMaxSumPath = new TwoDirectionalMaxSumPath();
    int[][] grid = {
      {1, 4, 3},
      {2, 7, 6},
      {5, 8, 9}
    };
    System.out.println(twoDirectionalMaxSumPath.solve(grid));
    System.out.println(twoDirectionalMaxSumPath.maxSum);
  }

  public List<List<Integer>> solve(int[][] grid) {
    rows = grid.length;
    columns = grid[0].length;
    maxSum = Integer.MIN_VALUE;
    List<List<Integer>> currentSumPath = new ArrayList<>();
    currentSumPath.add(List.of(0, 0));
    visit(grid, 0, 0, grid[0][0], currentSumPath);
    return maxSumPath;
  }

  private void visit(
      int[][] grid, int row, int column, int currentSum, List<List<Integer>> currentSumPath) {
    if (row == rows - 1 && column == columns - 1) {
      if (currentSum > maxSum) {
        maxSum = currentSum;
        maxSumPath = currentSumPath.stream().map(ArrayList::new).collect(Collectors.toList());
      }
      return;
    }
    if (row < rows - 1) {
      currentSumPath.add(List.of(row + 1, column));
      visit(grid, row + 1, column, currentSum + grid[row + 1][column], currentSumPath);
      currentSumPath.removeLast();
    }
    if (column < columns - 1) {
      currentSumPath.add(List.of(row, column + 1));
      visit(grid, row, column + 1, currentSum + grid[row][column + 1], currentSumPath);
      currentSumPath.removeLast();
    }
  }
}
