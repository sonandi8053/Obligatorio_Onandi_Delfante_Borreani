package uy.edu.um.prog2.tad.hash;

import uy.edu.um.prog2.tad.linkedlist.*;

import java.lang.Math;
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
            if (tablahash[pos].getKey() == key) {
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

    public Lista<NodoHash<K, V>> getNodesAsList(){
        Lista<NodoHash<K, V>> temp = new ListaEnlazada<>();
        for (int i = 0; i<this.size; i++){
            if (this.tablahash[i] != null){
                temp.add(this.tablahash[i]);
            }
        }
        return temp;

    }
}
