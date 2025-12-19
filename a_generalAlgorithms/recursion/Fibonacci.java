package a_generalAlgorithms.recursion;

import java.util.HashMap;

public class Fibonacci {
  public static void main(String[] args) {
    int n = 10;
    System.out.println("solveWithMemoization - Fibonacci(" + n + ") = " + solveWithMemoization(n));
    System.out.println("solveWithMemoization - Fibonacci(" + n + ") = " + solveWithRecursion(n));
    System.out.println("solveWithMemoization - Fibonacci(" + n + ") = " + solveWithIteration(n));

    int m = 4;
    System.out.println("solveWithMemoization - Fibonacci(" + m + ") = " + solveWithMemoization(m));
    System.out.println("solveWithMemoization - Fibonacci(" + m + ") = " + solveWithRecursion(m));
    System.out.println("solveWithMemoization - Fibonacci(" + m + ") = " + solveWithIteration(m));
  }

  public static int solveWithMemoization(int n) {
    HashMap<Integer, Integer> memo = new HashMap<>();

    if (n <= 1) {
      return n;
    }

    if (memo.containsKey(n)) {
      return memo.get(n);
    }

    int result = solveWithMemoization(n - 1) + solveWithMemoization(n - 2);
    memo.put(n, result);

    return result;
  }

  public static int solveWithRecursion(int n) {
    if (n <= 1) {
      return n;
    }

    return solveWithRecursion(n - 1) + solveWithRecursion(n - 2);
  }

  public static int solveWithIteration(int n) {
    if (n <= 1) {
      return n;
    }

    int a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
      int temp = a + b;
      a = b;
      b = temp;
    }

    return b;
  }
}
