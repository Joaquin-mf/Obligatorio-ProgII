import org.junit.Before;
import org.junit.Test;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;
import uy.edu.um.tad.queue.MyQueue;

import static org.junit.Assert.*;

public class linkedListTest { //Add, Size, Contains, Get, Remove,getFirst,isEmpty
    MyList<Integer> lista;
    @Before
    public void base(){
        lista = new MyLinkedListImpl<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
    }

    @Test
    public void linkedlistTestsSize() {
        assertEquals(4, lista.size());
    }
    @Test
    public void linkedlistTestsAdd() {
        assertTrue(lista.contains(3));
        assertEquals(Integer.valueOf(3),lista.get(2));
    }

    @Test
    public void linkedlistTestsGet() {
        assertEquals(Integer.valueOf(3),lista.get(2));
    }
    @Test
    public void linkedlistTestsContainsTrue() {
        assertTrue(lista.contains(3));
        lista.remove(3);
        assertFalse(lista.contains(3));
    }
    @Test
    public void linkedlistTestsContainsFalse() {
        lista.remove(3);
        assertFalse(lista.contains(3));
    }
    @Test
    public void linkedlistTestsRemoveTrue() {
        lista.remove(3);
        assertFalse(lista.contains(3));
    }
    @Test
    public void linkedlistTestsRemoveFalse() {
        lista.remove(78); // esta removiendo cosas que no están en la  lista
    }
    @Test
    public void linkedlistTestsGetFirst(){
        assertEquals(lista.get(0),lista.getFirst().getValue()); //creo que agarra el last
    }
    @Test
    public void linkedlistTestsNotIsEmpty(){
        assertFalse(lista.isEmpty());
    }

    @Test
    public void linkedlistTestsIsEmpty(){
        MyList<Integer> listaVacia = new MyLinkedListImpl<>();
        assertTrue(listaVacia.isEmpty());
    }


}
