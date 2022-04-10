package publicBus;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class arrivalTimes {

	// converts file to arraylist	
	public static ArrayList<String> fileToList(String file) throws FileNotFoundException, IOException {

		ArrayList<String> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while (br.ready()) {
				list.add(br.readLine());
			}
		}
		return list;
	}


	// time format is hh:mm:ss
	public static ArrayList<String> tripInfo(String timeOfArrival) throws FileNotFoundException, IOException {

		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> fullList = fileToList("/Users/chineduanonyai/eclipse-workspace/Public-Bus-system/publicBus/src/publicBus/stop_times.txt");

		// removing invalid hours
		for (int i = 1; i < fullList.size(); i++) {
			try {

				String index = fullList.get(i);
				stopTimes stopTime = new stopTimes(index);
				LocalTime checkTime = stopTime.getArrivalTime();
				int hour = checkTime.getHour();

				if (hour > 24) {
					fullList.remove(i);
				}
			}
			catch (Exception e) {
			}

		}

		// looping through list to find arrival times
		for (int i = 1; i < fullList.size(); i++) {

			String index = fullList.get(i);
			stopTimes stopTime = new stopTimes(index);
			String busTime = stopTime.getArrivalTimeString();

			try {
				if (timeOfArrival.equalsIgnoreCase(busTime)) {

					String depTime = stopTime.getDepartureTimeString();
					String info = "" + Integer.toString(stopTime.getTripId()) + "\n *) Stop Name: "+ mainClass.stopName(Integer.toString(stopTime.getStopId())) 
							+ "\n *) Stop id: "+ Integer.toString(stopTime.getStopId()) + "\n *) Departure time: " + depTime 
							+ "\n *) Stop Sequence: "+ Integer.toString(stopTime.getStopOrder()) + "\n *) Pick-up type:  "+ Integer.toString(stopTime.getDeliverType()) 
							+ "\n *) Drop-off type: "+ Integer.toString(stopTime.getType()) + "\n *) Distance: "+ Double.toString(stopTime.getDistance());

					results.add(info);
				} else {

				}

			} catch (Exception e) {
			}
		}
		Collections.sort(results);
		return results;
	}

}
