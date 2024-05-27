package uy.edu.um.prog2.tad.linkedlist;

public class Nodo<T> {
    private T value;
    private Integer priority = 0;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;


    public Nodo(T val){
        this.value = val;
    }

    public Nodo(T val, int priority){
        this.value = val;
        this.priority = priority;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo<T> getAnterior() {return anterior;}

    public void setAnterior(Nodo<T> anterior) {this.anterior = anterior;}

    public T getValue() {return value;}

    public void setValue(T value) {this.value = value;}

    public Integer getPriority(){
        return this.priority;
    }

    public void setPriority(int val){
        this.priority = val;
    }
}
