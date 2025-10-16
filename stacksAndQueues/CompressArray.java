package stacksAndQueues;

import java.util.*;

public class CompressArray {
  public static void main(String[] args) {
    CompressArrayTests compressArrayTests = new CompressArrayTests();
    compressArrayTests.runTests();
  }

  public List<Integer> solve(int[] arr) {
    List<Integer> stack = new ArrayList<>();
    for (int num : arr) {
      while (!stack.isEmpty() && stack.get(stack.size() - 1) == num) {
        num = num + stack.remove(stack.size() - 1);
      }
      stack.add(num);
    }
    return stack;
  }
}

class CompressArrayTests {
  public void runTests() {
    TestCase[] tests = {
      new TestCase(new int[] {8, 4, 2, 2, 2, 4}, List.of(16, 2, 4)),
      new TestCase(new int[] {4, 4, 4, 4}, List.of(16)),
      new TestCase(new int[] {1, 2, 3, 4}, List.of(1, 2, 3, 4)),
      new TestCase(new int[] {}, List.of()),
      new TestCase(new int[] {0, 0, 0, 0}, List.of(0)),
    };

    CompressArray solution = new CompressArray();
    for (TestCase test : tests) {
      List<Integer> got = solution.solve(test.input);
      if (!got.equals(test.want)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s): got: %s, want: %s\n",
                java.util.Arrays.toString(test.input), got, test.want));
      } else {
        System.out.print(".");
      }
    }
  }

  private static class TestCase {
    final int[] input;
    final List<Integer> want;

    TestCase(int[] input, List<Integer> want) {
      this.input = input;
      this.want = want;
    }
  }
}
