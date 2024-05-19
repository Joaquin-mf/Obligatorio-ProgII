package uy.edu.um.adt.hash;

import exceptions.ElementNotFoundException;
import exceptions.OutofSize;

public interface MyHash <K,T> {
    void put (K key, T value) throws OutofSize;
    int find(K key);
    void remove(K key) throws ElementNotFoundException;
    boolean contains(K key);
    HashNode<K,T> findNode(K key);

}
