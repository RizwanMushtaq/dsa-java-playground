import java.util.*;

/** Reverse an Array using O(1) extra space */
class ArrayReversal {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public void solve(char[] arr) {
    int l = 0, r = arr.length - 1;

    while (l < r) {
      swap(arr, l, r);
      l++;
      r--;
    }
  }

  private void swap(char[] arr, int l, int r) {
    char temp = arr[l];
    arr[l] = arr[r];
    arr[r] = temp;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {"hello".toCharArray(), "olleh".toCharArray()},
      {"".toCharArray(), "".toCharArray()},
      {"a".toCharArray(), "a".toCharArray()},
      {"ab".toCharArray(), "ba".toCharArray()},
      {"abc".toCharArray(), "cba".toCharArray()},
      {"abcd".toCharArray(), "dcba".toCharArray()},
      {"12345".toCharArray(), "54321".toCharArray()},
    };

    ArrayReversal solution = new ArrayReversal();
    for (Object[] test : tests) {
      char[] arr = Arrays.copyOf((char[]) test[0], ((char[]) test[0]).length);
      char[] want = (char[]) test[1];
      solution.solve(arr);
      if (!Arrays.equals(arr, want)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %s, want: %s\n",
                Arrays.toString((char[]) test[0]), Arrays.toString(arr), Arrays.toString(want)));
      } else {
        System.out.print(".");
      }
    }
  }
}
