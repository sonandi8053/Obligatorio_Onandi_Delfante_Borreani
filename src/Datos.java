import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import Exceptions.EmptyQueueException;
import exceptions.FechaInvalida;

import uy.edu.um.prog2.tad.hash.*;
import uy.edu.um.prog2.tad.heap.Exceptions.EmptyHeapException;
import uy.edu.um.prog2.tad.heap.Heap;
import uy.edu.um.prog2.tad.heap.HeapImpl;
import uy.edu.um.prog2.tad.heap.HeapNode;
import uy.edu.um.prog2.tad.linkedlist.Lista;
import uy.edu.um.prog2.tad.linkedlist.ListaEnlazada;
import uy.edu.um.prog2.tad.queue.MyPriorityQueue;
import uy.edu.um.prog2.tad.queue.MyPriorityQueueImp;


public class Datos {
    private BufferedReader lector;
    private String linea;
    private String partes[] = null;
    private final String ruta;

    public Datos(String ruta){
        this.ruta = ruta;
    }

    public void top10PaisDado(String pais, String fecha) {
        Heap<Integer,String> mapaTop = new HeapImpl<>(false);
        try {
            BufferedReader lector = new BufferedReader(new FileReader(this.ruta));
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",\"");
                this.eliminarComillasDeListaVacia(partes);

                if (partes[6].equals(pais) && partes[7].equals(fecha)) {
                    int clave = Integer.valueOf(partes[3]);
                    String valor = partes[1];
                    mapaTop.insert(clave, valor);
                }
            }
            for (int i = 0; i<10; i++) {
                System.out.println(mapaTop.getKey() + " - " + mapaTop.getValue());
                mapaTop.delete();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void top5CancionesQueMasAparecenEnUnDiaDado(String fecha) {
        String cancion;
        int apariciones = 1;

        MyHashInterface<String, Integer> mapaCantidadApariciones = new HashCerrado<>(4);
        try {
            BufferedReader lector = new BufferedReader(new FileReader(this.ruta));
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",\"");
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

            Heap<Integer, String> listaTop = mapaCantidadApariciones.getAsSwappedHeap(false);

            for (int i = 0; i<5; i++) {
                System.out.println(listaTop.getKey() + " - " + listaTop.getValue());
                listaTop.delete();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    public void cantidadDeVecesQueApareceUnArtistaEnElTop(String artista, String fecha) {
        Integer apariciones = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(this.ruta));
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",\"");
                this.eliminarComillasDeListaVacia(partes);
                if (partes[2].equals(artista) && partes[7].equals(fecha)){
                    apariciones+=1;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        System.out.printf("El artista %s aparece %s veces en el top el dia %s \n", artista, apariciones, fecha);
    }

    public void top7ArtistasQueMasAparecen(String fecha1, String fecha2) {
        // Top 7 artistas que mas aparecen en el top 50 en un dia dado
        LocalDate fechaInicio = LocalDate.parse(fecha1);
        LocalDate fechaFin = LocalDate.parse(fecha2);

        MyHashInterface<String, Integer> mapaCantidadApariciones = new HashCerrado<>(4);
        try {
            BufferedReader lector = new BufferedReader(new FileReader(this.ruta));
            String linea;
            String[] partes;
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",\"");
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
                    continue;
                }
            }
            // Transformar a heap
            Heap<Integer, String> listaTop7 = mapaCantidadApariciones.getAsSwappedHeap(false);

            for (int i = 0; i<7; i++) {
                System.out.println(listaTop7.getKey() + " - " + listaTop7.getValue());
                listaTop7.delete();
            }
        } catch (IOException | EmptyHeapException e) {
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
            BufferedReader lector = new BufferedReader(new FileReader(this.ruta));
            String linea;

            while((linea = lector.readLine()) != null){
                partes = linea.split(",\"");
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



