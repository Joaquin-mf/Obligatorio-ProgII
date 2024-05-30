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
                linea = linea.replaceAll("Ω", ", ");
                //Manejo de canciones problematicas
                linea = linea.replaceAll("Ya no me duele :\\)", "Ya no me duele :,)");
                linea = linea.replaceAll("Dear My Friend", "Dear My Friend,");

                linea = linea.replaceAll("[\";]", "");
                String[] columnas = linea.split("∆");

                // Crear lista de artistas a partir de los datos
                MyList<Artists> artistas = new MyLinkedListImpl<>();
                String[] nombresArtistas = columnas[2].split(", "); // Suponiendo que los artistas están separados por coma
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
        System.out.println("El top 10 del "+fecha.toString()+" en "+Pais+":\n");
        for (int i = 0; i <= 9; i++) {
            lista.add(songsList.get(i));
            System.out.println(songsList.get(i).getName() + " tiene el rank: " + songsList.get(i).getDailyRank());
        }
        return lista;
    }

    @Override
    public MyList<SpotifySong> Top5inTop50(LocalDate fecha) {

        return null;
    }


    @Override
    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDate current = fechaFin;
        MyHash<String,Artists> artists = new MyHashImpl<>(1113);

        //manejo del rango de fechas
        while(!current.equals(fechaFin.plusDays(1))){
            MyList<SpotifySong> listaHash = hashDate.findNode(current.toString()).getData();
            for(int i=0; i<listaHash.size();i++){
                MyList<Artists> listaArtista = listaHash.get(i).getArtists();
                for(int j=0; j<listaArtista.size();j++){
                    Artists artist = listaArtista.get(j);
                    if(!artists.contains(artist.getName())){
                        artist.setRank(1);
                        artists.put(artist.getName(),artist);
                    }
                    Artists var = artists.findNode(artist.getName()).getData();
                    int ocurrencia = var.getRank();
                    var.setRank(ocurrencia+1);
                }
            }
            current = current.plusDays(1);
        }
        MyList<Artists> lista = new MyLinkedListImpl<>();
        artists.values(lista).sort();
//        for(int i=0; i< lista.size(); i++){
//            System.out.println(lista.get(i).getName()+" ___ "+lista.get(i).getRank());

        for(int i=0; i<7;i++) {
            System.out.println(lista.get(i).getName() + " con " + lista.get(i).getRank() + " ocurrencias");
        }
        return lista;
    }

    @Override
    public int OccurrenciesArtistinTop50(String name, LocalDate fecha) {
        MyList<SpotifySong> songs = hashArtistDate.findNode(fecha.toString() + "_" + name).getData();
        return songs.size();
    }

    @Override
    public int SongsbetweenTempoAndDate(int TempoMax, int TempoMin, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDate current = fechaFin;
        MyList<SpotifySong> lista = new MyLinkedListImpl<>();
        while(!current.equals(fechaFin.plusDays(1))){
            MyList<SpotifySong> listaSongs = hashDate.findNode(current.toString()).getData();
            for(int i=0; i<listaSongs.size();i++){
                if(listaSongs.get(i).getTempo()<TempoMax && listaSongs.get(i).getTempo()<TempoMin){
                    lista.add(listaSongs.get(i));
                }
            }
        }
        return lista.size();
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
