public class MyArrayList<E> {
    private int size;
    private Object[] values;

    public MyArrayList() {
        size = 0;
        values = new Object[1];
    }

    public String toString() {
        if (size == 0) return "[]";
        String s = "[";
        for (int i = 0; i < size - 1; i++) {
            s += values[i] + ", ";
        }
        return s + values[size - 1] + "]";
    }

    private void doubleCapacity() {
        int len = values.length;
        Object[] newValues = new Object[2*len];
        for (int i=0; i<len; i++) {
            newValues[i] = values[i];
        }
        values = newValues;
    }

    public int getCapacity() { return values.length; }

    public int size() { return size; }

    public E get(int index) { return (E) values[index]; }

    public E set(int index, E obj) {
        E returnObject = (E) values[index];
        values[index] = obj;
        return returnObject;
    }

    public boolean add(E obj) {
        resizeArray();
        values[size - 1] = obj;
        return true;
    }

    public E remove(int index) {
        E returnObj = (E) values[index];
        int len = values.length;
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }
        size--;
        return returnObj;
    }

    public void add(int index, E obj) {
        resizeArray();
        Object[]temp = new Object[size - index];
        int tempCount = 0;
        for (int i = index; i < size - 1; i++) {
            temp[tempCount] = values[i];
            tempCount++;
        }
        tempCount = 0;
        for(int i = index; i < size - 1; i++) {
            values[i + 1] = temp[tempCount];
            tempCount++;
        }
        values[index] = obj;
    }

    public void resizeArray() {
    	int len = getCapacity();
        size++;
        if (len + 1 == size) { doubleCapacity(); }
    }
}