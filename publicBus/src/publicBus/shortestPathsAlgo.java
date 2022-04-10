package publicBus;
import java.util.ArrayList;

public class shortestPathsAlgo {

		// An arraylist of stops on the route
		ArrayList<stops> stops;
		
		// The total weight of the path.
		private double weight;

	
		public shortestPathsAlgo() {
			stops = new ArrayList<stops>();
			weight = -1;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}
	
}
