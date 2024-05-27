package uy.edu.um.prog2.tad.linkedlist;

public class ListaDoblementeEncadenada<T extends Comparable<T>> extends ListaEnlazada<T>{
    /**
     * Inserta un valor en una posicion dada
     * Por lo tanto la posicion donde se insertara el nuevo elemento
     * sera anterior al que estaba previamente
     */
    @Override
    public void insert(int position, T value){

        Nodo<T> new_ = new Nodo<>(value);
        if (position == 0){
            primero.setAnterior(new_);
            new_.setSiguiente(primero);
            primero = new_;
        }
        else if (position == length){
            this.addLast(value);
            return;
        }
        else {
            Nodo<T> temp = this.getNode(position - 1);
            Nodo<T> temp2 = temp.getSiguiente();

            // Actualizar siguiente y anterior de _new, temp y temp2
            new_.setSiguiente(temp2);
            new_.setAnterior(temp);
            temp.setSiguiente(new_);
            temp2.setAnterior(new_);
        }
        length++;
    }
    @Override
    public void addFirst(T val){

        Nodo<T> temp = new Nodo<T>(val);
        temp.setSiguiente(primero);
        primero = temp;
        length ++;
    }
    @Override
    public void addLast(T value){

        if (primero == null) {
            primero = new Nodo<>(value);
        }
        else {
            // Agrega un nuevo nodo a siguiente de primero
            // Y este puede contener un nodo y asi hasta n
            Nodo<T> temp = primero;
            while (temp.getSiguiente() != null){
                temp = temp.getSiguiente();
            }
            // Crea el nuevo nodo y setea el anterior y el siguiente
            Nodo<T> temp2 = new Nodo<>(value);
            temp.setSiguiente(temp2);


        }
        length ++;
    }
}
