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

	// Find the last node via the name we are searching for
	private tstNodes lastNode(String name) {

		if (name.length() == 0) {
			return root;
		}

		tstNodes currentNode = root;
		int i = 0;

		while (currentNode != null) {

			if (name.charAt(i) == currentNode.data) {

				if (i == name.length() - 1) {
					return currentNode;
				}

				++i;
				currentNode = currentNode.current;
			} 
			else if (name.charAt(i) < currentNode.data) {
				currentNode = currentNode.prev;
			} 
			else {
				currentNode = currentNode.next;
			}

		}

		return null;

	}

	// function which autocompletes a given prefix
	public ArrayList<String> autocomplete(String prefix) {

		ArrayList<String> words = new ArrayList<String>();

		if (prefix.length() == 0) {
			inOrder(root, words, prefix);
		}
		else {

			tstNodes node = lastNode(prefix);
			if (node == null) {
				return null;
			}

			if (node.isEnd == true) {
				words.add(prefix);
			}
			inOrder(node.current, words, prefix);
		}
		return words;

	}

	// Recursively adds the nodes in order and stores in arraylist
	private void inOrder(tstNodes current, ArrayList<String> words, String name) {

		if (current == null) {
			return;
		}

		inOrder(current.prev, words, name);
		if (current.isEnd == true) {
			words.add(name + current.data);
		}

		inOrder(current.current, words, name + current.data);
		inOrder(current.next, words, name);

	}




}
