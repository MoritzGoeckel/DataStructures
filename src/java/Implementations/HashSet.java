package Implementations;

import java.util.Iterator;

public class HashSet<V> implements Iterable<V>{

    private HashMap<V, V> map = new HashMap<>();
    public void add(V value){
        map.set(value, value);
    }

    public void clear(){
        map = new HashMap<>();
    }

    public void remove(V value){
        map.remove(value);
    }

    public int size(){
        return map.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Iterator<V> it = map.keys(); it.hasNext(); ) {
            sb.append(it.next());
            if(it.hasNext())
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<V> iterator() {
        return map.keys();
    }
}
