package uy.edu.um.prog2.tad.trees;

public class Node<K, T> {
    private K key;
    private T data;
    private Node<K, T> leftChild;
    private Node<K, T> rightChild;

    public Node(K key, T data){
        this.key = key;
        this.data = data;
    }

    public Node<K, T> find(K key){
        if (this.getKey() == key){
            return this;
        }
        Node<K, T> result = null;
        if (this.getLeftChild() != null){
            result = this.getLeftChild().find(key);
        }
        if (result == null && this.getRightChild() != null){
            result = this.getRightChild().find(key);
        }
        return result;
    }

    public Node<K, T> findParent(K key){
        if (this.getKey() == key){
            return this;
        }
        Node<K, T> result = null;
        if (this.getLeftChild() != null){
            result = this.getLeftChild().findParent(key);
        }
        if (result == null && this.getRightChild() != null){
            result = this.getRightChild().findParent(key);
        }

        if (result != null && result.getKey() == key){
            result = this;
        }

        return result;
    }


    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean equals(Node<K, T> node) {
        return node.getKey().equals(this.key);
    }
}
