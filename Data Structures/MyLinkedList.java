public class MyLinkedList<E> {
    private Node first;
    private Node last;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    } 

    private class Node {
        private Object value;
        private Node previous;
        private Node next;
        public Node(Object v) {
            value = v;
            previous = null;
            next = null;
        }
        public Object getValue() { return value; }
        public Node getPrevious() { return previous; }
        public Node getNext() { return next; }
        public void setValue(Object v) { value = v; }
        public void setPrevious(Node p) { previous = p; }
        public void setNext(Node n) { next = n; }
    }

    public String toString() {
        Node node = first;
        if (node == null) return "[]";
        String s = "[";
        while (node.getNext() != null) {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }

    private Node getNodeFromFirst(int index) { return this.getNodeFromFirstHelper(index, first); }
    private Node getNodeFromFirstHelper(int index, Node current) {
        if (index == 0 || current.getNext() == null) return current;
        return this.getNodeFromFirstHelper(index - 1, current.getNext());
    }

    private Node getNodeFromLast(int index) { return this.getNodeFromLastHelper(this.size() - index - 1, last); }
    private Node getNodeFromLastHelper(int index, Node current) {
        if (index == 0) return current;
        return this.getNodeFromLastHelper(index - 1, current.getPrevious());
    }

    private Node getNode(int index) {
        if (index <= this.size() / 2) return this.getNodeFromFirst(index); 
        return this.getNodeFromLast(index);
    }

    public int size() { return size; }

    public E get(int index) { return (E) this.getNode(index).getValue(); }

    public E set(int index, E obj) {
        E objAtIndex = this.get(index); //get the object at the index
        this.getNode(index).setValue(obj); //replace the object
        return objAtIndex; //return the replaced object
    }

    public boolean add(E obj) {
        Node toAppend = new Node(obj);
        if (last != null) {
            last.setNext(toAppend);
            toAppend.setPrevious(last);
        }
        else { first = toAppend; }
        last = toAppend;
        size++;
        return true;
    }

    public E remove(int index) {
        Node current = this.getNode(index);
        Node prev = current.getPrevious();
        Node next = current.getNext();
        if (prev == null && next == null) { //removing from list of size 1
            first = null;
            last = null;
        }
        else if (prev == null) { //removing from beginning of list
            next.setPrevious(null);
            first = next;
        }
        else if (next == null) { //removing from the end of the list
            prev.setNext(null);
            last = prev;
        }
        else { //removing from middle of list
            prev.setNext(next);
            next.setPrevious(prev);
        }
        size--;
        return (E) current.getValue(); 
    }

    public void add(int index, E obj) {
        if (index == 0) this.addFirst(obj);
        else if (index == this.size() - 1) this.addLast(obj);
        else {
            Node toAdd = new Node(obj);
            Node prev = this.getNode(index);
            Node next = this.getNode(index + 1);
            prev.setNext(toAdd);
            next.setPrevious(toAdd);
            toAdd.setPrevious(prev);
            toAdd.setNext(next);
            size++;
        }
    }
    public void addFirst(E obj) {
        Node toAdd = new Node(obj);
        if (first != null) {
            Node next = this.getNode(0);
            next.setPrevious(toAdd);
            toAdd.setNext(next);
        }
        else { last = toAdd; }
        first = toAdd;
        size++;
    }

    public void addLast(E obj) {
        Node toAdd = new Node(obj);
        if (last != null) {
            Node prev = this.getNode(this.size() - 1);
            prev.setNext(toAdd);
            toAdd.setPrevious(prev);
        }
        else { first = toAdd; }
        last = toAdd;
        size++;
    }

    public E getFirst() {return (E) this.get(0); }

    public E getLast() { return (E) this.get(this.size() - 1); } 

    public E removeFirst() {
        E removedObj = this.getFirst();
        if (this.size() == 1) { //remove the only element from the list
            first = null;
            last = null;
        }
        else {
            Node next = this.getNode(1);
            first = next;
            next.setPrevious(null);
        }
        size--;
        return removedObj;
    }

    public E removeLast() {
        E removedObj = this.getLast();
        if (this.size() == 1) { //remove the only element from the list
            first = null;
            last = null;
        }
        else {
            Node prev = this.getNode(this.size() - 2);
            last = prev;
            prev.setNext(null);
        }
        size--;
        return removedObj;
    }
}