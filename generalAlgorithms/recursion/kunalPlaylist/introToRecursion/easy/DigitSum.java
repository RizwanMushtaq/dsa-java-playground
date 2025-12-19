package generalAlgorithms.recursion.kunalPlaylist.introToRecursion.easy;

public class DigitSum {
  public static void main(String[] args) {
    System.out.println(digitSum(1342));
  }

  static int digitSum(int n) {
    if (n == 0) return 0;
    return n % 10 + digitSum(n / 10);
  }
}
