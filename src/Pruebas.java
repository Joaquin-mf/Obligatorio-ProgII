import DataStructures.CSVReader;
import Entities.SpotifySong;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;

public class Pruebas {
    public static void main(String[] args) {

//        CSVReader clase = new CSVReader();
//        MyList<SpotifySong> lista = clase.CSVload();
//        System.out.println(lista.get(0).getArtists().get(0).getName());
//        MySongStats pruebas = new MySongStatsImpl();
//        LocalDate fecha = LocalDate.of(2024,05,13);
//        BinaryTree<SpotifySong> tpo10 = pruebas.Top10(fecha,"ZA");

        MySongStatsImpl prueba = new MySongStatsImpl();
        System.out.println(prueba.getMySongs().get(108).getArtists().get(0).getName());

    }
}