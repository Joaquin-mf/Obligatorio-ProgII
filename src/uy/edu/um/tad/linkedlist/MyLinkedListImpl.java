package uy.edu.um.tad.linkedlist;


import uy.edu.um.tad.queue.EmptyQueueException;
import uy.edu.um.tad.queue.MyQueue;
import uy.edu.um.tad.stack.EmptyStackException;
import uy.edu.um.tad.stack.MyStack;

public class MyLinkedListImpl<T extends Comparable<T>> implements MyList<T>, MyQueue<T>, MyStack<T> {

    //@Getter
    private Node<T> first;

    private Node<T> last;

    public MyLinkedListImpl() {
        this.first = null;
        this.last = null;
    }


    @Override
    public void add(T value) {
        addToTheEnd(value);
    }

    private void addToBeginning(T value) {
        if (value != null) {

            Node<T> elementToAdd = new Node<>(value);

            if (this.first == null) { // si la lista es vacia

                this.first = elementToAdd;
                this.last = elementToAdd;

            } else { // en caso de no ser vacia se agrega al comienzo

                elementToAdd.setNext(this.first);
                this.first = elementToAdd;
            }

        } else {
            // si el elemento es vacio se ignora
        }
    }

    private void addToTheEnd(T value) {
        if (value != null) {

            Node<T> elementToAdd = new Node<>(value);

            if (this.first == null) { // si la lista es vacia

                this.first = elementToAdd;
                this.last = elementToAdd;

            } else { // en caso de no ser vacia se agrega al final

                this.last.setNext(elementToAdd);
                this.last = elementToAdd;
            }

        } else {
            // si el elemento es vacio se ignora
        }
    }

    public T get(int position) {
        T valueToReturn = null;
        int tempPosition = 0;
        Node<T> temp = this.first;

        // Se busca el nodo que corresponde con la posicion
        while (temp != null && tempPosition != position) {

            temp = temp.getNext();
            tempPosition++;

        }

        // si se encontro la posicion se retorna el valor
        // en caso que se haya llegado al final y no se llego a la posicion se retorna null
        if (tempPosition == position) {


            valueToReturn = temp.getValue();

        }

        return valueToReturn;
    }

    public boolean contains(T value) {
        boolean contains = false;
        Node<T> temp = this.first;

        while (temp != null && !temp.getValue().equals(value)) {

            temp = temp.getNext();

        }

        if (temp != null) { // Si no se llego al final de la lista, se encontro el valor

            contains = true;

        }

        return contains;
    }

    public void remove(T value) {
        Node<T> beforeSearchValue = null;
        Node<T> searchValue = this.first;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value)) {

            beforeSearchValue = searchValue;
            searchValue = searchValue.getNext();

        }

        if (searchValue != null) { // si encontre el elemento a eliminar

            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.first && searchValue != this.last) {

                Node<T> temp = this.first;
                this.first = this.first.getNext(); // salteo el primero

                temp.setNext(null); // quito referencia del elemento eliminado al siguiente.

                // Verifico si es el primer valor (caso borde) y no el primero
            } else if (searchValue == this.last && searchValue != this.first) {

                beforeSearchValue.setNext(null);
                this.last = beforeSearchValue;

                // Si es el primer valor y el ultimo (lista de un solo valor)
            } else if (searchValue == this.last && searchValue == this.first) {

                this.first = null;
                this.last = null;

            } else { // resto de los casos

                beforeSearchValue.setNext(searchValue.getNext());
                searchValue.setNext(null);

            }

        } else {

            // Si no es encuentra el valor a eliminar no se realiza nada

        }

    }

    private T removeLast() { // esta operación remueve el último elemento y retorna el elemento eliminado
        T valueToRemove = null;

        if (this.last != null) {
            valueToRemove = this.last.getValue();

            remove(valueToRemove);
        }

        return valueToRemove;
    }

    public int size() {
        int size = 0;

        Node<T> temp = this.first;

        while (temp != null) {

            temp = temp.getNext();
            size++;

        }

        return size;
    }

    // Operaciones particulares a Queue

    public void enqueue(T value) {
        addToBeginning(value);
    }

    public T dequeue() throws EmptyQueueException {
        if (this.last == null) { // si la queue esta vacia

            throw new EmptyQueueException();
        }

        return removeLast();
    }

    // Operaciones particulares a Stack

    public void push(T value) {
        addToTheEnd(value);
    }

    public T pop() throws EmptyStackException {
        if (this.last == null) { // si la pila esta vacia

            throw new EmptyStackException();
        }

        return removeLast();
    }

    public T peek() {
        T valueToReturn = null;

        if (this.last != null) {
            valueToReturn = this.last.getValue();
        }

        return valueToReturn;
    }

    public Node<T>  getFirst() {
        return first;
    }

    public boolean isEmpty() {
        return (this.first == null && this.last==null);
    }

    public void sort() {
        if (first == null || first.getNext() == null) {
            return; // No necesita ordenarse si la lista está vacía o tiene solo un elemento
        }
        first = mergeSort(first);
    }

    private Node<T> mergeSort(Node<T> head) {
        if (head == null || head.getNext() == null) {
            return head; // Caso base: si la lista está vacía o tiene solo un elemento
        }

        // Paso 1: Dividir la lista en dos mitades
        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.getNext();
        middle.setNext(null);

        // Paso 2: Ordenar recursivamente las dos mitades
        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(nextOfMiddle);

        // Paso 3: Mezclar las mitades ordenadas en orden descendente
        return sortedMerge(left, right);
    }

    private Node<T> sortedMerge(Node<T> left, Node<T> right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Node<T> result;
        if (left.getValue().compareTo(right.getValue()) >= 0) {
            result = left;
            result.setNext(sortedMerge(left.getNext(), right));
        } else {
            result = right;
            result.setNext(sortedMerge(left, right.getNext()));
        }
        return result;
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return head;
        }

        Node<T> slow = head;
        Node<T> fast = head.getNext();

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }
}
