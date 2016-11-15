public class Node {

	public Node right = null;
	public Node left = null;
	public Node parent = null;
	public Integer value = null;

	public Node(Integer value, Node left, Node right, Node parent) {
		this.left = left;
		this.right = right;
		this.value = value;
		this.parent = parent;
	}

	public boolean isLeftChild() {
		return parent.left == this;
	}

	public void deleteNode() {
		if (isLeftChild()) {
			parent.left = null;
		} else {
			parent.right = null;
		}
	}
}
