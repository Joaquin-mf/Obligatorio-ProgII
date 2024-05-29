package uy.edu.um.tad.hash;

import exceptions.ElementNotFoundException;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

public class MyHashImpl<K, T> implements MyHash<K, T> {
    private HashNode<K, T>[] hashTable;
    private int capacity;
    private int size = 0;
    private static final float loadFactor = 0.70F;

    public MyHashImpl(int capacity) {
        this.hashTable = new HashNode[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    @Override
    public void put(K key, T value) {
        if (size >= capacity * loadFactor) {
            reSize();
        }
        int hashCode = key.hashCode();
        int absHashCode = hashCode & Integer.MAX_VALUE; // Operación bit a bit para obtener el valor absoluto
        int posicion = absHashCode % capacity;

        // Manejo de colisiones mediante sondeo lineal
        while (hashTable[posicion] != null && !hashTable[posicion].getKey().equals(key)) {
            posicion = (posicion + 1) % capacity;
        }

        if (hashTable[posicion] == null) {
            size++;
        }

        hashTable[posicion] = new HashNode<>(key, value);
    }

    public void reSize() {
        HashNode<K, T>[] oldHash = hashTable;
        capacity *= 2;
        hashTable = new HashNode[capacity];
        size = 0;

        for (HashNode<K, T> node : oldHash) {
            if (node != null) {
                put(node.getKey(), node.getData());
            }
        }
    }

    public HashNode<K, T> findNode(K key) {
        int hashCode = key.hashCode();
        int absHashCode = hashCode & Integer.MAX_VALUE; // Operación bit a bit para obtener el valor absoluto
        int posicion = absHashCode % capacity;
        int attempts = capacity;

        while (hashTable[posicion] != null && attempts > 0) {
            if (hashTable[posicion].getKey().equals(key)) {
                return hashTable[posicion];
            }
            posicion = (posicion + 1) % capacity;
            attempts--;
        }
        return null;
    }

    @Override
    public int find(K key) {
        int hashCode = key.hashCode();
        int absHashCode = hashCode & Integer.MAX_VALUE; // Operación bit a bit para obtener el valor absoluto
        int posicion = absHashCode % capacity;
        int attempts = capacity;

        while (hashTable[posicion] != null && attempts > 0) {
            if (hashTable[posicion].getKey().equals(key)) {
                return posicion;
            }
            posicion = (posicion + 1) % capacity;
            attempts--;
        }
        return -1;
    }

    @Override
    public void remove(K key) throws ElementNotFoundException {
        int position = find(key);
        if (position != -1) {
            hashTable[position] = null;
            size--;
        } else {
            throw new ElementNotFoundException();
        }
    }

    @Override
    public boolean contains(K key) {
        return find(key) != -1;
    }

    @Override
    public MyList<T> values(){
        MyList<T> ans = new MyLinkedListImpl<>();
        for(int i=0; i<hashTable.length; i++){
            if (hashTable[i].getData() != null){
                ans.add(hashTable[i].getData());
            }
        }
        return ans;
    }

    public HashNode<K, T>[] getHashTable() {
        return hashTable;
    }

    public void setHashTable(HashNode<K, T>[] hashTable) {
        this.hashTable = hashTable;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

