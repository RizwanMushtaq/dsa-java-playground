package a_generalAlgorithms.dynamicProgramming.bctci.roadTrip;

public class RoadTrip {
  public static void main(String[] args) {
    RoadTrip roadTrip = new RoadTrip();
    int[] times1 = {8, 1, 2, 3, 9, 6, 2, 4};
    System.out.println(roadTrip.solve(times1));
  }

  public int solve(int[] times) {
    int n = times.length;
    if (n < 3) {
      return 0;
    }
    return Math.min(Math.min(delayRec(times, 0), delayRec(times, 1)), delayRec(times, 2));
  }

  private int delayRec(int[] times, int i) {
    int n = times.length;
    if (i >= n - 3) return times[i];
    return times[i]
        + Math.min(
            Math.min(delayRec(times, i + 1), delayRec(times, i + 2)), delayRec(times, i + 3));
  }
}
