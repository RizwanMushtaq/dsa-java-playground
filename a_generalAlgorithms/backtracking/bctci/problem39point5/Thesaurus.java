package a_generalAlgorithms.backtracking.bctci.problem39point5;

import java.util.*;
import java.util.stream.Collectors;

public class Thesaurus {
  private List<String> result;
  private List<String> input;
  private Map<String, List<String>> synonymsMap;
  private List<String> processed;

  public static void main(String[] args) {
    String str = "one does not simply walk into mordor";
    HashMap<String, List<String>> synonyms = new HashMap<>();
    synonyms.put("walk", List.of("stroll", "hike", "wander"));
    synonyms.put("simply", List.of("just", "merely"));
    System.out.println(new Thesaurus().solve(str, synonyms));
    new RunTests().runTests();
  }

  public List<String> solve(String str, Map<String, List<String>> synonyms) {
    result = new ArrayList<>();
    input = List.of(str.split(" "));
    synonymsMap = synonyms;
    processed = new ArrayList<>();
    visit(0);
    return result;
  }

  private void visit(int index) {
    if (index == input.size()) {
      result.add(String.join(" ", processed));
      return;
    }
    String word = input.get(index);
    List<String> synList = synonymsMap.containsKey(word) ? synonymsMap.get(word) : List.of(word);
    for (String syn : synList) {
      processed.add(syn);
      visit(index + 1);
      processed.removeLast();
    }
  }
}

class RunTests {
  public void runTests() {
    TestCase[] tests = {
      // Example from the book
      new TestCase(
          "one does not simply walk into mordor",
          new Object[][] {
            {"walk", new String[] {"stroll", "hike", "wander"}},
            {"simply", new String[] {"just", "merely"}}
          },
          new String[] {
            "one does not just stroll into mordor",
            "one does not just hike into mordor",
            "one does not just wander into mordor",
            "one does not merely stroll into mordor",
            "one does not merely hike into mordor",
            "one does not merely wander into mordor"
          }),
      // Edge case - no synonyms
      new TestCase("hello world", new Object[][] {}, new String[] {"hello world"}),
      // Single word with synonyms
      new TestCase(
          "walk",
          new Object[][] {{"walk", new String[] {"stroll", "hike"}}},
          new String[] {"stroll", "hike"}),
      // Multiple words, some with synonyms
      new TestCase(
          "I walk to the park",
          new Object[][] {{"walk", new String[] {"stroll", "hike"}}},
          new String[] {"I stroll to the park", "I hike to the park"})
    };

    Thesaurus solution = new Thesaurus();
    for (TestCase test : tests) {
      List<String> got = solution.solve(test.sentence, test.synonyms);
      List<String> wantList = Arrays.asList(test.want);
      Collections.sort(got);
      Collections.sort(wantList);

      if (!got.equals(wantList)) {
        throw new RuntimeException(
            String.format(
                "\nsolve(%s, %s): got: %s, want: %s\n",
                test.sentence, test.synonyms, got, wantList));
      } else {
        System.out.print(".");
      }
    }
  }

  private static class TestCase {
    String sentence;
    Map<String, List<String>> synonyms;
    String[] want;

    TestCase(String sentence, Object[][] synonymPairs, String[] want) {
      this.sentence = sentence;
      this.synonyms =
          Arrays.stream(synonymPairs)
              .collect(
                  Collectors.toMap(
                      pair -> (String) pair[0],
                      pair -> Arrays.stream((String[]) pair[1]).collect(Collectors.toList())));
      this.want = want;
    }
  }
}
