package uy.edu.um.prog2.tad.heap.Exceptions;

public class EmptyHeapException extends Exception{
    public EmptyHeapException(String mensaje ){
        System.out.println(mensaje);
    }
}
