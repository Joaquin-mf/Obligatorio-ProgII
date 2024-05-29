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
            LocalDate dia = song.getSnapshotDate();
            for (int j = 0; j < song.getArtists().size(); j++) {
                Artists persona = song.getArtists().get(j);
                String songKey = song.getSnapshotDate().toString() + "_" + persona.getName();
                if (!hash.contains(songKey)) {
                    hash.put(songKey, new MyLinkedListImpl<>());
                }
                hash.findNode(songKey).getData().add(song);
            }
        }
        return hash;
    }
}