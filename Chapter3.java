/**
 * Cracking the Coding Interview Chapter 1
 * Grace Guan 8/27/17
 * 
 * Stacks and Queues
 */

import java.util.*;
import java.io.*;
public class Chapter3 {
	public class Stack<T> {
		private static class StackNode<T> {
			private T data;
			private StackNode<T> next;
		
			public StackNode(T data) {

				this.data = data;
			}
		}

		private StackNode<T> top;
		public T pop() {
			if (top == null) throw new EmptyStackException();
			T item = top.data;
			top = top.next;
			return item;
		}
		public void push(T item) {
			StackNode<T> t = new StackNode<T>(item);
			t.next = top;
			top = t;
		}
		public T peek() {
			if (top == null) throw new EmptyStackException();
			return top.data;
		}
		public boolean isEmpty() {
			return top == null;
		}
	}

	public class Queue<T> {
		private static class QueueNode<T> {
			private T data;
			private QueueNode<T> next;
			public QueueNode(T data) {
				this.data = data;
			}
		}

		private QueueNode<T> first;
		private QueueNode<T> last;

		public void add(T item) {
			QueueNode<T> t = new QueueNode<T>(item);
			if (last != null) {
				last.next = t;
			}
			last = t;
			if (first == null) {
				first = last;
			}
		}

		public T remove() {
			if (first == null) throw new NoSuchElementException();
			T data = first.data;
			first = first.next;
			if (first == null) {
				last = null;
			}
			return data;
		}

		public T peek() {
			if (first == null) throw new NoSuchElementException();
			return first.data;
		}

		public boolean isEmpty() {
			return first == null;
		}
	}

	// 3.1 define array
	// stack 1 from front to end
	// stack 2 from end to front
	// stack 3 in the middle, with indexes of the following: 5 6 4 7 3 8 2 9 1 etc.

	// 3.2
	// store the minimum element, when pushing, check to see if the element
	// is smaller than the current min, if so, replace
	class MinStack {
	    private class StackNode {
	        private int data;
	        private StackNode next;
	        public StackNode(int data) {
	            this.data = data;
	        }
	    }
	    
	    private StackNode top;
	    private StackNode auxTop;

	    /** initialize your data structure here. */
	    public MinStack() {
	        top = null;
	        auxTop = null;
	    }
	    
	    public void push(int x) { 
	        StackNode t = new StackNode(x);
	        t.next = top;
			top = t;
	        if (auxTop != null) {
	            StackNode n = new StackNode(Math.min(auxTop.data, x));
	            n.next = auxTop;
	            auxTop = n;
	        } else {
	            StackNode n = new StackNode(x);
	            auxTop = n;
	        }        
	    }
	    
	    public void pop() {
	        top = top.next;
	        auxTop = auxTop.next;
	    }
	    
	    public int top() {
	        return top.data;
	    }
	    
	    public int getMin() {
	        return auxTop.data;
	    }
	}

	// 3.3
	public class SetOfStacks {
		ArrayList<Stack> stacks = new ArrayList<Stack>();
		public int capacity;
		
		public SetOfStacks(int capacity) { 
			this.capacity = capacity; 
		}
		
		public Stack getLastStack() {
			if (stacks.size() == 0) {
				return null;
			}
			return stacks.get(stacks.size() - 1);
		}
		
		public void push(int v) {
			Stack last = getLastStack();
			if (last != null && !last.isFull()) { // add to last
				last.push(v);
			} else { // must create new stack
				Stack stack = new Stack(capacity);
				stack.push(v);
				stacks.add(stack);
			}
		}
		
		public int pop() {
			Stack last = getLastStack();
			if (last == null) throw new EmptyStackException();
			int v = last.pop();
			if (last.size == 0) {
				stacks.remove(stacks.size() - 1);
			}
			return v;
		}
		
		public int popAt(int index) {
			return leftShift(index, true);
		}
		
		public int leftShift(int index, boolean removeTop) {
			Stack stack = stacks.get(index);
			int removed_item;
			if (removeTop) removed_item = stack.pop();
			else removed_item = stack.removeBottom();
			if (stack.isEmpty()) {
				stacks.remove(index);
			} else if (stacks.size() > index + 1) {
				int v = leftShift(index + 1, false);
				stack.push(v);
			}
			return removed_item;
		}
		
		public boolean isEmpty() {
			Stack last = getLastStack();
			return last == null || last.isEmpty();
		}
	}

	// 3.4
	public class Queue<E> {

	    private Stack<E> inbox = new Stack<E>();
	    private Stack<E> outbox = new Stack<E>();

	    public void queue(E item) {
	        inbox.push(item);
	    }

	    public E dequeue() {
	        if (outbox.isEmpty()) {
	            while (!inbox.isEmpty()) {
	               outbox.push(inbox.pop());
	            }
	        }
	        return outbox.pop();
	    }
	}

	// 3.5 use two stacks to do mergesort
	public static Stack<Integer> mergesort(Stack<Integer> inStack) {
		if (inStack.size() <= 1) {
			return inStack;
		}

		Stack<Integer> left = new Stack<Integer>();
		Stack<Integer> right = new Stack<Integer>();
		int count = 0;
		while (inStack.size() != 0) {
			count++;
			if (count % 2 == 0) {
				left.push(inStack.pop());
			} else {
				right.push(inStack.pop());
			}
		}

		left = mergesort(left);
		right = mergesort(right);

		while (left.size() > 0 || right.size() > 0) {
			if (left.size() == 0) {
				inStack.push(right.pop());
			} else if (right.size() == 0) {
				inStack.push(left.pop());
			} else if (right.peek().compareTo(left.peek()) <= 0) {
				inStack.push(left.pop());
			} else {
				inStack.push(right.pop());
			}
		}

		Stack<Integer> reverseStack = new Stack<Integer>();
		while (inStack.size() > 0) {
			reverseStack.push(inStack.pop());
		}

		return reverseStack;
	}
	
	public static void sort(Stack<Integer> s) {
		Stack<Integer> r = new Stack<Integer>();
		while(!s.isEmpty()) {
			/* Insert each element in s in sorted order into r. */
			int tmp = s.pop();
			while(!r.isEmpty() && r.peek() > tmp) {
				s.push(r.pop());
			}
			r.push(tmp);
		}
		
		/* Copy the elements back. */
		while (!r.isEmpty()) {
			s.push(r.pop());
		}
	}

	// 3.6 animal shelter (choose dog or cat from stack)
}