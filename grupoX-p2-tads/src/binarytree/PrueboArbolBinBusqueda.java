package binarytree;

import uy.edu.um.adt.linkedlist.*;

//import uy.edu.um.adt.binarytree.MySearchBinaryTree;
//import uy.edu.um.adt.binarytree.MySearchBinaryTreeImpl;
//import uy.edu.um.adt.linkedlist.MyList;

class  PrueboArbolBinBusqueda {
  public static void main (String [] args) {
      MySearchBinaryTreeImpl<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();

      oTree.add(3, 3);
      System.out.println("Se agrego 3");
      oTree.add(21, 21);
      System.out.println("Se agrego 21");
      oTree.add(11, 11);
      System.out.println("Se agrego 11");
      oTree.add(-1, -1);
      System.out.println("Se agrego -1");
      oTree.add(4, 4);
      System.out.println("Se agrego 4");
      oTree.add(18, 18);
      System.out.println("Se agrego 18");

      MyList<Integer> ls = oTree.inOrder();

      System.out.println("Listado enOrden:");
      ls.recorro_recu(ls.getPrimero());
      System.out.println("Fin Lista");

  }
}
