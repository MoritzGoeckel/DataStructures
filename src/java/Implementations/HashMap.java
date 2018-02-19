package Implementations;

import java.util.Iterator;

public class HashMap<K, V> implements Map<K, V>{

    private LinkedList<KeyValuePair<K, V>>[] array = new LinkedList[3];
    private int elementsCount = 0;

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
            for(KeyValuePair<K, V> pair : bucket)
                newArray[Math.abs(pair.key.hashCode()) % size].add(pair);

        array = newArray;
    }

    @Override
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

    @Override
    public V get(K key){
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, null);

        int bucket = getBucket(key);
        int listIndex = array[bucket].getIndex(pair);

        if(listIndex == -1)
            throw new RuntimeException("Key not found: " + key);

        return array[bucket].get(listIndex).value;
    }

    @Override
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

    @Override
    public boolean contains(K key){
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, null);
        return array[getBucket(key)].getIndex(pair) != -1;
    }

    @Override
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
    public Iterator<K> keys() {
        return new Iterator<K>() {

            int currentItem = 0;
            int currentIndex = 0;
            Iterator<KeyValuePair<K, V>> currentInterator = null;

            @Override
            public boolean hasNext() {
                return currentItem < elementsCount;
            }

            @Override
            public K next() {
                currentItem++;

                if(currentInterator != null && currentInterator.hasNext())
                    return currentInterator.next().key;

                while(currentIndex < array.length){
                    currentInterator = array[currentIndex].iterator();
                    currentIndex++;
                    if(currentInterator.hasNext())
                        return currentInterator.next().key;
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
        return "{"+ key + ":" + value + "}";
    }
}
