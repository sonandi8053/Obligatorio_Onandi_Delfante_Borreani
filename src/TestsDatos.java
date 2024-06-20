import org.junit.Test;

public class TestsDatos {
    public Datos datos = new Datos("universal_top_spotify_songs.csv");

    @Test
    public void test10CacionesEnUnPaisDado(){
        datos.top10PaisDado("UY", "2023-5-10");
    }

    //terminar
}
