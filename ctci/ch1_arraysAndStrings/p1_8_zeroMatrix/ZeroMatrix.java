package ctci.ch1_arraysAndStrings.p1_8_zeroMatrix;

/**
 * Write an algorithm such that if an element in an M*N matrix is 0, the entire row and column is
 * set to 0.
 */
public class ZeroMatrix {
  public static void main() {
    System.out.println("zero matrix");

    int[][] matrix2 = {
      {1, 2, 3, 4},
      {5, 6, 0, 8},
      {9, 10, 11, 11},
      {13, 14, 15, 16},
    };
    ZeroMatrix zeroMatrix = new ZeroMatrix();
    zeroMatrix.printMatrix(matrix2);
    zeroMatrix.solve(matrix2);
    zeroMatrix.printMatrix(matrix2);
  }

  /** The runtime for this approach is O(n^2) */
  void solve(int[][] matrix) {
    boolean[] row = new boolean[matrix.length];
    boolean[] column = new boolean[matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          row[i] = true;
          column[j] = true;
        }
      }
    }
    // Nullify rows
    for (int i = 0; i < row.length; i++) {
      if (row[i]) nullifyRow(matrix, i);
    }
    // Nullify Column
    for (int j = 0; j < column.length; j++) {
      if (column[j]) nullifyColumn(matrix, j);
    }
  }

  void nullifyRow(int[][] matrix, int row) {
    for (int j = 0; j < matrix[0].length; j++) {
      matrix[row][j] = 0;
    }
  }

  void nullifyColumn(int[][] matrix, int column) {
    for (int i = 0; i < matrix.length; i++) {
      matrix[i][column] = 0;
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
}
