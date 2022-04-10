package publicBus;

public class transfer {


	// data stored from transfers.txt
	private int departureStopId;
	private int arrivalStopId;
	private int transferType;
	private int transferTime;


	public transfer(String line) {

		// split lines into individual parameters
		String[] input = line.split(",");

		// assign the data to variables
		try {

			this.departureStopId = Integer.parseInt(input[0]);
		} 
		catch (Exception e) {
			this.departureStopId = -1;
		}

		try {
			this.arrivalStopId = Integer.parseInt(input[1]);
		} 
		catch (Exception e) {
			this.departureStopId = -1;
		}

		try {
			this.transferType = Integer.parseInt(input[2]);
		} 
		catch (Exception e) {
			this.transferType = -1;
		}

		try {
			this.transferTime = Integer.parseInt(input[3]);
		} 
		catch (Exception e) {
			this.transferTime = -1;
		}

	}

	public int getDepartureStopId() {
		return departureStopId;
	}

	public int getArrivalStopId() {
		return arrivalStopId;
	}

	public int getTransferTime() {
		return transferTime;
	}

	public int getTransferType() {
		return transferType;
	}
}
