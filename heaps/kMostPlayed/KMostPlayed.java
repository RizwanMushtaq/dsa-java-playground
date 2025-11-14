package heaps.kMostPlayed;

import java.util.*;

public class KMostPlayed {
  public static void main(String[] args) {
    new RunTests().runTests();
  }
}

class KMostPlayedSort {
  /**
   * trivially we can use sorting and returning first k elements.
   *
   * <p>Here time is O(n log n) and the extra space is O(n).
   *
   * <p>Even if we sort the array in-place, keep in mind that most built-in sorting libraries use
   * O(n) extra space internally.
   */
  List<String> solve(List<Song> songs, int k) {
    List<Song> sortedSongs = new ArrayList<>(songs);
    sortedSongs.sort((a, b) -> Integer.compare(b.plays(), a.plays()));
    List<String> result = new ArrayList<>();
    for (int i = 0; i < Math.min(k, sortedSongs.size()); i++) {
      result.add(sortedSongs.get(i).title());
    }
    return result;
  }
}

class KMostPlayedMaxHeap {
  /**
   * Using Max Heap.
   *
   * <p>This approach leverages that we can build a heap in linear time with heapify. After that we
   * do k heap pops. Since the heap has O(n) elements, each pop takes O(log n) time.
   *
   * <p>The time is O(n + k log n) and the extra space is O(n).
   */
  List<String> solve(List<Song> songs, int k) {
    PriorityQueue<Song> maxHeap =
        new PriorityQueue<>((a, b) -> Integer.compare(b.plays(), a.plays()));
    maxHeap.addAll(songs);
    List<String> result = new ArrayList<>();
    for (int i = 0; i < Math.min(k, songs.size()); i++) {
      result.add(maxHeap.remove().title());
    }
    return result;
  }
}

class KMostPlayedMinHeap {
  /**
   * Solve using Min Heap.
   *
   * <p>The time is O(n log k) and extra space is O(k).
   */
  List<String> solve(List<Song> songs, int k) {
    PriorityQueue<Song> minHeap = new PriorityQueue<>(Comparator.comparingInt(Song::plays));
    for (Song song : songs) {
      minHeap.offer(song);
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }
    List<String> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      result.add(minHeap.poll().title());
    }
    return result;
  }
}

record Song(String title, int plays) {}

class RunTests {
  public void runTests() {
    Object[][] testCases = {
      // Example from the book
      {
        new Song[] {
          new Song("All the Single Brackets", 132),
          new Song("Oops! I Broke Prod Again", 274),
          new Song("Coding In The Deep", 146),
          new Song("Boolean Rhapsody", 193),
          new Song("Here Comes The Bug", 291),
          new Song("All About That Base Case", 291)
        },
        3,
        new String[] {"All About That Base Case", "Here Comes The Bug", "Oops! I Broke Prod Again"}
      },

      // Test with fewer songs than k
      {
        new Song[] {new Song("Song A", 100), new Song("Song B", 200)},
        5,
        new String[] {"Song A", "Song B"}
      },

      // Test with exact k songs
      {
        new Song[] {new Song("Song A", 100), new Song("Song B", 200), new Song("Song C", 300)},
        3,
        new String[] {"Song A", "Song B", "Song C"}
      },

      // Test with k = 1
      {
        new Song[] {new Song("Song A", 100), new Song("Song B", 200), new Song("Song C", 300)},
        1,
        new String[] {"Song C"}
      },

      // Test with ties in play counts
      {
        new Song[] {
          new Song("Song A", 100),
          new Song("Song B", 100),
          new Song("Song C", 200),
          new Song("Song D", 200)
        },
        2,
        new String[] {"Song C", "Song D"}
      },

      // Test empty input
      {new Song[] {}, 3, new String[] {}}
    };

    // Test all implementations
    Object[][] implementations = {
      {"sort", new KMostPlayedSort()},
      {"maxHeap", new KMostPlayedMaxHeap()},
      {"minHeap", new KMostPlayedMinHeap()},
    };

    for (Object[] impl : implementations) {
      String solutionName = (String) impl[0];
      Object solution = impl[1];

      for (Object[] testCase : testCases) {
        List<Song> songs = Arrays.asList((Song[]) testCase[0]);
        int k = (Integer) testCase[1];
        List<String> want = Arrays.asList((String[]) testCase[2]);

        List<String> got = null;
        if (solution instanceof KMostPlayedSort) {
          got = ((KMostPlayedSort) solution).solve(songs, k);
        } else if (solution instanceof KMostPlayedMaxHeap) {
          got = ((KMostPlayedMaxHeap) solution).solve(songs, k);
        } else if (solution instanceof KMostPlayedMinHeap) {
          got = ((KMostPlayedMinHeap) solution).solve(songs, k);
        }

        assert got != null;
        Collections.sort(got);
        Collections.sort(want);

        if (!got.equals(want)) {
          throw new RuntimeException(
              String.format(
                  "\n%s(%s, %d): got: %s, want: %s\n", solutionName, songs, k, got, want));
        } else {
          System.out.print(".");
        }
      }

      // Also test tie breaking, any possible result is accepted
      List<Song> tieTest = Arrays.asList(new Song("Song A", 100), new Song("Song B", 100));

      List<String> got = null;
      switch (solution) {
        case KMostPlayedSort kMostPlayedSort -> got = kMostPlayedSort.solve(tieTest, 1);
        case KMostPlayedMaxHeap kMostPlayedMaxHeap -> got = kMostPlayedMaxHeap.solve(tieTest, 1);
        case KMostPlayedMinHeap kMostPlayedMinHeap -> got = kMostPlayedMinHeap.solve(tieTest, 1);
        default -> {}
      }

      if (!got.equals(List.of("Song A")) && !got.equals(List.of("Song B"))) {
        throw new RuntimeException(
            String.format("\n%s: got: %s, want: [Song A] or [Song B]\n", solutionName, got));
      } else {
        System.out.print(".");
      }
    }
  }
}
