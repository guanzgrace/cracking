import java.util.Comparable;

public class MyPriorityQueue<Object> {
    private Comparable[] queue;
    private int items;
    private static final int START_SIZE = 16;

    public MyPriorityQueue() {
        queue = new Comparable[START_SIZE];
        items = 0;
    }

    public boolean add(Comparable obj) {
        if (items == queue.length) { resize(); }
        queue[items] = obj; 
        items++;
        return true;
    }

    private void resize() {
        Comparable[] newQueue = new Comparable[items * 2];
        for (int i = 0; i < items; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    public boolean isEmpty() { return items == 0; }

    public Comparable peek() { 
        if (isEmpty()) return null;
        return queue[indexOfMin()]; 
    } 

    public Comparable remove() {
        if (isEmpty()) return null; 
        int min = indexOfMin();
        Comparable result = queue[min]; 
        items--; 
        queue[min] = queue[items]; 
        return result; 
    }

    public int indexOfMin() {
        int min = 0; 
        for (int i=1; i<items; i++) {
            if (queue[i].compareTo (queue[min]) < 0) { min = i; }
        } 
    }
}
