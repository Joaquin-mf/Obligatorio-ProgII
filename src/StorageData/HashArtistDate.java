package StorageData;

import Entities.Artists;
import Entities.SpotifySong;
import uy.edu.um.tad.hash.MyHash;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;

public class HashArtistDate {
    public static MyHash<String, MyList<SpotifySong>> MyHashArtistDate(MyList<SpotifySong> songsList) {
        MyHash<String, MyList<SpotifySong>> hash = new MyHashImpl<>(113);
        for (int i = 0; i < songsList.size(); i++) {
            SpotifySong song = songsList.get(i);
            for (int j = 0; j < song.getArtists().size(); j++)
                String songKey = generateKey(song.getSnapshotDate(), song.getArtists().get(j));

//            if(!hashDateCountry.contains(songKey)){
//                hashDateCountry.put(songKey, new MyLinkedListImpl<>());
//            }
//            hashDateCountry.findNode(songKey).getData().add(song);
//        }
//        return hashDateCountry;
        }
    }
    public static String generateKey (LocalDate date, Artists artists){
                return date.toString() + "_" + artists.getName();
            }
}
