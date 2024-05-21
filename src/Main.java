import java.util.*;

public class Main {

    public static void main(String[] args) {
        Datos datos = new Datos();
        Scanner scanner = new Scanner(System.in);
        Opcion opciones = new Opcion();
        int opcion;
        do {
            System.out.println("Elija una opcion:\n");
            System.out.println("1)Top 10 canciones en un pais dado");
            System.out.println("2)Top 5 canciones que más aparecen en el top 50 en un dia dado");
            System.out.println("3)Top 7 artistas que más aparecen en los top 50 en un rango de fechas dado");
            System.out.println("4)Cantidad de veces que aparece un artista especifico en un top 50 en una fecha dada");
            System.out.println("5)Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas");
            System.out.println("6)Salir");
            System.out.print("--> ");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    opciones.opcion1();

                    break;
                case 2:
                    opciones.opcion2();
                    break;
                case 3:
                    //opciones.opcion3();
                    datos.top7ArtistasQueMasAparecen("2024-03-01","2024-03-07");
                    break;
                case 4:
                    opciones.opcion4();
                    break;
                case 5:
                    opciones.opcion5();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Opcion Invalida, vuelva a intentarlo.");
            }
        }while (opcion!=6);
    }

}