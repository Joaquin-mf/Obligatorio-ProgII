import com.sun.jdi.IntegerValue;
import org.junit.Test;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.stack.EmptyStackException;
import uy.edu.um.adt.stack.MyStack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class stackTest {  // Push,Pop,Peek,size,get


    @Test
    public void stackTestSize(){
        MyStack<Integer> stack = new MyLinkedListImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4, stack.size());
    }

    @Test
    public void stackTestPush(){
        MyStack<Integer> stack = new MyLinkedListImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(Integer.valueOf(4), stack.peek());
    }

    @Test
    public void stackTestPop(){
        MyStack<Integer> stack = new MyLinkedListImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        try {
            assertEquals(Integer.valueOf(4), stack.pop());
        } catch (EmptyStackException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void stackTestPeek1(){
        MyStack<Integer> stack = new MyLinkedListImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(Integer.valueOf(4), stack.peek());
    }
    @Test
    public void stackTestPeek2(){
        MyStack<Integer> stack = new MyLinkedListImpl<>();
        assertNull(stack.peek());
    }

    @Test
    public void stackTestGet(){
        MyStack<Integer> stack = new MyLinkedListImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(Integer.valueOf(1), stack.get(0));
    }
}

