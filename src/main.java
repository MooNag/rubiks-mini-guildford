
public class main {
	
	public static double[] owen = {4, 17, 70, 120, 33, 135, 10, 35, 10.8, 6};
	public static double[] max = {4, 11, 38, 78, 20, 95, 15, 38, 0, 8};
	public static double[] john = {6, 18, 65, 150, 40, 180, 15, 0, 30, 13};
	public static double[] Sebastian = {4.8, 19, 185, 140, 37, 0, 0, 0, 0, 0};
	public static double[] squasome = {3, 11, 41, 79, 17, 90, 10, 10, 14, 6};
	public static double[] redTop = {5, 14, 60, 150, 24, 180, 7, 30, 0, 5};
	public static double[] Tak = {4, 11, 41, 100, 28, 55, 18, 0, 0, 0};
	public static double[] Jami = {3, 12, 53, 130, 25, 120, 5, 30, 23, 7};
	public static double[] Kerry = {3, 12, 48, 104, 21, 139, 3, 19, 6, 6};
	public static double[] Maxmill = {4, 12, 44, 100, 27, 44, 8, 0, 25, 0};
	public static double[] Parke = {5, 17, 210, 0, 34, 10, 0, 180, 13, 8};
	public static double[] Brody = {4, 14, 90, 180, 45, 0, 12, 45, 0, 11};
	public static double[] tyler = {4, 12, 45, 85, 20, 110, 5, 50, 15, 4.5};
	public static double[] bird = {4, 18.5, 90, 190, 65, 230, 10, 50, 20, 5};
	public static double[] zeke = {3, 9, 45, 95, 18, 95, 5.5, 13.5, 0, 5.5};
	public static double[] will = {3, 10.5, 45, 95, 30, 115, 7, 20, 20, 8};
	public static double[] peter = {4, 10, 45, 85, 35, 100, 7, 25, 20, 12};
	public static double[] wigs = {5, 17, 110, 195, 33, 180, 14, 90, 34, 6.5};
	public static double[] lucas = {4.5, 16, 70, 130, 38, 135, 7.5, 40, 9.9, 8};
	public static double[] ben = {3, 8.9, 41, 90, 20, 110, 3.5, 12, 14, 3.9};
	public static double[] jo = {4.3, 11, 47.5, 102, 19, 85, 5, 28, 24, 8.5};
	public static double[] akanearcane = {3, 10, 50, 110, 18, 65, 7, 0, 12, 5};
	public static double[] MJ = {4,11,50,100,24,0,0,0,0,0};
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final long startTime = System.nanoTime();
		
		new Solve(new Player[] {
				new Player(owen, "owen"),
				new Player(max, "max"),
				new Player(john, "john"),
				new Player(Sebastian, "Sebastian"),
				new Player(squasome, "squasome"),
				//new Player(redTop, "redTop"),
				//new Player(Tak, "Tak"),
				//new Player(Jami, "Jami"),
				//new Player(Kerry, "Kerry"),
				//new Player(Maxmill, "Maxmill"),
				//new Player(Parke, "Parke"),
				//new Player(Brody, "Brody"),
				//new Player(tyler, "tyler"),
				//new Player(bird, "bird"),
				//new Player(zeke, "zeke"),
				//new Player(will, "will"),
				//new Player(peter, "peter"),
				//new Player(wigs, "wigs"),
				//new Player(lucas, "lucas"),
				//new Player(ben, "ben"),
				//new Player(jo, "jo"),
				//new Player(akanearcane, "akanearcane"),
				//new Player(MJ, "MJ"),
				},
				0 //TODO the event switch penalty is 0 right now
				); 
		
		final long endTime = System.nanoTime();
		
		System.out.println("\n\nTime taken to solve: " + (endTime - startTime) / 1000000000.0);
		
	}



}
