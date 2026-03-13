package ctci.ch1_arraysAndStrings.p1_7_rotateMatrix;

/**
 * Given an image represented by N*N matrix, where each pixal in the image is a bytes, write a
 * method to rotate the image by 90 degrees. Can you do this in place?
 */
public class RotateMatrix {
  public static void main() {
    System.out.println("rotate matrix by 90 degree");
    RotateMatrix rotateMatrix = new RotateMatrix();
    int[][] matrix = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9},
    };
    rotateMatrix.printMatrix(matrix);
    rotateMatrix.solve1(matrix);
    rotateMatrix.printMatrix(matrix);

    int[][] matrix2 = {
      {1, 2, 3, 4},
      {5, 6, 7, 8},
      {9, 10, 11, 11},
      {13, 14, 15, 16},
    };
    rotateMatrix.printMatrix(matrix2);
    rotateMatrix.solve2(matrix2);
    rotateMatrix.printMatrix(matrix2);
  }

  /**
   * Approach1 -> Transpose the matrix and reverse the row.
   *
   * <p>The runtime for the approach is O(n^2)
   */
  void solve1(int[][] matrix) {
    int n = matrix.length;
    // transpose matrix
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
    // reverse each row
    for (int i = 0; i < n; i++) {
      int left = 0, right = n - 1;
      while (left < right) {
        int temp = matrix[i][left];
        matrix[i][left] = matrix[i][right];
        matrix[i][right] = temp;
        left++;
        right--;
      }
    }
  }

  void printMatrix(int[][] matrix) {
    System.out.println("[");
    for (int[] ints : matrix) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(ints[j] + ",");
      }
      System.out.println();
    }
    System.out.println("]");
    System.out.println();
  }

  /**
   * Approach2-> Layer by Layer 4 swap rotation
   *
   * <p>It has also same runtime O(n^2). This approach will test how well the candidate is aware of
   * matrix manipulation.
   */
  void solve2(int[][] matrix) {
    int n = matrix.length;
    for (int layer = 0; layer < n / 2; layer++) {
      @SuppressWarnings("intentionaly using first variable to better clarity")
      int first = layer;
      int last = n - 1 - layer;
      for (int i = first; i < last; i++) {
        int offset = i - first;
        // save top
        int top = matrix[first][i];
        // left -> top
        matrix[first][i] = matrix[last - offset][first];
        // bottom -> left
        matrix[last - offset][first] = matrix[last][last - offset];
        // right -> bottom
        matrix[last][last - offset] = matrix[i][last];
        // top -> right
        matrix[i][last] = top;
      }
    }
  }
}
