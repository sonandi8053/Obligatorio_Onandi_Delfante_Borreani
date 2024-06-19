package uy.edu.um.prog2.tad.heap;

import uy.edu.um.prog2.tad.heap.Exceptions.EmptyHeapException;

public interface Heap<K extends Comparable<K>, T> {

    T delete() throws EmptyHeapException;

    HeapNode<K,T> getNode();

    T getValue();

    K getKey();

    void insert(K key, T value);

    int size();
}
