package setsAndMaps.accountSharingDetection;

import java.util.*;

class AccountSharingDetection {
  public static void main(String[] args) {
    AccountSharingDetectionTests runTests = new AccountSharingDetectionTests();
    runTests.runTests();
  }

  public String solve(String[][] connections) {
    Set<String> seen = new HashSet<>();
    for (String[] entry : connections) {
      String user = entry[0];
      String ip = entry[1];
      if (seen.contains(user)) {
        return ip;
      }
      seen.add(user);
    }

    return "";
  }
}

class AccountSharingDetectionTests {
  public void runTests() {
    Object[][] tests = {
      {
        new String[][] {
          {"203.0.113.10", "mike"},
          {"298.51.100.25", "bob"},
          {"292.0.2.5", "mike"},
          {"203.0.113.15", "bob2"}
        },
        "203.0.113.10"
      },
      {
        new String[][] {
          {"111.0.0.0", "mike"},
          {"111.0.0.1", "mike"},
          {"111.0.0.2", "bob"},
          {"111.0.0.3", "bob"}
        },
        "111.0.0.0"
      },
      {
        new String[][] {
          {"111.0.0.0", "mike"},
          {"111.0.0.1", "mike2"},
          {"111.0.0.2", "mike3"},
          {"111.0.0.3", "mike4"}
        },
        ""
      },
      {new String[][] {}, ""},
      {new String[][] {{"1.1.1.1", "alice"}}, ""}
    };

    AccountSharingDetection solution = new AccountSharingDetection();
    for (Object[] test : tests) {
      String[][] connectionsArray = (String[][]) test[0];
      String want = (String) test[1];
      String got = solution.solve(connectionsArray);

      // Check if got matches want directly
      if (Objects.equals(got, want)) {
        System.out.print(".");
        continue;
      }

      // If want is empty, got must also be empty
      if (want.isEmpty()) {
        if (!got.isEmpty()) {
          throw new RuntimeException(
              String.format("\nsolve(%s): got: %s, want: %s\n", connectionsArray, got, want));
        }
        continue;
      } else {
        System.out.print(".");
      }
    }
  }
}
