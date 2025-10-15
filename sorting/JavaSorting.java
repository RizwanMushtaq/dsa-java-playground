package sorting;

import java.util.*;

class JavaSorting {
  public static void main(String[] args) {

    /** String Array - Ascending Order */
    String[] arr1 = {"apple", "Banana", "3", "Cherry", "42", "Grape", "10"};
    Arrays.sort(arr1, String.CASE_INSENSITIVE_ORDER);
    printArray(arr1);

    /** String Array - Descending Order */
    String[] arr2 = {"apple", "Banana", "3", "Cherry", "42", "Grape", "10"};
    Arrays.sort(arr2, (a, b) -> b.compareToIgnoreCase(a));
    printArray(arr2);

    /** Integer Array - Ascending Order */
    Integer[] intArray = {6, 4, 9, 2, 6};
    Arrays.sort(intArray);
    printArray(intArray);
  }

  public static <T> void printArray(T[] array) {
    System.out.println(Arrays.deepToString(array));
  }
}
