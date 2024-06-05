import Entities.SpotifySong;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;

public class Pruebas {
    public static void main(String[] args) {


//        MySongStats pruebas = new MySongStatsImpl();
//        LocalDate fecha = LocalDate.of(2024,05,13);
//        pruebas.Top10(fecha,"ZA");

        MySongStatsImpl prueba = new MySongStatsImpl();

        LocalDate fecha = LocalDate.of(2024,05,13);
        LocalDate fecha2 = LocalDate.of(2024,05,2);

//        prueba.Top5inTop50(fecha2);
        prueba.OccurrenciesArtistinTop50("Post Malone", fecha);


//       prueba.Top10(fecha,"ZA");
//        int nro = prueba.OccurrenciesArtistinTop50("Tommy Richman",fecha);
//        System.out.println(nro);
//
//        long st = System.nanoTime();
//        MyList<SpotifySong> lista = prueba.Top10(fecha,"US");
//        long fin = System.nanoTime();
//
//        System.out.println((fin-st)/1000000000);
//        prueba.Top7inTop50(fecha2,fecha);

//        prueba.SongsbetweenTempoAndDate(100,101,fecha2,fecha);
    }
}