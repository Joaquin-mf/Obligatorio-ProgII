import DataStructures.HashArtistDate;
import Entities.Artists;
import Entities.SpotifySong;
import DataStructures.CSVReader;
import DataStructures.HashDate;
import DataStructures.HashDateCountry;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.binarytree.SearchBinaryTreeImpl;
import uy.edu.um.tad.hash.HashNode;
import uy.edu.um.tad.hash.MyHash;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MySongStatsImpl implements MySongStats {
    private MyHash<String, MyList<SpotifySong>> hashDateCountry = new MyHashImpl<>(113);
    private MyHash<String, MyList<SpotifySong>> hashDate = new MyHashImpl<>(113);
    private MyHash<String, MyList<SpotifySong>> hashArtistDate = new MyHashImpl<>(113);


    public MySongStatsImpl() {
        String archivoCSV = "/Users/joaquinmartirena/Desktop/Obligatorio-ProgII/src/DatasetTEST.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            // Ignorar la primera línea
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                // Usa el separador para dividir la línea en columnas

                linea = linea.replaceAll(", ", "Ω");
                //Manejo de canciones problematicas
                linea = linea.replaceAll("Dear My Friend,", "Dear My Friend");
                linea = linea.replaceAll("Ya no me duele :,\\)", "Ya no me duele :)");
                linea = linea.replaceAll(",", "∆");
                //Manejo de canciones problematicas
                linea = linea.replaceAll("Ya no me duele :\\)", "Ya no me duele :,)");
                linea = linea.replaceAll("Dear My Friend", "Dear My Friend,");

                linea = linea.replaceAll("[\";]", "");
                String[] columnas = linea.split("∆");

                // Crear lista de artistas a partir de los datos
                MyList<Artists> artistas = new MyLinkedListImpl<>();
                String[] nombresArtistas = columnas[2].split("Ω"); // Suponiendo que los artistas están separados por coma
                for (String nombreArtista : nombresArtistas) {
                    artistas.add(new Artists(nombreArtista)); //RAROOOOO
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

                // Agrega la canción a las Estructuras
                String key1 = cancion.getSnapshotDate().toString() + "_" + cancion.getCountry();
                if (!hashDateCountry.contains(key1)) {
                    hashDateCountry.put(key1, new MyLinkedListImpl<>());
                }
                hashDateCountry.findNode(key1).getData().add(cancion);

                String key2 = cancion.getSnapshotDate().toString();
                if (!hashDate.contains(key2)) {
                    hashDate.put(key2, new MyLinkedListImpl<>());
                }
                hashDate.findNode(key2).getData().add(cancion);

                for (int i = 0; i < artistas.size(); i++) {
                    String key3 = cancion.getSnapshotDate().toString() + "_" + cancion.getArtists().get(i).getName();
                    if (!hashArtistDate.contains(key3)) {
                        hashArtistDate.put(key3, new MyLinkedListImpl<>());
                    }
                    hashArtistDate.findNode(key3).getData().add(cancion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public MyList<SpotifySong> Top10(LocalDate fecha, String Pais) {

        MyList<SpotifySong> songsList = hashDateCountry.findNode(fecha.toString() + "_" + Pais).getData();
        MyList<SpotifySong> lista = new MyLinkedListImpl<>();
        for (int i = 0; i <= 10; i++) {
            lista.add(songsList.get(i));
            System.out.println(songsList.get(i).getName() + "  " + songsList.get(i).getDailyRank());
        }
        return lista;
    }

    @Override
    public MyList<SpotifySong> Top5inTop50(LocalDate fecha) {

        return null;
    }


    @Override
    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin) {
//
//        //filtro segun el rango de fechas
//        MyList<SpotifySong> songs = new MyLinkedListImpl<>();
//        for(int i=0; i < mySongs.size(); i++){
//            if((!(mySongs.get(i).getSnapshotDate().isBefore(fechaInicio)) && mySongs.get(i).getSnapshotDate().isBefore(fechaFin)) && (mySongs.get(i).getDailyRank() <= 50)){
//                songs.add(mySongs.get(i));
//            }
//        }
//
//        MyHash<String,Artists> artistasCount = new MyHashImpl<>(113);
//        for(int i=0; i < songs.size(); i++) {
//            MyList<Artists> artist = songs.get(i).getArtists();
//
//            for (int k = 0; k < artist.size(); k++){
//                String name = artist.get(k).getName();
//                if (artistasCount.contains(name)) {
//                    int ocurrencias = artistasCount.findNode(name).getData().getRank();
//                    artistasCount.findNode(name).getData().setRank(ocurrencias + 1);
//                }else{
//                    artist.get(k).setRank(1);
//                    artistasCount.put(name,artist.get(k));
//                }
//            }
//        }
//        MyList<Artists> tabla = artistasCount.values();
//


        return null;
    }

    @Override
    public int OccurrenciesArtistinTop50(String name, LocalDate fecha) {

        MyList<SpotifySong> songs = hashArtistDate.findNode(fecha.toString() + "_" + name).getData();

// TESTEO DE FUNCION
//        MyList<SpotifySong> songs = new MyLinkedListImpl<>();
//
//        for(int i=0; i<mySongs.size(); i++){
//            SpotifySong song = mySongs.get(i);
//            if(song.getSnapshotDate().equals(fecha)){
//                for(int j=0; j<song.getArtists().size();j++){
//                    if(song.getArtists().get(j).getName().equals(name)){
//                        songs.add(song);
//                    }
//                }
//            }
//        }
//
//        System.out.println(songs.size());

        int contador = 0;
        for (int i = 0; i < songs.size(); i++) {
            SpotifySong song = songs.get(i);
            if (song.getDailyRank() <= 50) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public int SongsbetweenTempoAndDate(int TempoMax, int TempoMin, LocalDate fechaInicio, LocalDate fechaFin) {
        int contador = 0;
//        for(int i=0; i<mySongs.size(); i++){
//            SpotifySong song = mySongs.get(i);
//            if((song.getTempo()<TempoMax && song.getTempo()>TempoMin) && (song.getSnapshotDate().isBefore(fechaFin) && song.getSnapshotDate().isAfter(fechaFin))){
//                contador++;
//            }
//        }
        return contador;
    }


    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        while (flag == false) {
            System.out.println("Elija una funcion: \n1)Top 10 canciones en un país en tal día. \n2)Top 5 canciones que aparecen en más top 50 en un día dado. \n3)Top 7 artistas que más aparecen en el top 50 para un rango de fechas dado. \n4)cantidad de veces que aparece un artista en el top 50 en una fecha dada. \n5)cantidad de canciones con un tempo en un rango especifico para un rango especifico de fechas");
            int numero = scanner.nextInt();
            if (numero==1){
                System.out.println("ingrese el nombre del pais");
                String NombrePais = scanner.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato dd/MM/yyyy: ");
                    String fechaStr = scanner.nextLine();
                    try {
                        fecha = LocalDate.parse(fechaStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                Top10(fecha,NombrePais);
                flag = true;
            }else if(numero==2){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato dd/MM/yyyy: ");
                    String fechaStr = scanner.nextLine();
                    try {
                        fecha = LocalDate.parse(fechaStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                Top5inTop50(fecha);
                flag = true;
            }else if(numero==3){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaInicio = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato dd/MM/yyyy: ");
                    String fechaInicioStr = scanner.nextLine();
                    try {
                        fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                LocalDate fechaFin = null;
                boolean fechaValida2 = false;
                while (!fechaValida2) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato dd/MM/yyyy: ");
                    String fechaFinStr = scanner.nextLine();
                    try {
                        fechaFin = LocalDate.parse(fechaFinStr, formatter);
                        fechaValida2 = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                Top7inTop50(fechaInicio,fechaFin);
                flag = true;
            }else if(numero==4){
                System.out.println("ingrese el nombre del artista");
                String NombreArtista = scanner.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato dd/MM/yyyy: ");
                    String fechaStr = scanner.nextLine();
                    try {
                        fecha = LocalDate.parse(fechaStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                OccurrenciesArtistinTop50(NombreArtista,fecha);
                flag = true;
            }else if(numero==5){
                System.out.println("ingrese el tiempo máximo");
                int TempoMax = scanner.nextInt();
                System.out.println("ingrese el tiempo mínimo");
                int TempoMin = scanner.nextInt();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaInicio = null;
                boolean fechaValida = false;
                while (!fechaValida) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato dd/MM/yyyy: ");
                    String fechaInicioStr = scanner.nextLine();
                    try {
                        fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                LocalDate fechaFin = null;
                boolean fechaValida2 = false;
                while (!fechaValida2) {
                    System.out.print("Por favor, ingresa una fecha de Inicio en formato dd/MM/yyyy: ");
                    String fechaFinStr = scanner.nextLine();
                    try {
                        fechaFin = LocalDate.parse(fechaFinStr, formatter);
                        fechaValida2 = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: formato de fecha inválido. Por favor, intenta nuevamente.");
                    }
                }
                SongsbetweenTempoAndDate(TempoMax, TempoMin, fechaInicio, fechaFin);
                flag = true;
            }else{
                System.out.println("elija un numero entre 1 y 5");
            }
        }
        scanner.close();
    }

}
