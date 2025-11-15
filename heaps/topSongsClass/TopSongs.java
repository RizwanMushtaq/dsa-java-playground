package heaps.topSongsClass;

import java.util.*;

public class TopSongs {
  private final int k;
  private final PriorityQueue<Song> maxHeap;
  private final Map<String, Integer> titleToTotalPlays;

  public TopSongs(int k) {
    this.k = k;
    maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.plays(), a.plays()));
    titleToTotalPlays = new HashMap<>();
  }

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  public void registerPlays(String title, int plays) {
    int newTotalPlays = plays;
    if (titleToTotalPlays.containsKey(title)) {
      newTotalPlays += titleToTotalPlays.get(title);
    }
    titleToTotalPlays.put(title, newTotalPlays);
    maxHeap.add(new Song(title, newTotalPlays));
  }

  public List<String> topK() {
    List<String> topSongs = new ArrayList<>();
    List<Song> tempHeap = new ArrayList<>();
    while (topSongs.size() < k && !maxHeap.isEmpty()) {
      Song song = maxHeap.poll();
      if (titleToTotalPlays.get(song.title()) == song.plays()) {
        topSongs.add(song.title());
      }
      tempHeap.add(song);
    }
    maxHeap.addAll(tempHeap);
    return topSongs;
  }
}

record Song(String title, int plays) {}

class RunTests {
  public void runTests() {
    // Example from the book
    TopSongs s = new TopSongs(3);
    s.registerPlays("Boolean Rhapsody", 100);
    s.registerPlays("Boolean Rhapsody", 193); // Total 293
    s.registerPlays("Coding In The Deep", 75);
    s.registerPlays("Coding In The Deep", 75); // Total 150
    s.registerPlays("All About That Base Case", 200);
    s.registerPlays("All About That Base Case", 90); // Total 290
    s.registerPlays("All About That Base Case", 1); // Total 291
    s.registerPlays("Here Comes The Bug", 223);
    s.registerPlays("Oops! I Broke Prod Again", 274);
    s.registerPlays("All the Single Brackets", 132);

    List<String> result = s.topK();
    Set<String> got = new HashSet<>(result);
    Set<String> want =
        new HashSet<>(
            Arrays.asList(
                "All About That Base Case", "Boolean Rhapsody", "Oops! I Broke Prod Again"));
    if (!got.equals(want)) {
      throw new RuntimeException("\ntopK() failed\n");
    } else {
      System.out.print(".");
    }

    // Additional test cases
    // Test with fewer songs than k
    TopSongs s2 = new TopSongs(5);
    s2.registerPlays("Song A", 100);
    s2.registerPlays("Song B", 200);
    result = s2.topK();
    got = new HashSet<>(result);
    want = new HashSet<>(Arrays.asList("Song A", "Song B"));
    if (!got.equals(want)) {
      throw new RuntimeException("\ntopK() with fewer songs than k\n");
    } else {
      System.out.print(".");
    }

    // Test with exact k songs
    TopSongs s3 = new TopSongs(3);
    s3.registerPlays("Song A", 100);
    s3.registerPlays("Song B", 200);
    s3.registerPlays("Song C", 300);
    result = s3.topK();
    got = new HashSet<>(result);
    want = new HashSet<>(Arrays.asList("Song A", "Song B", "Song C"));
    if (!got.equals(want)) {
      throw new RuntimeException("\ntopK() with exactly k songs\n");
    } else {
      System.out.print(".");
    }

    // Test with ties in play counts
    TopSongs s4 = new TopSongs(2);
    s4.registerPlays("Song A", 100);
    s4.registerPlays("Song B", 100);
    s4.registerPlays("Song C", 100);
    s4.registerPlays("Song D", 100);
    result = s4.topK();
    if (result.size() != 2) {
      throw new RuntimeException(
          String.format(
              "\ntopK() with tied play counts: got length %d, want length 2\n", result.size()));
    } else {
      System.out.print(".");
    }
  }
}
