package ctci.ch1_arraysAndStrings.p1_3_URLify;

import java.util.Arrays;

/**
 * Write a method to replace all spaces in the string with '%20'. You may assume that the string has
 * sufficient space at the end to hold the additional characters, and that your given the "true"
 * length of the string . (Note: if implementing in Java, please use a character array so that you
 * can perform this operation in place.)
 */
public class URLify {
  public static void main() {
    System.out.println("URLify");
    URLify urLify = new URLify();
    System.out.println(urLify.solve("he ll o"));
    System.out.println(urLify.solve("h i "));
  }

  /**
   * We will use a two scan approach. In the first scan we count the number of spaces. By trippling
   * this number, we can compute how many extra characters we will have in the final string. In the
   * second pass, which is done in reverse order, we actually edit the string. When we see a space
   * we replace it with %20. If there is no space we copy the original character.
   */
  String solve(String str) {
    char[] charArray = str.toCharArray();
    int length = charArray.length;
    int spaceCount = 0;
    for (char c : charArray) {
      if (c == ' ') {
        spaceCount++;
      }
    }
    int newLength = length + (spaceCount * 2);
    char[] newStr = new char[newLength];

    for (int i = length - 1; i >= 0; i--) {
      if (charArray[i] == ' ') {
        newStr[newLength - 1] = '0';
        newStr[newLength - 2] = '2';
        newStr[newLength - 3] = '%';
        newLength = newLength - 3;
      } else {
        newStr[newLength - 1] = charArray[i];
        newLength--;
      }
    }
    return Arrays.toString(newStr);
  }
}
