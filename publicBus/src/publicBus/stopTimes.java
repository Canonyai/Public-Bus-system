package publicBus;
import java.time.LocalTime;

public class stopTimes {

	// data stored within stop_times.txt include:
	private int tripId;

	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private String arrivalTimeString;
	private String departureTimeString;

	private int stopId;
	private int stopOrder;
	private int getType;
	private int deliverType;
	private double distance;


	public stopTimes(String line) {

		// split lines into individual parameters.
		String[] input = line.split(",");

		// assign the data to variables and handle potential errors
		try {
			this.tripId = Integer.parseInt(input[0]);
		} 
		catch (Exception e) {
			this.tripId = -1;
		}

		try {
			this.arrivalTime = LocalTime.parse(input[1]);
		} 
		catch (Exception e) {
			this.arrivalTime = null;
		}

		try {
			this.departureTime = LocalTime.parse(input[2]);
		} 
		catch (Exception e) {
			this.departureTime = null;
		}


		try {
			this.arrivalTimeString = input[1];
		} 
		catch (Exception e) {

		}

		try {
			this.departureTimeString = input[2];
		} 
		catch (Exception e) {

		}

		try {
			this.stopId = Integer.parseInt(input[3]);
		} 
		catch (Exception e) {
			this.stopId = -1;
		}

		try {
			this.stopOrder = Integer.parseInt(input[4]);
		} 
		catch (Exception e) {
			this.stopOrder = -1;
		}

		try {
			this.getType = Integer.parseInt(input[6]);
		} 
		catch (Exception e) {
			this.getType = -1;
		}

		try {
			this.deliverType = Integer.parseInt(input[7]);
		} 
		catch (Exception e) {
			this.deliverType = -1;
		}

		try {
			this.distance = Double.parseDouble(input[8]);
		} 
		catch (Exception e) {
			this.distance = -1;
		}

	}

	public int getTripId() {
		return tripId;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public String getArrivalTimeString() {
		return arrivalTimeString;
	}

	public String getDepartureTimeString() {
		return departureTimeString;
	}

	public int getStopId() {
		return stopId;
	}

	public int getStopOrder() {
		return stopOrder;
	}

	public int getType() {
		return getType;
	}

	public int getDeliverType() {
		return deliverType;
	}

	public double getDistance() {
		return distance;
	}



}
