package a_generalAlgorithms.dynamicProgramming.bctci.problem40point4;

/**
 * If we can move right, bottom, diagonal -> how many paths are there to move from top-left to
 * bottom-right corner.
 */
public class NoOfPaths {
  private int rows, cols;
  private int[][] grid, memo;

  public static void main(String[] args) {
    NoOfPaths noOfPaths = new NoOfPaths();
    System.out.println(noOfPaths.solve(2, 2));
    System.out.println(noOfPaths.solve(3, 3));
  }

  public int solve(int inputRows, int inputCols) {
    rows = inputRows;
    cols = inputCols;
    memo = new int[rows][cols];
    return visit(0, 0);
  }

  private int visit(int row, int col) {
    if (row == rows - 1) return 1;
    if (col == cols - 1) return 1;
    if (memo[row][col] != 0) return memo[row][col];
    return memo[row][col] = visit(row + 1, col) + visit(row, col + 1) + visit(row + 1, col + 1);
  }
}
