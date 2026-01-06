package a_generalAlgorithms.dynamicProgramming.bctci.restaurantRatings;

import java.util.*;

/**
 * We are doing a road trip and trying to plan where to stop to eat. There are n restaurants along
 * the route. We are given an array, ratings, with the ratings of all the restaurants maximizing the
 * sum of ratings of the places where we stop. The only constraint is that we don't want to stop at
 * 2 consecutive restaurants, as we would be too full. Return the optimal sum of ratings.
 *
 * <p>Example 1: ratings = [8, 1, 3, 9, 5, 2, 1] Output: 19. The optimal restaurants are: [*8*, 1,
 * 3, *9*, 5, *2*, 1]
 *
 * <p>Example 2: ratings = [8, 1, 3, 7, 5, 2, 4] Output: 20. The optimal restaurants are: [*8*, 1,
 * *3*, 7, *5*, 2, *4*].
 *
 * <p>Example 3: ratings = [] Output: 0
 */
public class RestaurantRatings {
  private double[] ratings;
  private int length;
  private Map<Integer, Double> memo;

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  /**
   * For this problem, we have:
   *
   * <p>Subproblems: n. Non-recursive work per subproblem: O(1). Time: O(n). Extra space: O(n) - for
   * the memoization map. The space can be optimized to O(1) by using tabulation with the rolling
   * array optimization
   *
   * <p>Choices: eat at restaurant i or not. If we stop at restaurant i, we add ratings[i] to our
   * total, but we have to skip restaurant i+1. Aggregation logic: We pick the maximum between the
   * two options:
   *
   * <p>rating_sum(i) = max(ratings[i] + rating_sum(i+2), rating_sum(i+1))
   *
   * <p>Base case: rating_sum(n) = 0 (there are no restaurants left).
   *
   * @param inputRatings list
   * @return max ratings
   */
  public double solve(double[] inputRatings) {
    ratings = inputRatings;
    length = ratings.length;
    memo = new HashMap<>();
    return ratingSum(0);
  }

  private double ratingSum(int i) {
    if (i >= length) return 0;
    if (memo.containsKey(i)) return memo.get(i);
    memo.put(i, Math.max(ratings[i] + ratingSum(i + 2), ratingSum(i + 1)));
    return memo.get(i);
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {new double[] {8.0, 1.0, 3.0, 9.0, 5.0, 2.0, 1.0}, 19.0},
      {new double[] {8.0, 1.0, 3.0, 7.0, 5.0, 2.0, 4.0}, 20.0},
      {new double[] {10.0, 10.0, 10.0, 10.0, 10.0}, 30.0},
      {new double[] {}, 0.0},
      {new double[] {5.0, 5.0, 5.0, 5.0, 5.0}, 15.0},
    };

    RestaurantRatings solution = new RestaurantRatings();
    for (Object[] test : tests) {
      double[] ratings = (double[]) test[0];
      double want = (double) test[1];
      double got = solution.solve(ratings);
      if (Math.abs(got - want) > 1e-9) {
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %f, want: %f\n", Arrays.toString(ratings), got, want));
      } else {
        System.out.println("got = " + got);
      }
    }
  }
}
