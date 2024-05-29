package DataStructures;

import Entities.Artists;
import Entities.SpotifySong;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class CSVReader {
    public MyList<SpotifySong> CSVload() {
        String archivoCSV = "/Users/joaquinmartirena/Desktop/Obligatorio-ProgII/src/DatasetTEST.csv";


        // Lista para almacenar todas las canciones del CSV
        MyList<SpotifySong> datosCSV = new MyLinkedListImpl<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            // Ignorar la primera línea
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                // Usa el separador para dividir la línea en columnas

                linea = linea.replaceAll(", ","Ω");
                //Manejo de canciones problematicas
                linea = linea.replaceAll("Dear My Friend,","Dear My Friend∂");
                linea = linea.replaceAll("Ya no me duele :,\\)","Ya no me duele :)");
                linea = linea.replaceAll(",", "∆");
                //Manejo de canciones problematicas
                linea = linea.replaceAll("Ya no me duele :\\)","Ya no me duele :,)");
                linea = linea.replaceAll("Dear My Friend∂","Dear My Friend,");

                linea = linea.replaceAll("[\";]","");
                String[] columnas = linea.split("∆");

                // Crear lista de artistas a partir de los datos
                MyList<Artists> artistas = new MyLinkedListImpl<>();
                String[] nombresArtistas = columnas[2].split("Ω"); // Suponiendo que los artistas están separados por coma
                for (String nombreArtista : nombresArtistas) {
                    artistas.add(new Artists(nombreArtista));
                }

                // Crea un objeto SpotifySong con los datos de la línea
                SpotifySong cancion = new SpotifySong(
                        columnas[0], // SpotifyId
                        columnas[1], // Name
                        artistas, // Lista de artistas
                        Integer.parseInt(columnas[3]), // DailyRank
                        Integer.parseInt(columnas[4]), // DailyMovement
                        Integer.parseInt(columnas[5]), // WeeklyMovement
                        columnas[6], // Country
                        LocalDate.parse(columnas[7]), // SnapshotDate
                        Integer.parseInt(columnas[8]), // Popularity
                        Boolean.parseBoolean(columnas[9]), // IsExplicit
                        Integer.parseInt(columnas[10]), // DurationMs
                        columnas[11], // AlbumName
                        columnas[12], // AlbumReleaseDate
                        Double.parseDouble(columnas[13]), // Danceability
                        Double.parseDouble(columnas[14]), // Energy
                        Integer.parseInt(columnas[15]), // Key
                        Double.parseDouble(columnas[16]), // Loudness
                        Integer.parseInt(columnas[17]), // Mode
                        Double.parseDouble(columnas[18]), // Speechiness
                        Double.parseDouble(columnas[19]), // Acousticness
                        Double.parseDouble(columnas[20]), // Instrumentalness
                        Double.parseDouble(columnas[21]), // Liveness
                        Double.parseDouble(columnas[22]), // Valence
                        Double.parseDouble(columnas[23]), // Tempo
                        Integer.parseInt(columnas[24]) // TimeSignature
                );

                // Agrega la canción a la lista principal
                datosCSV.add(cancion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datosCSV;
    }
}


