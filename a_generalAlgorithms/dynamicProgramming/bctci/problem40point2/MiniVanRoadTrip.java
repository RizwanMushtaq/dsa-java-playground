package a_generalAlgorithms.dynamicProgramming.bctci.problem40point2;

import java.util.HashMap;
import java.util.Map;

public class MiniVanRoadTrip {
  private Map<Integer, Integer> memo;

  public static void main(String[] args) {
    MiniVanRoadTrip miniVanRoadTrip = new MiniVanRoadTrip();
    int[] times1 = {8, 1, 2, 3, 9, 6, 2, 4};
    System.out.println(miniVanRoadTrip.solve(times1, 2));
    System.out.println(miniVanRoadTrip.solve(times1, 3));
  }

  public int solve(int[] times, int k) {
    int n = times.length;
    if (n < k + 1) {
      return 0;
    }
    int minDelay = Integer.MAX_VALUE;
    memo = new HashMap<>();
    for (int i = 0; i <= k; i++) {
      minDelay = Math.min(minDelay, delayRec(times, k, i));
    }
    return minDelay;
  }

  private int delayRec(int[] times, int k, int i) {
    int n = times.length;
    if (i >= n - k - 1) return times[i];
    if (memo.containsKey(i)) return memo.get(i);
    int minDelay = Integer.MAX_VALUE;
    for (int p = 1; p <= k + 1; p++) {
      minDelay = Math.min(minDelay, delayRec(times, k, p + i));
    }
    memo.put(i, times[i] + minDelay);
    return memo.get(i);
  }
}
