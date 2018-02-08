package Lists;

public class ArrayList<T> implements List<T> {

    private T[] array;
    private int firstFreeIndex = 0;

    public ArrayList(){
        array = (T[])new Object[3];
    }

    private void grow(){
        T[] bigger = (T[])new Object[array.length * 3 / 2 + 1];
        System.arraycopy(array, 0, bigger, 0, array.length);
        array = bigger;
    }

    private void shrink(){
        T[] smaller = (T[])new Object[firstFreeIndex];
        System.arraycopy(array, 0, smaller, 0, smaller.length);
        array = smaller;
    }

    @Override
    public void add(T item) {
        if(array.length <= firstFreeIndex)
            grow();

        array[firstFreeIndex] = item;

        firstFreeIndex++;
    }

    @Override
    public T get(int index) {
        if(index >= firstFreeIndex)
            throw new IndexOutOfBoundsException();

        return array[index];
    }

    @Override
    public void remove(int index) {
        for (int i = index; i < firstFreeIndex - 1; i++){
            if(i == array.length - 1)
                array[i] = null;
            else
                array[i] = array[i + 1];
        }

        firstFreeIndex--;

        //Shrink
        if(array.length > firstFreeIndex + (firstFreeIndex * 3 / 2)){
            shrink();
        }
    }

    @Override
    public void clear() {
        firstFreeIndex = 0;
        array = (T[])new Object[3];
    }

    @Override
    public int getIndex(T item) {
        for(int i = 0; i < firstFreeIndex; i++)
            if(array[i].equals(item))
                return i;

        return -1;
    }

    @Override
    public int size() {
        return firstFreeIndex;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < firstFreeIndex; i++) {
            if(s.length() != 0)
                s.append(", ");
            s.append(array[i].toString());
        }

        return "[" + s.toString() + "]";
    }
}