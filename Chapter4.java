/**
 * Cracking the Coding Interview Chapter 4
 * Grace Guan 8/30/17
 * 
 * Trees and Graphs
 */

import java.util.*;
import java.io.*;
public class Chapter4 {

	private void dfs(Node root) {
		if (root == null) return;
		visit(root);
		root.visited = true;
		for (Node n : root.adjacent) {
			if (! n.visited) dfs(n);
		}
	}

	private void bfs(Node root) {
		Queue q = new Queue();
		root.marked = true;
		queue.enqueue(root);
		while (! queue.isEmpty()) {
			Node r = queue.dequeue();
			visit(r);
			for (Node n : r.adjacent) {
				if (! n.marked) {
					n.marked = true;
					queue.enqueue(n);
				}
			}
		}
	}

	// 4.1 whether there's a route between two nodes
	// do bfs, if node n == node end, return true
	private boolean isPath(Node root, Node end) {
		Queue q = new Queue();
		root.marked = true;
		queue.enqueue(root);
		while (! queue.isEmpty()) {
			Node r = queue.dequeue();
			if (r == end) return true;
			visit(r);
			for (Node n : r.adjacent) {
				if (! n.marked) {
					n.marked = true;
					queue.enqueue(n);
				}
			}
		}
		return false;
	}

	// 4.2 minimal tree - use recursion, start from middle
	private static TreeNode createMinimalBST(int arr[], int start, int end){
		if (end < start) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode n = new TreeNode(arr[mid]);
		n.setLeftChild(createMinimalBST(arr, start, mid - 1));
		n.setRightChild(createMinimalBST(arr, mid + 1, end));
		return n;
	}
	
	public static TreeNode createMinimalBST(int array[]) {
		return createMinimalBST(array, 0, array.length - 1);
	}

	// 4.3 use BFS
	public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		
		/* "Visit" the root */
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		if (root != null) {
			current.add(root);
		}
		
		while (current.size() > 0) {
			result.add(current); // Add previous level
			LinkedList<TreeNode> parents = current; // Go to next level
			current = new LinkedList<TreeNode>(); 
			for (TreeNode parent : parents) {
				/* Visit the children */
				if (parent.left != null) {
					current.add(parent.left);
				}
				if (parent.right != null) {
					current.add(parent.right);
				}
			}
		}

