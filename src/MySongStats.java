import Entities.Artists;
import Entities.SpotifySong;
import exceptions.ElementNotFoundException;
import exceptions.WrongOrder;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.linkedlist.MyList;
import java.time.LocalDate;
import java.util.List;

public interface MySongStats {
    public MyList<SpotifySong> Top10(LocalDate fecha, String Pais) throws ElementNotFoundException;

    public MyList<SpotifySong> Top5inTop50(LocalDate fecha) throws ElementNotFoundException;

    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin) throws WrongOrder;

    public int OccurrenciesArtistinTop50(String name, LocalDate fecha) throws ElementNotFoundException;

    public int SongsbetweenTempoAndDate(float TempoMax, float TempoMin, LocalDate fechaInicio, LocalDate fechaFin) throws WrongOrder;

}
