import uy.edu.um.adt.linkedlist.MyList;
import java.time.LocalDate;

public interface MySongStats {
    public MyList<SpotifySong> Top10(LocalDate fecha);

    public MyList<SpotifySong> Top5inTop50(LocalDate fecha);

    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin);

    public int OccurrenciesArtistinTop50(Artists artista);

    public int SongsbetweenTempoAndDate(int TempoMax, int TempoMin,LocalDate fechaInicio, LocalDate fechaFin);

}
