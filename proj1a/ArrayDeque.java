public class ArrayDeque<T> {
    /**
     * capactiy : actual size of array
     * size : number of the element in array
     * first, last : no item in the position;
     * */
    private final int factor = 4;
    private final int initialCapacity = 8;
    private final int criticalCapacity = 16;
    private int size;
    private T[] items;
    private int first;
    private int last;
    private int capacity;

    public ArrayDeque() {
        capacity = initialCapacity;
        first = 0;
        last = first + 1;
        size = 0;
        items = (T[]) new Object[capacity];
    }

    private void resize(int newCapacity) {
        // copy array
        T[] newItems = (T[]) new Object[newCapacity];
        for (int i = 1; i <= size; i++) {
            newItems[i] = items[format(first + i)];
        }
        items = newItems;
        capacity = newCapacity;
        // change first and last
        first = 0;
        last = format(size + 1); // adjust last to [0, capacity - 1]
    }

    public void addFirst(T item) {
        //capacity == size, what to do ? resize the array;
        // fisrt and last change: first --> 1, last --> size+1;
        size = size + 1;
        items[first] = item;
        first = format(first - 1);
        if (size == capacity) {
            resize(factor * capacity);
        }
    }

    public void addLast(T item) {
        size = size + 1;
        items[last] = item;
        last = format(last + 1);
        if (size == capacity) {
            resize(factor * capacity);
        }
    }

    /** Format first and last idx each time after add item.*/
    private int format(int idx) {
        return (idx + capacity) % capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size <= 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            int idx = format(first + 1 + i);
            System.out.print(items[idx] + " ");
        }
    }

    public T removeFirst() {
        if (size < 1) {
            return null;
        }
        size = size - 1;
        first = format(first + 1);
        T res = items[first];
        items[first] = null;

        if (size * factor < capacity && capacity > criticalCapacity) {
            resize(capacity / factor);
        }
        return res;
    }

    public T removeLast() {
        if (size < 1) {
            return null;
        }
        size = size - 1;
        last = format(last - 1);
        T res = items[last];
        items[last] = null;

        if (size * factor < capacity && capacity > criticalCapacity) {
            resize(capacity / factor);
        }
        return res;
    }

    public T get(int index) {
        // avoid index out of array
        if (index >= size || index < 0) {
            return null;
        }
        // attention please !!!
        return items[format(first + 1 + index)];
    }
}
