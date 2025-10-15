package strings;

import java.util.*;

class StringSplit {
  public static void main(String[] args) {
    System.out.println("String Split Problem");

    String originalString = "split by space";
    String[] result = splitString(originalString, ' ');
    System.out.println("Original String: " + originalString);
    System.out.println(Arrays.toString(result));
  }

  private static String[] splitString(String s, char c) {
    List<String> result = new ArrayList<>();

    String word = "";
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == c) {
        result.add(word);
        word = "";
      } else {
        word = word + s.charAt(i);
      }
    }

    if (word.length() > 0) {
      result.add(word);
    }

    return result.toArray(new String[0]);

    /**
     * Using Stream to convert List of Strings to String Array
     *
     * <p>return result.stream().toArray(String[]::new);
     */
  }
}
