package uy.edu.um.tad.linkedlist;

public interface MyList<T> {

    void add(T value);

    T get(int position);

    boolean contains(T value);

    void remove(T value);

    int size();

    Node<T> getFirst();

    boolean isEmpty();
}
