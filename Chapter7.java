/**
 * Cracking the Coding Interview Chapter 7
 * Grace Guan 9/6/17
 * 
 * Object Oriented Design
 */

// 7.8 Othello see book solution

// 7.9
public class CircularArray<T> implements Iterable<T> {
	private T[] items;
	private int head = 0;
	
	public CircularArray(int size) {
		items = (T[]) new Object[size];
	}
	
	private int convert(int index) {
		if (index < 0) {
			index += items.length;
		}
		return (head + index) % items.length;
	}
	
	public void rotate(int shiftRight) {
		head = convert(shiftRight);
	}
	
	public T get(int i) {
		if (i < 0 || i >= items.length) {
			throw new java.lang.IndexOutOfBoundsException("Index " + i + " is out of bounds");
		}
		return items[convert(i)];
	}
	
	public void set(int i, T item) {
		items[convert(i)] = item;
	}
	
	public Iterator<T> iterator() {
		return new CircularArrayIterator();
	}
	
	private class CircularArrayIterator implements Iterator<T> {
		private int _current = -1;
		
		public CircularArrayIterator() { }
		
		@Override
		public boolean hasNext() {
			return _current < items.length - 1;
		}
		
		@Override
		public T next() {
			_current++;
			return (T) items[convert(_current)];
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove is not supported by CircularArray");
		}
	}
}

// 7.12 use ArrayList of Linked Lists to manage the chaining
public class Hasher<K, V> {
	private static class LinkedListNode<K, V> {
		public LinkedListNode<K, V> next;
		public LinkedListNode<K, V> prev;
		public K key;
		public V value;
		public LinkedListNode(K k, V v) {
			key = k;
			value = v;
		}
		
		public String printForward() {
			String data = "(" + key + "," + value + ")";
			if (next != null) {
				return data + "->" + next.printForward();
			} else {
				return data;
			}
		}
	}	
	
	private ArrayList<LinkedListNode<K, V>> arr;
	public Hasher(int capacity) {
		/* Create list of linked lists. */
		arr = new ArrayList<LinkedListNode<K, V>>();
		arr.ensureCapacity(capacity);
		for (int i = 0; i < capacity; i++) {
			arr.add(null);
		}
	}
	
	/* Insert key and value into hash table. */
	public V put(K key, V value) {
		LinkedListNode<K, V> node = getNodeForKey(key);
		if (node != null) {
			V oldValue = node.value;
			node.value = value; // just update the value.
			return oldValue;
		}
		
		node = new LinkedListNode<K, V>(key, value);
		int index = getIndexForKey(key);
		if (arr.get(index) != null) {
			node.next = arr.get(index);
			node.next.prev = node;
		}
		arr.set(index, node);
		return null;
	}
	
	/* Remove node for key. */
	public V remove(K key) {
		LinkedListNode<K, V> node = getNodeForKey(key);
		if (node == null) {
			return null;
		}
		
		if (node.prev != null) {
			node.prev.next = node.next;
		} else {
			/* Removing head - update. */
			int hashKey = getIndexForKey(key);
			arr.set(hashKey, node.next);
		}
		
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		return node.value;
	}
	
	/* Get value for key. */
	public V get(K key) {
		if (key == null) return null;
		LinkedListNode<K, V> node = getNodeForKey(key);
		return node == null ? null : node.value;
	}
	
	/* Get linked list node associated with a given key. */
	private LinkedListNode<K, V> getNodeForKey(K key) {
		int index = getIndexForKey(key);
		LinkedListNode<K, V> current = arr.get(index);
		while (current != null) {
			if (current.key == key) {
				return current;
			}
			current = current.next;
		}
		return null;		
	}
	
	/* Really stupid function to map a key to an index. */
	public int getIndexForKey(K key) {
		return Math.abs(key.hashCode() % arr.size());
	}
	
	public void printTable() {
		for (int i = 0; i < arr.size(); i++) {
			String s = arr.get(i) == null ? "" : arr.get(i).printForward();
			System.out.println(i + ": " + s);
		}
	}
}