import Entities.Artists;
import Entities.SpotifySong;
import StorageData.CSVReader;
import StorageData.HashDateCountry;
import uy.edu.um.tad.binarytree.BinaryTree;
import uy.edu.um.tad.hash.MyHash;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;

public class MySongStatsImpl implements MySongStats{
    public MyList<SpotifySong> mySongs;

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
    public BinaryTree<SpotifySong> Top10(LocalDate fecha, String Pais) {
        HashDateCountry clase = new HashDateCountry();
        CSVReader myReader = new CSVReader();
        MyHash<String,MyList<SpotifySong>> myHash = clase.MyHashDateCountry(mySongs);
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
        return Top10;
    }

    @Override
    public MyList<SpotifySong> Top5inTop50(String fecha) {

        return null;
    }


    @Override
    public MyList<Artists> Top7inTop50(LocalDate fechaInicio, LocalDate fechaFin) {

        //filtro segun el rango de fechas
        MyList<SpotifySong> songs = new MyLinkedListImpl<>();
        for(int i=0; i < mySongs.size(); i++){
            if((!(mySongs.get(i).getSnapshotDate().isBefore(fechaInicio)) && mySongs.get(i).getSnapshotDate().isBefore(fechaFin)) && (mySongs.get(i).getDailyRank() < 50)){
                songs.add(mySongs.get(i));
            }
        }
        MyHash<String,Integer> artistasCount = new MyHashImpl<>(113);
        for(int i=0; i < mySongs.size(); i++) {
            MyList<Artists> artist = mySongs.get(i).getArtists();
            int k;
            for (k = 0; k < artist.size(); k++) ;
            String name = artist.get(k).getName();
            if(artistasCount.contains(name)){
                Integer ocurrencias = artistasCount.findNode(name).getData();
                artistasCount.findNode(name).setData(ocurrencias + 1);
            }
            artistasCount.put(name,1);
        }







        return null;
    }

    @Override
    public int OccurrenciesArtistinTop50(Artists artista) {
        return 0;
    }

    @Override
    public int SongsbetweenTempoAndDate(int TempoMax, int TempoMin, LocalDate fechaInicio, LocalDate fechaFin) {
        return 0;
    }
}
