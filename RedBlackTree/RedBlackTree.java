public class RedBlackTree {

	public class Node {
		public String key;
		public int color; // 1 = black, 0 = white
		public Node parent;
		private Node left;
		private Node right;

		public Node() {

		}

		public Node(String k) {
			this.key = k;
		}
	}

	// an instance variable that points to the root RBNode.
	private Node root;

	// lookup(String): searches for a key.
	public String lookup(String s) {
		if (RecursionLookup(root, s) == null)
			return null;
		else
			return s;
	}

	public Node RecursionLookup(Node x, String k) {
		if (x == null || k.equals(x.key))
			return x;
		if (k.compareTo(x.key) < 0) {
			return RecursionLookup(x.left, k);
		} else {
			return RecursionLookup(x.right, k);
		}

	}

	// printTree(): Start at the root node and traverse the tree using
	// preorder
	public String printTree() {
		if (root == null) {
			return "RedBlackTree is empty.";
		}
		return recursivePreOrder(root);
	}

	public String recursivePreOrder(Node n) {
		if (n == null)
			return "";

		return n.key + " " + recursivePreOrder(n.left) + recursivePreOrder(n.right);
	}

	// addNode(String): place a new node in the binary search tree with data
	// the parameter and color it red.
	public void insert(String s) {
		if (root == null) {
			root = new Node(s);
			root.parent = null;
			root.color = 0;
			fixTree(root);
			return;
		}
		Node x = root;
		Node previous = null;
		boolean isLastOprnLeft = false;
		while (x != null && s != x.key) {
			previous = x;
			if (s.compareTo(x.key) < 0) {
				isLastOprnLeft = true;
				x = x.left;
			} else {
				isLastOprnLeft = false;
				x = x.right;
			}
		}
		Node newNode = new Node(s);
		newNode.parent = previous;
		if (!isLastOprnLeft) {
			previous.right = newNode;
		} else if (isLastOprnLeft) {
			previous.left = newNode;
		}

		fixTree(newNode);
	}

	// getSibling(RBNode): returns the sibling node of the parameter If the
	// sibling does not exist, then return null.
	private Node getSibling(Node n) {
		if (n == null)
			return null;

		// If parent is bigger than n
		// get right side of child from parent
		if (n.parent.key.compareTo(n.key) > 0) {
			// check if sibling is null
			if (n.parent.right != null)
				return n.parent.right;
		}
		// If parent is less than or equals to n
		// get right side of child from parent
		else if (n.parent.key.compareTo(n.key) <= 0) {
			// check if sibling is null
			if (n.parent.left != null)
				return n.parent.left;
		}
		return null;
	}

	// getAunt(RBNode): returns the aunt of the parameter or the sibling of
	// the parent node. If the aunt node does not exist, then return null.
	private Node getAunt(Node n) {
		// if n, parent or grandparent is null return null
		if (n == null || n.parent == null || n.parent.parent == null) {
			return null;
		}
		// If grandparent is bigger than n
		else if (n.parent.parent.key.compareTo(n.key) >= 0) {
			// check if grandparent right child is null
			if (n.parent.parent.right != null)
				return n.parent.parent.right;
		}
		// If grandparent is less than or equal to n
		else if (n.parent.parent.key.compareTo(n.key) <= 0) {
			// check if grandparent left child is null
			if (n.parent.parent.left != null)
				return n.parent.parent.left;
		}
		return null;
	}

	// getGrandparent(RBNode): Similar to getAunt() and getSibling(), returns
	// the parent of your parent node, if it doesn’t exist return null.
	private Node getGrandparent(Node n) {
		// if n, parent or grandparent is null return null
		if (n == null || n.parent == null || n.parent.parent == null) {
			return null;
		}
		return n.parent.parent;
	}

	// rotateLeft(RBNode) and rotateRight(RBNode) functions: left, resp.
	// right, rotate around the node parameter.
	private void rotateLeft(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	private void rotateRight(Node y) {
		Node x = y.left;
		y.left = x.right;
		if (x.right != null) {
			x.right.parent = y;
		}
		x.parent = y.parent;
		if (y.parent == null) {
			root = x;
		} else if (y == y.parent.right) {
			y.parent.right = x;
		} else {
			y.parent.left = x;
		}
		x.right = y;
		y.parent = x;
	}

	// fixTree(RBNode current) recursive function: recursively traverse the
	// tree to make it a Red Black tree. Here is a description of all the cases
	// for the current pointer:
	private void fixTree(Node current) {
		// 1) current is the root node. Make it black and quit.
		if (current == root) {
			current.color = 1; // black
			return;
		}
		// 2) Parent is black. Quit, the tree is a Red Black Tree.
		else if (current.parent.color == 1) {
			return;
		}
		// 3) The current node is red and the parent node is red. The tree is
		// unbalanced and you will have to modify it in the following way.
		else if (current.color == 0 && current.parent.color == 0) {
			// I. If the aunt node is empty or black, then there are four sub
			// cases that
			// you have to process.
			if (getAunt(current) == null || getAunt(current).color == 1) {
				// A) grandparent –parent(is left child)— current (is right
				// child) case.
				if (getGrandparent(current).left == current.parent && current.parent.right == current) {
					// Solution: rotate the parent left and then continue
					rotateLeft(current.parent);
					// recursively fixing the
					// tree starting with the original parent.
					fixTree(current.left);
				}
				// B) grandparent –parent (is right child)— current (is left
				// child) case.
				else if (getGrandparent(current).right == current.parent && current.parent.left == current) {
					// Solution: rotate the parent right and then continue
					rotateRight(current.parent);
					// recursively fixing
					// the tree starting with the original parent.
					fixTree(current.right);
				}
				// C) grandparent –parent (is left child)— current (is left
				// child) case.
				else if (getGrandparent(current).left == current.parent && current.parent.left == current) {
					// Solution: make the parent black,
					current.parent.color = 1; // black
					// make the grandparent red,
					getGrandparent(current).color = 0; // red
					// rotate the grandparent to the right
					rotateRight(getGrandparent(current));
					// and quit, tree is balanced.
					return;
				}
				// D) grandparent –parent (is right child)— current (is right
				// child) case.
				else if (getGrandparent(current).right == current.parent && current.parent.right == current) {
					// Solution: make the parent black,
					current.parent.color = 1; // black
					// make the grandparent red,
					getGrandparent(current).color = 0; // red
					// rotate the grandparent to the left,
					rotateLeft(getGrandparent(current));
					// quit tree is balanced.
					return;
				}
			}
			// II. Else if the aunt is red,
			else if (getAunt(current).color == 0) {
				// then make the parent black,
				current.parent.color = 1;
				// make the aunt black,
				getAunt(current).color = 1;
				// make the grandparent red and
				getGrandparent(current).color = 0;
				// TODO continue recursively fix up the tree
				// starting with the grandparent.
				fixTree(getGrandparent(current));
			}
		}
	}

	public static interface Visitor {
		/**
		 * This method is called at each node.
		 * 
		 * @param n
		 *            the visited node
		 */
		void visit(Node n);
	}

	public void preOrderVisit(Visitor v) {
		preOrderVisit(root, v);
	}

	private static void preOrderVisit(Node n, Visitor v) {
		if (n == null)
			return;
		v.visit(n);
		preOrderVisit(n.left, v);
		preOrderVisit(n.right, v);
	}

}