class ReverseCaseMatch {
  public static void main(String[] args) {
    Test test = new Test();
    test.runTests();
  }

  /** Solution got from Book */
  public boolean isReverseCaseMatch(String s) {
    int l = 0, r = s.length() - 1;

    while (l < s.length() && r >= 0) {
      if (!Character.isLowerCase(s.charAt(l))) {
        l++;
      } else if (!Character.isUpperCase(s.charAt(r))) {
        r--;
      } else {
        if (s.charAt(l) != Character.toLowerCase(s.charAt(r))) {
          return false;
        }
        l++;
        r--;
      }
    }

    return true;
  }

  /** Solution Implemented by Rizwan */
  /*
  public boolean isReverseCaseMatch(String s) {
    int l = 0, r = s.length() - 1;
    String l_word = "", r_word = "";

    while (l < s.length() && r >= 0) {
      if (Character.isLowerCase(s.charAt(l))) {
        l_word += s.charAt(l);
      }

      if (Character.isUpperCase(s.charAt(r))) {
        r_word += s.charAt(r);
      }

      l++;
      r--;
    }

    System.out.println(l_word.toUpperCase());
    System.out.println(r_word);

    if (l_word.equalsIgnoreCase(r_word)) {
      return true;
    }

    return false;
  }
  */
}

class Test {
  public void runTests() {
    Object[][] tests = {
      {"haDrRAHd", true},
      {"haHrARDd", false}
    };

    ReverseCaseMatch reverseCaseMatch = new ReverseCaseMatch();
    for (Object[] test : tests) {
      String s = (String) test[0];
      boolean want = (boolean) test[1];
      boolean got = reverseCaseMatch.isReverseCaseMatch(s);
      if (got != want) {
        throw new RuntimeException(String.format("\nsolve(%s): got: %b, want: %b\n", s, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
