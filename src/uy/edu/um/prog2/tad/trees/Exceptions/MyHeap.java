package uy.edu.um.prog2.tad.trees.Exceptions;

import uy.edu.um.prog2.tad.trees.MyTree;
import uy.edu.um.prog2.tad.trees.Node;

public interface MyHeap<K extends Comparable<K>,T> extends MyTree<K,T> {
    public Node<K,T> maxHeap();
    public Node<K,T> minHeap();
}
