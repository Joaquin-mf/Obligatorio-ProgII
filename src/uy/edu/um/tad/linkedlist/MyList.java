package uy.edu.um.tad.linkedlist;

import Entities.SpotifySong;

public interface MyList<T extends Comparable<T>> extends Comparable<MyList<SpotifySong>> {

    void add(T value);

    T get(int position);

    boolean contains(T value);

    void remove(T value);

    int size();

    Node<T> getFirst();

    boolean isEmpty();

    void sort();
}
