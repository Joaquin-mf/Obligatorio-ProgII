import org.junit.Before;
import org.junit.Test;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.binarytree.SearchBinaryTreeImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

import static org.junit.Assert.*;


public class binaryTreeTest { //getRoot, getLeft, getRight, getKey, add, remove, contains, find, inOrder, PostOrder, PreOrder
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



    }
