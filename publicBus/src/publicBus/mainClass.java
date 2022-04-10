package publicBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class mainClass {

	public static boolean invalidInputs = false;
	public static ArrayList<stops> stops = new ArrayList<stops>();
	public static ArrayList<String> results = new ArrayList<String>();
	public static ArrayList<transfer> transfers = new ArrayList<transfer>();
	public static ArrayList<stopTimes> stopTimes = new ArrayList<stopTimes>();
	public static tstAlgo tst = new tstAlgo();




	//create an arrayList for stopTimes
	public static void stopTimesList() {

		try {
			File timesTxt = new File("/Users/chineduanonyai/eclipse-workspace/Public-Bus-system/publicBus/src/publicBus/stop_times.txt");
			Scanner input = new Scanner(timesTxt);
			input.nextLine();
			while (input.hasNextLine()) {
				String newLine = input.nextLine();
				stopTimes newStopTime = new stopTimes(newLine);
				stopTimes.add(newStopTime);
			}
			input.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("stop_times.txt can not found");
			invalidInputs = true;
		}
	}

	// create an arrayList for stops
	public static void stopsList() throws FileNotFoundException {

		try {
			File stopsTxt = new File("/Users/chineduanonyai/eclipse-workspace/Public-Bus-system/publicBus/src/publicBus/stops.txt");
			Scanner input = new Scanner(stopsTxt);
			input.nextLine();

			while (input.hasNextLine()) {
				String data = input.nextLine();
				stops.add(new stops(data));
			}
			input.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("stops.txt can not found.");
			invalidInputs = true;
		}
	}

	// create an arrayList for transfers
	public static void transfersList() {

		try {
			File transferTxt = new File("/Users/chineduanonyai/eclipse-workspace/Public-Bus-system/publicBus/src/publicBus/transfers.txt");
			Scanner input = new Scanner(transferTxt);
			// skip the first line
			input.nextLine();
			while (input.hasNextLine()) {
				String newLine = input.nextLine();
				transfers.add(new transfer(newLine));
			}
			input.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("transfers.txt can not found.");
			invalidInputs = true;
		}
	}


	// adds names of every stop to the TST. Auto-completion can come after
	public static void insertTST() {

		// Goes through every stop class...
		for (int i = 0; i < stops.size(); i++) {
			tst.insert(stops.get(i).getName());

		}
	}

	// returns data on the stops
	public static String[] stopData(String name) {

		String[] data = new String[7];

		for (int i = 0; i < stops.size(); i++) {

			if (name.equalsIgnoreCase(stops.get(i).getName())) {
				data[0] = stops.get(i).getID();
				data[1] = stops.get(i).getCode();
				data[2] = stops.get(i).getName();
				data[3] = stops.get(i).getDesc();
				data[4] = stops.get(i).getLatitude();
				data[5] = stops.get(i).getLongitude();
				data[6] = stops.get(i).getZone();
			}

		}
		return data;
	}

	// returns name of the stops
	public static String stopName(String ID) {

		String name = "";
		for (int i = 0; i < stops.size(); i++) {

			if (ID.equals(stops.get(i).getID())) {
				name = stops.get(i).getName();
			}

		}
		return titleFormat(name);
	}

	// formatting for the stop data displayed 
	public static String[] formatData(String[] stopData) {

		String[] data = new String[7];

		// changing the order of the outputs to look cleaner together
		data[0] = "Name: " + titleFormat(stopData[2]);
		data[1] = " 1) Description: " + titleFormat(stopData[3]);
		data[2] = " 2) Stop ID: " + stopData[0];
		data[3] = " 3) Stop Code: " + stopData[1];
		data[4] = " 4) Latitude: " + stopData[4];
		data[5] = " 5) Longitude: " + stopData[5];
		data[6] = " 6) Zone ID: " + stopData[6];
		return data;
	}

	// formatting words to start with capital letters
	public static String titleFormat(String name) {

		if (name != null) {

			name = name.toLowerCase(); // because this function only works on lower case
			char[] charArray = name.toCharArray();
			boolean isSpace = true;

			for (int i = 0; i < charArray.length; i++) {

				// if the array element is a letter
				if (Character.isLetter(charArray[i])) {

					// check space is present before the letter and if so, change next letter to upper case
					if (isSpace) {
						charArray[i] = Character.toUpperCase(charArray[i]);
						isSpace = false;
					}
				} 
				else {

					// if the new character is not character
					isSpace = true;
				}
			}

			// convert the char array to the string
			name = String.valueOf(charArray);
			return name;
		} 
		else {
			return name;
		}
	}

	public static boolean validTime(String data) {

		boolean legitTime = true;
		if (data.length() < 1) {
			System.out.println("Incorrect: Inputted time is empty");
			return false;
		}

		for (int i = 0; i < data.length(); i++) {
			if (Character.isLetter(data.charAt(i)) == true) {
				System.out.print("Incorrect: Time does not contain any letters");
				return false;
			}
		}

		if (data.length() == 8) {

			if (data.charAt(2) != ':' || data.charAt(5) != ':') {
				System.out.println("Incorrect: Time isn't formatted correctly");
				return false;
			}

		}

		if (data.length() < 8) {
			System.out.println("Incorrect input, it is too short");
			return false;
		}

		String[] splittime = null;

		if (data.charAt(0) == ' ') {
			data = data.substring(1);
		}

		splittime = data.split(":");

		if (Integer.parseInt(splittime[2]) > 59) {
			System.out.println("Incorrect time, seconds can not be greater than 59");
			legitTime = false;
		}

		if (Integer.parseInt(splittime[1]) > 59) {
			System.out.println("Incorrect time, minutes can not be greater than 59");
			legitTime = false;
		}

		if (Integer.parseInt(splittime[0]) > 23) {
			System.out.println("Incorrect time, hours can not be greater than 23");
			legitTime = false;
		}

		return legitTime;
	}

	public static boolean validStopID(String ID) {

		for (int i = 0; i < stops.size(); i++) {

			if (ID.equals(stops.get(i).getID())) {
				return true;
			}

		}
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		stopsList();
		stopTimesList();
		transfersList();
		insertTST();

		// Program does not run if the input files are not found
		if (invalidInputs == false) {

			Scanner input = new Scanner(System.in);
			boolean exit = false;
			while (exit == false) {
				System.out.println("\n");
				System.out.println("Please select from the options you want to use by typing their corresponding number. The available options are:");
				System.out.println(" 1. Finding shortest paths between 2 bus stops ");
				System.out.println(" 2. Searching for a bus stop ");
				System.out.println(" 3. Searching for all trips ");
				System.out.println(" 4. Option to exit the programme");
				System.out.println("\n");
				System.out.print("Please type here: ");

				if (input.hasNextLine()) {
					String option = input.nextLine();

					if (option.equals("4")) {
						exit = true;
						System.out.println("Exited");
						break; //exit program
					}


					// Shortest Paths between 2 Stops
					else if (option.equals("1")) { 
						String stop1 = "";
						String stop2 = "";

						try {

							System.out.println("Input the stop ID for the starting destination, then do the same for the end destination");
							String start = input.nextLine();
							String stop = input.nextLine();


							if(validStopID(start) == false) {
								stop1 = start;
							}

							if(validStopID(stop) == false) {
								stop1 = stop;
							}

							if(validStopID(stop) == true && validStopID(start) == true) {

								stops startStop = null;
								stops stopStop = null;
								int stopInt = Integer.parseInt(stop);
								int startInt = Integer.parseInt(start);
								boolean error = false;
								shortestPathsAlgo shortestPath = new shortestPathsAlgo();

								for (int i = 0; i < stops.size(); i++) {

									if (stops.get(i).stop_id == startInt) {
										startStop = stops.get(i);
									}

									if (stops.get(i).stop_id == stopInt) {
										stopStop = stops.get(i);
									}


								}

								graph bus = new graph(stops, transfers, stopTimes);

								if (!bus.invalidInput) {
									shortestPath = bus.getShortestPath(startStop, stopStop);

									if (shortestPath.getWeight() == -1 && error == false) {
										System.out.println("Shortest Path does not exist");
									} 
									if (shortestPath.getWeight() < 0) {
										error = true;
									}

									else {
										System.out.println("The shortest path between these two is a distance of: " + shortestPath.getWeight());
										System.out.println("\n");
										System.out.println("Journey takes the following path, beginning at the start stop: ");
										System.out.println("\n");

										String[] output = new String[7];

										for (int i = shortestPath.stops.size() - 1; i >= 0; i--) {

											output[0] = "Name: " + titleFormat(shortestPath.stops.get(i).stop_name);
											output[1] = " 1) Description: " + titleFormat(shortestPath.stops.get(i).stop_desc);
											output[2] = " 2) Stop ID: " + shortestPath.stops.get(i).stop_id;
											output[3] = " 3) Stop Code: " + shortestPath.stops.get(i).stop_code;
											output[4] = " 4) Latitude: " + shortestPath.stops.get(i).stop_lat;
											output[5] = " 5) Longitude: " + shortestPath.stops.get(i).stop_lon;
											output[6] = " 6) Zone ID: " + shortestPath.stops.get(i).zone_id;

											for (int j = 0; j < output.length; j++) {
												System.out.println(output[j]);
											}
											System.out.println("\n");
											System.out.println("*********************************");
											System.out.println("\n");
										}

										System.out.println("Final Destination:");

										output[0] = "Name: " + titleFormat(stopStop.stop_name);
										output[1] = " 1) Description: " + titleFormat(stopStop.stop_desc);
										output[2] = " 2) Stop ID: " + stopStop.stop_id;
										output[3] = " 3) Stop Code: " + stopStop.stop_code;
										output[4] = " 4) Latitude: " + stopStop.stop_lat;
										output[5] = " 5) Longitude: " + stopStop.stop_lon;
										output[6] = " 6) Zone ID: " + stopStop.zone_id;

										for (int k = 0; k < output.length; k++) {
											System.out.println(output[k]);
										}

									}
								}
							}

						} 
						catch (java.lang.NumberFormatException e) {
							System.out.println("Error.");
						} 
						catch (java.lang.NullPointerException e) {
							System.out.println("No paths can be found.");
						}
						if(stop1 != "") {
							System.out.print(" The Stop: " + stop1 + " does not exist.");
						}
						if(stop2 != "") {
							System.out.print(" The Stop: " + stop2 + " does not exist.");
						}


					}

					// Search for a Bus Stop
					else if (option.equals("2")) {

						System.out.print("Enter the stop to search for: ");
						String stopKey = input.nextLine();

						System.out.println("\n");
						System.out.println("Searching for: " + stopKey);

						// Auto-completes the input
						results = tst.autocomplete(stopKey.toUpperCase());

						// returns nothing if nothing is typed
						if(stopKey.length()< 1) {
							results = null; 
						}


						String[] currentData;

						if (results != null) {

							// print obtained data 
							for (int i = 0; i < results.size(); i++) {

								System.out.println("\n");
								System.out.println("*********************************");
								System.out.println("\n");
								System.out.println("Number " + (i + 1) + ": ");

								// get data from the stop name
								currentData = stopData(results.get(i));
								// format outlook of data
								currentData = formatData(currentData);
								for (int k = 0; k < currentData.length; k++) {
									System.out.println(currentData[k]);
								}
							}
						}
					} 



					// Search for All Trips
					else if (option.equals("3")) {

						try {

							System.out.println("Search for time in the format: 'hh:mm:ss' ");
							String time = input.nextLine();

							if (time.length() > 3) {

								if (time.charAt(1) == ':') {
									time = " " + time;
								}

							}
							//if the input is acceptable
							if (validTime(time) == true) {
								System.out.println("Searching for all trips with the time: " + time);

								//create an arraylist for the trips within said arrival time
								ArrayList<String> arrivalTimesList = new ArrayList<String>();
								arrivalTimesList = arrivalTimes.tripInfo(time);

								System.out.println("Trips found: " + arrivalTimesList.size());
								for (int i = 0; i < arrivalTimesList.size(); i++) {
									System.out.println("\n");
									System.out.println("Result " + (i + 1) + ": ");
									System.out.print("*) Trip id: ");
									System.out.println(arrivalTimesList.get(i));
								}

							}

						} 
						catch (java.time.format.DateTimeParseException e) {
							System.out.println("Incorrect Input");
						}

					}

				}

			}
			input.close();

			System.out.println("Exited");

		} 
		else if (invalidInputs == true) {
			System.out.println("\n");
			System.out.print("Program not running because an input file are missing.");
		}
	}





}
