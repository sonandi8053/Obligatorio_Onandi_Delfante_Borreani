package uy.edu.um.prog2.tad.linkedlist;

public class ListaCircular<T extends Comparable<T>> extends ListaDoblementeEncadenada<T>{
    @Override
    public void addLast(T value){

        if (primero == null) {
            primero = new Nodo<>(value);
        }
        else {
            // Agrega un nuevo nodo siguiente al primero y
            // fija el siguiente como el primero y el anterior del primero
            // como el nuevo noto, deshaciendo correctamente
            // los cambios anteriores
            Nodo<T> temp = primero;
            for (int i = 0; i< length -1; i++){
                temp = temp.getSiguiente();
            }
            Nodo<T> new_ = new Nodo<>(value);
            new_.setAnterior(temp);
            new_.setSiguiente(primero);
            temp.setSiguiente(new_);
            primero.setAnterior(new_);

        }
        length++;
    }

    @Override
    public void insert(int position, T value){

        Nodo<T> new_ = new Nodo<>(value);
        if (position == 0){
            // Esta parte cambia
            Nodo<T> temp = primero;
            for (int i = 0; i< length -1; i++){
                temp = temp.getSiguiente();
            }
            primero.setAnterior(new_);
            new_.setSiguiente(primero);
            primero = new_;
            temp.setSiguiente(primero);
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

    public T get(int position){
        return this.getNode(position%this.length).getValue();
    }
}
