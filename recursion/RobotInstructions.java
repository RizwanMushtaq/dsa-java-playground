package recursion;

import java.util.ArrayList;

public class RobotInstructions {
  public static void main(String[] args) {
    new RobotInstructionsTests().runTests();
  }

  public String solve(String seq) {
    ArrayList<Character> res = new ArrayList<>();
    movesRec(seq, 0, res);

    StringBuilder sb = new StringBuilder();
    for (char c : res) {
      sb.append(c);
    }
    return sb.toString();
  }

  private void movesRec(String seq, int pos, ArrayList<Character> res) {
    if (pos == seq.length()) {
      return;
    }
    if (seq.charAt(pos) == '2') {
      movesRec(seq, pos + 1, res);
      movesRec(seq, pos + 2, res);
    } else {
      res.add(seq.charAt(pos));
      movesRec(seq, pos + 1, res);
    }
  }
}

class RobotInstructionsTests {
  public void runTests() {
    Object[][] tests = {
      {"LL", "LL"},
      {"2LR", "LRR"},
      {"2L", "L"},
      {"22LR", "LRRLR"},
      {"LL2R2L", "LLRLL"},
      {"", ""},
      {"L", "L"},
      {"2222LR", "LRRLRLRRLRRLR"},
    };

    RobotInstructions solution = new RobotInstructions();
    for (Object[] test : tests) {
      String seq = (String) test[0];
      String want = (String) test[1];
      String got = solution.solve(seq);
      if (!got.equals(want)) {
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %s, want: %s\n", seq, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
