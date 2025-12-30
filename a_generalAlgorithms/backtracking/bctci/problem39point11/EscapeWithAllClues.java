package a_generalAlgorithms.backtracking.bctci.problem39point11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EscapeWithAllClues {
  private int rows;
  private int columns;
  private int minPathCount;
  private List<List<Integer>> minPath;
  private int cluesFound;
  private List<List<Integer>> allClues;

  public static void main(String[] args) {
    int[][] room = {
      {0, 1, 0},
      {0, 2, 0},
      {0, 0, 2}
    };
    EscapeWithAllClues e1 = new EscapeWithAllClues();
    System.out.println(e1.solve(room));
    System.out.println(e1.allClues);
    System.out.println("************************");
    int[][] room2 = {
      {0, 0, 0},
      {2, 1, 2},
    };
    EscapeWithAllClues e2 = new EscapeWithAllClues();
    System.out.println(e2.solve(room2));
    System.out.println(e2.allClues);
  }

  public List<List<Integer>> solve(int[][] grid) {
    minPath = new ArrayList<>();
    rows = grid.length;
    columns = grid[0].length;
    minPathCount = 0;
    cluesFound = 0;
    List<List<Integer>> currentMinPath = new ArrayList<>();
    currentMinPath.add(List.of(0, 0));
    boolean[][] memo = new boolean[rows][columns];
    allClues = new ArrayList<>();
    getClues(grid);
    visit(grid, memo, 0, 0, 1, currentMinPath, 0);
    return minPath;
  }

  private void getClues(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 2) allClues.add(List.of(i, j));
      }
    }
  }

  private void visit(
      int[][] grid,
      boolean[][] memo,
      int row,
      int column,
      int minPathCount,
      List<List<Integer>> currentMinPath,
      int currentClues) {
    if (memo[row][column]) {
      return;
    }
    if (grid[row][column] == 1) return; // obstacle
    if (grid[row][column] == 2) currentClues++; // Clue found

    if (row == rows - 1 && column == columns - 1) {
      if (currentClues == allClues.size()) {
        minPath = currentMinPath.stream().map(ArrayList::new).collect(Collectors.toList());
      }
      return;
    }

    memo[row][column] = true;
    if (row > 0) {
      currentMinPath.add(List.of(row - 1, column));
      visit(
          grid,
          memo,
          row - 1,
          column,
          minPathCount + grid[row - 1][column],
          currentMinPath,
          currentClues);
      currentMinPath.removeLast();
    }
    if (row < rows - 1) {
      currentMinPath.add(List.of(row + 1, column));
      visit(
          grid,
          memo,
          row + 1,
          column,
          minPathCount + grid[row + 1][column],
          currentMinPath,
          currentClues);
      currentMinPath.removeLast();
    }
    if (column > 0) {
      currentMinPath.add(List.of(row, column - 1));
      visit(
          grid,
          memo,
          row,
          column - 1,
          minPathCount + grid[row][column - 1],
          currentMinPath,
          currentClues);
      currentMinPath.removeLast();
    }
    if (column < columns - 1) {
      currentMinPath.add(List.of(row, column + 1));
      visit(
          grid,
          memo,
          row,
          column + 1,
          minPathCount + grid[row][column + 1],
          currentMinPath,
          currentClues);
      currentMinPath.removeLast();
    }
    memo[row][column] = false;
  }
}
