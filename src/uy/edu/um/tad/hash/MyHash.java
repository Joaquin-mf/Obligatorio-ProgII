package uy.edu.um.tad.hash;

import exceptions.ElementNotFoundException;

public interface MyHash <K,T> {
    void put (K key, T value);
    int find(K key);
    void remove(K key) throws ElementNotFoundException;
    boolean contains(K key);
    HashNode<K,T> findNode(K key);

}
