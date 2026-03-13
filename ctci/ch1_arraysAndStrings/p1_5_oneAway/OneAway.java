package ctci.ch1_arraysAndStrings.p1_5_oneAway;

/**
 * There are three types of edits that can be performed on strings, insert a charcater, remove a
 * character, or replace a character. Given two strings write a function if they are one edit (or
 * zero edits) away.
 *
 * <p>example: pale, ple -> true pales, pale -> true pale, bale -> true pale, bake -> false
 */
class OneAway {
  public static void main() {
    System.out.println("One Away");
    OneAway oneAway = new OneAway();
    System.out.println(oneAway.solve("pale", "ple"));
    System.out.println(oneAway.solve("pales", "baless"));
    System.out.println(oneAway.solve("pale", "bale"));
    System.out.println(oneAway.solve("pal", "pale"));
  }

  /** The runtime of this algorithm is O(n) where n is a length of shorter string. */
  boolean solve(String s1, String s2) {
    int s1Length = s1.length();
    int s2Length = s2.length();

    if (s1Length == s2.length()) {
      return isCharReplaced(s1, s2);
    } else if (s1Length - 1 == s2Length) {
      return isCharInserted(s1, s2);
    } else if (s1Length + 1 == s2Length) {
      return isCharInserted(s2, s1);
    }

    return false;
  }

  boolean isCharReplaced(String s1, String s2) {
    int noOfDifferences = 0;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        noOfDifferences++;
        if (noOfDifferences > 1) {
          return false;
        }
      }
    }
    return true;
  }

  boolean isCharInserted(String s1, String s2) {
    int noOfDifferences = 0;
    int p1 = 0, p2 = 0;
    while (p1 < s1.length() && p2 < s2.length()) {
      if (s1.charAt(p1) != s2.charAt(p2)) {
        noOfDifferences++;
        if (noOfDifferences > 1) {
          return false;
        }
        p1++;
      } else {
        p1++;
        p2++;
      }
    }
    return true;
  }
}
