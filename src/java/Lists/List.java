package Lists;

public interface List<T> {
    void add(T item);
    T get(int index);
    void remove(int index);
    void clear();
    int getIndex(T item);
    int size();
}
