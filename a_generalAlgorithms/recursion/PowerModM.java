package a_generalAlgorithms.recursion;

public class PowerModM {
  public static void main(String[] args) {
    new PowerModMTests().runTests();
  }

  public int solve(int a, int p, int m) {
    if (p == 0) {
      return 1;
    }

    if (p % 2 == 0) {
      long half = solve(a, p / 2, m);
      return (int) ((half * half) % m);
    }

    return (int) (((long) a * solve(a, p - 1, m)) % m);
  }
}

class PowerModMTests {
  public void runTests() {
    Object[][] tests = {
      {new int[] {2, 5, 100}, 32},
      {new int[] {2, 5, 30}, 2},
      {new int[] {2, 0, 10}, 1},
      {new int[] {3, 1, 5}, 3},
      {new int[] {5, 3, 7}, 6},
      {new int[] {123456789, 987654321, 1000000007}, 652541198},
    };

    PowerModM solution = new PowerModM();
    for (Object[] test : tests) {
      int[] params = (int[]) test[0];
      int want = (Integer) test[1];
      int got = solution.solve(params[0], params[1], params[2]);
      if (got != want) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%d, %d, %d): got: %d, want: %d\n",
                params[0], params[1], params[2], got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
