package sorting;

import java.util.Arrays;

public class Card implements Comparable<Card> {
  private int value;
  private String suit;

  public Card(int value, String suit) {
    this.value = value;
    this.suit = suit;
  }

  public static void main(String[] args) {
    Card[] deck = {
      new Card(8, "hearts"), new Card(8, "clubs"), new Card(3, "clubs"), new Card(3, "hearts")
    };

    Arrays.sort(deck);

    for (Card item : deck) {
      System.out.println(item);
    }
  }

  @Override
  public int compareTo(Card c) {
    if (this.value != c.value) {
      return Integer.compare(this.value, c.value);
    }

    return Integer.compare(getSuitValue(this.suit), getSuitValue(c.suit));
  }

  private int getSuitValue(String suit) {
    return switch (suit) {
      case "clubs" -> 1;
      case "hearts" -> 2;
      case "spades" -> 3;
      case "diamonds" -> 4;
      default -> 0;
    };
  }

  @Override
  public String toString() {
    return value + " " + suit;
  }
}
