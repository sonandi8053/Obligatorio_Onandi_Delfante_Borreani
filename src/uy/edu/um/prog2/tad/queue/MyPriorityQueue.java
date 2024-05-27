package uy.edu.um.prog2.tad.queue;

public interface MyPriorityQueue <T> extends MyQueue<T>{

    public void enqueueWithPriority (T element, int prioridad);
}
