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

	// 3.1
	public class ThreeStack {
		
	}


}