package backtracking.kunal;

import java.util.ArrayList;

public class Maze {
  public static void main(String[] args) {

    System.out.println(pathsList("", 3, 3));
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
  // cell such as (0,0)
  private static ArrayList<String> pathsList(String processed, int r, int c) {
    if (r == 1 && c == 1) {
      ArrayList<String> list = new ArrayList<>();
      list.add(processed);
      return list;
    }

    ArrayList<String> down = new ArrayList<>();
    ArrayList<String> right = new ArrayList<>();
    if (r > 1) {
      down = pathsList(processed.concat("D"), r - 1, c);
    }
    if (c > 1) {
      right = pathsList(processed.concat("R"), r, c - 1);
    }
    down.addAll(right);
    return down;
  }
}
