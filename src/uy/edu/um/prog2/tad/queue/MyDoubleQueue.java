package uy.edu.um.prog2.tad.queue;

import Exceptions.EmptyQueueException;

public interface MyDoubleQueue <T>{

    public void enqueueLeft (T element);
    public T dequeueLeft () throws EmptyQueueException;
    public void enqueueRight (T element);
    public T dequeueRight () throws EmptyQueueException;
    public boolean isEmpty();
}
