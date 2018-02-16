package Implementations;

public class Trie<V> {

    //Todo: WIP

    private TrieNode<V> root = new TrieNode<>();

    public Trie(){

    }

    public void add(V value[]){
        TrieNode<V> currentNode = root;
        for(int i = 0; i < value.length; i++) {
            if (currentNode.children.contains(value[i]))
                currentNode = currentNode.children.get(value[i]);
            else {
                TrieNode<V> newNode = new TrieNode();
                currentNode.children.set(value[i], newNode);
                currentNode = newNode;
            }
        }
    }

    public String getSuggestions(V value[]){
        TrieNode<V> currentNode = root;
        for(int i = 0; i < value.length; i++) {
            if (currentNode.children.contains(value[i]))
                currentNode = currentNode.children.get(value[i]);
            else break;
        }

        return currentNode.children.toString();
    }
}

class TrieNode<V>{
    HashMap<V, TrieNode> children = new HashMap<>();
}