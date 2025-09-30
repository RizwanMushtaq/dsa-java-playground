class Is2Sum {
  public static void main(String[] args) {
    Test test = new Test();
    test.test01();
    test.test02();
    test.test03();
  }

  public boolean is2Sum(int[] arr) {
    int l = 0, r = arr.length - 1;

    while (l < r) {
      if (arr[l] + arr[r] > 0) {
        r--;
      } else if (arr[l] + arr[r] < 0) {
        l++;
      } else {
        return true;
      }
    }

    return false;
  }
}

class Test {
  Is2Sum is2Sum = new Is2Sum();

  public void test01() {
    int[] input = {-5, -2, -1, 1, 1, 10};
    boolean result = is2Sum.is2Sum(input);
    if (result) {
      System.out.println("test01 passed");
    } else {
      System.out.println("test01 failed");
    }
  }

  public void test02() {
    int[] input = {-3, 0, 1, 2, 3, 10};
    boolean result = is2Sum.is2Sum(input);
    if (result) {
      System.out.println("test02 passed");
    } else {
      System.out.println("test02 failed");
    }
  }

  public void test03() {
    int[] input = {-5, -3, -1, 0, 2, 4, 6};
    boolean result = is2Sum.is2Sum(input);
    if (result == false) {
      System.out.println("test03 passed");
    } else {
      System.out.println("test03 failed");
    }
  }
}
