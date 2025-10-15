package TwoPointers;

class SmallerPrefixes {
  public static void main(String[] args) {
    System.out.println("SmallerPrefixes Problem");
    System.out.println(AreSmallerPrefixes(new int[] {1, 2, 2, -1}));
    System.out.println(AreSmallerPrefixes(new int[] {1, 2, -2, 1, 3, 5}));
  }

  private static boolean AreSmallerPrefixes(int[] arr) {
    int sp, fp, slow_sum, fast_sum;
    sp = fp = slow_sum = fast_sum = 0;

    while (fp < arr.length) {
      slow_sum += arr[sp];
      fast_sum += arr[fp] + arr[fp + 1];
      if (slow_sum >= fast_sum) {
        return false;
      }

      sp++;
      fp += 2;
    }

    return true;
  }
}
