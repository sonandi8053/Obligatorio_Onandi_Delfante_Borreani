package uy.edu.um.prog2.tad.hash;

public interface MyHashInterface <K,V> {

    public void put(K key, V value);
    public boolean contains(K key);
    public void remove(K clave);
    public int capacity();
    public int size();

    int entrySet();
}
