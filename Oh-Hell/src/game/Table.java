package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Scott Newton
 * 12/17/14
 */
public class Table {

	// table member variables
	private List<Player> players;
	private int numPlayers;
	private List<Integer> bets;
	private List<Integer> wins;
	private List<Integer> score;
	private Deck deck;
	
	// round member variables
	private String trumpSuit;
	private Card topCard;
	private Player topPlayer;
	private int startHandSize;
	private int startPlayerIndex;
	private Player currentPlayer;
	private String startSuit;
	
	// set up table
	public Table(List<Player> players) {
		this.players = players;
		numPlayers = players.size();
		bets = new ArrayList<Integer>();
		wins = new ArrayList<Integer>();
		score = new ArrayList<Integer>();
		initBets();
		
		deck = null;
	}
	
	// initialize lists to proper length
	private void initBets() {
		for (int i = 0; i < numPlayers; i++) {
			bets.add(0);
			wins.add(0);
			score.add(0);
		}
	}
	
	public void newShuffledDeck() {
		deck = new Deck();
		deck.shuffle();
	}
	
//	public int getWins(int num) {
//		return wins.get(num);
//	}
	
	public void addWin(Player p, int playerIndex) {
		wins.set(playerIndex, wins.get(playerIndex)+1); // update wins for table
		p.addWin(); // update wins for player
	}
	
	public void setBet(int player, int amount) {
		bets.set(player, amount);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public List<Integer> getScore() {
		return score;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
//	public Deck getDeck() {
//		return deck;
//	}
	
	// deals hands for each player
	public void setHands(int handSize) throws Exception {
		for (int j = 0; j < numPlayers; j++) { // for each player
			List<Card> hand = new ArrayList<Card>(); // create card array for hand
			for (int i = 0; i < handSize; i++) {
				hand.add(deck.dealCard()); // fills card array
			}
			players.get(j).setHand(new Hand(hand)); // sets hand
		}
	}
	
	// after each round this method is called to update the scores of each player
	public void updateScore() {
		for (int k = 0; k < numPlayers; k++) {
			// if player won number of tricks bet
			if (bets.get(k) == wins.get(k)) {
				score.set(k, score.get(k)+10);
				players.get(k).updateScore(10);
			}
			else { // player loses the difference between the bet and number of tricks won
				score.set(k, score.get(k) - Math.abs(bets.get(k) - wins.get(k)));
				players.get(k).updateScore(Math.abs(bets.get(k) - wins.get(k)));
			}
		}
	}

	// prepare for next round
	public void reset() {
		for (int i = 0; i < numPlayers; i++) {
			bets.set(i,0);
			wins.set(i,0);
		}
	}

	public void setupRound(int startHandSize) throws Exception {
		Random rand = new Random();
		int dealer = rand.nextInt(numPlayers);
		this.startPlayerIndex = (dealer+1) % numPlayers;
		System.out.println("Starting player is " + players.get(startPlayerIndex).getName());
		this.startHandSize = startHandSize;
		// create new shuffled deck
		newShuffledDeck();
		// deal cards
		setHands(startHandSize);
		// set trump suit
		Card trump = deck.dealCard();
		trumpSuit = trump.getSuit();
		System.out.println("Trump Suit is " + trumpSuit);
	}

	public void bets() {
		for (int seat = 0; seat < numPlayers; seat++) {
			// get current better
			int current = (startPlayerIndex+seat) % numPlayers;
			currentPlayer = players.get(current);
			currentPlayer.printHand(); // print out hand for text-play
			// set bet
			setBet(current, currentPlayer.setBet(seat));
		}
	}		

	public void playRound() {
		for (int turns = 0; turns < startHandSize; turns++) {
			// for each player
			for (int seat = 0; seat < numPlayers; seat++) {
				// get current player
				int current = (startPlayerIndex+seat) % numPlayers;
				currentPlayer = players.get(current);
				currentPlayer.printHand(); // print hand for text-play
				// get card choice input
				int cardInput = currentPlayer.chooseCardToPlay(seat, startSuit, topCard);
				// turn input into card object -- could return card object directly...
				try {
					if (seat == 0) {
						// set start suit
						startSuit = currentPlayer.getHand().getCards().get(cardInput).getSuit();
						topCard = currentPlayer.getHand().play(cardInput);
						topPlayer = currentPlayer;
					}
					// check that suit is matched
					else if (seat != 0 && currentPlayer.getHand().getCards().get(cardInput).getSuit().equals(startSuit)) {
						Card cd = currentPlayer.getHand().play(cardInput);
						// if card just played is best, update the top card
						if( topCard.getSuit().equals(cd.getSuit()) && topCard.getValue() < cd.getValue()) {
							topCard = cd;
							topPlayer = currentPlayer;
						}
					}
					else { // if suit doesn't match, check that card is valid play
						boolean invalidPlay = false;
						for (int i = 0; i < currentPlayer.getHand().handSize(); i++) {
							// check if any cards in hand match the start suit
							if (currentPlayer.getHand().getCards().get(i).getSuit().equals(startSuit)) {
								invalidPlay = true;
							}
						}
						// player has no cards of start suit => allowed to play anything
						if (invalidPlay == false) {
							Card cd = currentPlayer.getHand().play(cardInput);
							// new top card if it is the only trump suit played so far
							if (cd.getSuit().equals(trumpSuit) && !topCard.getSuit().equals(trumpSuit)) {
								topCard = cd;
								topPlayer = currentPlayer;
							} // or it is the highest valued trump suit played
							else if (cd.getSuit().equals(trumpSuit) && topCard.getSuit().equals(trumpSuit) && 
									cd.getValue() > topCard.getValue()) {
								topCard = cd;
								topPlayer = currentPlayer;
							}
						}
					}
				}
				catch (Exception e) {
					//error
					System.out.println("Not surprised you ended up here...");
				}
			}
			// End of hand. Add one hand win to winner, update startPlayer
			int indexOfTop = players.indexOf(topPlayer);
			addWin(topPlayer, indexOfTop);
			startPlayerIndex = indexOfTop;
			// output for testing
			System.out.println("Player " + topPlayer.getName() + " won!");
			System.out.println("Scores: " + score.get(0) + ", " + score.get(1) + ", " + score.get(2) + ", " + score.get(3));
			System.out.println("Bets: " + bets.get(0) + ", " + bets.get(1) + ", " + bets.get(2) + ", " + bets.get(3));
			System.out.println("Wins: " + wins.get(0) + ", " + wins.get(1) + ", " + wins.get(2) + ", " + wins.get(3));
			System.out.println();
		}
	}


}
