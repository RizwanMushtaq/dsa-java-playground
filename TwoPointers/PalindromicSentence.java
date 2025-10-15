package TwoPointers;

import java.util.*;

class PalindromicSentence {
  public static void main(String[] args) {
    System.out.println("PalindromicSentence Problem");
    RunTests test = new RunTests();
    test.runTests();
  }

  public static boolean isPalindromicSentence(String s) {
    int l = 0, r = s.length() - 1;

    while (l < r) {
      if (!Character.isLetter(s.charAt(l))) {
        l++;
      } else if (!Character.isLetter(s.charAt(r))) {
        r--;
      } else {
        if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
          return false;
        }
        l++;
        r--;
      }
    }

    return true;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {"Bob wondered, 'Now, Bob?'", true},
      {"", true},
      {"a", true},
      {"A man, a plan, a canal: Panama", true},
      {"race a car", false},
      {"Was it a car or a cat I saw?", true},
      {"hello", false},
      {".,?!'", true},
    };

    PalindromicSentence solution = new PalindromicSentence();
    for (Object[] test : tests) {
      String s = (String) test[0];
      boolean want = (boolean) test[1];
      boolean got = solution.isPalindromicSentence(s);
      if (got != want) {
        throw new RuntimeException(String.format("\nsolve(%s): got: %b, want: %b\n", s, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
