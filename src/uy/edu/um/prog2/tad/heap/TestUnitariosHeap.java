package uy.edu.um.prog2.tad.heap;

import org.junit.Test;
import uy.edu.um.prog2.tad.heap.Exceptions.EmptyHeapException;

import static org.junit.Assert.assertEquals;

public class TestUnitariosHeap {
    Heap<Integer, Integer> Minheap = new HeapImpl<>(true);
    Heap<Integer, Integer> Maxheap = new HeapImpl<>(false);

    @Test
    public void testInsert(){
        Minheap.insert(1, 2);
        Minheap.insert(2, 5);
        Minheap.insert(4, 2);
        Minheap.insert(5, 5);
        Minheap.insert(6, 2);
        Minheap.insert(7, 5);
        Minheap.insert(8, 2);
        Minheap.insert(9, 5);

        Maxheap.insert(1, 2);
        Maxheap.insert(2, 5);
        Maxheap.insert(4, 2);
        Maxheap.insert(5, 5);
        Maxheap.insert(6, 2);
        Maxheap.insert(7, 5);
        Maxheap.insert(8, 2);
        Maxheap.insert(9, 5);
    }

    @Test
    public void testGet(){
        Minheap.insert(1, 100);
        Minheap.insert(2, 200);

        Maxheap.insert(2, 140);
        Maxheap.insert(3, 120);

        // hacemos el get
        Integer valor = Minheap.getValue();
        assertEquals((Integer) 100, valor);
    }

    @Test
    public void testDeleteMinHeap() throws EmptyHeapException {
        // minimo
        Minheap.insert(1, 100);
        Minheap.insert(2, 200);

        assertEquals((Integer) 100, Minheap.delete());
        assertEquals((Integer) 200, Minheap.getValue());
    }

    @Test
    public void testMultipleDeletes() throws EmptyHeapException {
        // 10 inserts
        Minheap.insert(1, 2);
        Minheap.insert(2, 5);
        Minheap.insert(4, 2);
        Minheap.insert(5, 5);
        Minheap.insert(6, 2);
        Minheap.insert(7, 5);
        Minheap.insert(8, 2);
        Minheap.insert(9, 5);
        Minheap.insert(1, 2);
        Minheap.insert(2, 5);

        Minheap.delete();
        Minheap.delete();
        Minheap.delete();
        Minheap.delete();
        Minheap.delete();
        Minheap.delete();
        Minheap.delete();
        Minheap.delete();
        Minheap.delete();
        Minheap.delete();

        assertEquals( 0, Minheap.size());

    }

    @Test
    public void testDeleteMaxHeap() throws EmptyHeapException {
        // maximo
        Maxheap.insert(2, 140);
        Maxheap.insert(3, 120);
        Integer deletedValue = Maxheap.delete();
        assertEquals((Integer) 140, deletedValue);
        assertEquals((Integer) 120, Maxheap.getValue());
    }

    @Test
    public void testRepeated(){
        // Deberia poder tener dos keys repetidas
        // lo primero sera cantidad de apariciones que se puede repetir, despues los nombre canciones
        Minheap.insert(1, 100);
        Minheap.insert(2, 200);
        Maxheap.insert(2, 140);

    }
}
