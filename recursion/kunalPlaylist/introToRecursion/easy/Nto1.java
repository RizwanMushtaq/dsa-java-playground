package recursion.kunalPlaylist.introToRecursion.easy;

public class Nto1 {

  public static void main(String[] args) {
    nTo1(5);
    nTo1Reverse(5);
  }

  static void nTo1(int n) {
    if (n == 0) return;
    System.out.println(n);
    nTo1(n - 1);
  }

  static void nTo1Reverse(int n) {
    if (n == 0) return;
    nTo1Reverse(n - 1);
    System.out.println(n);
  }
}
