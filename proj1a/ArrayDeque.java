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

    public void resize(int newCapacity) {
        // copy array
        T[] newItems = (T[]) new Object[newCapacity];

        // increase or decrese array
        if (newCapacity > capacity) {
            System.arraycopy(items, first + 1, newItems, 1, capacity - first - 1);
            System.arraycopy(items, 0, newItems, capacity - first, last);
        } else if (first < last) {
            System.arraycopy(items, first + 1, newItems, 1, size);
        } else { //first > last
            System.arraycopy(items, first + 1, newItems, 1, capacity - first - 1);
            System.arraycopy(items, 0, newItems, capacity - first, last);
        }

        items = newItems;
        // decrease array
        capacity = newCapacity;
        // change first
        first = 0;
        //change last
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
    public int format(int idx) {
        if (idx < 0) {
            return idx + capacity;
        } else if (idx >= capacity) {
            return idx - capacity;
        } else {
            return idx;
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size <= 0) {
            return;
        }
        if (first < last) {
            for (int i = first + 1; i < last; i++) {
                System.out.print(items[i] + " ");
            }
        } else { //first >= last
            for (int i = first + 1; i < capacity; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < last; i++) {
                System.out.print(items[i] + " ");
            }
        }
    }

    public T removeFirst() {
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
        return items[index];
    }

    //    public static void main(String[] args) {
    //        ArrayDeque<Integer> A = new ArrayDeque<>();
    //        A.printDeque();
    //    }
}
