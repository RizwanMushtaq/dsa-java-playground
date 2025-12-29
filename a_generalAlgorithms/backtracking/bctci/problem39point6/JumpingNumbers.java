package a_generalAlgorithms.backtracking.bctci.problem39point6;

import java.util.*;

public class JumpingNumbers {
  int number;
  private List<Integer> res;

  public static void main(String[] args) {
    System.out.print(new JumpingNumbers().solve(34));
  }

  public List<Integer> solve(int n) {
    res = new ArrayList<>();
    number = n;
    for (int i = 1; i <= 9; i++) {
      visit(i);
    }
    Collections.sort(res);
    return res;
  }

  private void visit(int n) {
    if (n >= number) return;
    res.add(n);
    int lastDigit = n % 10;
    if (lastDigit > 0) visit(n * 10 + (lastDigit - 1));
    if (lastDigit < 9) visit(n * 10 + (lastDigit + 1));
  }
}
