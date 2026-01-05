package a_generalAlgorithms.dynamicProgramming.bctci.problem40point1;


public class RoadTripMemoization {
  public static void main(String[] args) {
    RoadTripMemoization roadTripMemoization = new RoadTripMemoization();
    int[] times1 = {8, 1, 2, 3, 9, 6, 2, 4};
    System.out.println(roadTripMemoization.solve(times1));
  }

  public int solve(int[] times) {
    int n = times.length;
    if (n < 3) {
      return 0;
    }
    int[] memo = new int[n];
    return Math.min(
        Math.min(delayRec(times, memo, 0), delayRec(times, memo, 1)), delayRec(times, memo, 2));
  }

  private int delayRec(int[] times, int[] memo, int i) {
    int n = times.length;
    if (i >= n - 3) return times[i];
    if (memo[i] != 0) return memo[i];
    return memo[i] =
        times[i]
            + Math.min(
                Math.min(delayRec(times, memo, i + 1), delayRec(times, memo, i + 2)),
                delayRec(times, memo, i + 3));
  }
}
