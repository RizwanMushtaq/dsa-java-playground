package sorting;

import java.util.Arrays;

public class CardBySuit implements Comparable<CardBySuit> {
  private int value;
  private String suit;

  public CardBySuit(int value, String suit) {
    this.value = value;
    this.suit = suit;
  }

  public static void main(String[] args) {
    CardBySuit[] deck = {
      new CardBySuit(8, "Hearts"),
      new CardBySuit(8, "Clubs"),
      new CardBySuit(3, "Clubs"),
      new CardBySuit(3, "Hearts")
    };

    Arrays.sort(deck);

    for (CardBySuit card : deck) {
      System.out.println(card);
    }
  }

  @Override
  public int compareTo(CardBySuit c) {
    if (this.suit != c.suit) {
      return Integer.compare(getSuitByValue(this.suit), getSuitByValue(c.suit));
    }
    return Integer.compare(this.value, c.value);
  }

  private int getSuitByValue(String suit) {
    return switch (suit.toLowerCase()) {
      case "hearts" -> 1;
      case "clubs" -> 2;
      case "diamonds" -> 3;
      case "spades" -> 4;
      default -> 0;
    };
  }

  @Override
  public String toString() {
    return this.value + " " + this.suit;
  }
}
