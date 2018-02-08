package Implementations;

public class Stack<T> {
    private StackItem<T> root;
    private int size = 0;

    public void push(T value){
        root = new StackItem<>(value, root);
        size++;
    }

    public T pop(){
        T out = root.value;
        root = root.nextItem;
        size--;
        return out;
    }

    public T peek(){
        return root.value;
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        StackItem item = root;
        while (item != null){
            sb.append(item.value.toString());
            if(item.nextItem != null)
                sb.append(", ");
            item = item.nextItem;
        }
        sb.append(" ]");
        return sb.toString();
    }
}

class StackItem<T>{
    T value;
    StackItem nextItem;

    StackItem(T value, StackItem nextItem){
        this.value = value;
        this.nextItem = nextItem;
    }
}