		return result;
	}

	// 4.4
	public int checkHeight(TreeNode root) {
		if (root == null) return -1;
		int leftHeight = checkHeight(root.left);
		if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Propagate error up
		
		int rightHeight = checkHeight(root.right);
		if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Propagate error up
		
		int heightDiff = leftHeight - rightHeight;
		if (Math.abs(heightDiff) > 1) {
			return Integer.MIN_VALUE; // Found error -> pass it back
		} else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}

	public boolean isBalanced(TreeNode root) {
		return checkHeight(root) != Integer.MIN_VALUE;
	}

	// 4.5
	public boolean validateBST(TreeNode root) {
		return validateBST(root.left, root.value, true) && validateBST(root.right, root.value, false);
	}
	public boolean validateBST(TreeNode node, int oldValue, boolean isLeft) {
		if (node == null) return true;

		if (!validateBST(node.left, node.value, true)) {
			return false;
		}
		if (oldValue != null) {
			if (isLeft && n.value < oldValue) return false;
			if (!isLeft && n.value <= oldValue) return false;
		}
		if (!validateBST(node.right, node.value, false)) {
			return false;
		}

		return true;
	}

	// 4.6
	public Node successor(TreeNode root) {
		if (root == null) return null;
		
		Node n = root;
		// Found right children -> return left most node of right subtree
		if (n.parent == null || n.right != null) { 
			while (n.right != null) {
				n = n.right;
			}
			return n; 
		} else { 
			TreeNode q = n;
			TreeNode x = q.parent;
			// Go up the left side
			while (x != null && x.left != q) {
				q = x;
				x = x.parent;
			}
			return x;
		}  
	}

	// 4.7
	public class CharPair {
		public char first;
		public char second;
		public CharacterPair(char first, char second) {
			this.first = first;
			this.second = second;
		}
	}

	public class CharNode{
		public char c;
		public ArrayList<CharNode> outgoing;
		public ArrayList<CharNode> incoming;
		public CharNode(char c) {
			this.c = c;
			adjacent = new ArrayList<CharNode>();
		}
		public void addOutgoing(CharNode c) {
			outogoing.add(c);
		}
		public void addIncoming(CharNode c) {
			incoming.add(c);
		}
		public boolean allValidIncoming(Hashtable<CharNode, Boolean> marked) {
			for (CharNode c : incoming) {
				if (marked.get(c) == false) return false;
			}
			return true;
		}
	}

	public ArrayList<Character> buildOrder(ArrayList<Character> projects, ArrayList<CharacterPair> dependencies) {
		Hashtable<Character, CharNode> chars = new Hashtable<Character, CharNode>();
		Hashtable<CharNode, Boolean> marked = new Hashtable<CharNode, Boolean>();
		for (Character c : projects) {
			marked.add(new CharNode(c), false);
		}
		for (CharacterPair p : dependencies) {
			chars.get(p.first).addOutgoing(chars.get(p.second));
			chars.get(p.second).addIncoming(chars.get(p.first));
		}

		// bfs
		ArrayList<Character> buildOrder = new ArrayList<Character>();
		Queue<CharNode> q = new Queue<CharNode>();
		for (CharNode c : marked) {
			if (c.incoming.size() == 0) {
				marked.put(c, true);
				q.enqueue(c);
				buildOrder.add(c.c);
			}
		}
		while (! q.isEmpty()) {
			CharNode c = q.dequeue();
			for (CharNode c2 : c.outgoing) {
				if (marked.get(c2) == false && c2.allValidIncoming(marked)) {
					q.enqueue(c2);
					marked.put(c2, true);
					buildOrder.add(c2.c);
				}
			}
		} // or pop to a stack

		if (buildOrder.size() != projects.size()) {
			return null;
		}
		return buildOrder;
	}

	// 4.8
	public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (!covers(root, p) || !covers(root, q)) { // Error check - one node is not in tree
			return null;
		}
		return ancestorHelper(root, p, q);
	}
	
	public static TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}
		
		boolean pIsOnLeft = covers(root.left, p);
		boolean qIsOnLeft = covers(root.left, q);
		if (pIsOnLeft != qIsOnLeft) { // Nodes are on different side
			return root;
		}
		TreeNode childSide = pIsOnLeft ? root.left : root.right;
		return ancestorHelper(childSide, p, q);
	}	
	
	// check if root is a parent of p
	public static boolean covers(TreeNode root, TreeNode p) { 
		if (root == null) return false;
		if (root == p) return true;
		return covers(root.left, p) || covers(root.right, p); 
	}

	// 4.9 ???????????????? review, do not understand
	public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
		/* One list is empty. Add the remainder to [a cloned] prefix and
		 * store result. */
		if (first.size() == 0 || second.size() == 0) {
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
			result.addAll(first);
			result.addAll(second);
			results.add(result);
			return;
		}
		
		/* Recurse with head of first added to the prefix. Removing the
		 * head will damage first, so we’ll need to put it back where we
		 * found it afterwards. */
		int headFirst = first.removeFirst();
		prefix.addLast(headFirst);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		first.addFirst(headFirst);
		
		/* Do the same thing with second, damaging and then restoring
		 * the list.*/
		int headSecond = second.removeFirst();
		prefix.addLast(headSecond);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();	
		second.addFirst(headSecond);
	}
	
	public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		
		if (node == null) {
			result.add(new LinkedList<Integer>());
			return result;
		} 
		
		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.data);
		
		/* Recurse on left and right subtrees. */
		ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
		ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
		
		/* Weave together each list from the left and right sides. */
		for (LinkedList<Integer> left : leftSeq) {
			for (LinkedList<Integer> right : rightSeq) {
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
				weaveLists(left, right, weaved, prefix);
				result.addAll(weaved);
			}
		}
		return result;
	}

	// 4.10 all we have to do is check the inorder traversals
	public void getInOrder(Node n, StringBuilder sb) {
		if (node == null) {
			sb.append("X");
			return;
		}
		sb.append(n.data);
		getInOrder(n.left, sb);
		getInOrder(n.right, sb);
	}

	public boolean isSubtree(Node n1, Node n2) {
		StringBuilder string1 = new StringBuilder();
		StringBuilder string2 = new StringBuilder();
		getInOrder(n1, string1);
		getInOrder(n2, string2);
		return string1.toString().indexOf(string2.toString()) != -1;
	}

	// 4.11
	// find the ith node of a tree
	public TreeNode getIthNode(int i) {
		int leftSize = left == null ? 0 : left.size();
		if (i < leftSize) {
			return left.getIthNode(i);
		} else if (i == leftSize) {
			return this;
		} else {
			return right.getIthNode(i - (leftSize + 1));
		}
	}

	// 4.12 ??? review again
	public static int countPathsWithSum(TreeNode root, int targetSum) {
		if (root == null) return 0;
		
		/* Count paths with sum starting from the root. */
		int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
		
		/* Try the nodes on the left and right. */
		int pathsOnLeft = countPathsWithSum(root.left, targetSum);
		int pathsOnRight = countPathsWithSum(root.right, targetSum);
		
		return pathsFromRoot + pathsOnLeft + pathsOnRight;
	}
	
	/* Returns the number of paths with this sum starting from this node. */
	public static int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
		if (node == null) return 0;
	
		currentSum += node.data;
		
		int totalPaths = 0;
		if (currentSum == targetSum) { // Found a path from the root
			totalPaths++;
		}
		
		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum); // Go left
		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum); // Go right
		
		return totalPaths;
	}


	// interview game
	 public void game(int[] input){
        int n = input[0];
        for(int t = 1; t < n; t++) {
            // change int to binary
            char[] chars = Integer.toBinaryString(input[t]).toCharArray();
            // count inversions
            int inversions = 0;
            for (int i = 0; i < chars.length; i++) {
                for (int j = i; j < chars.length; j++) {
                    if (chars[i] > chars[j]) {
                        inversions++;
                    }
                }
            }
            if (inversions % 2 == 0) {
                System.out.println("Second Player");
            } else {
                System.out.println("First Player");
            }
        }
    }
}