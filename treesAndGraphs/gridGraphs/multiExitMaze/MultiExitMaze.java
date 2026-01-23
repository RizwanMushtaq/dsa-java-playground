package treesAndGraphs.gridGraphs.multiExitMaze;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class MultiExitMaze {
  private final int[][] DIRECTIONS = {
    {-1, 0}, {1, 0}, {0, -1}, {0, 1},
  };
  private char[][] maze;
  private int[][] distances;

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int[][] solve(List<String> mazeRows) {
    maze = new char[mazeRows.size()][];
    for (int i = 0; i < mazeRows.size(); i++) {
      maze[i] = mazeRows.get(i).toCharArray();
    }
    int rows = maze.length;
    int columns = maze[0].length;
    distances = new int[rows][columns];
    for (int[] row : distances) {
      Arrays.fill(row, -1);
    }
    Queue<int[]> queue = new LinkedList<>();
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (maze[r][c] == 'O') {
          distances[r][c] = 0;
          queue.add(new int[] {r, c});
        }
      }
    }
    while (!queue.isEmpty()) {
      int[] curr = queue.remove();
      int r = curr[0], c = curr[1];
      for (int[] dir : DIRECTIONS) {
        int nbrR = r + dir[0], nbrC = c + dir[1];
        if (isValid(nbrR, nbrC)) {
          distances[nbrR][nbrC] = distances[r][c] + 1;
          queue.add(new int[] {nbrR, nbrC});
        }
      }
    }
    return distances;
  }

  private boolean isValid(int r, int c) {
    return r >= 0
        && r < maze.length
        && c >= 0
        && c < maze[0].length
        && maze[r][c] != 'X'
        && distances[r][c] == -1;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example from book
      {
        new String[] {"...X.O", "OX.X..", "...X..", ".X....", "XOX.XX"},
        new int[][] {
          {1, 2, 3, -1, 1, 0},
          {0, -1, 4, -1, 2, 1},
          {1, 2, 3, -1, 3, 2},
          {2, -1, 4, 5, 4, 3},
          {-1, 0, -1, 6, -1, -1}
        }
      },
      // Single exit
      {
        new String[] {"...", ".O.", "..."},
        new int[][] {
          {2, 1, 2},
          {1, 0, 1},
          {2, 1, 2}
        }
      },
      // Multiple exits
      {
        new String[] {"O.O", "...", "O.O"},
        new int[][] {
          {0, 1, 0},
          {1, 2, 1},
          {0, 1, 0}
        }
      },
      // Walls blocking direct paths
      {
        new String[] {"O.X.", "XX..", "...O"},
        new int[][] {
          {0, 1, -1, 2},
          {-1, -1, 2, 1},
          {3, 2, 1, 0}
        }
      },
      // Single cell
      {new String[] {"O"}, new int[][] {{0}}}
    };

    MultiExitMaze solution = new MultiExitMaze();
    for (Object[] test : tests) {
      String[] mazeArray = (String[]) test[0];
      List<String> maze = Arrays.stream(mazeArray).collect(Collectors.toList());
      int[][] want = (int[][]) test[1];
      int[][] got = solution.solve(maze);
      if (!Arrays.deepEquals(got, want)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %s, want: %s\n",
                maze, Arrays.deepToString(got), Arrays.deepToString(want)));
      } else {
        System.out.print(".");
      }
    }
  }
}
