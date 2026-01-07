package a_generalAlgorithms.greedyAlgorithms.bctci.minimumTripletMedians;

import java.util.Arrays;

public class MinimizedMiddleSum {
  public static void main(String[] args) {
    MinimizedMiddleSum minimizedMiddleSum = new MinimizedMiddleSum();
    System.out.println(minimizedMiddleSum.solve(new int[] {6, 5, 8, 2, 1, 9}));
    System.out.println(minimizedMiddleSum.solve(new int[] {6, 5, 8, 2, 1, 9, 12, 15, 14}));
  }

  public int solve(int[] arr) {
    Arrays.sort(arr);
    int middleSum = 0;
    for (int i = 0; i < arr.length / 3; i++) {
      middleSum += arr[i * 2 + 1];
    }
    return middleSum;
  }
}
