public class SpotifySong {
    private String spotifyId;
    private String name;
    private Artists[] artists;
    private int dailyRank;
    private int dailyMovement;
    private int weeklyMovement;
    private String country;
    private String snapshotDate;
    private int popularity;
    private boolean isExplicit;
    private int durationMs;
    private String albumName;
    private String albumReleaseDate;
    private double danceability;
    private double energy;
    private int key;
    private double loudness;
    private int mode;
    private double speechiness;
    private double acousticness;
    private double instrumentalness;
    private double liveness;
    private double valence;
    private double tempo;
    private int timeSignature;

    public SpotifySong(String spotifyId, String name, Artists[] artists, int dailyRank, int dailyMovement, int weeklyMovement, String country, String snapshotDate, int popularity, boolean isExplicit, int durationMs, String albumName, String albumReleaseDate, double danceability, double energy, int key, double loudness, int mode, double speechiness, double acousticness, double instrumentalness, double liveness, double valence, double tempo, int timeSignature) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.artists = artists;
        this.dailyRank = dailyRank;
        this.dailyMovement = dailyMovement;
        this.weeklyMovement = weeklyMovement;
        this.country = country;
        this.snapshotDate = snapshotDate;
        this.popularity = popularity;
        this.isExplicit = isExplicit;
        this.durationMs = durationMs;
        this.albumName = albumName;
        this.albumReleaseDate = albumReleaseDate;
        this.danceability = danceability;
        this.energy = energy;
        this.key = key;
        this.loudness = loudness;
        this.mode = mode;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.valence = valence;
        this.tempo = tempo;
        this.timeSignature = timeSignature;
    }
}

