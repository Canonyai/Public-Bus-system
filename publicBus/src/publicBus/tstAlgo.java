package publicBus;
import java.util.*;

public class tstAlgo {

	private tstNodes root;

	// set the root of the TST to null when we create the TST
	public tstAlgo() {

		root = null;

	}

	// add nodes to the TST, based on the name of stops
	public void insert(String name) {

		tstNodes node = insert(root, name);

		if (root == null) {
			root = node;
		}

	}

	// add current node to the TST, based on the name of stops
	private tstNodes insert(tstNodes current, String name) {

		if (current == null) {
			current = new tstNodes(name.charAt(0));
		}

		if (name.charAt(0) < current.data) {
			current.prev = insert(current.prev, name);
		}

		else if (name.charAt(0) > current.data) {
			current.next = insert(current.next, name);
		}

		else {

			if (name.length() == 1) {
				current.isEnd = true;
				return current;
			}

			current.current = insert(current.current, name.substring(1));
		}

		return current;

	}


}
