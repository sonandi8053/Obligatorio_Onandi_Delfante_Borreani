package uy.edu.um.prog2.tad.hash;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnitariosHash {
    MyHashInterface<Integer, String> hashPrueba = new HashCerrado<>(2);

    @Test
    public void testPut(){
        hashPrueba.put(10, "Prueba");
        hashPrueba.put(3, "Prueba2");
        hashPrueba.put(90, "Prueba3");
    }

    @Test
    public void testResize(){
        hashPrueba.put(10, "Prueba");
        hashPrueba.put(90, "Prueba2");
        assertEquals(4, hashPrueba.capacity());
    }

    @Test
    public void testContains(){
        hashPrueba.put(70, "Francia");
        hashPrueba.put(30, "Finlandia");
        assertTrue(hashPrueba.contains(70));
    }
    @Test
    public void testRemove() {
        hashPrueba.put(1, "uno");
        hashPrueba.put(2, "dos");
        hashPrueba.remove(1);
        hashPrueba.remove(2);
        assertFalse(hashPrueba.contains(1));
        assertFalse(hashPrueba.contains(2));

    }
}
