import Entities.Artists;
import Entities.SpotifySong;
import exceptions.ElementNotFoundException;
import exceptions.WrongOrder;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.binarytree.SearchBinaryTreeImpl;
import uy.edu.um.tad.hash.HashNode;
import uy.edu.um.tad.hash.MyHash;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.heap.MyHeap;
import uy.edu.um.tad.heap.MyHeapImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;
import uy.edu.um.tad.ntree.TreeImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class MySongStatsImpl implements MySongStats {
    private MyHash<String, MyList<SpotifySong>> hashDateCountry = new MyHashImpl<>(113);
    private MyHash<String, MyList<SpotifySong>> hashDate = new MyHashImpl<>(113);
    private MyHash<String, MyList<SpotifySong>> hashArtistDate = new MyHashImpl<>(113);


    public MySongStatsImpl() {
        String archivoCSV = "/Users/joaquinmartirena/Desktop/Obligatorio-ProgII/Dataset copy.csv";

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
                        Double.parseDouble(columnas[23]) // Tempo
                );

                // Agrega la canción a las Estructuras
                String key1 = cancion.getSnapshotDate().toString() + "_" + cancion.getCountry();
                if (!hashDateCountry.contains(key1)) {
                    hashDateCountry.put(key1, new MyLinkedListImpl<>());
                }
                hashDateCountry.findData(key1).add(cancion);

                String key2 = cancion.getSnapshotDate().toString();
                if (!hashDate.contains(key2)) {
                    hashDate.put(key2, new MyLinkedListImpl<>());
                }
                hashDate.findData(key2).add(cancion);

                for (int i = 0; i < artistas.size(); i++) {
                    String key3 = cancion.getSnapshotDate().toString() + "_" + cancion.getArtists().get(i).getName();
                    if (!hashArtistDate.contains(key3)) {
                        hashArtistDate.put(key3, new MyLinkedListImpl<>());
                    }
                    hashArtistDate.findData(key3).add(cancion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyList<SpotifySong> Top10(LocalDate fecha, String Pais) throws ElementNotFoundException {
        MyList<SpotifySong> songsList = hashDateCountry.findData(fecha.toString() + "_" + Pais);
        if(songsList == null){
            throw new ElementNotFoundException();
        }
        MyList<SpotifySong> lista = new MyLinkedListImpl<>();
        System.out.println("El top 10 del "+fecha.toString()+" en "+Pais+":\n");
        for (int i = 0; i <= 9; i++) {
            lista.add(songsList.get(i));
            System.out.println((i+1)+". "+songsList.get(i).getName()+ " tiene el rank: " + songsList.get(i).getDailyRank());
        }
        return lista;
    }

    @Override
    public MyList<SpotifySong> Top5inTop50(LocalDate fecha) throws ElementNotFoundException {
        MyList<SpotifySong> lista = hashDate.findData(fecha.toString());
        if(lista==null){
            throw new ElementNotFoundException();
        }
        MyHashImpl<String,SpotifySong> hashSong = new MyHashImpl<>(113);

        for(int i=0; i < lista.size(); i++){
            SpotifySong song = lista.get(i);
            if(!hashSong.contains(song.getName())){
                song.setCounter(1);
                hashSong.put(song.getName(),song);
            }
            SpotifySong cancion = hashSong.findData(song.getName());
            cancion.setCounter(cancion.getCounter() + 1);
        }

        MyHeap<SpotifySong> heap = new MyHeapImpl<>(5, true);
        for (int i=0; i<hashSong.getHashTable().length;i++){
            if(hashSong.getHashTable()[i] != null){
                heap.insert(hashSong.getHashTable()[i].getData());
            }
        }

        System.out.println("___Top 5 en el top 50___" + fecha.toString());
        MyList<SpotifySong> ans = new MyLinkedListImpl<>();
        for(int i=0; i<=4; i++){
            ans.add(heap.get());
            System.out.println((i+1)+". "+heap.delete().getName()+" "+ heap.delete().getCounter());
        }
        return ans;
    }

    @Override
    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin) throws WrongOrder {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new WrongOrder();
        }

        LocalDate current = fechaFin;
        MyHashImpl<String,Artists> artists = new MyHashImpl<>(1113);

        //manejo del rango de fechas
        while(!current.equals(fechaFin.plusDays(1))){
            MyList<SpotifySong> listaHash = hashDate.findData(current.toString());
            for(int i=0; i<listaHash.size();i++){
                MyList<Artists> listaArtista = listaHash.get(i).getArtists();
                for(int j=0; j<listaArtista.size();j++){
                    Artists artist = listaArtista.get(j);
                    if(!artists.contains(artist.getName())){
                        artist.setRank(1);
                        artists.put(artist.getName(),artist);
                    }
                    Artists var = artists.findData(artist.getName());
                    int ocurrencia = var.getRank();
                    var.setRank(ocurrencia+1);
                }
            }
            current = current.plusDays(1);
        }
        MyList<Artists> lista = new MyLinkedListImpl<>();
        for(int i=0; i<artists.getHashTable().length; i++){
            if(artists.getHashTable()[i] != null){
                lista.add(artists.getHashTable()[i].getData());
            }
        }
        lista.sort();
//        for(int i=0; i< lista.size(); i++){
//            System.out.println(lista.get(i).getName()+" ___ "+lista.get(i).getRank());

        for(int i=0; i<7;i++) {
            System.out.println((i+1)+". "+lista.get(i).getName() + " con: " + lista.get(i).getRank() + " ocurrencias");
        }
        return lista;
    }

    @Override
    public int OccurrenciesArtistinTop50(String name, LocalDate fecha) {
        MyList<SpotifySong> songs = hashArtistDate.findData(fecha.toString() + "_" + name);
        System.out.println("La cantidad de ocurrencias de "+ name + " es: "+songs.size());
        return songs.size();
    }

    @Override
    public int SongsbetweenTempoAndDate(float TempoMax, float TempoMin, LocalDate fechaInicio, LocalDate fechaFin) throws WrongOrder {
        if(TempoMin>TempoMax || fechaInicio.isAfter(fechaFin)){
            throw new WrongOrder();
        }

        LocalDate current = fechaFin;
        MyList<SpotifySong> lista = new MyLinkedListImpl<>();
        while(!current.equals(fechaFin.plusDays(1))){
            MyList<SpotifySong> listaSongs = hashDate.findData(current.toString());
            for(int i=0; i<listaSongs.size();i++){
                if(listaSongs.get(i).getTempo()<TempoMax && listaSongs.get(i).getTempo()<TempoMin){
                    lista.add(listaSongs.get(i));
                }
            }
            current=current.plusDays(1);
        }
        System.out.println("Las canciones entre: \n -"+TempoMin+" < Tempo < "+TempoMax+"\n -"+ fechaInicio.toString()+" < Fecha < "+fechaFin.toString()+"\n Respuesta ----> "+lista.size()+" canciones");
        return lista.size();
    }

}
