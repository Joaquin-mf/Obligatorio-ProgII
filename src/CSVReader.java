import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public MyList<SpotifySong> loadCSV(String fileName) { //PREGUNTAR
        MyList<SpotifySong> songs = new MyLinkedListImpl<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Leer la cabecera (primera línea) y descartarla
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Dividir la línea en valores separados por comas
                SpotifySong song = new SpotifySong(
                        values[0], // spotifyId
                        values[1], // name
                        values[2], // artists
                        Integer.parseInt(values[3]), // dailyRank
                        Integer.parseInt(values[4]), // dailyMovement
                        Integer.parseInt(values[5]), // weeklyMovement
                        values[6], // country
                        values[7], // snapshotDate
                        Integer.parseInt(values[8]), // popularity
                        Boolean.parseBoolean(values[9]), // isExplicit
                        Integer.parseInt(values[10]), // durationMs
                        values[11], // albumName
                        values[12], // albumReleaseDate
                        Double.parseDouble(values[13]), // danceability
                        Double.parseDouble(values[14]), // energy
                        Integer.parseInt(values[15]), // key
                        Double.parseDouble(values[16]), // loudness
                        Integer.parseInt(values[17]), // mode
                        Double.parseDouble(values[18]), // speechiness
                        Double.parseDouble(values[19]), // acousticness
                        Double.parseDouble(values[20]), // instrumentalness
                        Double.parseDouble(values[21]), // liveness
                        Double.parseDouble(values[22]), // valence
                        Double.parseDouble(values[23]), // tempo
                        Integer.parseInt(values[24]) // timeSignature
                );
                songs.add(song); // Añadir el objeto SongEntry a la lista
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejar errores de E/S
        }
        return songs; // Devolver la lista de canciones
    }
}

