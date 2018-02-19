package Implementations;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Iterator;

public class BinarySearchTree<K, V> implements Map<K, V>{
    private TreeItem<K, V> root;
    private int size = 0;

    @Override
    public void set(K key, V value){
        TreeItem<K, V> newNode = new TreeItem<>(key, value);
        if(root == null)
            root = newNode;
        else
            root.set(newNode);

        size++;
    }

    @Override
    public V get(K key){
        if(root == null)
            throw new RuntimeException("Entity not found");

        return root.get(key, key.hashCode());
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean contains(K key) {
        //Todo: Implement contains
        throw new NotImplementedException();
    }

    @Override
    public void remove(K key) {
        //Todo: Implement remove
        throw new NotImplementedException();
    }

    @Override
    public Iterator<K> keys() {
        //Todo: Implement keys
        throw new NotImplementedException();
    }

    public int height(){
        if(root == null)
            return 0;
        else
            return root.height();
    }

    @Override
    public String toString() {
        if(root == null)
            return "[]";

        StringBuilder sb = new StringBuilder("[");
        root.appendString(sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }
}

class TreeItem<K, V>{
    private TreeItem<K, V> leftNode, rightNode;
    private K key;
    private V value;
    private int keyHash;

    TreeItem(K key, V value){
        this.key = key;
        this.value = value;
        this.keyHash = key.hashCode();
    }

    void set(TreeItem<K, V> node) {
        if(this.key.equals(node.key))
            this.value = node.value;
        else {
            //Add right
            if (node.keyHash > this.keyHash) {
                if (this.rightNode == null)
                    this.rightNode = node;
                else
                    this.rightNode.set(node);
            }
            //Add left
            else {
                if (this.leftNode == null)
                    this.leftNode = node;
                else
                    this.leftNode.set(node);
            }
        }
    }

    V get(K key, int keyHash){
        if(this.key.equals(key))
            return this.value;
        else {
            if (keyHash > this.keyHash && this.rightNode != null)
                return rightNode.get(key, keyHash);
            else if(keyHash <= this.keyHash && this.leftNode != null)
                return leftNode.get(key, keyHash);

            throw new RuntimeException("Entity not found");
        }
    }

    int height(){
        return 1 + Math.max(
                leftNode != null ? leftNode.height() : 0,
                rightNode != null ? rightNode.height() : 0
        );
    }

    StringBuilder appendString(StringBuilder sb){
        if(leftNode != null)
            leftNode.appendString(sb);

        if(rightNode != null)
            rightNode.appendString(sb);

        sb.append("{")
            .append(key.toString())
            .append(":")
            .append(value.toString())
            .append("},");

        return sb;
    }
}
