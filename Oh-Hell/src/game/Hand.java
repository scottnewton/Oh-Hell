package game;
import java.util.Collections;
import java.util.List;

/**
 * Scott Newton
 * 12/17/14
 */
public class Hand {

	private List<Card> hand;
	private int size;
	
	public Hand(List<Card> hand) {
		this.hand = hand;
		size = hand.size();
	}
	
	public void sortHand() {
		Collections.sort(hand, Card.SuitComparator);
		Collections.sort(hand, Card.RankComparator);
	}
	
	public List<Card> getCards() {
		return hand;
	}
	
	public int handSize() {
		return size;
	}
	
	// play a card from hand
	public Card play(int index) {
		try {
			// get desired card
			Card c = hand.get(index);
			// shift remaining cards down
			for (int i = index; i < size-1; i++) {
				hand.set(i, hand.get(i+1));
			}
			size--;
			return c;
		} catch (Exception e) { // if index over end of hand
			System.out.println("Error: card not in hand");
			return null;
		}
	}
	
}