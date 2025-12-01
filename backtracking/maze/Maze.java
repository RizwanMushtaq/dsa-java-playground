package backtracking.maze;

public class Maze {
  public static void main(String[] args) {
    System.out.println(solve(3, 3));
    System.out.println(solve(2, 2));
    path("", 2, 2);
    path("", 3, 3);
  }

  private static int solve(int r, int c) {
    return count(r, c);
  }

  private static int count(int r, int c) {
    if (r == 1 || c == 1) return 1;
    int down = count(r - 1, c);
    int right = count(r, c - 1);
    return down + right;
  }

  private static void path(String processed, int r, int c) {
    if (r == 1 && c == 1) {
      System.out.println(processed);
      return;
    }

    if (r > 1) path(processed.concat("D"), r - 1, c);
    if (c > 1) path(processed.concat("R"), r, c - 1);
  }

  //  private static int count(int r, int c, int i, int j) {
  //    if (i == r - 1 || j == c - 1) return 1;
  //    int down = count(r, c, i + 1, j);
  //    int right = count(r, c, i, j + 1);
  //    return down + right;
  //  }
}
