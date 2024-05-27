package uy.edu.um.prog2.tad.trees;
import static org.junit.Assert.*;

import org.junit.Test;
import uy.edu.um.prog2.tad.linkedlist.Lista;

import java.util.Optional;

public class Test_Unitarios_Tree {
    MyTree<Integer,String>arbolTesteo = new Tree<>();

    @Test
    public void testeoInsertFlujoNormal () throws exceptions.KeyDoesNotExist, exceptions.ChildsAreOccupied, exceptions.WrongArgumentException, exceptions.KeyAlreadyExists {
        arbolTesteo.insert(1,"primero",null);
        arbolTesteo.insert(2,"segundo",1);
        arbolTesteo.insert(3,"tercero",2);
    }

    @Test
    public void testInsertFirstElement() throws Exception {
        arbolTesteo.insert(1, "España", null);
        assertEquals("España", arbolTesteo.find(1));
    }

    @Test
    public void testInsertChildElements() throws Exception {
        arbolTesteo.insert(1, "Holanda", null);
        arbolTesteo.insert(2, "Italia", 1);
        arbolTesteo.insert(3, "Francia", 1);
        assertEquals("Italia", arbolTesteo.find(2));
        assertEquals("Francia", arbolTesteo.find(3));
    }

    @Test
    public void testInsertThrowsChildsAreOccupied() {
        assertThrows(exceptions.ChildsAreOccupied.class, () -> {
            arbolTesteo.insert(1, "Canada", null);
            arbolTesteo.insert(2, "Italia", 1);
            arbolTesteo.insert(3, "Uruguay", 1);
            arbolTesteo.insert(4, "Paraguay", 1);
        });
    }

    @Test
    public void testInsertThrowsKeyAlreadyExists() {
        assertThrows(exceptions.KeyAlreadyExists.class, () -> {
            arbolTesteo.insert(1, "Uruguay", null);
            arbolTesteo.insert(2, "Francia", 1);
            arbolTesteo.insert(1, "Francia", 2);
        });
    }

    @Test
    public void testInsertThrowsKeyDoesNotExist() {
        assertThrows(exceptions.KeyDoesNotExist.class, () -> {
            arbolTesteo.insert(1, "Uruguay", null);
            arbolTesteo.insert(2, "Francia", 99);
        });
    }

    @Test
    public void testInsertThrowsWrongArgumentException() {
        assertThrows(exceptions.WrongArgumentException.class, () -> {
            arbolTesteo.insert(1, "Uruguay", 99);
        });
    }

    @Test
    public void testDeleteNode() throws Exception {
        arbolTesteo.insert(1, "España", null);
        arbolTesteo.insert(2, "Argentina", 1);
        arbolTesteo.insert(3, "Uruguay", 1);
        arbolTesteo.delete(2);
        assertThrows(exceptions.KeyDoesNotExist.class, () -> arbolTesteo.find(2));
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, arbolTesteo.size());
        arbolTesteo.insert(1, "Brasil", null);
        assertEquals(1, arbolTesteo.size());
        arbolTesteo.insert(2, "Francia", 1);
        arbolTesteo.insert(3, "Canada", 1);
        assertEquals(3, arbolTesteo.size());
    }

    @Test
    public void testCountLeaf() throws Exception {
        assertEquals(0, arbolTesteo.countLeaf());
        arbolTesteo.insert(1, "Brasil", null);
        assertEquals(1, arbolTesteo.countLeaf());
        arbolTesteo.insert(2, "Canada", 1);
        arbolTesteo.insert(3, "Italia", 1);
        assertEquals(2, arbolTesteo.countLeaf());
    }

    @Test
    public void testCountCompleteElements() throws Exception {
        assertEquals(0, arbolTesteo.countCompleteElements());
        arbolTesteo.insert(1, "Italia", null);
        assertEquals(0, arbolTesteo.countCompleteElements());
        arbolTesteo.insert(2, "Rusia", 1);
        arbolTesteo.insert(3, "Ucrania", 1);
        assertEquals(1, arbolTesteo.countCompleteElements());
    }

}