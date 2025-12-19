package a_generalAlgorithms.recursion.kunalPlaylist.introToRecursion.easy;

public class ReverseNumber {
  static int sum = 0;

  public static void main(String[] args) {
    System.out.println(rev2(1234321));
  }

  static void rev1(int n) {
    if (n == 0) return;
    int rem = n % 10;
    sum = sum * 10 + rem;
    rev1(n / 10);
  }

  static int rev2(int n) {
    int digits = (int) (Math.log10(n)) + 1;
    return rev2Helper(n, digits);
  }

  static int rev2Helper(int n, int digits) {
    if (n % 10 == 0) return n;
    int rem = n % 10;
    return rem * (int) Math.pow(10, digits - 1) + rev2Helper(n / 10, digits - 1);
  }
}
