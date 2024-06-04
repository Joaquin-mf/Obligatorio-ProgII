package Entities;
import uy.edu.um.tad.linkedlist.MyList;

import java.time.LocalDate;

public class SpotifySong implements Comparable<SpotifySong> {
    private String spotifyId;
    private String name;
    private MyList<Artists> artists;
    private int dailyRank;
    private int dailyMovement;
    private int weeklyMovement;
    private String country;
    private LocalDate snapshotDate;
    private double tempo;
    private int counter;


    public SpotifySong(String spotifyId, String name, MyList<Artists> artists, int dailyRank, int dailyMovement, int weeklyMovement, String country, LocalDate snapshotDate, double tempo) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.artists = artists;
        this.dailyRank = dailyRank;
        this.dailyMovement = dailyMovement;
        this.weeklyMovement = weeklyMovement;
        this.country = country;
        this.snapshotDate = snapshotDate;
        this.tempo = tempo;
        this.counter = 0;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyList<Artists> getArtists() {
        return artists;
    }

    public void setArtists(MyList<Artists> artists) {
        this.artists = artists;
    }

    public int getDailyRank() {
        return dailyRank;
    }

    public void setDailyRank(int dailyRank) {
        this.dailyRank = dailyRank;
    }

    public int getDailyMovement() {
        return dailyMovement;
    }

    public void setDailyMovement(int dailyMovement) {
        this.dailyMovement = dailyMovement;
    }

    public int getWeeklyMovement() {
        return weeklyMovement;
    }

    public void setWeeklyMovement(int weeklyMovement) {
        this.weeklyMovement = weeklyMovement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(LocalDate snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int compareTo(SpotifySong spotifySong) {
        if(spotifySong.getCounter()==0){
        if(dailyRank < spotifySong.getDailyRank()) {return -1;}
        else if (dailyRank == spotifySong.getDailyRank()) {return 0;}
        return 1;
        }
        else{
            if(counter < spotifySong.getCounter()){return 1;}
            else if(counter == spotifySong.getDailyRank()){return 0;}
            return -1;
        }
    }
}

