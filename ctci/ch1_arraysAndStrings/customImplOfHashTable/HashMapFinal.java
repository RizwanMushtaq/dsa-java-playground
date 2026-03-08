package ctci.ch1_arraysAndStrings.customImplOfHashTable;

import java.util.ArrayList;
import java.util.LinkedList;

class HashMapFinal<K, V> {
  private ArrayList<LinkedList<Entity<K, V>>> list;
  private int size = 0;

  public HashMapFinal() {
    list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new LinkedList<>());
    }
  }

  public static void main() {
    HashMapFinal<String, String> myHashMap = new HashMapFinal<>();
    myHashMap.put("Mango", "King of Fruits");
    myHashMap.put("Apple", "Sweet red food");
    myHashMap.put("Banana", "Only Carbs");

    System.out.println(myHashMap.get("Mango"));
    System.out.println(myHashMap.get("Banana"));
    System.out.println(myHashMap.containKey("Banana"));
    System.out.println(myHashMap);
  }

  public void put(K key, V value) {
    int hash = Math.abs(key.hashCode() % list.size());

    LinkedList<Entity<K, V>> entities = list.get(hash);
    for (Entity<K, V> entity : entities) {
      if (entity.key.equals(key)) {
        entity.value = value;
        return;
      }
    }

    float loadFactor = 0.5f;
    if ((float) (size) / list.size() > loadFactor) {
      reHash();
    }

    entities.add(new Entity<>(key, value));
    size++;
  }

  private void reHash() {
    System.out.println("We are rehashing");
    ArrayList<LinkedList<Entity<K, V>>> old = list;
    list = new ArrayList<>();
    size = 0;
    for (int i = 0; i < old.size() * 2; i++) {
      list.add(new LinkedList<>());
    }
    for (LinkedList<Entity<K, V>> entries : old) {
      for (Entity<K, V> entry : entries) {
        put(entry.key, entry.value);
      }
    }
  }

  public V get(K key) {
    int hash = Math.abs(key.hashCode() % list.size());
    LinkedList<Entity<K, V>> entries = list.get(hash);
    for (Entity<K, V> entry : entries) {
      if (entry.key.equals(key)) {
        return entry.value;
      }
    }
    return null;
  }

  public void remove(K key) {
    int hash = Math.abs(key.hashCode() % list.size());
    LinkedList<Entity<K, V>> entries = list.get(hash);
    Entity<K, V> target = null;
    for (Entity<K, V> entry : entries) {
      if (entry.key.equals(key)) {
        target = entry;
      }
    }
    entries.remove(target);
    size--;
  }

  public boolean containKey(K key) {
    return get(key) != null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    for (LinkedList<Entity<K, V>> entities : list) {
      for (Entity<K, V> entity : entities) {
        builder.append(entity.key);
        builder.append("=");
        builder.append(entity.value);
        builder.append(",");
      }
    }
    builder.append("}");
    return builder.toString();
  }

  private static class Entity<K, V> {
    K key;
    V value;

    public Entity(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}
