package Implementations;

public class HashSet<V> {

    //Todo: Iterator
    //Todo: Test

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
}
