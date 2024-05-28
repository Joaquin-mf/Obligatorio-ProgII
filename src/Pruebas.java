import Entities.SpotifySong;
import StorageData.CSVReader;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;
import java.util.List;

public class Pruebas {
    public static void main(String[] args) {

//        CSVReader clase = new CSVReader();
//        MyList<SpotifySong> lista = clase.CSVload();
//        System.out.println(lista.get(0).getArtists().get(0).getName());
        MySongStats pruebas = new MySongStatsImpl();
        LocalDate fecha = LocalDate.of(2024,05,13);
        MyList<SpotifySong> tpo10 = pruebas.Top10(fecha,"ZA");
        System.out.println(tpo10.size());


    }
}