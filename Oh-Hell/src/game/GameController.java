package game;
import java.util.ArrayList;
import java.util.List;

/**
 * Scott Newton
 * 12/17/14
 */
public class GameController {
	
	private static int numRounds = 10;

	public static void main(String[] args) throws Exception {
		
		// set up players
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		Player p3 = new Player("Player 3");
		Player p4 = new Player("Player 4");
		
		// add players to player list
		List<Player> players = new ArrayList<Player>();
		players.add(p1); players.add(p2);
		players.add(p3); players.add(p4);
		
		// create table with players
		Table table = new Table(players);
		p1.setTable(table); p2.setTable(table);
		p3.setTable(table); p4.setTable(table);
				
		// rounds
		for (int round = 0; round < numRounds; round++) {
			int startHandSize = numRounds - round;
		
			table.setupRound(startHandSize);
			table.bets(); // set bets for the round
			table.playRound();
			table.updateScore();
			table.reset();
		}
		
		// print final results:
		System.out.println();
		System.out.println("Final results");
		for (int i = 0; i < players.size(); i++) {
			System.out.println(table.getPlayers().get(i).getName() + " " + table.getScore().get(i));
		}
	}
	
	
}
