package Implementations;

import java.util.Iterator;

public class HashMap<K, V> implements Iterable<KeyValuePair<K, V>>{

    private LinkedList<KeyValuePair<K, V>>[] array = new LinkedList[3];
    private int elementsCount = 0;

    //Todo: Iterator

    public HashMap(){
        for(int i = 0; i < array.length; i++)
            array[i] = new LinkedList<>();

        setArraySize(3);
    }

    private void setArraySize(int size){
        LinkedList<KeyValuePair<K, V>> newArray[] = new LinkedList[size];
        for(int i = 0; i < size; i++)
            newArray[i] = new LinkedList<>();

        for(LinkedList<KeyValuePair<K, V>> bucket : array)
            for(int i = 0; i < bucket.size(); i++) //Todo: Inefficient, should use an iterator
                newArray[Math.abs(bucket.get(i).key.hashCode()) % size].add(bucket.get(i));

        array = newArray;
    }

    public void set(K key, V value){
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, value);

        int bucket = getBucket(key);
        int listIndex = array[bucket].getIndex(pair);

        if(listIndex == -1) {
            array[bucket].add(pair);
            elementsCount++;
        }
        else
            array[bucket].get(listIndex).value = pair.value;

        if(elementsCount > array.length)
            setArraySize(array.length + array.length / 3);
    }

    public V get(K key){
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, null);

        int bucket = getBucket(key);
        int listIndex = array[bucket].getIndex(pair);

        if(listIndex == -1)
            throw new RuntimeException("Key not found: " + key);

        return array[bucket].get(listIndex).value;
    }

    public void remove(K key){
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, null);

        int bucket = getBucket(key);
        int listIndex = array[bucket].getIndex(pair);

        if(listIndex == -1)
            throw new RuntimeException("Key not found: " + key);

        array[bucket].remove(listIndex);
        elementsCount--;

        if(elementsCount < array.length - array.length / 3 && elementsCount > 3)
            setArraySize(elementsCount);
    }

    public boolean contains(K key){
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, null);
        return array[getBucket(key)].getIndex(pair) != -1;
    }

    public int size(){
        return elementsCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < array.length; i++) {
            sb.append(array[i].toString());
            if (i < array.length - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private int getBucket(K key){
        return Math.abs(key.hashCode()) % array.length;
    }

    @Override
    public Iterator<KeyValuePair<K, V>> iterator() {
        return new Iterator<KeyValuePair<K, V>>() {

            int currentItem = 0;
            int currentIndex = 0;
            Iterator<KeyValuePair<K, V>> currentInterator = null;

            void findNext(){

            }

            @Override
            public boolean hasNext() {
                return currentItem < elementsCount;
            }

            @Override
            public KeyValuePair<K, V> next() {
                if(currentInterator.hasNext())
                    return currentInterator.next();

                for (; currentIndex < array.length; currentIndex++){
                    currentInterator = array[currentIndex].iterator();
                    if(currentInterator.hasNext())
                        return currentInterator.next();
                }

                return null;
            }
        };
    }
}

class KeyValuePair<K, V>{
    public K key;
    public V value;

    public KeyValuePair(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return key.equals(((KeyValuePair<K, V>) obj).key);
    }

    @Override
    public String toString() {
        return "{"+ key + ": " + value + "}";
    }
}
