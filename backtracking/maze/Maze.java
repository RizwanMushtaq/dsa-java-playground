package backtracking.maze;

public class Maze {
  public static void main(String[] args) {
    System.out.println(solve(3, 3));
    System.out.println(solve(2, 2));
  }

  private static int solve(int r, int c) {
    return count(r, c, 0, 0);
  }

  private static int count(int r, int c, int i, int j) {
    if (i == r - 1 || j == c - 1) return 1;
    int down = count(r, c, i + 1, j);
    int right = count(r, c, i, j + 1);
    return down + right;
  }
}
