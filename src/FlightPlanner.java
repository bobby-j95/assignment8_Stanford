import java.io.*;
import java.util.*;

import acm.graphics.*;

public class FlightPlanner {
	
	private HashMap<String, String> compoundList;
	public void run() {
		readFromFile();
		
	}
	
	private void readFromFile() {
		try {
			FileReader reader = new FileReader("flights.txt");
			BufferedReader buffReader = new BufferedReader(reader);
			String line = buffReader.readLine();
			String[] holding = line.split("->");
			String startLoc = holding[0];
			String endLoc = holding[1];
			compoundList.put(startLoc, endLoc);
			//DO MORE HERE
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
