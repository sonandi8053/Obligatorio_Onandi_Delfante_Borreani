package uy.edu.um.prog2.tad.heap;

import uy.edu.um.prog2.tad.heap.Exceptions.EmptyHeapException;

public interface Heap<K extends Comparable<K>, T> {

    void insert(K key, T value);

    T delete() throws EmptyHeapException;

    int size();

    boolean isEmpty();

    HeapNode<K, T> getNode();

    T getValue();

    K getKey();
}

