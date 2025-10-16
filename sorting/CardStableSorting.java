package sorting;

import java.util.Arrays;

/**
 * You don't need special logic for stable sort. Java sort for Objects will handle stability for
 * you.
 *
 * <p>Arrays.sort(Object[]) -> Objects -> TimSort -> ✅ Yes.
 *
 * <p>Collections.sort(List<T>) -> Objects-> TimSort -> ✅ Yes
 *
 * <p>List.sort(Comparator) -> Objects ->TimSort-> ✅ Yes
 *
 * <p>Arrays.sort(int[])` / `double[]` / etc. -> Primitives | Dual-Pivot-QuickSort -> ❌ No
 *
 * <p>No Arrays.parallelSort(Object[]) -> Objects -> Parallel Merge Sort -> ✅Yes
 *
 * <p>Arrays.parallelSort(int[]) -> Primitives -> Parallel QuickSort -> ❌ No
 */
public class CardStableSorting implements Comparable<CardStableSorting> {
  private int value;
  private String suit;

  public CardStableSorting(int value, String suit) {
    this.value = value;
    this.suit = suit;
  }

  public static void main(String[] args) {
    CardStableSorting[] deck = {
      new CardStableSorting(9, "clubs"),
      new CardStableSorting(4, "spades"),
      new CardStableSorting(9, "spades"),
      new CardStableSorting(4, "clubs"),
    };

    Arrays.sort(deck);

    for (CardStableSorting card : deck) {
      System.out.println(card);
    }
  }

  @Override
  public int compareTo(CardStableSorting c) {

    return Integer.compare(this.value, c.value);
  }

  @Override
  public String toString() {
    return this.value + " " + this.suit;
  }
}
