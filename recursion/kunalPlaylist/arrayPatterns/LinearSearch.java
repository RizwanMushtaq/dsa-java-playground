package recursion.kunalPlaylist.arrayPatterns;

public class LinearSearch {
  public static void main(String[] args) {
    System.out.println(solve(new int[] {8, 2, 5, 9}, 9));
  }

  static int solve(int[] arr, int target) {
    return helper(arr, target, 0);
  }

  static int helper(int[] arr, int target, int index) {
    if (index == arr.length) return -1;
    if (arr[index] == target) return index;
    return helper(arr, target, index + 1);
  }
}
