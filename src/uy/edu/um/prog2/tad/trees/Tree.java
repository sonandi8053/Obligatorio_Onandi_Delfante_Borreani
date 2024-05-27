package uy.edu.um.prog2.tad.trees;

import exceptions.ChildsAreOccupied;
import exceptions.KeyAlreadyExists;
import exceptions.KeyDoesNotExist;
import exceptions.WrongArgumentException;
import uy.edu.um.prog2.tad.linkedlist.Lista;
import uy.edu.um.prog2.tad.linkedlist.ListaEnlazada;
import uy.edu.um.prog2.tad.trees.MyTree;
import uy.edu.um.prog2.tad.trees.Node;

public class Tree<K extends Comparable<K>, T> implements MyTree<K, T> {
    public Node<K, T> first;
    @Override
    public T find(K key) throws KeyDoesNotExist {
        Node<K, T> nodo = first.find(key);
        if (nodo == null){
            throw new KeyDoesNotExist();
        }
        return first.find(key).getData();
    }

    /**
     * Inserta un elemento al arbol dado una parentKey, empieza preferencialmente por
     * la izquierda, si esta ya tiene un elemento lo inserta en la derecha y si ambos
     * estan ocupados lanza el error ChildsAreOccupied.
     * **/
    @Override
    public void insert(K key, T data, K parentKey) throws ChildsAreOccupied, WrongArgumentException, KeyDoesNotExist, KeyAlreadyExists {

        Node<K, T> nodeToAdd = new Node<>(key, data);

        // Comprobar que esten bien escritos los parametros
        if ((parentKey != null && this.first == null)){
            throw new WrongArgumentException();
        }

        // Si la parentKey es null significa que es el primer elemento del arbol
        if (parentKey == null){
            first = nodeToAdd;
            return;
        }

        // Lanzar error si la key ya existe
        Node<K, T> temp = first.find(key);
        if (temp != null){
            throw new KeyAlreadyExists();
        }

        // Lanzar error si no se encuentra una parent key
        Node<K, T> parentNode = first.find(parentKey);
        if (parentNode == null){
            throw new KeyDoesNotExist();
        }

        if (parentNode.getLeftChild() == null){
            parentNode.setLeftChild(nodeToAdd);
        }
        else if (parentNode.getRightChild() == null){
            parentNode.setRightChild(nodeToAdd);
        }
        else{
            throw new ChildsAreOccupied();
        }
    }

    /**
     * Delete en cascade: Dada la key borra su nodo y los nodos debajo de esta.
     * **/
    @Override
    public void delete(K key) {
        if (first.getKey() == key){
            first = null;
            return;
        }
        Node<K, T> temp = first.findParent(key);
        if (temp.getLeftChild().getKey() == key){
            temp.setLeftChild(null);
        }
        else {
            temp.setRightChild(null);
        }
    }

    @Override
    public int size(Node<K, T> root) {
        if (root == null){
            return 0;
        }
        return 1 + size(root.getRightChild()) + size(root.getLeftChild());
    }

    @Override
    public int size(){
        return this.size(this.first);
    }

    private int countLeaf(Node<K, T> root) {
        if (root == null){return 0;}

        if (root.getRightChild() == null && root.getLeftChild() == null){
            return 1;
        }

        return countLeaf(root.getLeftChild()) + countLeaf(root.getRightChild());
    }

    @Override
    public int countLeaf(){
        return countLeaf(first);
    }

    /**
     * Contaba solo los nodos que tienen hijo izquierdo y derecho.
     * **/
    private int countCompleteElements(Node<K, T> root) {
        if (this.size() == 0){
            return 0;
        }
        int valores = 0;
        if (root.getRightChild() != null){
            valores += countCompleteElements(root.getRightChild());
        }
        if (root.getLeftChild() != null){
            valores += countCompleteElements(root.getLeftChild());
        }
        if (root.getLeftChild()!= null && root.getRightChild() != null){
            return 1 + valores;
        }
        return valores;
    }

