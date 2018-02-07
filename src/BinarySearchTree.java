


public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree<E>.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	/*********************************************
	 * 
	 * IMPLEMENT THE METHODS BELOW!
	 *
	 *********************************************/
	
	
	// Method #1.
	public Node findNode(E val) {

		if (val == null) {
			return null;
		}
		
		return findNode(root, val);
	}
	
	private Node findNode(Node node, E value) {
		if (node == null) {
			return null;
		}
		
		int compare = node.value.compareTo(value);
		if (compare == 0) {
			return node;
		} else if (compare > 0) {
			return findNode(node.leftChild, value);
		} else {
			return findNode(node.rightChild, value);
		}
	}

	// Method #2.
	protected int depth(E val) {
		return depth(root, val);
	}
	
	private int depth(Node node, E value) {
		if (node == null || value == null) {
			return -1;
		}
		
		int compare = node.value.compareTo(value);
		if (compare == 0) {
			return 0;
		} else {
			Node childNode = compare > 0 ? node.leftChild : node.rightChild;
			int depth = depth(childNode, value);
			return depth >= 0 ? depth + 1 : depth;
		}
	}

	// Method #3.
	protected int height(E val) {
		return height(findNode(val));
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}
		
		int rightHeight = height(node.rightChild) + 1;
		int leftHeight = height(node.leftChild) + 1;
		return rightHeight > leftHeight ? rightHeight : leftHeight;
	}

	// Method #4.
	protected boolean isBalanced(Node n) {
		if (n == null) {
			return false;
		}
		
		if (!this.contains(n.value)) {
			return false;
		}
		
		if (n.leftChild == null && n.rightChild ==  null) {
			return true;
		}

		
		int lHeight = height(n.leftChild);
		int rHeight = height(n.rightChild);
		boolean isBalanced = Math.abs(lHeight - rHeight) <= 1;
		
		if (n.leftChild != null) {
			isBalanced = isBalanced && isBalanced(n.leftChild);
		}
		
		if (n.rightChild != null) {
			isBalanced = isBalanced && isBalanced(n.rightChild);
		}
		
		return isBalanced;
	}
	
	// Method #5. .
	public boolean isBalanced() {
		return this.isBalanced(root);
	}

}
