package uy.edu.um.adt.hash;

import exceptions.ElementNotFoundException;

import java.util.Hashtable;

public class MyHashImpl<K,T> implements MyHash<K,T>{
    private HashNode<K,T>[] hashTable;
    private int size; //numero primo

    public MyHashImpl(int size) {
        this.hashTable = new HashNode[size];
        this.size = size;
    }

    @Override
    public void put(K key, T value) {
        int hashCode = key.hashCode();
        int absHashCode = hashCode & Integer.MAX_VALUE; // Operación bit a bit para obtener el valor absoluto
        int posicion = absHashCode % size;
        HashNode<K, T> newNode = new HashNode(key, value);

        // Manejo de colisiones mediante sondeo lineal
        while (hashTable[posicion] != null && !hashTable[posicion].getKey().equals(key)) {
            posicion = (posicion + 1) % size;
        }
        hashTable[posicion] = newNode;
    }

    public HashNode<K,T> findNode(K key) {
        int hashCode = key.hashCode();
        int absHashCode = hashCode & Integer.MAX_VALUE; // Operación bit a bit para obtener el valor absoluto
        int posicion = absHashCode % size;
        int attempts = size;

        while (hashTable[posicion] != null && attempts > 0) {
            if (hashTable[posicion].getKey().equals(key)) {
                return hashTable[posicion];
            }
            posicion = (posicion + 1) % size;
            attempts--;
        }
        return null;
    }


    @Override
    public int find(K key) {
        int hashCode = key.hashCode();
        int absHashCode = hashCode & Integer.MAX_VALUE; // Operación bit a bit para obtener el valor absoluto
        int posicion = absHashCode % size;
        if(this.contains(key)){
            while(hashTable[posicion] != null){
                if(hashTable[posicion].getKey().equals(key)){
                    return posicion;
                }
                posicion = (posicion + 1) % size;
            }
        }
        return -1;
    }

    @Override
    public void remove(K key) throws ElementNotFoundException {
        int position = this.find(key);
        if(position != -1) {
            this.hashTable[position] = null;
        }else{
            throw new ElementNotFoundException();
        }
    }

    @Override
    public boolean contains(K key) {
        int hashCode = key.hashCode();
        int absHashCode = hashCode & Integer.MAX_VALUE; // Operación bit a bit para obtener el valor absoluto
        int posicion = absHashCode % size;
        int attempts = size;
        while(hashTable[posicion] != null  && attempts > 0){
            if (hashTable[posicion].getKey().equals(key)) {
                return true;
            }
            posicion = (posicion + 1) % size;
            attempts --;
        }
        return false;
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
}
