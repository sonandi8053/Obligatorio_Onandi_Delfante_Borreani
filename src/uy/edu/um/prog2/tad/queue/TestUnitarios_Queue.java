package uy.edu.um.prog2.tad.queue;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestUnitarios_Queue {
    MyQueue queuePrueba = new MyQueueImp();


    @Test
    public void testEnqueue() {
        queuePrueba.enqueue(10);
        queuePrueba.enqueue(20);
        queuePrueba.enqueue(30);

        // Comprobar el tamaño de la queuePrueba
        assertEquals(3, queuePrueba.size());
    }
    @Test
    public void testDequeue() throws Exceptions.EmptyQueueException {
        queuePrueba.enqueue(10);
        queuePrueba.enqueue(20);
        queuePrueba.enqueue(30);

        Object valor = queuePrueba.dequeue();

        assertEquals(10, valor);
        assertEquals(2, queuePrueba.size());
    }

    @Test
    public void testIsEmpty() {
        MyQueue<Integer> queuePrueba = new MyQueueImp<>();
        assertTrue(queuePrueba.isEmpty());
        queuePrueba.enqueue(10);
        assertFalse(queuePrueba.isEmpty());
    }

    @Test
    public void testNormalPriorityQueue() {
        MyPriorityQueue<Integer> q = new MyPriorityQueueImp<>();
        q.enqueueWithPriority(1, 2);
        q.enqueueWithPriority(2, 3);
        q.enqueueWithPriority(3, 1);
        q.enqueueWithPriority(4, 2);
    }

    @Test
    public void testDequeuePriorityQueue() throws Exceptions.EmptyQueueException {
        MyPriorityQueue<Integer> q = new MyPriorityQueueImp<>();
        q.enqueueWithPriority(1, 2);
        q.enqueueWithPriority(2, 3);
        q.enqueueWithPriority(3, 1);
        q.enqueueWithPriority(4, 2);

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        assertThrows(Exceptions.EmptyQueueException.class, () -> q.dequeue());
    }

}