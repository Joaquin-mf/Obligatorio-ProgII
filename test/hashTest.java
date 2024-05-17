import org.junit.Test;
import uy.edu.um.adt.hash.MyHashImpl;

import static org.junit.Assert.assertEquals;

public class hashTest {
    @Test
    public void hashTest(){
        MyHashImpl<Integer,String> tablaHash = new MyHashImpl<>(13);
        tablaHash.put(13,"Joaquin");
        tablaHash.put(26,"Maria");
        tablaHash.put(43,"Sofia");
        tablaHash.put(21,"Lucia");

        assertEquals(Integer.valueOf(13),tablaHash.getHashTable()[0].getKey());
        assertEquals(Integer.valueOf(26),tablaHash.getHashTable()[1].getKey());
    }

}
