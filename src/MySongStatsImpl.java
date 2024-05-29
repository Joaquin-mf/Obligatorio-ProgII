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

import java.time.LocalDate;
import java.util.List;

public class MySongStatsImpl implements MySongStats{
    private MyList<SpotifySong> mySongs;

    public MyList<SpotifySong> getMySongs() {
        return mySongs;
    }

    public void setMySongs(MyList<SpotifySong> mySongs) {
        this.mySongs = mySongs;
    }

    public MySongStatsImpl() {
        CSVReader lector = new CSVReader();
        this.mySongs = lector.CSVload();
    }



    @Override
    public List<SpotifySong> Top10(LocalDate fecha, String Pais) {
        MyHash<String,MyList<SpotifySong>> myHash = HashDateCountry.MyHashDateCountry(mySongs);
        MyList<SpotifySong> songsList = myHash.findNode(fecha.toString()+"_"+Pais).getData();
        BinaryTree<SpotifySong> Top10 = null;
        int i=0;
        int iter = 0;
        while (i < songsList.size() &&  iter < 11){
            if (songsList.get(i).getDailyRank() < 11){
                Top10.add(songsList.get(i));
                iter++;
            }
            i++;
        }
        List<SpotifySong> ans = Top10.inOrder();
        return ans;
    }

    @Override
    public MyList<SpotifySong> Top5inTop50(LocalDate fecha) {
        CSVReader myReader = new CSVReader();
        MyHash<String,MyList<SpotifySong>> myHash = HashDate.MyHashDate(mySongs);
        if (myHash != null) {
            MyList<SpotifySong> songsDate = myHash.findNode(fecha.toString()).getData();
        }



        return null;
    }


    @Override
    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin) {

        //filtro segun el rango de fechas
        MyList<SpotifySong> songs = new MyLinkedListImpl<>();
        for(int i=0; i < mySongs.size(); i++){
            if((!(mySongs.get(i).getSnapshotDate().isBefore(fechaInicio)) && mySongs.get(i).getSnapshotDate().isBefore(fechaFin)) && (mySongs.get(i).getDailyRank() <= 50)){
                songs.add(mySongs.get(i));
            }
        }

        MyHash<String,Artists> artistasCount = new MyHashImpl<>(113);
        for(int i=0; i < songs.size(); i++) {
            MyList<Artists> artist = songs.get(i).getArtists();

            for (int k = 0; k < artist.size(); k++){
                String name = artist.get(k).getName();
                if (artistasCount.contains(name)) {
                    int ocurrencias = artistasCount.findNode(name).getData().getRank();
                    artistasCount.findNode(name).getData().setRank(ocurrencias + 1);
                }else{
                    artist.get(k).setRank(1);
                    artistasCount.put(name,artist.get(k));
                }
            }
        }
        MyList<Artists> tabla = artistasCount.values();



        return null;
    }

    @Override
    public int OccurrenciesArtistinTop50(String name, LocalDate fecha) {
        MyHash<String,MyList<SpotifySong>> hash = HashArtistDate.MyHashArtistDate(mySongs);
        MyList<SpotifySong> songs = hash.findNode(fecha.toString()+"_"+name).getData();


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
        for (int i=0; i<songs.size(); i++){
            SpotifySong song = songs.get(i);
            if (song.getDailyRank() <= 50){contador++;}
        }
        return contador;
    }

    @Override
    public int SongsbetweenTempoAndDate(int TempoMax, int TempoMin, LocalDate fechaInicio, LocalDate fechaFin) {
        int contador = 0;
        for(int i=0; i<mySongs.size(); i++){
            SpotifySong song = mySongs.get(i);
            if((song.getTempo()<TempoMax && song.getTempo()>TempoMin) && (song.getSnapshotDate().isBefore(fechaFin) && song.getSnapshotDate().isAfter(fechaFin))){
                contador++;
            }
        }
        return contador;
    }
}
