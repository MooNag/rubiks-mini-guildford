import java.util.HashMap;
import java.util.Map;

public class Solve {

	public Situation situation;
	
	//public Map<String, Player> situation = ;
	double event_switch_penalty;
	
	Solve(Player[] players, double event_switch_penalty) {
		this.event_switch_penalty = event_switch_penalty;
		
		Map<String, Player> starting_situation = new HashMap<> ();
		
		//Initialize the situation with the first player doing all events.
		starting_situation.put("2x2", players[0]);
		starting_situation.put("3x3", players[0]);
		starting_situation.put("4x4", players[0]);
		starting_situation.put("5x5", players[0]);
		starting_situation.put("OH", players[0]);
		starting_situation.put("Mega", players[0]);
		starting_situation.put("pyramid", players[0]);
		starting_situation.put("square one", players[0]);
		starting_situation.put("clock", players[0]);
		starting_situation.put("skewb", players[0]);
		
		situation = new Situation(starting_situation, event_switch_penalty);
		
		//Find the best solve,
		situation = bruteSolve (situation, players);
		
		situation.print(players);
		
	}
	
	
	Situation bruteSolve (Situation starting_situation, Player[] players) {
		
		byte[] combinations = new byte[starting_situation.map.keySet().size()];
		double best_situation_time = Double.MAX_VALUE;
		
		//Fill the array with zeros.
		for(int i = 0; i < combinations.length; i++) {
			combinations[i] = 0;
		}
		
		Situation test_situation = new Situation (starting_situation.map, event_switch_penalty);
		Situation best_situation = new Situation (starting_situation.map, event_switch_penalty);
		
		whileLoop: while (true) {
			
			//Add one to the first element in the first array
			combinations[0]++;
			
			//Do the logic that is required for "bit-shifting"
			for(int i = 0; i < combinations.length - 1; i++) {
				//If any element on the array is overflowing, move it to the next one
				if(combinations[i] >= players.length) {
					//Shift over the "bit"
					combinations[i] = 0;
					combinations[i+1] += 1;
				}
			}
			
			//Break out of loop if the top most value is greater than players length.
			if(combinations[test_situation.events_array.length - 1] >= players.length) {
				break whileLoop;
			}
			
			
			//Loop through every possible situation and check if it is faster and stuff.
			//The array is X events long, and the index of player is the value.
			
			//printByteArray(combinations);
			
			//Initialize the situation with the first player doing all events.
			test_situation.map.put("2x2", players[combinations[0]]);
			test_situation.map.put("3x3", players[combinations[1]]);
			test_situation.map.put("4x4", players[combinations[2]]);
			test_situation.map.put("5x5", players[combinations[3]]);
			test_situation.map.put("OH", players[combinations[4]]);
			test_situation.map.put("Mega", players[combinations[5]]);
			test_situation.map.put("pyramid", players[combinations[6]]);
			test_situation.map.put("square one", players[combinations[7]]);
			test_situation.map.put("clock", players[combinations[8]]);
			test_situation.map.put("skewb", players[combinations[9]]);
			
			
			//Check if a single player's event's time is greater than the current best situation.
			//If that is true, there is no possible way this situation is faster. Therefore skip
			//all of the expensive calculations.
			for(int i = 0; i < test_situation.events_array.length; i++) {
				
				if(test_situation.map.get(test_situation.events_array[i]).times.get(test_situation.events_array[i]) > best_situation_time) {
					
					//Continue to the next player for this event.
					//Take the last overflowed index in the array, and increment it.
					combinations[i] += 1;
					
					//Set all of the values less than the index to zero.
					for(int x = i - 1; x > 0; x--) {
						combinations[x] = 0;
					}
					
					//printByteArray(combinations);
					//System.out.println("Continuing...");
					
					//Continue to the next iteration with the jumped values.
					continue whileLoop;
				}
			
				//System.out.println(i);
			}
			
			double time_for_situation = test_situation.calculateTime(players);
			
			//Check if this solution is faster than the current fastest
			if(time_for_situation < best_situation_time) {
				best_situation = new Situation (test_situation.map, event_switch_penalty);
				best_situation_time = best_situation.calculateTime(players);
				//printSituation(best_situation, players);
			}
			
			
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
	
}





