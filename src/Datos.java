import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Datos {
    private BufferedReader lector;
    private String linea;
    private String partes[] = null;

    public void top10PaisDado(String pais, String fecha) {
        Map<Integer, String> mapaTop = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"))) {
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",");
                for (int i=0;i<partes.length;i++){
                    System.out.println(partes[i]);
                }
                if (partes[6].equals(pais) && partes[7].equals(fecha)) {
                    int clave = Integer.valueOf(partes[3]);
                    String valor = partes[1];
                    mapaTop.put(clave, valor);
                    System.out.println("Añadido al mapa: " + clave + " - " + valor); // Mensaje de depuración
                }
            }
            List<Map.Entry<Integer, String>> listaTop = new ArrayList<>(mapaTop.entrySet());
            listaTop.sort(Comparator.comparingInt(Map.Entry::getKey));
            List<Map.Entry<Integer, String>> primeros10 = listaTop.subList(0, Math.min(10, listaTop.size()));
            for (Map.Entry<Integer, String> entry : primeros10) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}


