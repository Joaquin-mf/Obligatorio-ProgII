package uy.edu.um.adt.hash;

public interface MyHash <K,T> {
    void put (K key, T value);
    T find(K key);
    void remove(K key);
    boolean contains(K key);

}
