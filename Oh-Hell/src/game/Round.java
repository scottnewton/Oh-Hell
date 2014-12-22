//package game;
//import java.util.Random;
//import java.util.Scanner;
//
///**
// * Scott Newton
// * 12/17/14
// */
//public class Round {
//
//	private String trumpSuit;
//	private Card topCard;
//	private int topPlayer;
//	private int startHandSize;
//	private int startPlayer;
//	private Table table;
//	
//	public Round(int startHandSize, Table table) throws Exception {
//		// the first dealer is selected randomly
//		Random rand = new Random();
//		int dealer = rand.nextInt(table.getNumPlayers());
//		this.startPlayer = (dealer+1) % table.getNumPlayers();
//		this.startHandSize = startHandSize;
//		this.table = table;
//		// create new shuffled deck
//		table.newShuffledDeck();
//		// deal cards
//		table.setHands(startHandSize);
//		// set trump suit
//		Card trump = table.getDeck().dealCard();
//		trumpSuit = trump.getSuit();
//		System.out.println("Trump Suit is " + trumpSuit);
//	}
//	
//	public void bets(Scanner sc) {
//		for (int seat = 0; seat < table.getNumPlayers(); seat++) {
//			// get current better
//			int current = (startPlayer+seat) % table.getNumPlayers();
//			table.getPlayers().get(current).printHand(); // print out hand for text-play
//			// get bet input (FORMAT UH OH :/)
//			int bet = sc.nextInt(); // ????????????????????????????
//			// set bet
//			table.setBet(current, bet);
//		}
//		System.out.println();
//	}		
//	
//	public void playRound(Scanner sc) {
//		for (int turns = 0; turns < startHandSize; turns++) {
//			// get current player
//			String startSuit = null;
//			// for each player
//			for (int seat = 0; seat < table.getNumPlayers(); seat++) {
//				// get current player
//				int currentPlayer = (startPlayer+seat) % table.getNumPlayers();
//				Player p = table.getPlayers().get(currentPlayer);
//				p.printHand(); // print hand for text-play
//						
//				// get card choice input (FORMAT UH OH)
//				int cardInput = sc.nextInt(); // where in list card is
//				// turn input into card object
//				try {
//					if (seat == 0) {
//						// set start suit
//						startSuit = p.getHand().getCards().get(cardInput).getSuit();
//						topCard = p.getHand().play(cardInput);
//						topPlayer = currentPlayer;
//					}
//					// check that suit is matched
//					else if (seat != 0 && p.getHand().getCards().get(cardInput).getSuit().equals(startSuit)) {
//						Card cd = p.getHand().play(cardInput);
//						// if card just played is best, update the top card
//						if( topCard.getSuit().equals(cd.getSuit()) && topCard.getValue() < cd.getValue()) {
//							topCard = cd;
//							topPlayer = currentPlayer;
//						}
//					}
//					else { // if suit doesn't match, check that card is valid play
//						boolean invalidPlay = false;
//						for (int i = 0; i < p.getHand().handSize(); i++) {
//							// check if any cards in hand match the start suit
//							if (p.getHand().getCards().get(i).getSuit().equals(startSuit)) {
//								invalidPlay = true;
//							}
//						}
//						// player has no cards of start suit => allowed to play anything
//						if (invalidPlay == false) {
//							Card cd = p.getHand().play(cardInput);
//							// new top card if it is the only trump suit played so far
//							if (cd.getSuit().equals(trumpSuit) && !topCard.getSuit().equals(trumpSuit)) {
//								topCard = cd;
//								topPlayer = currentPlayer;
//							} // or it is the highest valued trump suit played
//							else if (cd.getSuit().equals(trumpSuit) && topCard.getSuit().equals(trumpSuit) && 
//								cd.getValue() > topCard.getValue()) {
//									topCard = cd;
//									topPlayer = currentPlayer;
//							}
//						}
//					}
//				}
//				catch (Exception e) {
//				//error
//				System.out.println("Not surprised you ended up here...");
//				}
//			}
//			// End of hand. Add one hand win to winner, update startPlayer
//			table.addWin(topPlayer);
//			startPlayer = topPlayer;
//			System.out.println("Player " + topPlayer + " won!");
//			System.out.println();
//		}
//	}
//
//	
//}
