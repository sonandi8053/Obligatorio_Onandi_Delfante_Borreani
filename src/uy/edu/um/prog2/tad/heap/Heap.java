package uy.edu.um.prog2.tad.heap;

public interface Heap<K extends Comparable<K>, T> {

    T delete();

    HeapNode<K,T> get();

    void insert(K key, T value);

    int size();
}
