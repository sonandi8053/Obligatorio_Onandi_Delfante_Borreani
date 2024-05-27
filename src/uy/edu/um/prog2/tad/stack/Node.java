package uy.edu.um.prog2.tad.stack;

public class Node<T> {
    private T value;
    private Node<T> siguiente;
    private Node<T> anterior;


    public Node(T val){
        this.value = val;
    }

    public Node<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Node<T> siguiente) {
        this.siguiente = siguiente;
    }

    public Node<T> getAnterior() {return anterior;}

    public void setAnterior(Node<T> anterior) {this.anterior = anterior;}

    public T getValue() {return value;}

    public void setValue(T value) {this.value = value;}
}
