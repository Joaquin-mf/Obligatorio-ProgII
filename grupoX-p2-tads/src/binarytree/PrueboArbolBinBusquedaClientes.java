package binarytree;

import uy.edu.um.adt.linkedlist.MyList;

//import uy.edu.um.adt.binarytree.MySearchBinaryTree;
//import uy.edu.um.adt.binarytree.MySearchBinaryTreeImpl;
//import uy.edu.um.adt.linkedlist.MyList;

class PrueboArbolBinBusquedaClientes {
  public static void main (String [] args) {
      MySearchBinaryTreeImpl<Integer,Cliente> oTree = new MySearchBinaryTreeImpl<>();

      Cliente cli1=new Cliente("pepe",30);
      oTree.add(30,cli1);
      System.out.println("Se agrego " + cli1.getNombre());

      Cliente cli2=new Cliente("maria",60);
      oTree.add(60,cli2);
      System.out.println("Se agrego " + cli2.getNombre());

      Cliente cli3=new Cliente("rodrigo",20);
      oTree.add(20,cli3);
      System.out.println("Se agrego " + cli3.getNombre());

      Cliente cli4=new Cliente("teresa",50);
      oTree.add(50,cli4);
      System.out.println("Se agrego " + cli4.getNombre());

      Cliente cli5=new Cliente("pedro",70);
      oTree.add(70,cli5);
      System.out.println("Se agrego " + cli5.getNombre());

      System.out.println("Verifico si existe clave 50");
      Cliente busco=oTree.find(50);
      System.out.println("Encontré  cliente " + busco.getNombre());



      MyList<Integer> ls = oTree.inOrder();

      System.out.println("Listado enOrden:");
      ls.recorro_recu(ls.getPrimero());
      System.out.println("Fin Lista");

      ///Creo el arbol con los nombres como clave
      MySearchBinaryTreeImpl<String,Cliente> oTree2 = new MySearchBinaryTreeImpl<>();

      Cliente cli10=new Cliente("pepe",30);
      oTree2.add("pepe",cli10);
      System.out.println("Se agrego " + cli1.getNombre());

      Cliente cli11=new Cliente("maria",60);
      oTree2.add("maria",cli11);
      System.out.println("Se agrego " + cli2.getNombre());

      Cliente cli12=new Cliente("rodrigo",20);
      oTree2.add("rodrigo",cli12);
      System.out.println("Se agrego " + cli3.getNombre());

      System.out.println("****************************************");
      MyList<String> ls2 = oTree2.inOrder();
      System.out.println("Listado enOrden del segundo arbol:");
      ls2.recorro_recu(ls2.getPrimero());
      System.out.println("Fin EnOrden");

      System.out.println("****************************************");

      MyList<String> ls3 = oTree2.postOrder();
      System.out.println("Listado postOrden del segundo arbol:");
      ls2.recorro_recu(ls3.getPrimero());
      System.out.println("Fin PostOrden");

      System.out.println("****************************************");

      MyList<String> ls4 = oTree2.preOrder();
      System.out.println("Listado preOrden del segundo arbol:");
      ls2.recorro_recu(ls4.getPrimero());
      System.out.println("Fin PreOrden");

  }
}
