package generalAlgorithms.recursion;

import java.util.*;

public class NestedArraySumLazy {
  public static void main(String[] args) {
    new NestedArraySumTests().runTests();
  }

  public int solve(Object arr) {
    if (arr instanceof Integer) {
      return (Integer) arr;
    }

    if (arr instanceof List) {
      int res = 0;
      for (Object element : (List<?>) arr) {
        res += solve(element);
      }
      return res;
    }

    throw new IllegalArgumentException("Input must be Integer or List");
  }
}

class NestedArraySumTests {
  @SuppressWarnings("unchecked")
  public void runTests() {
    Object[][] tests = {
      {Arrays.asList(1, Arrays.asList(2, 3), Arrays.asList(4, Arrays.asList(5)), 6), 21},
      {Arrays.asList(Arrays.asList(Arrays.asList(Arrays.asList(1))), 2), 3},
      {new ArrayList<>(), 0},
      {5, 5},
      {Arrays.asList(Arrays.asList(Arrays.asList(Arrays.asList(Arrays.asList(1))))), 1},
      {Arrays.asList(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()), 0},
      {
        Arrays.asList(new ArrayList<>(), Arrays.asList(1, 2), new ArrayList<>(), Arrays.asList(3)),
        6
      },
      {
        Arrays.asList(
            1,
            Arrays.asList(2, new ArrayList<>(), Arrays.asList(3, new ArrayList<>())),
            Arrays.asList(4, Arrays.asList(5))),
        15
      },
      {Arrays.asList(0, Arrays.asList(0, 0), Arrays.asList(0, Arrays.asList(0)), 0), 0},
      {Arrays.asList(-1, Arrays.asList(-2, 3), Arrays.asList(4, Arrays.asList(-5)), 6), 5},
    };

    NestedArraySumLazy solution = new NestedArraySumLazy();
    for (Object[] test : tests) {
      Object arr = test[0];
      int want = (Integer) test[1];
      int got = solution.solve(arr);
      if (got != want) {
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %d, want: %d\n", arr, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
