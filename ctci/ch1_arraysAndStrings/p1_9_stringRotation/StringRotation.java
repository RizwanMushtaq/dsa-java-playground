package ctci.ch1_arraysAndStrings.p1_9_stringRotation;

/**
 * Assume you have a method isSubstring which checks if one word is substring of another. Given two
 * strings s1 and s2 arite code to check if s2 is a rotation of s1 using only one call to
 * isSubstring. for example "waterbottle" is a rotation of "erbottlewat"
 */
public class StringRotation {
  public static void main() {
    System.out.println("string rotation");
    StringRotation sr = new StringRotation();
    System.out.println(sr.solve("waterbottle", "erbottlewat"));
    System.out.println(sr.solve("system", "temsyss"));
  }

  /**
   * The runtime of this varies based on the runtime of isSubstring, so talking about isSubstring
   * method -> s1.contains(s2); -> it will have O(m .n) where m and n are length of s1 and s2, in
   * worst case.
   *
   * <p>Except isSubstring method, we can think for the runtime of this approach is O(n) where n is
   * length of string s1 for code following code:
   *
   * <p>String s1s1 = s1 + s1; as Linear scan to copy all characters to a new array.
   *
   * <p>Space Complexity -> O(n) -> A new string object of size must be created.
   */
  boolean solve(String s1, String s2) {
    if (s1.length() != s2.length() || s1.isEmpty()) return false;

    String s1s1 = s1 + s1;
    return isSubstring(s1s1, s2);
  }

  boolean isSubstring(String s1, String s2) {
    return s1.contains(s2);
  }
}
