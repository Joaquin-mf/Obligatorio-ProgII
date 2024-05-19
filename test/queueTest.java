import org.junit.Test;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.queue.EmptyQueueException;
import uy.edu.um.adt.queue.MyQueue;
import uy.edu.um.adt.stack.MyStack;

import static org.junit.Assert.assertEquals;

public class queueTest { //Enqueue, Dequeue, size
    @Test
    public void queueTest(){
        MyQueue<Integer> queue = new MyLinkedListImpl<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(3, queue.size());
        try {
            assertEquals(Integer.valueOf(1), queue.dequeue());
        } catch (EmptyQueueException e) {
            throw new RuntimeException(e);
        }


    }


}
