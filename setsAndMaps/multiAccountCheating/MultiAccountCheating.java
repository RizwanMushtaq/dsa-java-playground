package setsAndMaps.multiAccountCheating;

import java.util.*;

class MultiAccountCheating {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public boolean solve(String[][] users) {
    Map<String, Set<String>> userToIps = new HashMap<>();

    for (String[] user : users) {
      String username = user[0];
      String ip = user[1];

      userToIps.computeIfAbsent(username, k -> new HashSet<>()).add(ip);
    }

    List<Set<String>> ipSets = new ArrayList<>(userToIps.values());
    for (int i = 0; i < ipSets.size(); i++) {
      for (int j = i + 1; j < ipSets.size(); j++) {
        if (ipSets.get(i).equals(ipSets.get(j))) {
          return true;
        }
      }
    }

    return false;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {
        new String[][] {
          {"alice", "192.168.1.1"},
          {"alice", "192.168.1.2"},
          {"bob", "192.168.1.2"},
          {"bob", "192.168.1.1"}
        },
        true
      },
      {
        new String[][] {
          {"alice", "192.168.1.1"},
          {"bob", "192.168.1.2"}
        },
        false
      },
      {
        new String[][] {
          {"alice", "1.1.1.1"},
          {"alice", "2.2.2.2"},
          {"bob", "2.2.2.2"},
          {"bob", "1.1.1.1"},
          {"charlie", "3.3.3.3"}
        },
        true
      },
      {new String[][] {}, false}
    };

    MultiAccountCheating solution = new MultiAccountCheating();
    for (Object[] test : tests) {
      String[][] usersArray = (String[][]) test[0];
      boolean want = (boolean) test[1];
      boolean got = solution.solve(usersArray);
      if (got != want) {
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %b, want: %b\n", usersArray, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
