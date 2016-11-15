import java.util.ArrayList;

public class Set {

	public Set() {
	}

	// flattens the tree into and arraylist
	public ArrayList<Integer> flatten(Node root) {
		ArrayList<Integer> flattened = new ArrayList<>();

		if (root.left != null) {
			flattened = flatten(root.left);
		}
		flattened.add(root.value);
		if (root.right != null) {
			flattened.addAll(flatten(root.right));
		}
		return flattened;
	}

	// merges two lists together and excludes any duplicates
	// This is then used for union
	public ArrayList<Integer> merge(ArrayList<Integer> one, ArrayList<Integer> two) {
		ArrayList<Integer> mergedList = new ArrayList<>();
		ArrayList<Integer> oneCopy = (ArrayList<Integer>) one.clone();
		ArrayList<Integer> twoCopy = (ArrayList<Integer>) two.clone();
		int i = 0;
		int j = 0;
		while (i < oneCopy.size() && j < twoCopy.size()) {
			if (oneCopy.get(i).equals(twoCopy.get(j))) {
				mergedList.add(oneCopy.get(i));
				oneCopy.remove(i);
				twoCopy.remove(j);
			} else if (oneCopy.get(i) < twoCopy.get(j)) {
				mergedList.add(oneCopy.get(i));
				oneCopy.remove(i);
			} else if (oneCopy.get(i) > twoCopy.get(j)) {
				mergedList.add(twoCopy.get(j));
				twoCopy.remove(j);
			}
		}
		if (i == oneCopy.size()) {
			mergedList.addAll(twoCopy);
		} else {
			mergedList.addAll(oneCopy);
		}
		return mergedList;
	}

	// creates a new 'tree' out of nodes with children.
	// it first, finds the mid point. it does the same for the left side, the
	// mid point then becomes a node. The same thing is done for the right side
	// this balances the tree
	public Node newUnionTree(int low, int high, ArrayList<Integer> one, ArrayList<Integer> two, Node parent) {
		if (low > high)
			return null;

		int mid = (high + low) / 2;
		Integer mainRoot = merge(one, two).get(mid);
		Node root = new Node(mainRoot, null, null, parent);

		root.left = newUnionTree(low, mid - 1, one, two, root);
		root.right = newUnionTree(mid + 1, high, one, two, root);
		return root;

	}

	//creates the new union tree that is balanced by assigning the mid point as the root of the tree
	public BinarySearchTree union(BinarySearchTree first, BinarySearchTree second) {

		ArrayList<Integer> one = flatten(first.root);
		ArrayList<Integer> two = flatten(second.root);
		int high = merge(one, two).size() - 1;

		Node root = newUnionTree(0, high, one, two, null);
		BinarySearchTree tree = new BinarySearchTree();
		tree.root = root;
		return tree;
	}

	// method to implement set intersection
		// it adds any duplicate values of to lists to a new one
		public ArrayList<Integer> intersectionMerge(ArrayList<Integer> one, ArrayList<Integer> two) {
			ArrayList<Integer> mergedList = new ArrayList<>();
			int i = 0;
			int j = 0;
			while (i < one.size() && j < two.size()) {
				if (one.get(i) == (two.get(j))) {
					mergedList.add(one.get(i));
					i++;
					j++;
				} else if (one.get(i) < two.get(j)) {
					i++;
				} else if (one.get(i) > two.get(j)) {
					j++;
				}
			}
			return mergedList;
		}
	
	//does the same as newUnionTree but for intersection
	public Node newIntersectionTree(int low, int high, ArrayList<Integer> one, ArrayList<Integer> two, Node parent) {
		if (low > high)
			return null;

		int mid = (high + low) / 2;
		Integer mainRoot = intersectionMerge(one, two).get(mid);
		Node root = new Node(mainRoot, null, null, parent);

		root.left = newIntersectionTree(low, mid - 1, one, two, root);
		root.right = newIntersectionTree(mid + 1, high, one, two, root);
		return root;

	}

	//does the same as the method union but for intersection
	public BinarySearchTree intersection(BinarySearchTree first, BinarySearchTree second) {

		ArrayList<Integer> one = flatten(first.root);
		ArrayList<Integer> two = flatten(second.root);
		int high = intersectionMerge(one, two).size() - 1;

		Node root = newIntersectionTree(0, high, one, two, null);
		BinarySearchTree tree = new BinarySearchTree();
		tree.root = root;
		return tree;
	}

	//prints the list as a string, used to making testing easier
	public String printList(ArrayList<Integer> list) {
		String newList = "";
		for (int i = 0; i < list.size(); i++) {
			newList = newList.concat(list.get(i).toString() + ", ");
		}
		return newList;
	}
}
