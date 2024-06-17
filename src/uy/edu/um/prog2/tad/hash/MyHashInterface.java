package uy.edu.um.prog2.tad.hash;

import uy.edu.um.prog2.tad.linkedlist.Lista;
import uy.edu.um.prog2.tad.linkedlist.ListaEnlazada;
import uy.edu.um.prog2.tad.queue.MyPriorityQueue;

import java.util.LinkedList;

public interface MyHashInterface <K extends Comparable<K>,V extends Comparable<V>> {

    public void put(K key, V value);
    public V getValue(K key);
    public boolean contains(K key);
    public void remove(K clave);
    public int capacity();
    public int size();
    public Lista<NodoHash<K, V>> getNodesAsList(boolean reversed);
    public Lista<NodoHash<V, K>> getNodesAsSwappedList(boolean reversed);
    public MyPriorityQueue<NodoHash<K, V>> getNodesAsPriorityQueue(boolean reversed);
    public MyPriorityQueue<NodoHash<V, K>> getNodesAsSwappedPriorityQueue(boolean reversed);
}
