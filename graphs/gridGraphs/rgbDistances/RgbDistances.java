package graphs.gridGraphs.rgbDistances;

import java.util.*;
import java.util.stream.Collectors;

public class RgbDistances {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int[][] solve(List<String> screen) {
    int rows = screen.size();
    int cols = screen.getFirst().length();
    int[][] output = new int[rows][cols];
    Map<Character, Character> targets = new HashMap<>();
    targets.put('R', 'G');
    targets.put('G', 'B');
    targets.put('B', 'R');
    for (Map.Entry<Character, Character> entry : targets.entrySet()) {
      char color = entry.getKey();
      char target = entry.getValue();
      List<int[]> sources = getSources(screen, target);
      int[][] distances = multisourceBfs(screen, sources);

      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          if (screen.get(i).charAt(j) == color) {
            output[i][j] = distances[i][j];
          }
        }
      }
    }
    return output;
  }

  private int[][] multisourceBfs(List<String> screen, List<int[]> sources) {
    int rows = screen.size(), cols = screen.getFirst().length();
    int[][] distances = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      Arrays.fill(distances[i], -1);
    }
    Queue<int[]> queue = new LinkedList<>();
    for (int[] p : sources) {
      queue.add(p);
      distances[p[0]][p[1]] = 0;
    }
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while (!queue.isEmpty()) {
      int[] p = queue.remove();
      for (int[] dir : dirs) {
        int nr = p[0] + dir[0];
        int nc = p[1] + dir[1];
        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && distances[nr][nc] == -1) {
          distances[nr][nc] = distances[p[0]][p[1]] + 1;
          queue.add(new int[] {nr, nc});
        }
      }
    }
    return distances;
  }

  private List<int[]> getSources(List<String> screen, char target) {
    List<int[]> sources = new ArrayList<>();
    for (int i = 0; i < screen.size(); i++) {
      for (int j = 0; j < screen.getFirst().length(); j++) {
        if (screen.get(i).charAt(j) == target) {
          sources.add(new int[] {i, j});
        }
      }
    }
    return sources;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example from the book
      {
        new String[] {"RRRGRB", "BGRGRR", "RRRGRR", "RGRRRR", "GBGRGG"},
        new int[][] {
          {2, 1, 1, 2, 1, 1},
          {1, 1, 1, 3, 1, 2},
          {2, 1, 1, 4, 1, 2},
          {1, 1, 1, 1, 1, 1},
          {1, 2, 1, 1, 3, 4}
        }
      },
      // Single row
      {new String[] {"RGB"}, new int[][] {{1, 1, 2}}},
      // Single column
      {new String[] {"R", "G", "B"}, new int[][] {{1}, {1}, {2}}},
      // All colors adjacent
      {
        new String[] {"RGB", "BGR"},
        new int[][] {
          {1, 1, 1},
          {1, 1, 1}
        }
      }
    };

    RgbDistances solution = new RgbDistances();
    for (Object[] test : tests) {
      String[] screenArray = (String[]) test[0];
      List<String> screen = Arrays.stream(screenArray).collect(Collectors.toList());
      int[][] want = (int[][]) test[1];
      int[][] got = solution.solve(screen);
      if (!Arrays.deepEquals(got, want)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %s, want: %s\n",
                screen, Arrays.deepToString(got), Arrays.deepToString(want)));
      } else {
        System.out.print(".");
      }
    }
  }
}
