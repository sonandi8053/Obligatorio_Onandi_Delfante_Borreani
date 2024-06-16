package uy.edu.um.prog2.tad.trees;

import uy.edu.um.prog2.tad.trees.Exceptions.MyHeap;

public class Heap<K extends Comparable<K>, T> extends Tree<K, T> implements MyHeap<K, T> {

    public void insert(K key, T value) {

    }

    @Override
    public Node<K, T> maxHeap() {
        return null;
    }

    @Override
    public Node<K, T> minHeap() {
        return null;
    }
}
