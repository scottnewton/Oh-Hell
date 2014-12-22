package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Scott Newton
 * 12/17/14
 */
public class Player {

	private String name;
	protected Hand hand;
	protected int score;
	protected int bet;
	protected int wins;
	protected Table table;
	
	public Player(String name) {
		this.name = name;
		score = 0;
		bet = 0;
		wins = 0;
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
		this.hand.sortHand();
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public void setTable(Table t) {
		table = t;
	}
	
	public void addWin() {
		wins++;
	}
	
	// unfinished method
	// seat = position of player in [0 numPlayers-1]
	public int setBet(int seat) {
		//calculate bet...
		// popup bet option
		//
		//int handSize = hand.getCards().size();
		//int numPlayers = table.getNumPlayers();
		bet = 0;
		return bet;
	}
	
	// seat = position of player in [0 numPlayers-1]
	public int chooseCardToPlay(int seat, String startSuit, Card topCard) {
		//choose which card to play and 
		//return the index of that card in hand
		List<Card> choices = new ArrayList<Card>();
		// find all cards in hand of the start suit
		for (int i = 0; i < hand.handSize(); i++) {
			if (hand.getCards().get(i).getSuit().equals(startSuit)) {
				choices.add(hand.getCards().get(i));
			}
		}
		if (choices.size() != 0) {
			// can choose any card in hand
			return hand.getCards().indexOf(choices.get(0));
		}
		else {
			// choose card from choices list
			return 0;
		}
	}
	
	public void printHand() {
		System.out.print("Hand: ");
		for (int i = 0; i < hand.handSize(); i++) {
			System.out.print(hand.getCards().get(i).outputCard());
			if (i != hand.handSize()-1) {
				System.out.print(", ");
			}
		}
		System.out.print("\n");
	}
	
	public int getScore() {
		return score;
	}
	
	public void updateScore(int pts) {
		score += pts;
	}
	
	public String getName() {
		return name;
	}
}
