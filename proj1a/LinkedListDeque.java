public class LinkedListDeque<T> {

    private class StuffNode {
        private T item;
        private StuffNode prev;
        private StuffNode next;
        public StuffNode(T i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private StuffNode sentinel;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public void addFirst(T item) {

        size = size + 1;
        StuffNode p = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
    }

    public void addLast(T item) {
        size = size + 1;
        StuffNode p = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
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
        StuffNode p = sentinel.next;
        for (int i = 0; i < size; i++, p = p.next) {
            System.out.print(p.item + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size = size - 1;
        StuffNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        T res = first.item;
        first.prev = null;
        first.next = null;
        first.item = null;
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size = size - 1;
        StuffNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        T res = last.item;
        last.prev = null;
        last.next = null;
        last.item = null;
        return res;
    }

    public T get(int index) {
        if (index < 0 || index + 1 > size) {
            return null;
        }
        StuffNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursive(int index, StuffNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    //    public static void main(String[] args) {
    //        LinkedListDeque<Integer> L = new LinkedListDeque<>();
    //        L.addFirst(3);
    //        L.addLast(5);
    //        L.addLast(6);
    //        L.addLast(10);
    //        System.out.println(L.size());
    //        L.printDeque();
    //
    //        System.out.println();
    //        System.out.println(L.removeFirst());
    //        System.out.println(L.size());
    //        L.printDeque();
    //
    //        System.out.println();
    //        System.out.println(L.removeLast());
    //        System.out.println(L.size());
    //        L.printDeque();
    //
    //        System.out.println();
    //        System.out.println(L.get(0));
    //        System.out.println(L.get(1));
    //        System.out.println(L.get(-1));
    //
    //
    //    }
}
