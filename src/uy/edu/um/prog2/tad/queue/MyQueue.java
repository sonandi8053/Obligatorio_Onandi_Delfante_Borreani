package uy.edu.um.prog2.tad.queue;

import Exceptions.EmptyQueueException;

public interface MyQueue <T>  {
    void enqueue (T element);

    T dequeue () throws EmptyQueueException;
    boolean isEmpty();

    int size();
}
