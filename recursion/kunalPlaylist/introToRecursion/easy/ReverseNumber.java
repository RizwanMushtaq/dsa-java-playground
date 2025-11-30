package recursion.kunalPlaylist.introToRecursion.easy;

public class ReverseNumber {
  static int sum = 0;

  public static void main(String[] args) {
    rev1(456789);
    System.out.println(sum);
  }

  static void rev1(int n) {
    if (n == 0) return;
    int rem = n % 10;
    sum = sum * 10 + rem;
    rev1(n / 10);
  }
}
