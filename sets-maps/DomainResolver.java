import java.util.*;

class DomainResolver {
  private Map<String, Set<String>> ipToDomains;
  private Map<String, Set<String>> domainToSubdomains;

  public DomainResolver() {
    this.ipToDomains = new HashMap<>();
    this.domainToSubdomains = new HashMap<>();
  }

  public static void main(String[] args) {
    RunTests runTests = new RunTests();
    runTests.runTests();
  }

  public Object registerDomain(String ip, String domain) {
    if (!ipToDomains.containsKey(ip)) {
      ipToDomains.put(ip, new HashSet<>());
    }

    ipToDomains.get(ip).add(domain);
    return null;
  }

  public Object registerSubdomain(String domain, String subdomain) {
    if (!domainToSubdomains.containsKey(domain)) {
      domainToSubdomains.put(domain, new HashSet<>());
    }

    domainToSubdomains.get(domain).add(subdomain);
    return null;
  }

  public boolean hasSubdomain(String ip, String domain, String subdomain) {
    if (!ipToDomains.containsKey(ip)) {
      return false;
    }

    if (!ipToDomains.get(ip).contains(domain)) {
      return false;
    }

    if (!domainToSubdomains.containsKey(domain)) {
      return false;
    }

    return domainToSubdomains.get(domain).contains(subdomain);
  }
}

record TestCase(String[][] operations, Object[] wants) {}

class RunTests {
  public void runTests() {
    TestCase[] tests = {
      new TestCase(
          new String[][] {
            {"register_domain", "192.168.1.1", "example.com"},
            {"register_domain", "192.168.1.1", "example.org"},
            {"register_domain", "192.168.1.2", "domain.com"},
            {"register_subdomain", "example.com", "a"},
            {"register_subdomain", "example.com", "b"},
            {"has_subdomain", "192.168.1.1", "example.com", "a"},
            {"has_subdomain", "192.168.1.1", "example.com", "c"},
            {"has_subdomain", "127.0.0.1", "example.com", "a"},
            {"has_subdomain", "192.168.1.1", "example.org", "a"},
            {"has_subdomain", "192.168.1.2", "example.com", "a"}
          },
          new Object[] {null, null, null, null, null, true, false, false, false, false}),
      new TestCase(
          new String[][] {
            {"register_domain", "1.1.1.1", "test.com"},
            {"register_subdomain", "test.com", "www"},
            {"has_subdomain", "1.1.1.1", "test.com", "www"}
          },
          new Object[] {null, null, true}),
      new TestCase(
          new String[][] {
            {"register_domain", "1.1.1.1", "site1.com"},
            {"register_domain", "2.2.2.2", "site2.com"},
            {"register_subdomain", "site1.com", "www"},
            {"register_subdomain", "site2.com", "www"},
            {"has_subdomain", "1.1.1.1", "site1.com", "www"},
            {"has_subdomain", "2.2.2.2", "site2.com", "www"},
            {"has_subdomain", "1.1.1.1", "site2.com", "www"},
            {"has_subdomain", "2.2.2.2", "site1.com", "www"}
          },
          new Object[] {null, null, null, null, true, true, false, false})
    };

    for (TestCase testCase : tests) {
      DomainResolver resolver = new DomainResolver();
      for (int i = 0; i < testCase.operations().length; i++) {
        String[] op = testCase.operations()[i];
        String operation = op[0];
        Object got;
        if (operation.equals("register_domain")) {
          got = resolver.registerDomain(op[1], op[2]);
        } else if (operation.equals("register_subdomain")) {
          got = resolver.registerSubdomain(op[1], op[2]);
        } else {
          got = resolver.hasSubdomain(op[1], op[2], op[3]);
        }
        Object want = testCase.wants()[i];
        if (!Objects.equals(got, want)) {
          throw new RuntimeException(
              String.format(
                  "\n%s(%s): got: %s, want: %s\n",
                  operation, Arrays.toString(Arrays.copyOfRange(op, 1, op.length)), got, want));
        } else {
          System.out.print(".");
        }
      }
    }
  }
}
