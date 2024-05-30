package Entities;

import java.util.Objects;

public class Artists implements Comparable<Artists> {
    private String name;
    private int rank;

    public Artists(String name) {
        this.name = name;
        this.rank = 0;
    }

    @Override
    public int compareTo(Artists artists) {
        if (artists == null) {
            throw new NullPointerException("The argument 'artists' is null");
        }
        return Integer.compare(this.rank, artists.getRank());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artists artists = (Artists) o;
        return Objects.equals(name, artists.name);
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
