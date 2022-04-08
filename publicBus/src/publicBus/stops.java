package publicBus;

public class stops {

	// data stored within stops.txt for the stops
	// NB: There are unused columns of data eg stop_url, location_type, parent_station
	int stop_id;
	int stop_code;
	double stop_lat;
	double stop_lon;
	String stop_name;
	String stop_desc;
	String zone_id;

	public stops(String line) {

		// split lines into individual parameters.
		String[] inputValues = line.split(",");

		// exception handling that deals with errors if we try to parse a string with no
		// numbers, as a double or integer. If such scenario occurs set stop_id to be = -1;
		try {
			this.stop_id = Integer.parseInt(inputValues[0]);
		} 
		catch (Exception e) {
			this.stop_id = -1;
		}


		try {
			this.stop_code = Integer.parseInt(inputValues[1]);
		} 
		catch (Exception e) {
			this.stop_code = -1;
		}

		// moving the keywords to the end of the stop name.
		this.stop_name = movePrefix(inputValues[2]);

		this.stop_desc = inputValues[3];

		try {
			this.stop_lat = Double.parseDouble(inputValues[4]);
		} 
		catch (Exception e) {
			this.stop_id = -1;
		}
		try {
			this.stop_lon = Double.parseDouble(inputValues[5]);
		} 
		catch (Exception e) {
			this.stop_id = -1;
		}

		this.zone_id = inputValues[6];

	}

	// move prefixes from start of the names to the end of the names of the stops
	private String movePrefix(String input) {

		// for the prefix "FLAGSTOP "
		if (input.charAt(0) == 'F' && input.charAt(1) == 'L' && input.charAt(2) == 'A' 
				&& input.charAt(3) == 'G' && input.charAt(4) == 'S' && input.charAt(5) == 'T' 
				&& input.charAt(6) == 'O' && input.charAt(7) == 'P' && input.charAt(8) == ' ') {
			// we don't return the string yet, since flagstop can be followed by EB, WB, SB or NB too
			input = input.substring(9) + " FLAGSTOP";

		}

		// for reference:substring(3) gets rid of the first 3 characters in a string
		if (input.charAt(0) == 'E' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " EB";
		} 

		else if (input.charAt(0) == 'W' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " WB";
		}

		else if (input.charAt(0) == 'S' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " SB";
		}

		else if (input.charAt(0) == 'N' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " NB";
		}


		else {
			return input;
		}

	}


	public String getID() {

		if (stop_id == -1) {
			return "unavailable";
		} 
		else {
			return "" + this.stop_id;
		}

	}

	public String getCode() {
		if (stop_code == -1) {
			return "unavailable";
		} 
		else {
			return "" + this.stop_code;
		}
	}
	
	public String getLatitude() {
		if (stop_lat == -1) {
			return "unavailable";
		}
		else {
			return "" + this.stop_lat;
		}
	}

	public String getLongitude() {
		if (stop_lon == -1) {
			return "unavailable";
		} 
		else {
			return "" + this.stop_lon;
		}
	}

	public String getName() {
		return this.stop_name;
	}

	public String getDesc() {
		return this.stop_desc;
	}


	public String getZone() {
		return this.zone_id;
	}

}
