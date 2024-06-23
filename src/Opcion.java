import exceptions.FechaInvalida;

import java.util.Scanner;

public class Opcion {
    public Scanner scanner = new Scanner(System.in);

    private String agregarCeroANumero(String num){
        if (Integer.parseInt(num) < 10 && Integer.parseInt(num) > 0){
            // Le agrega un 0 antes del string
            num = "0" + num;
        }
        return num;
    }

    private String ingresarDia(String msg) {
        System.out.print(msg);
        String dia = scanner.nextLine();

        dia = this.agregarCeroANumero(dia);

        int diaInt = Integer.parseInt(dia);

        if (diaInt < 0 || diaInt > 31){
            System.out.println("Dia Invalido \n");
            dia = ingresarDia(msg);
        }

        return dia;
    }

    private String ingresarMes(String msg) {
        System.out.print(msg);
        String mes = scanner.nextLine();

        mes = this.agregarCeroANumero(mes);

        int mesInt = Integer.parseInt(mes);
        if (mesInt < 0 || mesInt > 12){
            System.out.println("Mes Invalido \n");
            mes = ingresarMes(msg);
        }

        return mes;
    }

    private String ingresarAnio(String msg) {
        System.out.print(msg);
        String anio = scanner.nextLine();
        int num_anio = Integer.parseInt(anio);

        if (num_anio < 2010 || num_anio > 2024){
            System.out.println("Año invalido \n");
            anio = ingresarAnio(msg);
        }
        return anio;
    }

    private String ingresarFecha() {

        String dia = ingresarDia("Ingrese el dia: ");

        String mes = ingresarMes("Ingrese el mes: ");

        String anio = ingresarAnio("Ingrese el año: ");

        return anio + "-" + mes + "-" + dia;
    }

    private String ingresarCodigoPais(String msg){
        System.out.print(msg);
        String codPais =  scanner.nextLine();
        return codPais;
    }

    private String ingresarNombreArtista(String msg){
        System.out.print(msg);

        String nombreArtista = scanner.nextLine();
        return nombreArtista;
    }

    private Float ingresarTempo(String msg){
        System.out.print(msg);
        Float tempo = scanner.nextFloat();
        return tempo;
    }

    public void opcion1(Datos datos){
        String codPais = this.ingresarCodigoPais("Ingrese el codigo del pais: ");

        String fecha = this.ingresarFecha();

        datos.top10PaisDado(codPais,fecha);
    }

    public void opcion2(Datos datos){

        String fecha = this.ingresarFecha();

        datos.top5CancionesQueMasAparecenEnUnDiaDado(fecha);
    }

    public void opcion3(Datos datos){
        System.out.println("Ingrese la primera fecha");
        String fecha1 = this.ingresarFecha();

        System.out.println("Ingrese la segunda fecha");
        String fecha2 = this.ingresarFecha();

        datos.top7ArtistasQueMasAparecen(fecha1,fecha2);
    }

    public void opcion4(Datos datos){
        String artista = this.ingresarNombreArtista("Ingrese el nombre del artista: ");

        String fecha = this.ingresarFecha();

        datos.cantidadDeVecesQueApareceUnArtistaEnElTop(artista, fecha);
    }

    public void opcion5(Datos datos) throws FechaInvalida {

        System.out.println("Ingrese la primera fecha");
        String fecha1 = this.ingresarFecha();

        System.out.println("Ingrese la segunda fecha");
        String fecha2 = this.ingresarFecha();

        Float tempo = this.ingresarTempo("Ingrese el tempo minimo: ");

        Float tempo2 = this.ingresarTempo("Ingrese el tempo maximo: ");

        datos.cantidadDeCancionesConUnTempoEnUnRangoEspecificoParaUnRangoEspecificoDeFechas(fecha1, fecha2, tempo, tempo2);
    }
}
