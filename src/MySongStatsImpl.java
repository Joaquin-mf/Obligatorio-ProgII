import Entities.Artists;
import Entities.SpotifySong;
import StorageData.HashDateCountry;
import uy.edu.um.tad.hash.MyHash;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;

public class MySongStatsImpl implements MySongStats{
    @Override
    public MyList<SpotifySong> Top10(LocalDate fecha, String Pais) {
        HashDateCountry clase = new HashDateCountry();
        MyHash<String,MyList<SpotifySong>> myHash = clase.MyHashDateCountry();
        MyList<SpotifySong> songsList = myHash.findNode(fecha.toString()+"_"+Pais).getData();
        MyList<SpotifySong> Top10 = null;
        int i=0;
        int cont=0;
        while (i < songsList.size() && cont<11){
            if (songsList.get(i).getDailyRank() < 11){
                Top10.add(songsList.get(i));
                cont++;
            }
            i++;
        }
        return Top10;
    }

    @Override
    public MyList<SpotifySong> Top5inTop50(String fecha) {
        return null;
    }

    @Override
    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }

    @Override
    public int OccurrenciesArtistinTop50(Artists artista) {
        return 0;
    }

    @Override
    public int SongsbetweenTempoAndDate(int TempoMax, int TempoMin, LocalDate fechaInicio, LocalDate fechaFin) {
        return 0;
    }
}
