package slidingWindows.fixedLengthWindows.uniqueBestSellerStreak;

import java.util.ArrayList;
import java.util.List;

public class UniqueBestSellerStreak {
  boolean solve(String[] bestSeller, int k) {
    int l = 0, r = 0;
    List<String> subArrayWithUniqueTitles = new ArrayList<>();

    while (r < bestSeller.length) {
      subArrayWithUniqueTitles.add(bestSeller[r]);
      r++;
      if (r - l == k) {
        // check if subArrayWithUniqueTitles have unique titles
        // if yes return true
      }
    }

    return false;
  }
}
