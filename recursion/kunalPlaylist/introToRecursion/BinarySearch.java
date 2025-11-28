package recursion.kunalPlaylist.introToRecursion;

public class BinarySearch {
  public static void main(String[] args) {
    System.out.println("Binary Search");
    //    System.out.println(binarySearch(new int[] {10, 20, 30, 40, 50, 60, 70}, 60));
    System.out.println(binarySearchWithRecursion(new int[] {10, 20, 30, 40, 50, 60, 70}, 60));
  }

  static int binarySearchWithRecursion(int[] arr, int target) {
    int start = 0, end = arr.length - 1;
    return search(arr, target, start, end);
  }

  static int binarySearch(int[] arr, int target) {
    int start = 0, end = arr.length - 1;
    while (end >= start) {
      int mid = start + (end - start) / 2;
      if (target == arr[mid]) {
        return mid;
      } else if (target > arr[mid]) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return -1;
  }

  private static int search(int[] arr, int target, int start, int end) {
    if (start > end) return -1;
    int mid = start + (end - start) / 2;
    if (target == arr[mid]) return mid;
    if (target > arr[mid]) return search(arr, target, mid + 1, end);
    return search(arr, target, start, mid - 1);
  }
}
