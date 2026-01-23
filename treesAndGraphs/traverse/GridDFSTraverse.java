package treesAndGraphs.traverse;

import java.util.ArrayList;
import java.util.List;

public class GridDFSTraverse {
  private int[][] grid;
  private int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
  private boolean[][] visited;
  private int rows;
  private int columns;

  public static void main(String[] args) {
    GridDFSTraverse traverse = new GridDFSTraverse();
    int[][] grid1 = {
      {0, 0, 1, 0},
      {1, 1, 0, 1},
      {0, 0, 1, 1}
    };
    System.out.println("graph: " + grid1 + " dfs: " + traverse.dfs(grid1));
  }

  private List<String> dfs(int[][] grid) {
    this.grid = grid;
    if (grid.length == 0) return new ArrayList<>();
    rows = grid.length;
    columns = grid[0].length;
    visited = new boolean[rows][columns];
    List<String> res = new ArrayList<>();
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (grid[r][c] == 1 && !visited[r][c]) {
          visited[r][c] = true;
          res.add(r + "," + c);
          visit(r, c, res);
        }
      }
    }
    return res;
  }

  private void visit(int r, int c, List<String> res) {
    for (int[] dir : directions) {
      int nbrR = r + dir[0];
      int nbrC = c + dir[1];
      if (isValid(nbrR, nbrC)) {
        res.add(nbrR + "," + nbrC);
        visited[nbrR][nbrC] = true;
        visit(nbrR, nbrC, res);
      }
    }
  }

  private boolean isValid(int r, int c) {
    return r >= 0 && r < rows && c >= 0 && c < columns && !visited[r][c] && grid[r][c] == 1;
  }
}
