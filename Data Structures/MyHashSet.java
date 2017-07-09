import java.util.*;

public class MyHashSet<E> {
    private static final int NUM_BUCKETS = 42;
    private LinkedList<E>[] buckets;
    private int size;

    public MyHashSet() {
        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList();
        }
    }

    private int toBucketIndex(Object obj) { return buckets.length % Math.abs(obj.hashCode()); }

    public int size() { return size; }

    public boolean contains(Object obj) { return (buckets[toBucketIndex(obj)].contains(obj)); }

    public boolean add(E obj) {
        int index = toBucketIndex(obj);
        if (buckets[index].contains(obj)) return false;
        buckets[index].add(obj);
        size++;
        return true;
    }

    public boolean remove(Object obj) {
        int index = toBucketIndex(obj);
        if (! buckets[index].contains(obj)) return false;
        buckets[index].remove(obj);
        size--;
        return true;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < buckets.length; i++)
            if (buckets[i].size() > 0)
                s += i + ":" + buckets[i] + " ";
        return s;
    }
}