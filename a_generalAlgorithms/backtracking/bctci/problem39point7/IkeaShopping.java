package a_generalAlgorithms.backtracking.bctci.problem39point7;

import java.util.*;

public class IkeaShopping {
  private int budget;
  private int[] prices;
  private double[] ratings;
  private int n;

  private double bestRatingSum;
  private List<Integer> bestItems;
  private List<Integer> items;

  public static void main(String[] args) {
    int budget = 20;
    int[] prices = {10, 5, 15, 8, 3};
    double[] ratings = {7.0, 3.5, 9.0, 6.0, 2.0};
    System.out.println(new IkeaShopping().solve(budget, prices, ratings));
  }

  public List<Integer> solve(int budget, int[] prices, double[] ratings) {
    this.budget = budget;
    this.prices = prices;
    this.ratings = ratings;
    n = prices.length;

    bestRatingSum = 0;
    bestItems = new ArrayList<>();
    items = new ArrayList<>();
    visit(0, 0, 0);
    return bestItems;
  }

  private void visit(int i, int curCost, double curRatingSum) {
    if (i == n) {
      if (curRatingSum > bestRatingSum) {
        bestRatingSum = curRatingSum;
        bestItems = new ArrayList<>(items);
      }
      return;
    }
    visit(i + 1, curCost, curRatingSum);
    if (curCost + prices[i] <= budget) {
      items.add(i);
      visit(i + 1, curCost + prices[i], curRatingSum + ratings[i]);
      items.removeLast();
    }
  }
}
