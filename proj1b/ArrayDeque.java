public class ArrayDeque<Item> implements Deque<Item>{
    /**
     * capactiy : actual size of array
     * size : number of the element in array
     * first, last : no item in the position;
     * */
    private final int factor = 4;
    private final int initialCapacity = 8;
    private final int criticalCapacity = 16;
    private int size;
    private Item[] items;
    private int first;
    private int last;
    private int capacity;

    public ArrayDeque() {
        capacity = initialCapacity;
        first = 0;
        last = first + 1;
        size = 0;
        items = (Item[]) new Object[capacity];
    }

    private void resize(int newCapacity) {
        // copy array
        Item[] newItems = (Item[]) new Object[newCapacity];
        for (int i = 1; i <= size; i++) {
            newItems[i] = items[format(first + i)];
        }
        items = newItems;
        capacity = newCapacity;
        // change first and last
        first = 0;
        last = format(size + 1); // adjust last to [0, capacity - 1]
    }

    @Override
    public void addFirst(Item item) {
        //capacity == size, what to do ? resize the array;
        // fisrt and last change: first --> 1, last --> size+1;
        size = size + 1;
        items[first] = item;
        first = format(first - 1);
        if (size == capacity) {
            resize(factor * capacity);
        }
    }

    @Override
    public void addLast(Item item) {
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size <= 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            int idx = format(first + 1 + i);
            System.out.print(items[idx] + " ");
        }
    }

    @Override
    public Item removeFirst() {
        if (size < 1) {
            return null;
        }
        size = size - 1;
        first = format(first + 1);
        Item res = items[first];
        items[first] = null;

        if (size * factor < capacity && capacity > criticalCapacity) {
            resize(capacity / factor);
        }
        return res;
    }

    @Override
    public Item removeLast() {
        if (size < 1) {
            return null;
        }
        size = size - 1;
        last = format(last - 1);
        Item res = items[last];
        items[last] = null;

        if (size * factor < capacity && capacity > criticalCapacity) {
            resize(capacity / factor);
        }
        return res;
    }

    @Override
    public Item get(int index) {
        // avoid index out of array
        if (index >= size || index < 0) {
            return null;
        }
        // attention please !!!
        return items[format(first + 1 + index)];
    }
}
