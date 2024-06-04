package uy.edu.um.tad.hash;

import exceptions.ElementNotFoundException;
import uy.edu.um.tad.heap.MyHeap;
import uy.edu.um.tad.linkedlist.MyList;

public interface MyHash <K,T extends Comparable<T>> {
    void put (K key, T value);
    int find(K key);
    void remove(K key) throws ElementNotFoundException;
    boolean contains(K key);
    T findData(K key);
}
