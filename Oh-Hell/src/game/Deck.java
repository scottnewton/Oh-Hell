package game;
import java.util.Random;

/**
 * Scott Newton
 * 12/17/14
 */
public class Deck {
	
	private Card[] deck;
	private int numCards;
	
	// create deck
	public Deck() {
		deck = new Card[52];
		numCards = 0;
		// initialize all the cards
		for (int val = 1; val <= 13; val++) {
			for (int suit = 0; suit < 4; suit++) {
				deck[numCards] = new Card(val,suit);
				numCards++;
			}	
		}
	}
	
	// Fisher Yates Shuffle
	public void shuffle() {
		Random rand = new Random();
		for (int i = numCards-1; i >= 0; i--) {
			int randNum = rand.nextInt(numCards);
			Card temp = deck[randNum];
			deck[randNum] = deck[i];
			deck[i] = temp;
		}
	}
	
	// returns top card on deck
	public Card dealCard() throws Exception {
		if (numCards <= 0) {
			throw new Exception("Empty deck: cannot deal card!");
		}
		// numCards == 1 + position of last card, so decrement 
		// numCards before returning card
		numCards--;
		return deck[numCards];
	}
	
	public int cardsLeft() {
		return numCards;
	}
}
