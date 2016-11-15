
public class BinarySearchTree {

	public Node root = null;

	public BinarySearchTree() {
	}

	public void add(Integer newValue) {
		
		if (root == null) {
			root = new Node(newValue, null, null, null);
		} else {
			Node current = root;
			while (true) {
				if (newValue < current.value) {
					if (current.left == null) {
						current.left = new Node(newValue, null, null, current);
						return;
					}
					current = current.left;
				} else if (newValue > (current.value)) {
					if (current.right == null) {
						current.right = new Node(newValue, null, null, current);
						return;
					}
					current = current.right;
				} else if (current.value.equals(newValue)) {
					return;
				}
			}
		}
	}

	public boolean contains(Integer searchValue) {
		Node current = root;
		while (current != null) {
			if (searchValue.equals(current.value)) {
				return true;
			} else if (searchValue < current.value) {
				current = current.left;
			} else if (searchValue > current.value) {
				current = current.right;
			}

		}
		return false;
	}

	public void remove(Integer deleteValue) {
		Node current = findNode(deleteValue);

		if (current != null) {
			if (current.left == null && current.right == null) {
				current.deleteNode();
			} else if (current.left != null && current.right == null) {
				if (current.isLeftChild()) {
					current.parent.left = current.left;
					current.left.parent = current.parent;
				} else {
					current.parent.right = current.left;
					current.left.parent = current.parent;
				}
			} else if (current.left == null) {
				if (current.isLeftChild()) {
					current.parent.left = current.right;
					current.right.parent = current.parent;
				} else {
					current.parent.right = current.right;
					current.right.parent = current.parent;
				}
			} else {
				if (current.isLeftChild()) {
					current.parent.left = findRightMostChild(current);
				} else {
					current.parent.right = findRightMostChild(current);
				}
			}
		}
	}

	public Node findNode(Integer value) {
		Node current = root;
		while (current != null) {
			if (value.equals(current.value)) {
				return current;
			} else if (value < current.value) {
				current = current.left;
			} else if (value > current.value) {
				current = current.right;
			}
		}
		return null;
	}

	public Node findRightMostChild(Node current) {
		if (current.right != null) {
			current = current.right;
			findRightMostChild(current);
		}
		return current;
	}
}
