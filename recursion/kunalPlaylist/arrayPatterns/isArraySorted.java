package recursion.kunalPlaylist.arrayPatterns;

public class isArraySorted {
  public static void main(String[] args) {
    System.out.println(solve(new int[] {5, 6, 6, 10}));
  }

  static boolean solve(int[] arr) {
    return helper(arr, 0);
  }

  static boolean helper(int[] arr, int index) {
    if (index == arr.length - 1) return true;
    if (arr[index] > arr[index + 1]) return false;
    return helper(arr, index + 1);
  }
}
