import uy.edu.um.adt.linkedlist.MyList;
import java.time.LocalDate;

public interface MySongStats {
    public MyList<SpotifySong> Top10(String fecha, String Pais);

    public MyList<SpotifySong> Top5inTop50(String fecha);

    public MyList<Artists> Top7inTop50(String fechaInicio, String fechaFin);

    public int OccurrenciesArtistinTop50(Artists artista);

    public int SongsbetweenTempoAndDate(int TempoMax, int TempoMin, String fechaInicio, String fechaFin);

}
