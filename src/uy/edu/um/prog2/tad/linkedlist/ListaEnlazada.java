package uy.edu.um.prog2.tad.linkedlist;

import java.util.LinkedList;

// El compilador de java importa automaticamente java.lang
// que es donde se encuentra la interfaz Comparable
public class ListaEnlazada<T extends Comparable<T>>
        implements Lista<T> {

    protected Nodo<T> primero;
    protected int length = 0;


    @Override
    public void add(T value) {this.addLast(value);}

    /**
    * Remueve un elemento de una posicion dada.
    */
    @Override
    public void remove(int position) {
        if (position >= length){
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> temp;
        Nodo<T> temp2;
        if (position == 0){
            primero = this.getNode(position).getSiguiente();
        }
        else{
             temp = this.getNode(position - 1);
             temp2 = this.getNode(position);
             temp.setSiguiente(temp2.getSiguiente());
        }
        length--;
    }

    @Override
    public T get(int position) {
        return this.getNode(position).getValue();
    }

    /**
     * Devuelve el nodo de la posicion dada
     * position 0 devuelve primero
     */
    public Nodo<T> getNode(int position) {
        if (position >= length){
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> temp = primero;
        for (int i = 0; i<position; i++){
            temp = temp.getSiguiente();
        }
        return temp;
    }

    /**
     * Imprime en pantalla en una previsualizacion de la lista completa
     */
    @Override
    public void printList(){
        String var = new String("[");
        for (int i = 0; i< this.size(); i++){
            var += this.get(i);
            if (i != this.size() - 1){var += ", ";}

            // Imprime hasta donde pueda, util para probar errores
            //if (this.getNode(i).getSiguiente() == null){break;}
        }
        var += "]";
        System.out.println(var);
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public int size() {
        return this.length;
    }

    /**
     * Inserta un valor en una posicion dada
     */
    private void insert(int position, T value, int priority){
        if (position > this.size()){
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> new_ = new Nodo<>(value, priority);
        if (position == 0){
            new_.setSiguiente(primero);
            primero = new_;
        }
        else if (position == this.size()){
            this.addLast(value, priority);
            return;
        }
        else {
            Nodo<T> temp = this.getNode(position - 1);
            Nodo<T> temp2 = temp.getSiguiente();

            // Actualizar siguiente de _new, temp y temp2
            new_.setSiguiente(temp2);
            temp.setSiguiente(new_);
        }
        length++;
    }
    @Override
    public void insert(int position, T value){
        this.insert(position, value, 0);
    }
    @Override
    public boolean isInside(T value){
        /*
        Devuelve true o false dependiendo si existe un elemento de la lista que tenga
        el valor de tipo T dado.
         */
        Nodo<T> temp = primero;
        for (int i = 0; i< this.size(); i++){
            if (temp != null && temp.getValue() == value){return true;}
            temp = temp.getSiguiente();

        }
        return false;
    }
    /**
     * Agrega un elemento en la primera posicion de la lista
     */
    @Override
    public void addFirst(T val){
        if (primero == null) {
            primero = new Nodo<>(val, 0);
        }
        else {
            Nodo<T> temp = new Nodo<T>(val);
            temp.setSiguiente(primero);
            primero = temp;
            length++;
        }
    }

    /**
     * Agrega un elemento en la ultima posicion de la lista
     */
    private void addLast(T value, int priority){

        if (primero == null) {
            primero = new Nodo<>(value, priority);
        }
        else {
            // Agrega un nuevo nodo a siguiente de primero
            // Y este puede contener un nodo y asi hasta n
            Nodo<T> temp = primero;
            while (temp.getSiguiente() != null){
                temp = temp.getSiguiente();
            }
            // Crea el nuevo nodo y setea el anterior y el siguiente
            Nodo<T> temp2 = new Nodo<>(value, priority);
            temp.setSiguiente(temp2);

        }
        length++;
    }

    public void addLast(T value){
        this.addLast(value, 0);
    }

    /**
     * Ordena la lista de forma ascendente utilizando el
     * algoritmo bubble sort y compareTo de comparable
     *
     * @return
     */
    @Override
    public void sort(){
        if (size() == 0){return;}
        Nodo<T> temp;
        Nodo<T> temp2;
        for (int i = 0; i< this.size(); i++){

            for (int j = 0; j< this.size() -1; j++){
                temp = this.getNode(j);
                temp2 = temp.getSiguiente();
                if (temp.getValue().compareTo(temp2.getValue()) > 0){
                    this.switchPositions(j, j+1);
                }
            }
        }
    }

    public void addWithPriority(T value, int priority){
        Nodo<T> thisNode;
        for (int i = 0; i<this.size(); i++){
            thisNode = this.getNode(i);
            if (thisNode.getPriority() > priority){
                this.insert(i, value, priority);
                return;
            }
        }
        // Si no encontro
        this.insert(this.size(), value, priority);
    }

    /**
     * Intercambia dos elementos dada sus posiciones
     */
    public void switchPositions(int pos1, int pos2){
        Nodo<T> temp = this.getNode(pos1);
        Nodo<T> temp2 = this.getNode(pos2);

        this.remove(pos1);
        this.insert(pos1, temp2.getValue());
        this.remove(pos2);
        this.insert(pos2, temp.getValue());
    }

    /**
     * El	método	visualizar(LinkedList	P)	de	la	clase	LinkedList	imprime	los
     * elementos	de	la	lista	L	que	estén	en	posiciones	dadas	por	P.	Por	ejemplo:
     * LinkedList	l	=	new	LinkedList();	//	se	carga	l	con	objetos	.......	LinkedList	p	=
     * new	LinkedList();	Integer	i	=	new	Integer(1);	Integer	j	=	new	Integer(3);
     * p.agregarPrimero(j);	p.agregarPrimero(i);	l.visualizar(P);
     */
    public void visualizar(ListaEnlazada<Integer> P){
        ListaEnlazada<T> listaDevolver = new ListaEnlazada<>();
        for (int i = 0; i<P.size(); i++){
            listaDevolver.add(this.get(P.get(i)));
        }
        listaDevolver.printList();
    }

    public ListaEnlazada<Integer> find(T Object){
        ListaEnlazada<Integer> indexesFound = new ListaEnlazada<>();
        for (int i = 0; i< this.size(); i++){
            if (this.get(i) == Object){
                indexesFound.add(i);
            }
        }
        return indexesFound;
    }

    public void intercambiar(T Object, int direccion){
        ListaEnlazada<Integer> elementosIndex = this.find(Object);
        for (int i = 0; i<elementosIndex.size(); i++){
            int index = elementosIndex.get(i);
            this.switchPositions(index, index + direccion);
        }
    }

    /**
     * Dado un parametro de tipo ListaEmlazada<T> devolvera los elementos
     * que esten en ambas listas.
     */
    public ListaEnlazada<T> getSameElements(ListaEnlazada<T> list){
        ListaEnlazada<T> res = new ListaEnlazada<>();
        for (int i = 0; i<list.size(); i++){
            T element = list.get(i);
            boolean indexInside = this.isInside(element);
            if (indexInside){
                res.add(element);
            }
        }
        return res;
    }

    /**
     * Testea si existe un anterior y siguiente para una posicion correctamente
     */
    public void testAnteriorSiguiente(int position){
        Nodo<T> n = this.getNode(position);
        Nodo<T> ant = n.getAnterior();
        Nodo<T> sig = n.getSiguiente();

        System.out.print(n.getValue());
        System.out.print(") ");

        System.out.print("Ant=");
        if (ant != null){
            System.out.print(ant.getValue());
        }
        System.out.print(" , Sig=");
        if (sig != null){
            System.out.println(sig.getValue());
        }
    }

    public void limitarElementos(int cantidad){
        if (this.size() <= cantidad){
            return;
        }
        ListaEnlazada<T> temp = new ListaEnlazada<>();
        for (int i = 0; i<cantidad; i++){
            temp.add(this.get(i));
        }
        this.primero = temp.primero;
    }


    @Override
    public void reverse(){
        if (primero == null){
            return;
        }
        Lista<T> listaNueva = new ListaEnlazada<>();
        for (int i = 0; i<this.size(); i++){
            listaNueva.addFirst(this.get(i));
        }
        this.primero = listaNueva.getNode(0);
    }
}
