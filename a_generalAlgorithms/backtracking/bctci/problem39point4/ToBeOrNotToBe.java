package a_generalAlgorithms.backtracking.bctci.problem39point4;

import java.util.*;

public class ToBeOrNotToBe {
  private List<String> result;

  public static void main(String[] args) {
    System.out.println(new ToBeOrNotToBe().solve("I love dogs"));
    new RunTests().runTests();
  }

  public List<String> solve(String str) {
    result = new ArrayList<>();
    List<String> input = str.isEmpty() ? new ArrayList<>() : Arrays.asList(str.split(" "));
    visit(input, 0, new ArrayList<>());
    return result;
  }

  private void visit(List<String> input, int index, List<String> processed) {
    if (index == input.size()) {
      result.add(String.join(" ", processed));
      return;
    }
    visit(input, index + 1, processed);
    processed.add(input.get(index));
    visit(input, index + 1, processed);
    processed.removeLast();
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      // Example from the book
      {
        "I love dogs",
        new String[] {"", "I", "love", "dogs", "I love", "I dogs", "love dogs", "I love dogs"}
      },
      // Edge case - empty sentence
      {"", new String[] {""}},
      // Single word
      {"hello", new String[] {"", "hello"}},
      // Two words
      {"hello world", new String[] {"", "hello", "world", "hello world"}},
    };

    ToBeOrNotToBe solution = new ToBeOrNotToBe();
    for (Object[] test : tests) {
      String sentence = (String) test[0];
      String[] want = (String[]) test[1];
      List<String> got = solution.solve(sentence);
      Collections.sort(got);
      List<String> wantList = Arrays.asList(want);
      Collections.sort(wantList);

      if (!got.equals(wantList)) {
        throw new RuntimeException(
            String.format("\nsolve(\"%s\"): got: %s, want: %s\n", sentence, got, wantList));
      } else {
        System.out.print(".");
      }
    }
  }
}
