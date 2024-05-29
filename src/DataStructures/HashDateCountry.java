package DataStructures;

import Entities.SpotifySong;
import uy.edu.um.tad.hash.MyHash;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;
import java.time.LocalDate;

public class HashDateCountry {
    public static MyHash<String, MyList<SpotifySong>> MyHashDateCountry(MyList<SpotifySong> songsList){
        MyHash<String,MyList<SpotifySong>> hashDateCountry = new MyHashImpl<>(111113);

        for(int i=0; i< songsList.size(); i++){
            SpotifySong song = songsList.get(i);
            String songKey = generateKey(song.getSnapshotDate(),song.getCountry());

            if(!hashDateCountry.contains(songKey)){
                hashDateCountry.put(songKey, new MyLinkedListImpl<>());
            }
            hashDateCountry.findNode(songKey).getData().add(song);
        }
        return hashDateCountry;
    }

    public static String generateKey(LocalDate date, String country) {
        return date.toString() + "_" + country;
    }
}
