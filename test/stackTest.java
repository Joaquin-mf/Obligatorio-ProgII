import org.junit.Before;
import org.junit.Test;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.stack.EmptyStackException;
import uy.edu.um.tad.stack.MyStack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class stackTest {  // Push,Pop,Peek,size,get

    MyStack<Integer> stack;
    @Before
    public void base(){
        stack = new MyLinkedListImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
    }
    @Test
    public void stackTestSize(){
        assertEquals(4, stack.size());
    }

    @Test
    public void stackTestPush(){
        assertEquals(Integer.valueOf(4), stack.peek());
    }

    @Test
    public void stackTestPop(){
        try {
            assertEquals(Integer.valueOf(4), stack.pop());
        } catch (EmptyStackException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void stackTestPeek1(){
        assertEquals(Integer.valueOf(4), stack.peek());
    }
    @Test
    public void stackTestPeek2(){
        MyStack<Integer> stackVacio = new MyLinkedListImpl<>();
        assertNull(stackVacio.peek());
    }

}

