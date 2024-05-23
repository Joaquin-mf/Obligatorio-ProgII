import org.junit.Before;
import org.junit.Test;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.binarytree.SearchBinaryTreeImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

import static org.junit.Assert.*;


public class binaryTreeTest { // add, remove, contains, find, inOrder, PostOrder, PreOrder
    BinaryTree<Integer> ombu ;
    @Before
    public void base(){
        ombu = new SearchBinaryTreeImpl<>();
        ombu.add(30);
        ombu.add(15);
        ombu.add(11);
        ombu.add(20);
        ombu.add(40);
        ombu.add(35);
        ombu.add(45);
        }

    @Test
    public void TestAddContains(){
        assertTrue(ombu.contains(30));
        //el binary tree contiene el elemento 30, lo que quiere decir que fue agregado al binary tree
        //al arbol se le agrego el elemento 30 y el contain da verdadero, se verifica que el contain funciona
    }
    @Test
    public void TestRemove(){
        ombu.remove(15);
        assertFalse(ombu.contains(15));
    }
    @Test
    public void TestFindFalse(){
        assertNull(ombu.find(3));
    }
    @Test
    public void TestFindTrue(){
        assertNotNull(ombu.find(30));
    }
    @Test
    public void TestPreOrder(){
        Integer[] PreOManual = {30,15,11,20,40,35,45};
        Integer[] PreOMetodo = ombu.preOrder().toArray(new Integer[0]);
        assertArrayEquals(PreOManual,PreOMetodo);
    }

    @Test
    public void TestPosOrder(){ //agarra mal el elemento de la posicion [0] del array (expected in [0] 15, actual 11)
        Integer[] PosOManual = {11,20,15,35,45,40,30};
        Integer[] PosOMetodo = ombu.posOrder().toArray(new Integer[0]);
        assertArrayEquals(PosOManual,PosOMetodo);
    }
    @Test
    public void TestInOrder(){
        Integer[] InOManual = {11,15,20,30,35,40,45};
        Integer[] InOMetodo = ombu.inOrder().toArray(new Integer[0]);
        assertArrayEquals(InOManual,InOMetodo);
    }



    }
