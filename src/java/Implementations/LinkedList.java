package Implementations;

public class LinkedList<T> implements List<T> {

    private LinkedListItem<T> rootItem = null;
    private LinkedListItem<T> lastItem = null;
    private int size = 0;

    @Override
    public void add(T item) {
        if(rootItem == null)
            rootItem = lastItem = new LinkedListItem<>(item, null);
        else {
            lastItem.nextItem = new LinkedListItem<>(item, null);
            lastItem = lastItem.nextItem;
        }

        size++;
    }

    @Override
    public T get(int index) {
        if(index >= size)
            throw new IndexOutOfBoundsException();

        LinkedListItem<T> currentItem = rootItem;
        for(int i = 0; i < index; i++)
            currentItem = currentItem.nextItem;

        return currentItem.value;
    }

    @Override
    public void remove(int index) {
        if(index > size - 1)
            throw new IndexOutOfBoundsException();

        if(index == 0)
            rootItem = rootItem.nextItem;
        else {
            LinkedListItem<T> currentItem = rootItem;
            for (int i = 0; i < index - 1 && i < size; i++)
                currentItem = currentItem.nextItem;

            currentItem.nextItem = currentItem.nextItem.nextItem;
        }

        size--;
    }

    @Override
    public void clear() {
        rootItem = lastItem = null;
        size = 0;
    }

    @Override
    public int getIndex(T item) {
        LinkedListItem<T> currentItem = rootItem;
        for(int i = 0; i < size; i++) {
            if(currentItem.value.equals(item))
                return i;
            else
                currentItem = currentItem.nextItem;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        LinkedListItem<T> currentItem = rootItem;
        for(int i = 0; i < size; i++) {
            if(s.length() != 0)
                s.append(", ");
            s.append(currentItem.value.toString());
            currentItem = currentItem.nextItem;
        }

        return "[" + s.toString() + "]";
    }
}

class LinkedListItem<T>{
    LinkedListItem(T value, LinkedListItem<T> nextItem){
        this.value = value;
        this.nextItem = nextItem;
    }

    T value;
    LinkedListItem<T> nextItem;
}
