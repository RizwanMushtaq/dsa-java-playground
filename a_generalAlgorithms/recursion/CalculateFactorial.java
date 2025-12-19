package a_generalAlgorithms.recursion;

public class CalculateFactorial {
  public static void main(String[] args) {
    new CalculateFactorialTests().runTests();
  }

  public int factorial(int num) {

    if (num == 0 || num == 1) {
      return 1;
    }
    return num * factorial(num - 1);
  }
}

class CalculateFactorialTests {
  private Object[][] tests = {
    {5, 120},
    {0, 1},
    {3, 6},
  };

  public void runTests() {
    CalculateFactorial calculateFactorial = new CalculateFactorial();
    for (Object[] test : tests) {
      int num = (int) test[0];
      int want = (int) test[1];
      int got = calculateFactorial.factorial(num);

      if (got != want) {
        throw new RuntimeException(
            String.format("\nsolve %d: got: %d " + "want: %d\n", num, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
