import exceptions.AnioInvalido;
import exceptions.DiaInvalido;
import exceptions.MesInvalido;

import java.util.Scanner;

public class Opcion {
    Datos datosPrueba = new Datos();
    Scanner scanner = new Scanner(System.in);

    private String agregarCeroANumero(String num){
        if (Integer.parseInt(num) < 10){
            // Le agrega un 0 antes del string
            num = "0" + num;
        }
        return num;
    }

    private String ingresarFecha() throws DiaInvalido, MesInvalido, AnioInvalido, NumberFormatException {
        System.out.print("Ingrese el dia: ");
        String dia = scanner.next();

        int diaInt = Integer.parseInt(dia);
        if (diaInt < 0 || diaInt > 31){
            throw new DiaInvalido();
        }

        dia = this.agregarCeroANumero(dia);

        System.out.print("Ingrese el numero del mes: ");
        String mes = scanner.next();

        int mesInt = Integer.parseInt(mes);
        if (mesInt < 0 || mesInt > 12){
            throw new MesInvalido();
        }

        mes = this.agregarCeroANumero(mes);

        System.out.print("Ingrese el año: ");
        String anio = scanner.next();
        int num_anio = Integer.parseInt(anio);

        if (num_anio < 2000 || num_anio > 2024){
            throw new AnioInvalido();
        }

        return anio + "-" + mes + "-" + dia;
    }

    private String ingresarCodigoPais(){
        System.out.print("Ingrese el codigo del pais: ");
        String codPais = scanner.next();
        return codPais;
    }

    public void opcion1(){
        String codPais = this.ingresarCodigoPais();

        String fecha = null;
        try {
            fecha = this.ingresarFecha();
        } catch (DiaInvalido e) {
            System.out.println("Dia Invalido.");
        } catch (MesInvalido e) {
            System.out.println("Mes Invalido");
        } catch (AnioInvalido e) {
            System.out.println("Anio Invalido");
        } catch (NumberFormatException e){
            System.out.println("Ingrese el año");
        }


        datosPrueba.top10PaisDado(codPais,fecha);
    }

    public void opcion2(){

        String fecha = null;
        try {
            fecha = this.ingresarFecha();
        } catch (DiaInvalido e) {
            System.out.println("Dia Invalido.");
        } catch (MesInvalido e) {
            System.out.println("Mes Invalido");
        } catch (AnioInvalido e) {
            System.out.println("Anio Invalido");
        }
        System.out.println(fecha);
        //datosPrueba.top5CancionesQueMasAparecenEnUnDiaDado("2023-11-07");
    }

    public void opcion3(){
        System.out.println("Pedrito");
    }

    public void opcion4(){
        //int v = datosPrueba.cantidadDeVecesQueApareceUnArtistaEnElTop("Taylor Swift","2023-11-07");
        //System.out.println(v);
    }

    public void opcion5(){
        System.out.println("Adrian");
    }
}
