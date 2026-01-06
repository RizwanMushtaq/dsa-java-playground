package a_generalAlgorithms.dynamicProgramming.bctci.problem40point5;

import java.util.*;

public class MagicBlackJack {
  private Map<Integer, Integer> memo;

  public static void main(String[] args) {
    MagicBlackJack magicBlackJack = new MagicBlackJack();
    System.out.println(magicBlackJack.solve());
  }

  public int solve() {
    memo = new HashMap<>();
    return numOfWayRec(0);
  }

  private int numOfWayRec(int i) {
    if (i > 21) return 1;
    if (i >= 16) return 0;
    if (memo.containsKey(i)) return memo.get(i);
    int res = 0;
    for (int card = 1; card <= 10; card++) {
      res += numOfWayRec(i + card);
    }
    memo.put(i, res);
    return res;
  }
}
