
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

public class Datos {
    private BufferedReader lector;
    private String linea;
    private String partes[] = null;

    public void leerArchivo(String nombreArchivo){
        try{
            lector = new BufferedReader(new FileReader(nombreArchivo));
            linea = lector.readLine();
            while (linea!=null){
                partes = linea.split(",");
            }
            lector.close();
            linea = null;
            partes = null;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }
    public void imprimirLinea(){
        for (int i = 0; i< partes.length;i++){
            System.out.println(partes[i]+" | ");
        }
    }

    public void top10PaisDado(String pais,String fecha){
        //DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
        //LocalDate fechaConvertida = LocalDate.parse(fecha,format);

        try{
            lector = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"));
            linea = lector.readLine();
            while (linea!=null){
                partes = linea.split(",");
                if (Objects.equals(partes[6], pais) && Objects.equals(partes[7], fecha)){

                }
            }
            lector.close();
            linea = null;
            partes = null;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }



    }

}
