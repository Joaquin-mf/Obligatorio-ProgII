package DataStructures;

import Entities.SpotifySong;
import uy.edu.um.tad.hash.MyHash;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

public class HashDate {
    public static MyHash<String, MyList<SpotifySong>> MyHashDate(MyList<SpotifySong> songsList){
        MyHash<String,MyList<SpotifySong>> hashDate = new MyHashImpl<>(113);
        for(int i = 0; i < songsList.size(); i++){
            SpotifySong song = songsList.get(i);
            String songKey = song.getSnapshotDate().toString();

            if(!hashDate.contains(songKey)){
                hashDate.put(songKey, new MyLinkedListImpl<>());
            }
            hashDate.findNode(songKey).getData().add(song);
        }
        return null;
    }
}
