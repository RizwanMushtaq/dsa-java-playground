package backtracking.kunal;

import java.util.ArrayList;
import java.util.Arrays;

public class Maze {
  public static void main(String[] args) {
    boolean[][] grid = {
      {true, true, true},
      {true, true, true},
      {true, true, true},
    };
    int[][] path = new int[grid.length][grid[0].length];
    printAllPathsWithLevelsBacktrack("", grid, 0, 0, path, 1);
    //    printPaths("", 3, 3);
  }

  private static int solve(int r, int c) {
    return countPaths(r, c);
  }

  // counts paths for a given grid of size rows * columns such as 3*3
  private static int countPaths(int r, int c) {
    if (r == 1 || c == 1) return 1;
    int down = countPaths(r - 1, c);
    int right = countPaths(r, c - 1);
    return down + right;
  }

  // prints all the paths from top left cell such as (3,3) to top bottom
  // cell such as (0,0)
  private static void printPaths(String processed, int r, int c) {
    if (r == 1 && c == 1) {
      System.out.println(processed);
      return;
    }

    if (r > 1) printPaths(processed.concat("D"), r - 1, c);
    if (c > 1) printPaths(processed.concat("R"), r, c - 1);
  }

  // return List of all the paths from top left cell such as (3,3) to top bottom
  // cell such as (0,0) But you can only move right or down
  private static ArrayList<String> pathsList(String processed, int r, int c) {
    if (r == 1 && c == 1) {
      ArrayList<String> list = new ArrayList<>();
      list.add(processed);
      return list;
    }

    ArrayList<String> result = new ArrayList<>();
    if (r > 1) {
      result.addAll(pathsList(processed.concat("D"), r - 1, c));
    }
    if (c > 1) {
      result.addAll(pathsList(processed.concat("R"), r, c - 1));
    }
    return result;
  }

  // return List of all the paths from top left cell such as (3,3) to top bottom
  // cell such as (0,0) But you can only right or down or diagonal
  private static ArrayList<String> pathsListDiagonal(String processed, int r, int c) {
    if (r == 1 && c == 1) {
      ArrayList<String> list = new ArrayList<>();
      list.add(processed);
      return list;
    }

    ArrayList<String> result = new ArrayList<>();
    if (r > 1) {
      result.addAll(pathsListDiagonal(processed.concat("V"), r - 1, c));
    }
    if (r > 1 && c > 1) {
      result.addAll(pathsListDiagonal(processed.concat("D"), r - 1, c - 1));
    }
    if (c > 1) {
      result.addAll(pathsListDiagonal(processed.concat("H"), r, c - 1));
    }
    return result;
  }

  // print all the paths from top left cell such as (3,3) to top bottom
  // cell such as (0,0) But you can only right or down, You will also get
  // Boolean Matrix and based on true condition on a cell, you can move to
  // that cell otherwise there is obstacle
  private static void printPathsRestrictions(String processed, boolean[][] grid, int r, int c) {
    if (r == 0 && c == 0) {
      System.out.println(processed);
      return;
    }
    if (!grid[r][c]) return;
    if (r > 0) printPathsRestrictions(processed.concat("D"), grid, r - 1, c);
    if (c > 0) printPathsRestrictions(processed.concat("R"), grid, r, c - 1);
  }

  // Its Backtracking :)
  // Here you make a change and do some work, when that work is done, revert
  // the change.
  private static void printAllPathsBacktrack(String processed, boolean[][] grid, int r, int c) {
    if (r == grid.length - 1 && c == grid[0].length - 1) {
      System.out.println(processed);
      return;
    }
    if (!grid[r][c]) return;
    grid[r][c] = false; // I am considering this path
    if (r < grid.length - 1) printAllPathsBacktrack(processed.concat("D"), grid, r + 1, c);
    if (c < grid[0].length - 1) printAllPathsBacktrack(processed.concat("R"), grid, r, c + 1);
    if (c > 0) printAllPathsBacktrack(processed + "L", grid, r, c - 1);
    if (r > 0) printAllPathsBacktrack(processed + "U", grid, r - 1, c);
    // So before function gets removed, also remove the changes that were made
    // by that function
    grid[r][c] = true;
  }

  /*
  Now you nned to print path like DDRR as well as path inside matrix
   */
  private static void printAllPathsWithLevelsBacktrack(
      String processed, boolean[][] grid, int r, int c, int[][] path, int step) {
    if (r == grid.length - 1 && c == grid[0].length - 1) {
      path[r][c] = step;
      System.out.println(processed);
      for (int[] arr : path) {
        System.out.println(Arrays.toString(arr));
      }
      path[r][c] = 0;
      return;
    }
    if (!grid[r][c]) return;
    grid[r][c] = false; // I am considering this path
    path[r][c] = step;
    if (r < grid.length - 1)
      printAllPathsWithLevelsBacktrack(processed.concat("D"), grid, r + 1, c, path, step + 1);
    if (c < grid[0].length - 1)
      printAllPathsWithLevelsBacktrack(processed.concat("R"), grid, r, c + 1, path, step + 1);
    if (c > 0) printAllPathsWithLevelsBacktrack(processed + "L", grid, r, c - 1, path, step + 1);
    if (r > 0) printAllPathsWithLevelsBacktrack(processed + "U", grid, r - 1, c, path, step + 1);
    // So before function gets removed, also remove the changes that were made
    // by that function
    grid[r][c] = true;
    path[r][c] = 0;
  }
}
