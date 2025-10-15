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

    /** Integer Array - Descending Order */
    Integer[] intArray2 = {6, 4, 9, 2, 6};
    Arrays.sort(intArray2, (a, b) -> b.compareTo(a));
    printArray(intArray2);

    /**
     * 2D Integer Array where each interval consists of start and end value Sort the interval by the
     * end value
     */
    Integer[][] array2D = {{3, 9}, {1, 4}, {4, 7}, {2, 3}};
    Arrays.sort(array2D, (a, b) -> a[1].compareTo(b[1]));
    printArray(array2D);
  }

  public static <T> void printArray(T[] array) {
    System.out.println(Arrays.deepToString(array));
  }
}
