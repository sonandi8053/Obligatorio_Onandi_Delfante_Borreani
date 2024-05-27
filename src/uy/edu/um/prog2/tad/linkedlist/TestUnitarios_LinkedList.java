package uy.edu.um.prog2.tad.linkedlist;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.LinkedList;

public class TestUnitarios_LinkedList {
    Lista<Integer> listaTesteos = new ListaEnlazada<>();
    @Test
    public void TestAddFlujoNormal() {
        listaTesteos.add(1);
        listaTesteos.add(2);
        listaTesteos.add(3);
        listaTesteos.add(4);
        assertEquals(4, listaTesteos.size());
    }


    @Test
    public void testRemovePrimerNodo() {
        Lista<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        lista.add(2);
        lista.remove(0);
        assertEquals(1, lista.size());
        assertEquals(Integer.valueOf(2), lista.get(0));
    }

    @Test
    public void testRemoveNodoDelMedio() {
        Lista<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.remove(1);
        assertEquals(2, lista.size());
        assertEquals(Integer.valueOf(1), lista.get(0));
        assertEquals(Integer.valueOf(3), lista.get(1));
    }

    @Test
    public void testRemoveUltimoNodo() {
        Lista<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.remove(2);
        assertEquals(2, lista.size());
        assertEquals(Integer.valueOf(1), lista.get(0));
        assertEquals(Integer.valueOf(2), lista.get(1));
    }

    @Test
    public void testRemovePosicionInvalida() {
        Lista<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        lista.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.remove(2);
        });
    }

    @Test
    public void testRemoveEnListaVacia() {
        Lista<Integer> lista = new ListaEnlazada<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.remove(0);
        });
    }

    @Test
    public void testGetPrimerNodo() {
        Lista<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        lista.add(2);
        assertEquals(Integer.valueOf(1), lista.get(0));
    }

    @Test
    public void testGetNodoMedio() {
        Lista<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        assertEquals(Integer.valueOf(2), lista.get(1));
    }

    @Test
    public void testGetUltimoNodo() {
        Lista<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        assertEquals(Integer.valueOf(3), lista.get(2));
    }

    @Test
    public void testGetPosicionInvalida() {
        Lista<Integer> lista = new ListaEnlazada<>();
        lista.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.get(1);
        });
    }

    @Test
    public void testGetEnListaVacia() {
        Lista<Integer> lista = new ListaEnlazada<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.get(0);
        });
    }
}