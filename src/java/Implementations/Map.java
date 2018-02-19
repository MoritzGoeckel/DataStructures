package Implementations;

import java.util.Iterator;

public interface Map<K, V> {
    void set(K key, V value);
    V get(K key);
    boolean contains(K key);
    int size();
    void remove(K key);
    String toString();
    Iterator<K> keys();
}
