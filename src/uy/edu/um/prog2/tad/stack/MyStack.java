package uy.edu.um.prog2.tad.stack;


import java.util.EmptyStackException;

public interface MyStack<T> {
    int lenght();

    public void push(T element);

    public void pop() throws EmptyStackException;

    public T peek() throws EmptyStackException;

    boolean isEmpty();

    public void makeEmpty();
}