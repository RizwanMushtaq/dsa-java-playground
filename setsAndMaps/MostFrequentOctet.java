package setsAndMaps;

import java.util.*;

class MostFrequentOctet {
  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public String solve(String[] arr) {

    Map<String, Integer> octetToCount = new HashMap<>();
    for (String ip : arr) {
      String[] octets = ip.split("\\.");
      String firstOctet = octets[0];

      octetToCount.put(firstOctet, octetToCount.getOrDefault(firstOctet, 0) + 1);
    }

    String mostFrequentOctet = null;
    for (Map.Entry<String, Integer> item : octetToCount.entrySet()) {
      String octet = item.getKey();
      int count = item.getValue();

      if (mostFrequentOctet == null || count > octetToCount.get(mostFrequentOctet)) {
        mostFrequentOctet = octet;
      }
    }

    return mostFrequentOctet;
  }
}

class RunTests {
  public void runTests() {
    Object[][] tests = {
      {
        new String[] {
          "203.0.113.10", "208.51.100.25", "202.0.2.5", "203.0.113.15",
        },
        "203"
      },
      {new String[] {"12.1.1.1", "12.1.1.2", "12.1.1.3", "1.1.1.4"}, "12"},
      {new String[] {"192.168.1.1", "192.168.1.2", "192.168.1.3", "10.0.0.1", "10.0.0.2"}, "192"},
      {new String[] {"1.1.1.1", "2.2.2.2", "1.2.3.4", "2.3.4.5"}, "1"},
      {new String[] {}, null}
    };

    MostFrequentOctet solution = new MostFrequentOctet();
    for (Object[] test : tests) {
      String[] ipsArray = (String[]) test[0];
      String want = (String) test[1];
      String got = solution.solve(ipsArray);
      if (!Objects.equals(got, want)) {
        throw new RuntimeException(
            String.format("\nsolve(%s): got: %s, want: %s\n", ipsArray, got, want));
      } else {
        System.out.print(".");
      }
    }
  }
}
