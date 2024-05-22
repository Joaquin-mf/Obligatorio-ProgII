package uy.edu.um.tad.hash;

import java.util.Objects;

public class HashNode <K,T> {
    private K key;
    private T data;


    public HashNode(K key, T data) {
        this.key = key;
        this.data = data;
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashNode<?, ?> hashNode = (HashNode<?, ?>) o;
        return Objects.equals(key, hashNode.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
