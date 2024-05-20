import org.junit.Assert;
import org.junit.Test;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;
import uy.edu.um.adt.queue.EmptyQueueException;
import uy.edu.um.adt.queue.MyQueue;

import static org.junit.Assert.*;

public class linkedListTest { //Add, Size, Contains, Get, Remove
    @Test
    public void linkedlistTestsSize() {
        MyList<Integer> lista = new MyLinkedListImpl<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        assertEquals(4, lista.size());
    }
    @Test
    public void linkedlistTestsAdd() {
        MyList<Integer> lista = new MyLinkedListImpl<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        assertTrue(lista.contains(3));
        assertEquals(Integer.valueOf(3),lista.get(2));

    }

    @Test
    public void linkedlistTestsGet() {
        MyList<Integer> lista = new MyLinkedListImpl<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        assertEquals(Integer.valueOf(3),lista.get(2));
    }
    @Test
    public void linkedlistTestsContains() {
        MyList<Integer> lista = new MyLinkedListImpl<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        assertTrue(lista.contains(3));
        lista.remove(3);
        assertFalse(lista.contains(3));
    }
    @Test
    public void linkedlistTestsRemove() {
        MyList<Integer> lista = new MyLinkedListImpl<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.remove(3);
        assertFalse(lista.contains(3));

    }

}
