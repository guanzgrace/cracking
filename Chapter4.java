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
			if (! n.visited) search(n);
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

}