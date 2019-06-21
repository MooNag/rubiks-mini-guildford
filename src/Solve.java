import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solve {

	public Map<String, Player> situation = new HashMap<> ();
	double event_switch_penalty;
	
	Solve(Player[] players, double event_switch_penalty) {
		this.event_switch_penalty = event_switch_penalty;
		
		//Initialize the situation with the first player doing all events.
		situation.put("2x2", players[0]);
		situation.put("3x3", players[0]);
		situation.put("4x4", players[0]);
		situation.put("5x5", players[0]);
		situation.put("OH", players[0]);
		situation.put("Mega", players[0]);
		situation.put("pyramid", players[0]);
		situation.put("square one", players[0]);
		situation.put("clock", players[0]);
		situation.put("skewb", players[0]);
		
		//TODO debug.
		situation = bruteSolve (situation, players);
		
		
		printSituation(situation);
		
		//The first thing is to find the player with the 
		
	}
	
	
	Map<String, Player> bruteSolve (Map<String, Player> starting_situation, Player[] players) {
		
		byte[] combinations = new byte[starting_situation.keySet().size()];
		double best_situation_time = Double.MAX_VALUE;
		
		//Fill the array with zeros.
		for(int i = 0; i < combinations.length; i++) {
			combinations[i] = 0;
		}
		
		Map<String, Player> test_situation = new HashMap<String, Player> (starting_situation);
		Map<String, Player> best_situation = new HashMap<String, Player> (starting_situation);
		
		whileLoop: while (true) {
			
			//Add one to the first array
			combinations[0]++;
			
			for(int i = 0; i < combinations.length - 1; i++) {
				//If any element on the array is overflowing, move it to the next one
				if(combinations[i] >= players.length) {
					combinations[i] = 0;
					combinations[i+1] += 1;
					
					//Break out of loop if the top most value is greater than players length.
					if(combinations[starting_situation.keySet().size()-1] >= players.length) {
						break whileLoop;
					}
				}
			}
			
			
			//Loop through every possible situation and check if it is faster and stuff.
			//The array is X events long, and the index of player is the value.
			
			//printByteArray(combinations);
			
			//Initialize the situation with the first player doing all events.
			test_situation.put("2x2", players[combinations[0]]);
			test_situation.put("3x3", players[combinations[1]]);
			test_situation.put("4x4", players[combinations[2]]);
			test_situation.put("5x5", players[combinations[3]]);
			test_situation.put("OH", players[combinations[4]]);
			test_situation.put("Mega", players[combinations[5]]);
			test_situation.put("pyramid", players[combinations[6]]);
			test_situation.put("square one", players[combinations[7]]);
			test_situation.put("clock", players[combinations[8]]);
			test_situation.put("skewb", players[combinations[9]]);
			
			
			double time_for_situation = calculateTimeForSituation(test_situation);
			
			//Check if this solution is faster than the current fastest
			if(time_for_situation < best_situation_time) {
				best_situation = new HashMap<String, Player> (test_situation);
				best_situation_time = calculateTimeForSituation(best_situation);
				printSituation(best_situation);
			}
			
			//System.out.println(combinations[9]);
			
		
			
			
		}
		
		
		
		return best_situation;
	}
	
	
	void printByteArray (byte[] a) {
		System.out.print("(");
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			System.out.print(", ");
		}
		System.out.println(")");
	}
	
	Player find_fastest_for_event (Map<String, Player> situation, Player[] players, String event) {
		
		Player best_player = players[0]; //Init with random player.
		for(int i = 0; i < players.length; i++) {
			
			
			if(players[i].times.get(event) < best_player.times.get(event)) {
				best_player = players[i];
			}
			
			//test_situation.put(event, players[i]);
			//if(calculateTimeForSituation(test_situation) < calculateTimeForSituation(situation)) {
			//	best_player = players[i];
			//}
		}
		
		return best_player;
	}
	
	
	double calculateTimeForSituation (Map<String, Player> situation) {
		
		double total = 0;
		
		Set<String> keys = situation.keySet();
		String[] array = keys.toArray(new String[keys.size()]);
		
		Set<Player> players = new HashSet<Player>();
		players.addAll(situation.values());
		Player[] players_array = players.toArray(new Player[players.size()]);
		
		//For each player doing events.
		for(int i = 0; i < players.size(); i++) {
			
			double player_time = getPlayerTime(situation, players_array[i]);
			if(player_time > total) {
				total = player_time;
			}
			
		}
		
		return total;
	}
	
	double getPlayerTime (Map<String, Player> situation, Player player) {
		
		Set<String> keys = situation.keySet();
		String[] array = keys.toArray(new String[keys.size()]);
		
		double player_time = 0;
		
		int event_count = 0; //Total number of events this player does.
					
		//Loop through each event and see if that player is doing that event
		for(int x = 0; x < keys.size(); x++) {
			if(situation.get(array[x]) == player) {
				event_count++;
				//System.out.println(players_array[i].name + " is doing event " + array[x]);
				double time = player.times.get(array[x]);
				if(time == 0) {
					player_time += 9999999d;
				} else {
					player_time += time;
				}
				//System.out.println(players_array[i].times.get(array[x]));
			}
		}
		
		//Add an event switching penalty to the player.
		player_time += (event_count * event_switch_penalty);
		
		//TODO debug.
		player.event_count = event_count;
		player.penalty = (event_count * event_switch_penalty);
		
		return player_time;
		
	}
	
	void printSituation (Map<String, Player> situation) {
		System.out.println("");
		System.out.println("");
		System.out.println("Situation: ");
		
		double total = calculateTimeForSituation(situation);
		
		Set<String> keys = situation.keySet();
		String[] array = keys.toArray(new String[keys.size()]);
		
		Set<Player> players = new HashSet<Player>();
		players.addAll(situation.values());
		Player[] players_array = players.toArray(new Player[players.size()]);
		
		//For each player doing events.
		for(int i = 0; i < keys.size(); i++) {
			
			System.out.println("Event: " + array[i] + " - Player: " + situation.get(array[i]).name + " (events: " + situation.get(array[i]).event_count + ") - Time: " + situation.get(array[i]).times.get(array[i]));
			
		}
		
		System.out.println("");
		System.out.println("Players: ");
		//For each player doing events.
		for(int i = 0; i < players.size(); i++) {
					
			double player_time = 0;
			//System.out.println(players_array[i]);
			//total += situation.get(array[i]).times.get(array[i]);
					
			//Loop through each event and see if that player is doing that event
			for(int x = 0; x < keys.size(); x++) {
				if(situation.get(array[x]) == players_array[i]) {
					//System.out.println(players_array[i].name + " is doing event " + array[x]);
					player_time += players_array[i].times.get(array[x]);
					//System.out.println(players_array[i].times.get(array[x]));
				}
			}
			
			System.out.println(players_array[i].name + ": " + player_time);
		}
		
		System.out.println("Total Time: " + total);
	}
	
	
}
