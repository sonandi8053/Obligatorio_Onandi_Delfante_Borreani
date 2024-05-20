import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        Datos datosPrueba = new Datos();
        int opcion = 0;
        do {
            System.out.println("Elija una opcion:\n");
            System.out.println("1)Top 10 canciones en un pais dado");
            System.out.println("2)Top 5 canciones que más aparecen en el top 50 en un dia dado");
            System.out.println("3)Top 7 artistas que más aparecen en los top 50 en un rango de fechas dado");
            System.out.println("4)Cantidad de veces que aparece un artista especifico en un top 50 en una fecha dada");
            System.out.println("5)Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas");
            System.out.println("6)Salir");
            System.out.println("\n");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    datosPrueba.top10PaisDado("ZA","2024-05-13");
                    break;
                case 2:
                    System.out.println("Juanito");
                    datosPrueba.top5CancionesQueMasAparecenEnUnDiaDado("2023-11-07");
                    break;
                case 3:
                    System.out.println("Pedrito");
                    break;
                case 4:
                    int v=datosPrueba.cantidadDeVecesQueApareceUnArtistaEnElTop("Taylor Swift","2023-11-07");
                    System.out.println(v);
                    break;
                case 5:
                    System.out.println("Adrian");
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Opcion Invalida, vuelva a intentarlo.");
            }
        }while (opcion!=6);
    }

}