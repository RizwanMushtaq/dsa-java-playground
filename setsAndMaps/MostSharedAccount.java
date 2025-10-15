package setsAndMaps;

import java.util.*;

class MostSharedAccount {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public String solve(String[][] connections) {
    Map<String, Integer> userToCount = new HashMap<>();
    for (String[] conn : connections) {
      String ip = conn[0];
      String user = conn[1];

      userToCount.put(user, userToCount.getOrDefault(user, 0) + 1);
    }

    String mostSharedUsers = null;
    for (Map.Entry<String, Integer> entry : userToCount.entrySet()) {
      String user = entry.getKey();
      int count = entry.getValue();
      if (mostSharedUsers == null || count > userToCount.get(mostSharedUsers)) {
        mostSharedUsers = user;
      }
    }

    return mostSharedUsers;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {
        new String[][] {
          {"203.0.113.10", "mike"},
          {"208.51.100.25", "bob"},
          {"202.0.2.5", "mike"},
          {"203.0.113.15", "bob2"}
        },
        "mike"
      },
      {new String[][] {}, null},
      {new String[][] {{"1.1.1.1", "alice"}}, "alice"},
      {
        new String[][] {
          {"1.1.1.1", "alice"},
          {"1.1.1.2", "bob"},
          {"1.1.1.3", "alice"},
          {"1.1.1.4", "bob"}
        },
        "alice"
      }
    };

    MostSharedAccount solution = new MostSharedAccount();
    for (Object[] test : tests) {
      String[][] connectionsArray = (String[][]) test[0];
      String want = (String) test[1];
      String got = solution.solve(connectionsArray);
      if (!Objects.equals(got, want)) {
        // Check if got is an acceptable alternative (same count as want)
        if (want != null && got != null) {
          int gotCount = 0;
          int wantCount = 0;
          for (String[] conn : connectionsArray) {
            if (conn[1] == got) gotCount++;
            if (conn[1] == want) wantCount++;
          }
          if (gotCount == wantCount) {
            System.out.print(".");
            continue;
          }
        }
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %s, want: %s\n", connectionsArray, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
