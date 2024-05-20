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
                linea = linea.replaceAll("\"", "");
                partes = linea.split(",");
                if (partes[6].equals(pais) && partes[7].equals(fecha)) {
                    int clave = Integer.valueOf(partes[3]);
                    String valor = partes[1];
                    mapaTop.put(clave, valor);
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

    public void top5CancionesQueMasAparecenEnUnDiaDado(String fecha) {
        String cancion = null;
        int apariciones = 1;

        Map<String, Integer> mapaCantidadApariciones = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"))) {
            while ((linea = lector.readLine()) != null) {
                linea = linea.replaceAll("\"", "");
                partes = linea.split(",");
                if (partes[7].equals(fecha)) {
                    cancion = partes[1];
                    if (mapaCantidadApariciones.containsKey(cancion)) {
                        apariciones = mapaCantidadApariciones.get(cancion);
                        mapaCantidadApariciones.remove(cancion);
                        mapaCantidadApariciones.put(cancion, apariciones + 1);
                    } else {
                        mapaCantidadApariciones.put(cancion, apariciones);
                    }
                }
            }
            List<Map.Entry<String, Integer>> listaTop = new ArrayList<>(mapaCantidadApariciones.entrySet());
            listaTop.sort(Comparator.comparingInt(Map.Entry::getValue));
            Collections.reverse(listaTop);
            List<Map.Entry<String, Integer>> primeros5 = listaTop.subList(0, Math.min(10, listaTop.size()));
            for (Map.Entry<String, Integer> entry : primeros5) {
                System.out.println(entry.getValue() + " - " + entry.getKey());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public Integer cantidadDeVecesQueApareceUnArtistaEnElTop(String artista, String fecha) {
        int apariciones = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"))) {
            while ((linea = lector.readLine()) != null) {
                linea = linea.replaceAll("\"", "");
                partes = linea.split(",");
                if (partes[2].equals(artista) && partes[7].equals(fecha)){
                    apariciones+=1;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return apariciones;
    }
}


