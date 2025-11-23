package slidingWindows.minimumWindows.shortestWithAllLetters;

import java.util.*;

public class ShortestWithAllLetters {
  public static void main(String[] args) {
    new RunTests().runTests();
  }

  int solve(String s1, String s2) {
    int l = 0, r = 0;
    Map<Character, Integer> missing = new HashMap<>();
    for (char c : s2.toCharArray()) {
      missing.put(c, missing.getOrDefault(c, 0) + 1);
    }
    int distinctMissing = missing.size();
    int curMin = Integer.MAX_VALUE;
    while (true) {
      boolean mustGrow = distinctMissing > 0;
      if (mustGrow) {
        if (r == s1.length()) break;
        char c = s1.charAt(r);
        if (missing.containsKey(c)) {
          missing.put(c, missing.get(c) - 1);
          if (missing.get(c) == 0) distinctMissing--;
        }
        r++;
      } else {
        curMin = Math.min(curMin, r - l);
        char c = s1.charAt(l);
        if (missing.containsKey(c)) {
          missing.put(c, missing.get(c) + 1);
          if (missing.get(c) == 1) distinctMissing++;
        }
        l++;
      }
    }
    return curMin != Integer.MAX_VALUE ? curMin : -1;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {"helloworld", "well", 5},
      {"helloworld", "weelll", -1},
      // Edge case - s2 is single character
      {"hello", "l", 1},
      // s2 not in s1
      {"hello", "z", -1},
    };

    ShortestWithAllLetters solution = new ShortestWithAllLetters();
    for (Object[] test : tests) {
      String s1 = (String) test[0];
      String s2 = (String) test[1];
      int want = (int) test[2];
      int got = solution.solve(s1, s2);
      if (got != want) {
        throw new RuntimeException(
            String.format("\nsolve(%s, %s): got: %d, want: %d\n", s1, s2, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
