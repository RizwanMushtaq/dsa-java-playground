package backtracking.kunal;

import java.util.ArrayList;

// Total number of Permutations of a String with n characters are n! -> n
// factorial
public class Permutations {
  public static void main(String[] args) {
    System.out.println(countPermutationsFormula("abc"));
  }

  // It will print all possible permutations of String
  private static void printPermutations(String str) {
    printPermutationsHelper("", str);
  }

  private static void printPermutationsHelper(String p, String up) {
    if (up.isEmpty()) {
      System.out.println(p);
      return;
    }

    char ch = up.charAt(0);
    for (int i = 0; i <= p.length(); i++) {
      String first = p.substring(0, i);
      String second = p.substring(i);
      printPermutationsHelper(first + ch + second, up.substring(1));
    }
  }

  private static ArrayList<String> permutationsList(String str) {
    return permutationsListHelper("", str);
  }

  private static ArrayList<String> permutationsListHelper(String p, String up) {
    if (up.isEmpty()) {
      ArrayList<String> list = new ArrayList<>();
      list.add(p);
      return list;
    }

    char ch = up.charAt(0);
    ArrayList<String> result = new ArrayList<>();
    for (int i = 0; i <= p.length(); i++) {
      String first = p.substring(0, i);
      String second = p.substring(i);
      result.addAll(permutationsListHelper(first + ch + second, up.substring(1)));
    }
    return result;
  }

  private static int countPermutations(String str) {
    return countPermutationsHelper("", str);
  }

  private static int countPermutationsHelper(String p, String up) {
    if (up.isEmpty()) {
      return 1;
    }

    char ch = up.charAt(0);
    int count = 0;
    for (int i = 0; i <= p.length(); i++) {
      String first = p.substring(0, i);
      String second = p.substring(i);
      count += countPermutationsHelper(first + ch + second, up.substring(1));
    }
    return count;
  }

  private static int countPermutationsFormula(String str) {
    return factorial(str.length());
  }

  private static int factorial(int n) {
    if (n < 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }
}
