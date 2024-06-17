import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;

import exceptions.FechaInvalida;
import uy.edu.um.prog2.tad.hash.*;
import uy.edu.um.prog2.tad.linkedlist.Lista;
import uy.edu.um.prog2.tad.linkedlist.ListaEnlazada;


public class Datos {
    private BufferedReader lector;
    private String linea;
    private String partes[] = null;

    public void top10PaisDado(String pais, String fecha) {
        MyHashInterface<Integer,String> mapaTop = new HashCerrado<>(50);
        try {
            BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
            while ((linea = lector.readLine()) != null) {
                // Problema con el split, al separar una cancion con varios artistas se corre lo demas, tipo artistas son parte[2,3] si son 2
                partes = linea.split(",\"\"");
                this.eliminarComillasDeListaVacia(partes);
                if (partes[6].equals(pais) && partes[7].equals(fecha)) {
                    int clave = Integer.valueOf(partes[3]);
                    String valor = partes[1];
                    mapaTop.put(clave, valor);
                }
            }
            Lista<NodoHash<Integer, String>> listaTop = mapaTop.getNodesAsList(); // Devuelve una linked list ordenada segun la key
            listaTop.sort();
            listaTop.limitarElementos(10);
            for (int i = 0; i<listaTop.size(); i++) {
                System.out.println(listaTop.get(i).getKey() + " - " + listaTop.get(i).getValue());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void top5CancionesQueMasAparecenEnUnDiaDado(String fecha) {
        String cancion = null;
        int apariciones = 1;

        MyHashInterface<String, Integer> mapaCantidadApariciones = new HashCerrado<>(4);
        try {
            BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",");
                this.eliminarComillasDeListaVacia(partes);
                if (partes[7].equals(fecha)) {
                    cancion = partes[1];
                    if (mapaCantidadApariciones.contains(cancion)) {
                        apariciones = mapaCantidadApariciones.getValue(cancion);
                        mapaCantidadApariciones.put(cancion, apariciones + 1);
                    } else {
                        mapaCantidadApariciones.put(cancion, apariciones);
                    }
                }
            }

            Lista<NodoHash<Integer, String>> listaTop = mapaCantidadApariciones.getNodesAsSwapedList();
            listaTop.sort();
            listaTop.reverse();
            listaTop.limitarElementos(5);
            for (int i = 0; i < listaTop.size(); i++) {
                System.out.println(listaTop.getNode(i).getValue().getKey() + " - " + listaTop.getNode(i).getValue().getValue());
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
                partes = linea.split(",");
                this.eliminarComillasDeListaVacia(partes);
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
        // Top 7 artistas que mas aparecen en el top 50 en un dia dado
        int apariciones = 1;
        LocalDate fechaInicio = LocalDate.parse(fecha1);
        LocalDate fechaFin = LocalDate.parse(fecha2);

        MyHashInterface<String, Integer> mapaCantidadApariciones = new HashCerrado<>(4);
        try {
            BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
            String linea;
            String[] partes;
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",");
                this.eliminarComillasDeListaVacia(partes);
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
                                    if (!mapaCantidadApariciones.contains(cantante)) {
                                        mapaCantidadApariciones.put(cantante, 1);
                                    } else {
                                        mapaCantidadApariciones.put(cantante, mapaCantidadApariciones.getValue(cantante) + 1);
                                    }
                                }
                            }
                        } else {
                            String cantante = partes[2].replaceAll("\"", "").trim();
                            if (!cantante.isEmpty()) {
                                if (!mapaCantidadApariciones.contains(cantante)) {
                                    mapaCantidadApariciones.put(cantante, 1);
                                } else {
                                mapaCantidadApariciones.put(cantante, mapaCantidadApariciones.getValue(cantante) + 1);
                                }   
                            }
                        }
                    }
                } catch (DateTimeParseException e) {
                    // Manejar el caso donde partes[7] no es una fecha válida
                    // No existe una fecha
                    continue;
                }
            }

            Lista<NodoHash<Integer, String>> listaTop7 = mapaCantidadApariciones.getNodesAsSwapedList(); // Devuelve una linked list ordenada segun la key (EntrySet)
            // Problema en el sort, sortea(compara) los nodos por el String y no por los Interger
            listaTop7.sort();
            listaTop7.reverse();
            listaTop7.limitarElementos(7);
            for (int i = 0; i<listaTop7.size(); i++) {
                System.out.println(listaTop7.get(i).getKey() + " - " + listaTop7.get(i).getValue());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void cantidadDeCancionesConUnTempoEnUnRangoEspecificoParaUnRangoEspecificoDeFechas(String  fecha1, String fecha2, float tempo) throws FechaInvalida {
        // TEMPO ES LA COLUMNA 23 DE 24 COLUMNAS
        // En un rango de fechas (fecha1 - fecha2)
        LocalDate fechaInicio = LocalDate.parse(fecha1);
        LocalDate fechaFin = LocalDate.parse(fecha2);
        int num = 0;

        if (fechaInicio.isAfter(fechaFin)){
            throw new FechaInvalida();
        }

        try {
            BufferedReader lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
            String linea;

            while((linea = lector.readLine()) != null){
                partes = linea.split(",");
                this.eliminarComillasDeListaVacia(partes);
                if (partes.length >= 23){
                    try{
                        String tempoString = partes[23];
                        float tempoPartes = Float.parseFloat(tempoString);
                        if (tempoPartes == tempo){
                            num ++;
                        }
                    }
                    catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                        continue;
                    }
                }



            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (lector != null){
                try {
                    lector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("Entre la fecha %s y %s con el tempo %s hay %s canciones \n", fechaInicio, fechaFin, tempo, num);

    }

    /**
     * Elimina las comillas a una lista, menos a los elementos que esten vacios
     */
    private void eliminarComillasDeListaVacia(String[] listaComillas){
        for (int i = 0; i<listaComillas.length; i++){
            listaComillas[i] = listaComillas[i].replace("\"", "");

            if (listaComillas[i].isEmpty() || listaComillas[i].isBlank()){
                listaComillas[i] = "\"\"";
            }
        }
    }
}



