package uy.edu.um.prog2.tad.trees;

import exceptions.ChildsAreOccupied;
import exceptions.KeyAlreadyExists;
import exceptions.KeyDoesNotExist;
import exceptions.WrongArgumentException;
import uy.edu.um.prog2.tad.linkedlist.Lista;

public interface MyTree<K extends Comparable<K>, T> {
    T find(K key) throws KeyDoesNotExist;
    void insert (K key, T data, K parentKey) throws ChildsAreOccupied, WrongArgumentException, KeyDoesNotExist, KeyAlreadyExists;
    void delete (K key);

    // cuenta	la	cantidad	de	elementos	dentro	del	árbol.
    int size(Node<K, T> root);

    int size();

    // cuenta	la	cantidad	de	hojas	de	un	árbol.
    int countLeaf();

    // Cuenta el número	de nodos con subárboles
    // izquierdo y derecho no nulos.
    int countCompleteElements();

    Lista<K> inOrder();

    Lista<K> preOrder();

    Lista<K> postOrder();

    Lista<K> porNivel();

    void loadPostFijaExpression (String sPostFija);
}
