import exceptions.ElementNotFoundException;
import exceptions.reSize;
import org.junit.Test;
import org.junit.Before;
import uy.edu.um.tad.hash.MyHashImpl;

import static org.junit.Assert.*;

public class hashTest { // put, find, remove, contains, findNode
    MyHashImpl<Integer,String> tablaHash;
    @Before
    public void base(){
        tablaHash = new MyHashImpl<>(13);
        tablaHash.put(13,"Joaquin");
        tablaHash.put(26,"Maria");
        tablaHash.put(43,"Sofia");
        tablaHash.put(21,"Lucia");
    }

    @Test
    public void TestPutFind() throws reSize {
        assertEquals(1,tablaHash.find(26));
    }
    @Test
    public void TestRemove() throws reSize {
        try {
            tablaHash.remove(13);
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(-1,tablaHash.find(13));
    }
    @Test
    public void TestContainsTrue() throws reSize {
        assertTrue(tablaHash.contains(26));
    }
    @Test
    public void TestContainsFalse() throws reSize {
        assertFalse(tablaHash.contains(23));
    }
    @Test
    public void TestFindNode() throws reSize {

    }
}

