package a_generalAlgorithms.dynamicProgramming.fibonacci;

public class Fibonacci {
  public static void main(String[] args) {
    System.out.println(fibDP(8));
  }

  private static int recursiveFibonacci(int n) {
    if (n < 2) return n;
    return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
  }

  private static int fibDP(int n) {
    int[] memo = new int[n + 1];
    return fibDPHelper(n, memo);
  }

  private static int fibDPHelper(int n, int[] memo) {
    if (n == 1) return 1;
    if (n == 2) return 1;
    if (memo[n] != 0) return memo[n];

    memo[n] = fibDPHelper(n - 1, memo) + fibDPHelper(n - 2, memo);
    return memo[n];
  }
}
