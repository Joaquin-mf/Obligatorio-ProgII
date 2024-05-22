import org.junit.Test;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.queue.EmptyQueueException;
import uy.edu.um.tad.queue.MyQueue;

import static org.junit.Assert.*;

public class queueTest { //Enqueue, Dequeue, size, Contains
    @Test
    public void queueTestSize(){
        MyQueue<Integer> queue = new MyLinkedListImpl<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size()); //el tamaño coincide con la cantidad de elementos agregados en la queue
    }
    @Test
    public void queueTestEnqueueContains(){
        MyQueue<Integer> queue = new MyLinkedListImpl<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertTrue(queue.contains(3));
        //la queue contiene el elemento 3, lo que quiere decir que fue puesto en la queue
        //a la queue se le enqueue el elemento 3 y el contain da verdadero, se verifica que el contain funciona
    }

    @Test
    public void queueTestDequeue(){
        MyQueue<Integer> queue = new MyLinkedListImpl<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        try {
            assertEquals(Integer.valueOf(1), queue.dequeue());
        } catch (EmptyQueueException e) {
            throw new RuntimeException(e);
        }
    }


}

