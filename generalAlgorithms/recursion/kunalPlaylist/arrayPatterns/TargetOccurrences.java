package generalAlgorithms.recursion.kunalPlaylist.arrayPatterns;

import java.util.*;

public class TargetOccurrences {
  public static void main(String[] args) {
    System.out.println(solve(new int[] {8, 5, 4, 5}, 5));
    System.out.println(solve(new int[] {8, 5, 4, 5}, 4));
    System.out.println(solve(new int[] {8, 5, 4, 5}, 40));
  }

  private static ArrayList<Integer> solve(int[] arr, int target) {
    ArrayList<Integer> result = new ArrayList<>();
    //    return helper(arr, target, 0, result);

    return helperWithoutResultArray(arr, target, 0);
  }

  private static List<Integer> helper(int[] arr, int target, int index, List<Integer> result) {
    if (index == arr.length) return result;
    if (arr[index] == target) result.add(index);
    return helper(arr, target, index + 1, result);
  }

  // This is not good approach as every function call will create new object.
  // This is another thought process, by which some problem could be solved.
  // When we dont want to pass result array in argument and will create new
  // array with every function call.
  private static ArrayList<Integer> helperWithoutResultArray(int[] arr, int target, int index) {
    ArrayList<Integer> list = new ArrayList<>();
    if (index == arr.length) return list;
    // this will contain only answer for that specific function call.
    if (arr[index] == target) list.add(index);
    ArrayList<Integer> ansFromBelowCalls = helperWithoutResultArray(arr, target, index + 1);
    list.addAll(ansFromBelowCalls);
    return list;
  }
}
