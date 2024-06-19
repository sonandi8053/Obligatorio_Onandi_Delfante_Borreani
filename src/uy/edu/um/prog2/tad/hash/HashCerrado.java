package uy.edu.um.prog2.tad.hash;

import uy.edu.um.prog2.tad.heap.Heap;
import uy.edu.um.prog2.tad.heap.HeapImpl;
import uy.edu.um.prog2.tad.linkedlist.*;
import uy.edu.um.prog2.tad.queue.MyPriorityQueue;
import uy.edu.um.prog2.tad.queue.MyPriorityQueueImp;

import java.lang.Math;

import static java.lang.Math.round;

public class HashCerrado<K extends Comparable<K>, V extends Comparable<V>> implements MyHashInterface<K, V> {
    private NodoHash[] tablahash;
    private int size;
    private double LoadFactor =0.79;

    public int capacity;

    public HashCerrado(int capacity) {
        this.capacity = capacity;
        this.tablahash = new NodoHash[this.capacity];
        this.size = 0;
    }

    private void resize() {
        NodoHash<K, V>[] oldTable = tablahash;
        capacity = 2 * capacity;
        size = 0;
        tablahash = new NodoHash[capacity];
        for (NodoHash<K, V> nodoTemp : oldTable) {
            if(nodoTemp != null ){
                put(nodoTemp.getKey(), nodoTemp.getValue());
            }
        }
    }

    @Override
    public void put(K key, V value) {
        int pos = HashFunction(key);
        NodoHash<K, V> nodoAgregado = new NodoHash<>(key, value);
        if (tablahash[pos] == null) {
            tablahash[pos] = nodoAgregado;
        } else {
            while (tablahash[pos] != null) {
                if (tablahash[pos].getKey().equals(key)) {
                    tablahash[pos] = nodoAgregado;
                    return;
                }
                if(pos == capacity-1){
                    pos=0;
                }
                pos = pos + 1;
            }
            tablahash[pos] = nodoAgregado;
        }
        size ++;
        if((size) >= (int)(capacity * LoadFactor)) {
            resize();
        }

    }


    private NodoHash<K, V> getNodo(K key) {
        int pos = HashFunction(key);
        while (tablahash[pos] != null) {
            if (tablahash[pos].getKey().equals(key)) {
            return tablahash[pos];
            } else {
                if(pos == capacity-1){
                    return null;
                }
                pos = pos + 1;
            }
        }
        return null;
    }

    @Override
    public V getValue(K key) {
        NodoHash<K, V> nodo = getNodo(key);
        if(nodo != null) {
            return nodo.getValue();
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        int pos = HashFunction(key);
        while (pos<capacity && tablahash[pos] != null){
            if(tablahash[pos].getKey().equals(key)){
                return true;
            }
            pos =pos +1;
        }
        return false;
    }

    @Override
    public void remove(K clave) {
        int pos = HashFunction(clave);
        while(tablahash[pos]!= null) {
            if (tablahash[pos].getKey().equals(clave)) {
                tablahash[pos] = null;
            }
            pos = (pos + 1)%capacity;
        }
        size --;
    }

    public int HashFunction(K key){
        int HashValue = key.hashCode();
        return Math.abs(HashValue) % capacity;
    }

    public String showHash(){
        String resp = "{";
        for (int i = 0; i<this.capacity; i++){
            if (this.tablahash[i] != null) {
                resp += this.tablahash[i].getKey() + ":" + this.tablahash[i].getValue();
            }
            else {
                resp += "null:null";
            }
            if (i != this.capacity-1) {
                resp += ", ";
            }
        }
        resp += "}";
        return resp;
    }

    public int capacity(){
        return this.capacity;
    }

    public int size(){
        return this.size;
    }

    public Lista<NodoHash<K, V>> getNodesAsList(boolean reversed){
        Lista<NodoHash<K, V>> temp = new ListaEnlazada<>();
        if (reversed){
            for (int i = this.capacity; i>0; i--){
                if (this.tablahash[i] != null){
                    temp.add(this.tablahash[i]);
                }
            }
        }
        else {
            for (int i = 0; i<this.capacity; i++){
                if (this.tablahash[i] != null){
                    temp.add(this.tablahash[i]);
                }
            }
        }

        return temp;
    }

    public Heap<K, V> getAsHeap(boolean isMin){
        Heap<K, V> temp = new HeapImpl<>(isMin);
        for (int i = 0; i<this.capacity; i++){
            NodoHash<K, V> elemento = this.tablahash[i];
            if (elemento != null){
                temp.insert(elemento.getKey(), elemento.getValue());
            }
        }
        return temp;
    }

    public Heap<V, K> getAsSwappedHeap(boolean isMin){
        Heap<V, K> temp = new HeapImpl<>(isMin);
        for (int i = 0; i<this.capacity; i++){
            NodoHash<K, V> elemento = this.tablahash[i];
            if (elemento != null){
                temp.insert(elemento.getValue(), elemento.getKey());
            }
        }
        return temp;
    }

    public Lista<NodoHash<V, K>> getNodesAsSwappedList(boolean reversed){
        Lista<NodoHash<V, K>> temp = new ListaEnlazada<>();
        if (reversed){
            for (int i = this.capacity; i>0; i--){
                if (this.tablahash[i] != null){
                    temp.add(this.tablahash[i].swap());
                }
            }
        }
        else {
            for (int i = 0; i<this.capacity; i++){
                if (this.tablahash[i] != null){
                    temp.add(this.tablahash[i].swap());
                }
            }
        }

        return temp;
    }

    public MyPriorityQueue<NodoHash<K, V>> getNodesAsPriorityQueue(boolean reversed){
        MyPriorityQueue<NodoHash<K, V>> temp = new MyPriorityQueueImp<>();
        if (reversed){
            for (int i = this.capacity; i>0; i--){
                if (this.tablahash[i] != null){
                    temp.enqueueWithPriority(this.tablahash[i], round(this.hashCode()));
                }
            }
        }
        else {
            for (int i = 0; i<this.capacity; i++){
                if (this.tablahash[i] != null){
                    temp.enqueueWithPriority(this.tablahash[i], round(this.hashCode()));
                }
            }
        }

        return temp;
    }

    public MyPriorityQueue<NodoHash<V, K>> getNodesAsSwappedPriorityQueue(boolean reversed){
        MyPriorityQueue<NodoHash<V, K>> temp = new MyPriorityQueueImp<>();
        if (reversed){
            for (int i = this.capacity; i>0; i--){
                if (this.tablahash[i] != null){
                    temp.enqueueWithPriority(this.tablahash[i].swap(), round(this.hashCode()));
                }
            }
        }
        else{
            for (int i = 0; i<this.capacity; i++){
                if (this.tablahash[i] != null){
                    temp.enqueueWithPriority(this.tablahash[i].swap(), round(this.hashCode()));
                }
            }
        }

        return temp;
    }
}
