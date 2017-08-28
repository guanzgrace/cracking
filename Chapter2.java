/**
 * Cracking the Coding Interview Chapter 1
 * Grace Guan 8/27/17
 * 
 * Linked Lists
 */

import java.util.*;
import java.io.*;
public class Chapter2 {
	class Node {
		Node next = null;
		int data;
		public Node(int d) {
			data = d;
		}
		void add(int d) {
			Node end = new Node(d);
			Node n = this;
			while (n.next != null) {
				n = n.next;
			}
			n.next = end;
		}
		Node delete(Node head, int d) {
			Node n = head;
			if (n.data == d) {
				return head.next;
			}
			while (n.next != null) {
				if (n.next.data == d) {
					n.next = n.next.next;
					return head;
				}
				n = n.next;
			}
			return head;
		}
	}

	// 2.1
	public void removeDups(Node head) {
		HashSet<Integer> set = new HashSet<Integer>();
		Node n = head;
		while (n.next != null) {
			if (set.contains(n.data)) {
				n.next = n.next.next;
			} else {
				set.add(n.data);
			}
			n = n.next;
		}
	}

	public void removeDupsNoBuffer(LinkedList l) {
		// sort and then check adjacent values
		Collections.sort(l, null);
		// iterate
	}

	// 2.2
	public Node kthToLast(Node head, int k) {
		Stack<Integer> s = new Stack<Integer>();
		int count = 0;
		while (n2.next != null) {
			s.push(n.data);
			n = n.next();
			count++;
		}
		if (k > count) {
			return 0;
		}
		for (int i = 0; i < k - 1; i++) {
			s.pop();
		}
		return s.pop();
	}

	public Node kthToLast2(Node head, int k) {
	    if (head == null || k < 1) {
	        return null;
	    }

	    Node c = head;
	    Node d = head;

	    for (int i = 0; i < k - 1; i++) {
	        c = c.next;
	        if (c == null) {
	            return null;
	        }
	    }

	    while (c.next != null) {
	        d = d.next;
	        c = c.next;
	    }

	    return d;
	}

	// 2.3 copy data from next node into this node, delete this node
	public void deleteMiddleNode(Node toDelete) {
		Node n = toDelete.next;
		toDelete.data = n.data;
		toDelete.next = n.next;
		n.data = null;
		n.next = null;
	}

	// 2.4
	public void partition(Node head) {

	}

	// 2.5 sum two numbers same as leetcode
	public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = new ListNode(0);
        ListNode current = first;
        int firstVal = l1.val + l2.val;
        int carryOver = firstVal / 10;
        first.val = firstVal % 10;
        while (l1.next != null || l2.next != null || carryOver > 0) {
            ListNode next = new ListNode(0);
            current.next = next;
            current = next;
            int val = carryOver;
            if (l1.next != null) {
                l1 = l1.next;
                val += l1.val;
            } 
            if (l2.next != null) {
                l2 = l2.next;
                val += l2.val;
            } 
            current.val = val % 10;
            carryOver = val / 10;
        }
        return first;
    }
}