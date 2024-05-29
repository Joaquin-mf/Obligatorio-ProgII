package Entities;

public class Artists {
    private String name;
    private int rank;

    public Artists(String name) {
        this.name = name;
        this.rank = 0;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
