package Implementations;

public class Queue<T> {
    private QueueItem<T> first;
    private QueueItem<T> last;
    private int size = 0;

    public void enqueue(T value){
        if(first == null && last == null)
            first = last = new QueueItem<>(value, null);
        else {
            QueueItem<T> newItem = new QueueItem<>(value, null);
            last.nextItem = newItem;
            last = newItem;
        }

        size++;
    }

    public T dequeue(){
        if(first == null)
            return null;

        T out = first.value;

        if(first.nextItem == null)
            first = last = null;
        else
            first = first.nextItem;

        size--;
        return out;
    }

    public T peek(){
        if(first == null)
            return null;

        return first.value;
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        QueueItem item = first;
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

class QueueItem<T>{
    T value;
    QueueItem nextItem;

    QueueItem(T value, QueueItem nextItem){
        this.value = value;
        this.nextItem = nextItem;
    }
}
