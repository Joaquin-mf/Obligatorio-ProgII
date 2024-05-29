import DataStructures.CSVReader;
import Entities.SpotifySong;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Pruebas {
    public static void main(String[] args) {

//        CSVReader clase = new CSVReader();
//        MyList<SpotifySong> lista = clase.CSVload();
//        System.out.println(lista.get(0).getArtists().get(0).getName());
//        MySongStats pruebas = new MySongStatsImpl();
//        LocalDate fecha = LocalDate.of(2024,05,13);
//        BinaryTree<SpotifySong> tpo10 = pruebas.Top10(fecha,"ZA");

        MySongStatsImpl prueba = new MySongStatsImpl();

        LocalDate fecha = LocalDate.of(2024,05,13);
//        int nro = prueba.OccurrenciesArtistinTop50("Tommy Richman",fecha);
//        System.out.println(nro);

        long st = System.nanoTime();
        MyList<SpotifySong> lista = prueba.Top10(fecha,"ZA");
        long fin = System.nanoTime();

        System.out.println((fin-st)/1000000000);
    }
}