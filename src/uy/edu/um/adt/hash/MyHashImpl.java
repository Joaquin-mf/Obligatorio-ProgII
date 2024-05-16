package uy.edu.um.adt.hash;

public class MyHashImpl<K,T> implements MyHash<K,T>{
    private HashNode[] hashTable;
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
        if(hashTable[posicion] != null){
            if(posicion == size){
                posicion = 0;
            }else{
                posicion++;
            }
        }
        hashTable[posicion] = newNode;
    }

    @Override
    public T find(K key) {

        return null;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public boolean contains(K key) {
        return false;
    }
}
