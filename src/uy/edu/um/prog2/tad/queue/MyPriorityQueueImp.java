package uy.edu.um.prog2.tad.queue;

import Exceptions.EmptyQueueException;
import uy.edu.um.prog2.tad.linkedlist.ListaDoblementeEncadenada;

public class MyPriorityQueueImp<T extends Comparable<T>> implements MyPriorityQueue<T>{
    private ListaDoblementeEncadenada<T> lista = new ListaDoblementeEncadenada<>();
    @Override
    public void enqueueWithPriority(T element, int prioridad) {
        lista.addWithPriority(element, prioridad);
    }

    @Override
    public void enqueue(T element) {

    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (this.isEmpty()){
            throw new EmptyQueueException();
        }
        T val = lista.get(lista.size()-1);
        lista.remove(lista.size()-1);
        return val;
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    @Override
    public int size() {
        return lista.size();
    }
}
