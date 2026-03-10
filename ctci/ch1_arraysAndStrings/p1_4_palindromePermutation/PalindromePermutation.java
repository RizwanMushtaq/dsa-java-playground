package ctci.ch1_arraysAndStrings.p1_4_palindromePermutation;

import java.util.HashMap;
import java.util.Set;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is
 * a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of
 * letters. The palindrome does not need to be limited to just dictionary words.
 */
public class PalindromePermutation {
  public static void main() {
    PalindromePermutation palindromePermutation = new PalindromePermutation();
    System.out.println(palindromePermutation.solve1("hellohello"));
    System.out.println(palindromePermutation.solve1("hellohel"));
  }

  /**
   * What does it take to be able to write a set of characters the same way forwards and backwards?
   * We need to have an even number of almost all characters, so that half can be on one side and
   * half can be on the other side. At most one character (the middle character) can have an odd
   * count.
   *
   * <p>To be more precise, strings with even length must have all even count of characters. Strings
   * with odd length must have exactly one character with odd count.
   *
   * <p>Implementation #1 -> We use a hash table to count how many times each character appears.
   * Then we iterate through the hash table and ensure that no more than one character has an odd
   * count if string has odd length. If the length of string is even, then there should be no odd
   * count of any character.
   *
   * <p>Time Complexity -> O(n) where n is size of string.
   */
  boolean solve1(String str) {
    int strLength = str.length();
    HashMap<Character, Integer> charToCount = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (charToCount.containsKey(ch)) {
        int count = charToCount.get(ch);
        count++;
        charToCount.put(ch, count);
      } else {
        charToCount.put(ch, 1);
      }
    }

    Set<Character> keySet = charToCount.keySet();
    if (strLength % 2 == 0) {
      for (char key : keySet) {
        if (charToCount.get(key) % 2 != 0) {
          return false;
        }
      }
    } else {
      int oddCount = 0;
      for (char key : keySet) {
        if (charToCount.get(key) % 2 != 0) {
          oddCount++;
          if (oddCount > 1) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
