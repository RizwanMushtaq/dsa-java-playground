package companies.IBMQuestions;

import java.util.*;

public class GetMinInstability {

  public static int getMinInstability(List<Integer> request) {
    int n = request.size();
    if (n < 3) return 0; // can't have spikes or dips

    int[] instability = new int[n];
    int total = 0;

    // Step 1: Count initial spikes/dips
    for (int i = 1; i < n - 1; i++) {
      int curr = request.get(i);
      if ((curr > request.get(i - 1) && curr > request.get(i + 1))
          || (curr < request.get(i - 1) && curr < request.get(i + 1))) {
        instability[i] = 1;
        total++;
      }
    }

    int maxReduction = 0;

    // Step 2: Try modifying each element
    for (int i = 1; i < n - 1; i++) {
      int original = request.get(i);

      // Try replacing with left neighbor
      int reduction = getReduction(request, instability, i, request.get(i - 1));
      maxReduction = Math.max(maxReduction, reduction);

      // Try replacing with right neighbor
      reduction = getReduction(request, instability, i, request.get(i + 1));
      maxReduction = Math.max(maxReduction, reduction);

      // Restore original value
      request.set(i, original);
    }

    return Math.max(0, total - maxReduction);
  }

  private static int getReduction(List<Integer> req, int[] inst, int idx, int newVal) {
    int n = req.size();
    int before = 0, after = 0;

    // Count instability before change for indices (idx-1 to idx+1)
    for (int j = idx - 1; j <= idx + 1; j++) {
      if (j > 0 && j < n - 1 && inst[j] == 1) before++;
    }

    // Apply change
    req.set(idx, newVal);

    // Count instability after change for the same window
    for (int j = idx - 1; j <= idx + 1; j++) {
      if (j <= 0 || j >= n - 1) continue;
      int curr = req.get(j);
      if ((curr > req.get(j - 1) && curr > req.get(j + 1))
          || (curr < req.get(j - 1) && curr < req.get(j + 1))) after++;
    }

    return before - after; // how many spikes/dips we removed
  }

  // 🔹 Demo
  public static void main(String[] args) {
    System.out.println(getMinInstability(Arrays.asList(1, 5, 3))); // 0
    System.out.println(getMinInstability(Arrays.asList(5, 3, 6, 4, 6, 3))); // 1
  }
}
