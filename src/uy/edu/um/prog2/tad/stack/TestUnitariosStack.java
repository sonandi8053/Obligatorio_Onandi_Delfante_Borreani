package uy.edu.um.prog2.tad.stack;
import static org.junit.Assert.*;
import org.junit.Test;


import java.util.EmptyStackException;
import java.util.Optional;

public class TestUnitariosStack {
    MyStack<Integer> stackTesteo = new Stack<>();

    @Test
    public void testPeekEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stackTesteo.peek());
    }

    @Test public void testPopEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stackTesteo.pop());
    }

    @Test
    public void testInsert() {
        stackTesteo.push(1);
        stackTesteo.push(2);
    }

    @Test
    public void testPeek(){
        stackTesteo.push(3);
        assertEquals(Integer.valueOf(3), stackTesteo.peek()) ;
    }

    @Test
    public void testPop(){
        stackTesteo.push(3);
        assertEquals(Integer.valueOf(3), stackTesteo.peek());
        stackTesteo.pop();
        assertThrows(EmptyStackException.class, () -> stackTesteo.peek());
    }

    @Test
    public void testIsEmpty(){
        assertEquals(true, stackTesteo.isEmpty());
        stackTesteo.push(3);
        assertEquals(false, stackTesteo.isEmpty());
    }

    @Test
    public void testMakeEmpty() {
        stackTesteo.push(3);
        stackTesteo.push(4);
        assertEquals(false, stackTesteo.isEmpty());
        stackTesteo.makeEmpty();
        assertEquals(true, stackTesteo.isEmpty());
    }
}
