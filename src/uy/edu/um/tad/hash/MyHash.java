package uy.edu.um.tad.hash;

import exceptions.ElementNotFoundException;
import uy.edu.um.tad.linkedlist.MyList;

public interface MyHash <K,T> {
    void put (K key, T value);
    int find(K key);
    void remove(K key) throws ElementNotFoundException;
    boolean contains(K key);
    HashNode<K,T> findNode(K key);
    MyList<T> values(MyList<T> ans);
}