    @Override
    public int countCompleteElements() {
        return this.countCompleteElements(first);
    }

    private Lista<K> inOrder(Node<K, T> root) {
        Lista<K> lista = new ListaEnlazada<>();
        if (root.getLeftChild() == null && root.getRightChild() == null){
            lista.add(root.getKey());
            return lista;
        }
        Lista<K> temp;
        if (root.getLeftChild() != null) {
            temp = this.inOrder(root.getLeftChild());
            for (int i = 0; i<temp.size(); i++){
                lista.add(temp.get(i));
            }
        }
        lista.add(root.getKey());
        if (root.getRightChild() != null){
            temp = this.inOrder(root.getRightChild());
            for (int i = 0; i<temp.size(); i++){
                lista.add(temp.get(i));
            }
        }

        return lista;
    }

    @Override
    public Lista<K> inOrder(){
        return this.inOrder(first);
    }

    private Lista<K> preOrder(Node<K, T> root) {
        Lista<K> lista = new ListaEnlazada<>();
        lista.add(root.getKey());
        if (root.getLeftChild() == null && root.getRightChild() == null){
            return lista;
        }
        Lista<K> temp;
        if (root.getLeftChild() != null) {
            temp = this.preOrder(root.getLeftChild());
            for (int i = 0; i<temp.size(); i++){
                lista.add(temp.get(i));
            }
        }
        if (root.getRightChild() != null){
            temp = this.preOrder(root.getRightChild());
            for (int i = 0; i<temp.size(); i++){
                lista.add(temp.get(i));
            }
        }

        return lista;
    }

    @Override
    public Lista<K> preOrder() {
        return this.preOrder(first);
    }


    private Lista<K> postOrder(Node<K, T> root) {
        Lista<K> lista = new ListaEnlazada<>();
        if (root.getLeftChild() == null && root.getRightChild() == null){
            lista.add(root.getKey());
            return lista;
        }
        Lista<K> temp;
        if (root.getLeftChild() != null) {
            temp = this.postOrder(root.getLeftChild());
            for (int i = 0; i<temp.size(); i++){
                lista.add(temp.get(i));
            }
        }

        if (root.getRightChild() != null){
            temp = this.postOrder(root.getRightChild());
            for (int i = 0; i<temp.size(); i++){
                lista.add(temp.get(i));
            }
        }
        lista.add(root.getKey());

        return lista;
    }
    @Override
    public Lista<K> postOrder() {
        return this.postOrder(first);
    }

    /**
     * Este	algoritmo lo que realiza es	recorrer todos los nodos
     * del árbol yendo por nivel de arriba abajo de	izquierda a
     * derecha.
     * **/
    private Lista<K> porNivel(Node<K, T> root, int level) {
        Lista<K> lista = new ListaEnlazada<>();
        if (root == null){
            return lista;
        }
        Lista<K> temp;
        if (level == 0){
            lista.add(root.getKey());
        }

        else if (level > 0){
            // Primero con leftChild
            temp = this.porNivel(root.getLeftChild(), level - 1);
            for (int i = 0; i<temp.size(); i++){
                lista.add(temp.get(i));
            }

            // Despues con rightChild
            temp = this.porNivel(root.getRightChild(), level - 1);
            for (int i = 0; i<temp.size(); i++){
                lista.add(temp.get(i));
            }
        }

        return lista;
    }

    @Override
    public Lista<K> porNivel() {
        Lista<K> returnList = new ListaEnlazada<>();
        Lista<K> tempList = new ListaEnlazada<>();
        int level = 0;
        while (level == 0 || tempList.size() != 0 ){
            tempList = porNivel(first, level);
            // Add templist to returnList
            for (int i = 0; i<tempList.size(); i++){
                returnList.add(tempList.get(i));
            }
            level += 1;
        }
        return returnList;
    }

    @Override
    public void loadPostFijaExpression(String sPostFija) {

    }

}
