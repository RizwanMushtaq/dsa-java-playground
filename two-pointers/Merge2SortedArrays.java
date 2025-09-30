import java.util.*;

class Merge2SortedArrays {
  public static void main(String[] args) {
    Test test = new Test();
    test.runTest01();
    test.runTest02();
  }

  public int[] merge2SortedArrays(int[] arr1, int[] arr2) {
    int p1 = 0, p2 = 0;
    List<Integer> result = new ArrayList<>();

    while (p1 < arr1.length && p2 < arr2.length) {
      if (arr1[p1] < arr2[p2]) {
        result.add(arr1[p1]);
        p1++;
      } else if (arr2[p2] < arr1[p1]) {
        result.add(arr2[p2]);
        p2++;
      } else {
        result.add(arr1[p1]);
        result.add(arr2[p2]);
        p1++;
        p2++;
      }
    }

    while (p1 < arr1.length) {
      result.add(arr1[p1]);
      p1++;
    }

    while (p2 < arr2.length) {
      result.add(arr2[p2]);
      p2++;
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}

class Test {
  public void runTest01() {
    int[] arr1 = {1, 3, 4, 5};
    int[] arr2 = {2, 4, 4};
    int[] wanted = {1, 2, 3, 4, 4, 4, 5};
    Merge2SortedArrays merge2SortedArrays = new Merge2SortedArrays();
    int[] result = merge2SortedArrays.merge2SortedArrays(arr1, arr2);

    System.out.println(Arrays.toString(result));

    if (Arrays.equals(wanted, result)) {
      System.out.println("test passed");
    } else {
      System.out.println("test failed");
    }
  }

  public void runTest02() {
    int[] arr1 = {1, 3, 9};
    int[] arr2 = {2, 4, 4, 4};
    int[] wanted = {1, 2, 3, 4, 4, 4, 9};
    Merge2SortedArrays merge2SortedArrays = new Merge2SortedArrays();
    int[] result = merge2SortedArrays.merge2SortedArrays(arr1, arr2);

    System.out.println(Arrays.toString(result));

    if (Arrays.equals(wanted, result)) {
      System.out.println("test passed");
    } else {
      System.out.println("test failed");
    }
  }
}
