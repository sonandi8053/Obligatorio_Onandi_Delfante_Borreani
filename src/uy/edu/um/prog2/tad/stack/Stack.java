package uy.edu.um.prog2.tad.stack;

import java.util.EmptyStackException;

public class Stack<T> implements MyStack<T>{
    private Node<T> primero;
    private int lenght = 0;

    @Override
    public int lenght(){
        return lenght;
    }


    @Override
    public void push(T value) {
        addToTheEnd(value);
    }

    private Node<T> getNode(int pos){
        if (primero == null){
            return null;
        }
        Node<T> temp = primero;
        for (int i = 1; i<pos; i++){
            temp = temp.getSiguiente();
        }
        return temp;
    }

    @Override
    public void pop() throws EmptyStackException{
        if (lenght() == 0){
            throw new EmptyStackException();
        }

        Node<T> nodo = this.getNode(this.lenght() - 1);
        nodo.setSiguiente(null);

        lenght--;
    }

    @Override
    public T peek() throws EmptyStackException{
        if (lenght() == 0){
            throw new EmptyStackException();
        }
        return this.getNode(this.lenght()).getValue();

    }

    private void addToTheEnd(T value) {
        if (primero == null){
            primero = new Node<>(value);
            lenght++;
            return;
        }
        Node<T> nFin = this.getNode(this.lenght());
        Node<T> temp = new Node<>(value);
        nFin.setSiguiente(temp);
        lenght++;
    }

    public boolean isEmpty(){
        return lenght() == 0;
    }

    @Override
    public void makeEmpty() {
        primero = null;
        lenght = 0;
    }

}
