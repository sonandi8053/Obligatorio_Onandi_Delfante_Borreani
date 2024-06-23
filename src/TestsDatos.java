import exceptions.FechaInvalida;
import org.junit.Test;

import java.io.IOException;

public class TestsDatos {
    public Datos datos = new Datos("universal_top_spotify_songs.csv");

    @Test
    public void test10CacionesEnUnPaisDado(){
        datos.top10PaisDado("UY", "2023-5-10");
    }

    @Test
    public void testTop5CancionesQueMasAparecenEnUnDiaDado() throws IOException {
        datos.top5CancionesQueMasAparecenEnUnDiaDado("2023-01-01");
    }

    @Test
    public void testCantidadDeVecesQueApareceUnArtistaEnElTop() throws IOException {
        datos.cantidadDeVecesQueApareceUnArtistaEnElTop("ArtistaEjemplo", "2023-01-01"); // Ejemplo de artista y fecha
    }

    @Test
    public void testTop7ArtistasQueMasAparecen() throws IOException {
        datos.top7ArtistasQueMasAparecen("2023-01-01", "2023-01-10");
    }

    @Test
    public void testCantidadDeCancionesConUnTempoEnUnRangoEspecificoParaUnRangoEspecificoDeFechas() throws IOException, FechaInvalida {
        datos.cantidadDeCancionesConUnTempoEnUnRangoEspecificoParaUnRangoEspecificoDeFechas("2023-01-01", "2023-01-10", 100.0f, 200.0f); // Ejemplo de fechas y rango de tempo
    }
}
