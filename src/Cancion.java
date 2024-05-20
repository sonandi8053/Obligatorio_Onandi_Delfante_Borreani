import java.util.Objects;

public class Cancion {
    private String spotifyID;
    private String nombre;
    private String artista;
    private int puestoRanking;
    private String fecha;
    private String pais;

    public Cancion(String spotifyID, String nombre, String artista, int puestoRanking, String fecha, String pais) {
        this.spotifyID = spotifyID;
        this.nombre = nombre;
        this.artista = artista;
        this.puestoRanking = puestoRanking;
        this.fecha = fecha;
        this.pais = pais;
    }

    public String getSpotifyID() {
        return spotifyID;
    }

    public void setSpotifyID(String spotifyID) {
        this.spotifyID = spotifyID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getPuestoRanking() {
        return puestoRanking;
    }

    public void setPuestoRanking(int puestoRanking) {
        this.puestoRanking = puestoRanking;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        Cancion cancion = (Cancion) o;
        return Objects.equals(spotifyID, cancion.spotifyID);
    }
}
