package uy.edu.um.prog2.tad.queue;

import Exceptions.EmptyQueueException;
import uy.edu.um.prog2.tad.linkedlist.ListaDoblementeEncadenada;
import uy.edu.um.prog2.tad.linkedlist.Nodo;

public class MyDobleQueueImp<T extends Comparable<T>> implements MyDoubleQueue {
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private ListaDoblementeEncadenada<T> lista;

    public MyDobleQueueImp() {
        this.primero = null;
        this.ultimo = null;
        this.lista = new ListaDoblementeEncadenada<>();
    }

    public Nodo<T> obtenerPrimerNodo(){
        T t = lista.get(0);
        Nodo<T> nodo = new Nodo<>(t);
        return nodo;
    }

    public Nodo<T> obtenerUltimoNodo(){
        T t = lista.get(lista.size()-1);
        Nodo<T> nodo = new Nodo<>(t);
        return nodo;
    }


    @Override
    public void enqueueLeft(Object element) {
        Nodo<T> nodoAAgregar;
        nodoAAgregar = new Nodo<T>((T)element);
        lista.insert(0,nodoAAgregar.getValue());
        primero = nodoAAgregar;
    }

    @Override
    public Object dequeueLeft() throws EmptyQueueException {
        if (this.size()!=0){
            Nodo<T>primeroTemp = obtenerPrimerNodo();
            lista.remove(0);
            return primeroTemp.getValue();
        }
        else{
            throw new EmptyQueueException();
        }
    }

    @Override
    public void enqueueRight(Object element) {
        Nodo<T> nodoAAgregar;
        nodoAAgregar = new Nodo<T>((T)element);
        lista.insert(this.size()-1,nodoAAgregar.getValue());
        ultimo = nodoAAgregar;
    }

    @Override
    public Object dequeueRight() throws EmptyQueueException {
        if (this.size()!=0){
            Nodo<T>ultimoTemp = obtenerUltimoNodo();
            lista.remove(this.size()-1);
            return ultimoTemp.getValue();
        }
        else{
            throw new EmptyQueueException();
        }
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public int size(){
        return this.lista.size();
    }
}
