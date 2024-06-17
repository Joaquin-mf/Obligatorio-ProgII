package Entities;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;

public class SpotifySong implements Comparable<SpotifySong> {
    private String name;
    private MyList<Artists> artists;
    private int dailyRank;
    private String country;
    private LocalDate snapshotDate;
    private double tempo;
    private int counter;


    public SpotifySong(String name, MyList<Artists> artists, int dailyRank, String country, LocalDate snapshotDate, double tempo) {
        this.name = name;
        this.artists = artists;
        this.dailyRank = dailyRank;
        this.country = country;
        this.snapshotDate = snapshotDate;
        this.tempo = tempo;
        this.counter = 0;
    }

    public String getName() {
        return name;
    }

    public MyList<Artists> getArtists() {
        return artists;
    }

    public int getDailyRank() {
        return dailyRank;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getSnapshotDate() {
        return snapshotDate;
    }

    public double getTempo() {
        return tempo;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int compareTo(SpotifySong spotifySong) {
        if(counter < spotifySong.getCounter()){return 1;}
        else if(counter == spotifySong.getCounter()){return 0;}
        return -1;
    }
}

