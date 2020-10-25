import java.io.*;
import java.util.*;
import acm.program.*;
import acm.graphics.*;

public class FlightPlanner extends ConsoleProgram{

	private HashMap<String, ArrayList<String>> compoundList = new HashMap<String,ArrayList<String>>();
	private String beginLoc = null;
	private String endLoc = null;
	private String currentLoc = null;
	private ArrayList<String> trip = new ArrayList<String>();

	public void run() {
		readFromFile();
		println("Welcome to flight planner!");
		println("Here is a list of all the cities in the database: ");
		println();
		println(compoundList.keySet());
		println("Lets plan a round trip route!");
		beginLoc = readLine("Choose your first location: ");
		trip(beginLoc);
		println();
		println("Here is your round-trip: ");
		for(String s : trip) {
			println(s);
		}
	}

	private void readFromFile() {
		
		String s = null;
		try {
			FileReader reader = new FileReader("flights.txt");
			BufferedReader buffReader = new BufferedReader(reader);
			String[] holding = new String[2];
			int x = 0;
			while (true) {
				ArrayList<String> temp = new ArrayList<String>();
				String line = buffReader.readLine();
				if ((line != null) && (line != "")) {
					holding = line.split("->");
					String startLoc = holding[0];
					if(s == null) {
						s = startLoc;
					}
					if(startLoc.equalsIgnoreCase(s)) {
						temp.add(holding[1]);
					}else {
						compoundList.put(s, temp);
						s = startLoc;
						temp.clear();
					}
					
				} else {
					break;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void trip(String b) {
		currentLoc = b;
		trip.add(b);
		//ArrayList<String> destination = compoundList.get(currentLoc);
;		do {
			println("Your Location is: " + b + "! Choose from the following locations: ");
			for(String x : compoundList.get(currentLoc)) {
				println(x);
			}
			println();
			currentLoc = readLine("Write Here: ");
			trip.add(currentLoc);
		} while(b.equalsIgnoreCase(endLoc));
	}
}
