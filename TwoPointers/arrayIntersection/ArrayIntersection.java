package TwoPointers.arrayIntersection;

import java.util.*;

class ArrayIntersection {
  public static void main(String[] args) {
    System.out.println("ArrayIntersection Problem");
    int[] result1 = getArrayIntersection(new int[] {1, 2, 3}, new int[] {1, 3, 5});
    System.out.println(Arrays.toString(result1));
    int[] result2 = getArrayIntersection(new int[] {1, 1, 1}, new int[] {1, 1});
    System.out.println(Arrays.toString(result2));
  }

  private static int[] getArrayIntersection(int[] arr1, int[] arr2) {
    int p1, p2;
    p1 = p2 = 0;
    List<Integer> result = new ArrayList<>();

    while (p1 < arr1.length && p2 < arr2.length) {
      if (arr1[p1] == arr2[p2]) {
        result.add(arr1[p1]);
        p1++;
        p2++;
      } else if (arr1[p1] < arr2[p2]) {
        p1++;
      } else {
        p2++;
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}
