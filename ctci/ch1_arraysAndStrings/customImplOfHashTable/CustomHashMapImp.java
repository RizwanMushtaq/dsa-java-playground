package ctci.ch1_arraysAndStrings.customImplOfHashTable;

/**
 * If we have simple Array, we can search data and get data in O(n) But if we sort the and we have
 * sorted array, then we can use Binary search and get data in O(logn).
 *
 * <p>In Binary Search Tree we get element in O(logn).
 *
 * <p>Now: What if want to get data from a data structure in just O(1). I want to search data in
 * constant time.
 *
 * <p>Why we are using it because: -> We can access data in constant amount of time as O (1).
 *
 * <p>Hash Table or HashMap store data as keys and values.
 *
 * <p>Examples where we use Hashmaps are, Routing, Networking, Cryptography.
 *
 * <p>How it works: Understand the Concept of Hash Code -> It is a way of getting a number based on
 * some function/formula. This number could be very large but we need to reduce the size of this
 * number.
 *
 * <p>What is hashcode in Java -> just to understand we have a hashcode value for every object in
 * Java. If value is same, the hash code will be same for that. for example in java:
 *
 * <p>String name ="rizwan"; int code = name.hashCode(); System.out.println(code);
 *
 * <p>So how does it actually works
 */
class CustomHashMapImp {
  public static void main() {
    System.out.println("Custom Hash Map");
  }
}

class MyHashMap {
  private final Entity[] entities;

  public MyHashMap() {
    entities = new Entity[100];
  }

  public void put(String key, String value) {
    int hash = Math.abs(key.hashCode() % entities.length);
    entities[hash] = new Entity(key, value); // overriding
  }

  public String get(String key) {
    int hash = Math.abs(key.hashCode() % entities.length);
    if (entities[hash] != null && entities[hash].key.equals(key)) {
      return entities[hash].value;
    }
    return null;
  }
}

class Entity {
  String key;
  String value;

  public Entity(String key, String value) {
    this.key = key;
    this.value = value;
  }
}
