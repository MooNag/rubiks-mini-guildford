import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Situation {

	Map<String, Player> map;
	double event_switch_penalty;
	String[] events_array;
	
	Situation(Map<String, Player> map, double event_switch_penalty) {
		this.event_switch_penalty = event_switch_penalty;
		
		this.map = new HashMap<String, Player>(map);
		
		//Generate the array of events so that the other functions do not have to do it later.
		//This improves performance.
		Set<String> keys = map.keySet();
		events_array = keys.toArray(new String[keys.size()]);
	}

	
	double getPlayerTime (Player player) {

		int player_time = 0;
		int event_count = 0;
		double penalty = 0.0;
					
		//Loop through each event and see if that player is doing that event
		for(int x = 0; x < events_array.length; x++) {
			if(map.get(events_array[x]) == player) {
				
				player_time += player.times.get(events_array[x]);
				event_count++;
				//System.out.println(players_array[i].times.get(array[x]));
			}
		}
		
		//Assign the player event count for later use.
		player.event_count = event_count;
		
		//If a player does not have an event, they will get a penalty that is below zero.
		if(event_count > 0) {
			
			//Calculate and assign the penalty for the player.
			penalty = ((player.event_count - 1) * event_switch_penalty);
			
			//Add an event switching penalty to the player's time.
			player_time += penalty;

		}
		
		//Assign the values to the player, this is done last because accessing a local instance is faster than a class instance.
		player.time = player_time;
		player.penalty = penalty;
		return player_time;
	}
	
	
	
	
	
	
	void print (Player[] players) {
		
		double total = calculateTime(players);
		
		
		System.out.println("Situation: ");
		
		//For each player doing events.
		for(int i = 0; i < events_array.length; i++) {
			
			System.out.println(events_array[i] + ": " + map.get(events_array[i]).name + " (" + map.get(events_array[i]).times.get(events_array[i]) + ")");
			
		}
		
		System.out.println("\nPlayers: ");
		
		//For each player doing events.
		for(int i = 0; i < players.length; i++) {
			
			String events_list = "[";
			int event_count = 0;
			
			//Loop through each event and see if that player is doing that event
			for(int x = 0; x < events_array.length; x++) {
				if(map.get(events_array[x]) == players[i]) {
					event_count++;
					events_list += events_array[x] + "(" + players[i].times.get(events_array[x]) + ")" + ", ";
					
				}
			}
			
			//Jank: Remove the last comma.
			events_list = events_list.substring(0, events_list.length()-2);
			
			events_list += "]";
			
			//Get the players total time.
			double player_time = getPlayerTime(players[i]);
			
			//If their total time is zero, they have no events assigned to them.
			if(player_time == 0) {
				System.out.println(players[i].name + ": NO EVENTS");
			} else {
				System.out.println(players[i].name + " " + event_count + " event(s): " + player_time + " seconds total -> " + events_list);
			}
		}
		
		System.out.println("\nTime: " + total + " seconds for " + events_array.length + " events with an event switch penalty of " + event_switch_penalty + " seconds");
	}
	
	
	double calculateTime (Player[] players) {
		
		double max = 0;
		
		//For each player doing events.
		for(int i = 0; i < players.length; i++) {
			
			//Get the time for the player and if it is the longest the total time increases.
			double player_time = getPlayerTime(players[i]);
			if(player_time > max) {
				max = player_time;
			}
			
		}
		
		return max;
	}
	
}
