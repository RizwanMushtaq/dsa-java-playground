package a_generalAlgorithms.recursion.kunalPlaylist.introToRecursion.easy;

public class NoOfZeros {
  public static void main(String[] args) {
    System.out.println(solve(450650005));
    System.out.println(solve(5));
  }

  static int solve(int n) {
    int count = 0;
    return helper(n, count);
  }

  static int helper(int n, int count) {
    if (n % 10 == n) return count;
    if (n % 10 == 0) {
      count++;
    }
    return helper(n / 10, count);
  }
}
