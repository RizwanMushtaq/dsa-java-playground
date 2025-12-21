package a_generalAlgorithms.backtracking.bctci.maxSumPath;

public class MaxSumPath {
  public static void main(String[] args) {
    MaxSumPathCal maxSumPathCal = new MaxSumPathCal();
    int[][] grid = {
      {1, 4, 3},
      {2, 7, 6},
      {5, 8, 9}
    };
    System.out.println(maxSumPathCal.getMaxSum(grid));
    System.out.println(maxSumPathCal.maxSumPathString);
  }
}

/**
 * You are given a matrix of integers and you need to find maximum sum path from origin to bottom
 * right of the matrix.
 */
class MaxSumPathCal {
  public int maxSum = Integer.MIN_VALUE;
  public String maxSumPathString;
  private int rows;
  private int columns;

  public int getMaxSum(int[][] grid) {
    rows = grid.length;
    columns = grid[0].length;
    String initialPath = 0 + "" + 0;
    maxSumHelper(grid, 0, 0, grid[0][0], initialPath);
    return maxSum;
  }

  private void maxSumHelper(int[][] grid, int row, int column, int currSum, String currPath) {
    if (row == rows - 1 && column == columns - 1) {
      if (currSum > maxSum) {
        maxSum = currSum;
        maxSumPathString = currPath;
      }
      return;
    }
    if (row < rows - 1)
      maxSumHelper(
          grid,
          row + 1,
          column,
          currSum + grid[row + 1][column],
          currPath + "->" + (row + 1) + column);
    if (column < columns - 1)
      maxSumHelper(
          grid,
          row,
          column + 1,
          currSum + grid[row][column + 1],
          currPath + "->" + row + (column + 1));
  }
}
