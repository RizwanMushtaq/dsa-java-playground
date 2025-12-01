package recursion.kunalPlaylist.arrayPatterns;

import java.util.*;

public class TargetOccurrences {
  public static void main(String[] args) {
    System.out.println(solve(new int[] {8, 5, 4, 5}, 5));
    System.out.println(solve(new int[] {8, 5, 4, 5}, 4));
    System.out.println(solve(new int[] {8, 5, 4, 5}, 40));
  }

  private static List<Integer> solve(int[] arr, int target) {
    List<Integer> result = new ArrayList<>();
    return helper(arr, target, 0, result);
  }

  private static List<Integer> helper(int[] arr, int target, int index, List<Integer> result) {
    if (index == arr.length) return result;
    if (arr[index] == target) result.add(index);
    return helper(arr, target, index + 1, result);
  }
}
