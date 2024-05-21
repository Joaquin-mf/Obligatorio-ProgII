import Entities.Artists;
import Entities.SpotifySong;
import uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;

public class MySongStatsImpl implements MySongStats{
    @Override
    public MyList<SpotifySong> Top10(LocalDate fecha, String Pais) {
        return null;
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
