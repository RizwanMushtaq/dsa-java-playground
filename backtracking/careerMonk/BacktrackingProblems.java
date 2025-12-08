package backtracking.careerMonk;

// Book Reference - Section 2.12
public class BacktrackingProblems {
  public static void main(String[] args) {
    //    nBitsWithArray(3);
    nBits("", 3);
  }

  // Generate all the strings of n bits. Using String as argument. String are
  // immutable in Java, so everytime you pass a string, new string object is
  // created.
  private static void nBits(String processed, int n) {
    if (n < 1) {
      System.out.println(processed);
      return;
    }
    String updatedWith0 = processed + '0';
    nBits(updatedWith0, n - 1);
    String updateWith1 = processed + '1';
    nBits(updateWith1, n - 1);
  }

  // Generate all the strings of n bits. Using Array as argument
  private static void nBitsWithArray(int n) {
    int[] arr = new int[n];
    nBitsWithArrayHelper(arr, 0, n);
  }

  private static void nBitsWithArrayHelper(int[] arr, int index, int n) {
    if (index == n) {
      printArray(arr);
      return;
    }
    arr[index] = 0;
    nBitsWithArrayHelper(arr, index + 1, n);
    arr[index] = 1;
    nBitsWithArrayHelper(arr, index + 1, n);
  }

  private static void printArray(int[] arr) {
    for (int element : arr) {
      System.out.print(element);
    }
    System.out.println();
  }
}
