import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import acm.program.ConsoleProgram;
/*This program allows the user to create a flight plan
 * based off of certain cities and the destinations they have.
 * It terminates when the current location and beginning location match
 * Created by: Robert Johns
 */
public class FlightPlanner extends ConsoleProgram {

	private HashMap<String, ArrayList<String>> compoundList = new HashMap<String, ArrayList<String>>();
	private String beginLoc = null;
	private String currentLoc = null;
	private ArrayList<String> trip = new ArrayList<String>();

	public void run() {
		//where to start to run program
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
		for (String s : trip) {
			println(s);
		}
	}

	private void readFromFile() {
		try {
			FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
			dialog.setMode(FileDialog.LOAD);
			dialog.setVisible(true);
			String path = dialog.getDirectory();
			path += dialog.getFile();
			FileReader reader = new FileReader(path);
			BufferedReader buffReader = new BufferedReader(reader);
			String[] holding = new String[2];
			String line = "";
			do {
				//reads the file line by line
				line = buffReader.readLine();
				System.out.println(line);
				if (line != null) {
					String wordTemp = "";
					boolean frontSwitch = true;
					//splits the line up based off of the arrow to know location -> destination
					for (String word : line.split(" -> ")) {
						//If key value is null create a new key/value of arraylist
						if (frontSwitch) {
							if (compoundList.getOrDefault(word, null) == null) {
								compoundList.put(word, new ArrayList<String>());
							}
							frontSwitch = false;
							wordTemp = word;
						} else {
							compoundList.put(wordTemp, addList(compoundList.get(wordTemp), word));
						}
					}
				}
				//do all this while line is not null
			} while (line != null);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//adds the destinations to the array list per location
	private static ArrayList<String> addList(ArrayList<String> list, String element) {
		ArrayList<String> newList = list;
		newList.add(element);
		return newList;
	}

	/*the main part of the program.
	 * lets the user add locations to their trip
	 * until the location they last chose equals to the beginning one
	*/
	private void trip(String b) {
		currentLoc = b;
		trip.add(b);
		do {
			println("Your Location is: " + currentLoc + "! Choose from the following locations: ");
			for (String x : compoundList.get(currentLoc)) {
				println(x);
			}
			println();
			currentLoc = readLine("Write Here: ");
			trip.add(currentLoc);
		} while (!b.equalsIgnoreCase(currentLoc));
	}
}
