package game;
import java.util.Comparator;

/**
 * Scott Newton
 * 12/17/14
 */
public class Card {

	private String suit;
	private int value;
	
	// Constructs card
	public Card(int val, int suite) {
		switch (suite%4) {
			case 0: suit = "C";
				break;
			case 1: suit = "H";
				break;
			case 2: suit = "S";
				break;
			case 3: suit = "D";
				break;
		}
		value = val;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	// for testing purposes
	public String outputCard() {
		return String.valueOf(value) + suit;
	}
	
	public boolean equals(Card c) {
		if (this.suit.equals(c.getSuit()) && this.value == c.getValue()) {
			return true;
		}
		return false;
	}
	
	public static Comparator<Card> SuitComparator = new Comparator<Card>() {
		public int compare(Card c1, Card c2) {
			return c1.getSuit().compareTo(c2.getSuit());
		}
	};
	
	public static Comparator<Card> RankComparator = new Comparator<Card>() {
		public int compare(Card c1, Card c2) {
			return c1.getValue() - c2.getValue();
		}
	};
	
}

