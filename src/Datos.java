import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Datos {
    private BufferedReader lector;
    private String linea;
    private String partes[] = null;

    public void top10PaisDado(String pais, String fecha) {
        Map<Integer, String> mapaTop = new HashMap<>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
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
        try {
            BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
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
        try {
            BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
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

    public void top7ArtistasQueMasAparecen(String fecha1, String fecha2) {
        int apariciones = 1;
        LocalDate fechaInicio = LocalDate.parse(fecha1);
        LocalDate fechaFin = LocalDate.parse(fecha2);

        Map<String, Integer> mapaCantidadApariciones = new HashMap<>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
            String linea;
            String[] partes;
            while ((linea = lector.readLine()) != null) {
                linea = linea.replaceAll("\"", "");
                partes = linea.split(",");
                if (partes.length < 24) {
                    // Verificar si hay suficientes elementos en la línea
                    continue;
                }
                String fechaString = partes[7].trim();
                if (fechaString.isEmpty()) {
                    // Verificar si la fecha está vacía
                    continue;
                }
                try {
                    LocalDate fechaEstimada = LocalDate.parse(fechaString);

                    if (fechaEstimada.isAfter(fechaInicio) && fechaEstimada.isBefore(fechaFin)) {
                        // Si una celda artista tiene una coma es que hay más que uno.
                        if (partes[2].contains(",")) {
                            String[] listaCantantes = partes[2].split(",");
                            for (String cantante : listaCantantes) {
                                cantante = cantante.replaceAll("\"", "").trim();
                                if (!cantante.isEmpty()) {
                                    mapaCantidadApariciones.put(cantante, mapaCantidadApariciones.getOrDefault(cantante, 0) + 1);
                                }
                            }
                        } else {
                            String cantante = partes[2].replaceAll("\"", "").trim();
                            if (!cantante.isEmpty()) {
                                mapaCantidadApariciones.put(cantante, mapaCantidadApariciones.getOrDefault(cantante, 0) + 1);
                            }
                        }
                    }
                } catch (DateTimeParseException e) {
                    // Manejar el caso donde partes[7] no es una fecha válida
                    // No existe una fecha
                    continue;
                }
            }

            List<Map.Entry<String, Integer>> listaTop = new ArrayList<>(mapaCantidadApariciones.entrySet());
            listaTop.sort(Comparator.comparingInt(Map.Entry::getValue));
            Collections.reverse(listaTop);
            List<Map.Entry<String, Integer>> primeros7 = listaTop.subList(0, Math.min(7, listaTop.size()));
            for (Map.Entry<String, Integer> entry : primeros7) {
                System.out.println(entry.getValue() + " - " + entry.getKey());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void cantidadDeCancionesConUnTempoEnUnRangoEspecificoParaUnRangoEspecificoDeFechas(String fecha1, String fecha2){
        // En un rango de fechas (fecha1 - fecha2)
        LocalDate fechaInicio = LocalDate.parse(fecha1);
        LocalDate fechaFin = LocalDate.parse(fecha2);


    }

}



