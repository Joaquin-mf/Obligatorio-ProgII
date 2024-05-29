import Entities.Artists;
import Entities.SpotifySong;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.linkedlist.MyList;
import java.time.LocalDate;
import java.util.List;

public interface MySongStats {
    public List<SpotifySong> Top10(LocalDate fecha, String Pais);

    public MyList<SpotifySong> Top5inTop50(LocalDate fecha);

    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin);

    public int OccurrenciesArtistinTop50(String name, LocalDate fecha);

    public int SongsbetweenTempoAndDate(int TempoMax, int TempoMin, LocalDate fechaInicio, LocalDate fechaFin);

}
