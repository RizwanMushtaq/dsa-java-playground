public class PalindromeCheck {
  public static void main(String[] args) {
    System.out.println("Palindrome Check");
    System.out.println("Is Naan palindrome: " + isPalindrome("NAAN"));
    System.out.println("Is twotwo palindrome: " + isPalindrome("twotwo"));
    System.out.println("Is level palindrome: " + isPalindrome("level"));
  }

  private static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }

      left++;
      right--;
    }
    return true;
  }
}
