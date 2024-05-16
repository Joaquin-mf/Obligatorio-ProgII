import org.junit.Test;
import uy.edu.um.adt.binarytree.MySearchBinaryTree;
import uy.edu.um.adt.binarytree.MySearchBinaryTreeImpl;

import static org.junit.Assert.assertEquals;

public class binaryTreeTest {
    @Test
    public void tests(){
        MySearchBinaryTree<Integer,String> arbol = new MySearchBinaryTreeImpl<Integer, String>();
        arbol.add(15,"primero");
        arbol.add(10,"segundo");
        arbol.add(30,"tercero");
        arbol.add(13,"cuarto");
        arbol.add(5,"quinto");
        arbol.add(13,"sexto");
        arbol.add(20,"septimo");

        assertEquals((Integer) 15, arbol.getRoot().getKey());
        assertEquals((Integer) 5, arbol.getRoot().getLeft().getLeft().getKey());
        assertEquals((Integer) 20, arbol.getRoot().getRight().getLeft().getKey());

        arbol.remove(30);


    }
}
