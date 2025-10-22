package recursion;

public class LaminalArrays {
  public static void main(String[] args) {
    new LaminalArraysTests().runTests();
  }

  public int solve(int[] arr) {
    return maximumLaminalSum(arr, 0, arr.length);
  }

  public int maximumLaminalSum(int[] arr, int l, int r) {
    if (r - l == 1) {
      return arr[l];
    }

    int mid = (l + r) / 2;
    int option1 = maximumLaminalSum(arr, l, mid);
    int option2 = maximumLaminalSum(arr, mid, r);
    int option3 = 0;
    for (int i = l; i < r; i++) {
      option3 += arr[i];
    }

    return Math.max(Math.max(option1, option2), option3);
  }
}

class LaminalArraysTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {3, -9, 2, 4, -1, 5, 5, -4}, 6},
      {new int[] {1}, 1},
      {new int[] {-1, -2}, -1},
      {new int[] {1, 2, 3, 4}, 10},
      {new int[] {-2, -1, -4, -3}, -1},
      {new int[] {1, -2, 3, -4, 5, -6, 7, -8, 9, -10, 11, -12, 13, -14, 15, -16}, 15},
    };

    LaminalArrays laminalArrays = new LaminalArrays();
    //    MaxLaminalSumInefficient solutionInefficient = new MaxLaminalSumInefficient();
    for (Object[] test : tests) {
      int[] arr = (int[]) test[0];
      int want = (Integer) test[1];
      int got = laminalArrays.solve(arr);
      if (got != want) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %d, want: %d\n", java.util.Arrays.toString(arr), got, want));
      } else {
        System.out.print(".");
      }
      //      int gotInefficient = solutionInefficient.solve(arr);
      //      if (gotInefficient != want) {
      //        throw new RuntimeException(
      //            String.format(
      //                "\nsolve(%s): got: %d, want: %d\n",
      //                java.util.Arrays.toString(arr), gotInefficient, want));
      //      }
    }
  }
}
