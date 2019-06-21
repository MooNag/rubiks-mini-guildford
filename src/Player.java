import java.util.HashMap;
import java.util.Map;

public class Player {
	
	//2x2, 3x3, 4x4, 5x5, OH, Mega, Pyra, square one, clock, skewb
	
	
		public Map<String, Double> times = new HashMap<> ();
		public double[] timingsRaw;
		
		public String name;
	
		//These values are used for extra meta-data
		public double penalty = 0;
		public int event_count = 0;
	
		public Player (double[] timings, String name) {
			
			/*
			double[] timesFixed = timings;
			
			//For any event that has no data, set the value to max value.
			for(int i = 0; i < timesFixed.length; i++) {
				if(timesFixed[i] == 0) {
					//Set it to a really large number to make the run useless
					timesFixed[i] = 9999999d;
				}
			}
			*/
			
			times.put("2x2", timings[0]);
			times.put("3x3", timings[1]);
			times.put("4x4", timings[2]);
			times.put("5x5", timings[3]);
			times.put("OH", timings[4]);
			times.put("Mega", timings[5]);
			times.put("pyramid", timings[6]);
			times.put("square one", timings[7]);
			times.put("clock", timings[8]);
			times.put("skewb", timings[9]);
			
			
			this.name = name;
			
		}
	

}
