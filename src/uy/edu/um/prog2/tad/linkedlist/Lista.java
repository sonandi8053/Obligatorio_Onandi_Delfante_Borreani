package uy.edu.um.prog2.tad.linkedlist;

public interface Lista<T extends Comparable<T>> {
    public void add(T value);

    public void addFirst(T val);

    public void remove(int position);

    public T get(int position);

    public Nodo<T> getNode(int position);

    public void printList();

    public boolean isEmpty();

    public int size();

    public void insert(int position, T value);

    public boolean isInside(T value);

    public void sort();

    public void limitarElementos(int cantidad);

    public void reverse();
}
