package Implementations;

import java.util.Iterator;

public class ArrayList<T> implements List<T>, Iterable<T> {

    private T[] array;
    private int size = 0;

    public ArrayList(){
        array = (T[])new Object[3];
    }

    private void grow(){
        T[] bigger = (T[])new Object[array.length * 3 / 2 + 1];
        System.arraycopy(array, 0, bigger, 0, array.length);
        array = bigger;
    }

    private void shrink(){
        T[] smaller = (T[])new Object[size];
        System.arraycopy(array, 0, smaller, 0, smaller.length);
        array = smaller;
    }

    @Override
    public void add(T item) {
        if(array.length <= size)
            grow();

        array[size] = item;

        size++;
    }

    @Override
    public T get(int index) {
        if(index >= size)
            throw new IndexOutOfBoundsException();

        return array[index];
    }

    @Override
    public void remove(int index) {
        if(index > size - 1)
            throw new IndexOutOfBoundsException();

        for (int i = index; i < size - 1; i++){
            if(i == array.length - 1)
                array[i] = null; //Todo: This seems to be not necessary
            else
                array[i] = array[i + 1];
        }

        size--;

        //Shrink
        if(array.length > size + (size * 3 / 2))
            shrink();
    }

    @Override
    public void clear() {
        size = 0;
        array = (T[])new Object[3];
    }

    @Override
    public int getIndex(T item) {
        for(int i = 0; i < size; i++)
            if(array[i].equals(item))
                return i;

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void set(int index, T value) {
        if(index >= size)
            throw new IndexOutOfBoundsException();

        array[index] = value;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < size; i++) {
            if(s.length() != 0)
                s.append(", ");
            s.append(array[i].toString());
        }

        return "[" + s.toString() + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return array[currentIndex++];
            }
        };
    }
}