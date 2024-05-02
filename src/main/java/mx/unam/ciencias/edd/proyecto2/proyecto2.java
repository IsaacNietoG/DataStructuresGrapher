package mx.unam.ciencias.edd.proyecto2;

import java.io.File;
import java.io.InputStreamReader;

import mx.unam.ciencias.edd.proyecto2.Graficadores.GraficadorFactory;
import mx.unam.ciencias.edd.proyecto2.Graficadores.Peticion;

/**
 * Proyecto 2 para la materia de Estructuras de Datos impartida por el profesor Canek Pelaez Valdez
 * @author Isaac Julian Nieto Gallegos
 * @version 1.0
 *
 * Este proyecto es un programa que, dada una entrada recibida a través de un archivo, o la entrada estándar,
 * crea una representación en formato SVG de la estructura de datos especificada en la entrada.
 *
 * Tiene la capacidad de graficar las implementaciones realizadas a lo largo del curso de las siguientes estructuras de datos:
 * - Arboles AVL
 * - Arboles Binarios Completos
 * - Arboles Binarios Ordenados
 * - Arboles Rojinegros
 * - Arreglos (esta no es implementación propia pero ajá)
 * - Colas
 * - Graficas
 * - Listas
 * - Monticulos Minimos
 * - Monticulos Arreglo
 * - Pilas
 *
 * Para ello, se sirve del uso de distintas clases que fueron modeladas para este propósito, cada una documentada por su cuenta
 * pero en general:
 *
 * De un lector de entrada, que es el encargado de recibir la entrada y convertirla a una String, también el encargado de
 * manejar las posibles excepciones en este paso.
 *
 * De un formador de peticiones, que recibe la String del paso anterior y la convierte en un objeto Peticion,
 * que contiene la información sobre que estructura de datos se desea fabricar y el cuerpo de la misma.
 * Este es el objeto designado a manejar todas las posibles excepciones que pudieran ocurrir durante este
 * proceso.
 *
 * Una vez formada la petición, se llama a la Factory de graficadores, la cual crea el graficador correspondiente
 * para la estructura de datos.
 *
 * El graficador asignado creará la hoja SVG correspondiente.
 *
 * La hoja SVG, que será el paso final y cuyo toString es justamente el código SVG necesario para graficar la EDD recibida.
 */
public class proyecto2 {

    /**
     *  Mensaje de error si el usuario no inserta una entrada correcta.
     *
     *  Sale del programa.
     *  */
    public static void uso(){
        System.err.println("La entrada no se ha recibido de manera correcta, verifique su entrada.");
        System.exit(0);
    }

    /**
     *  Main de la clase, recibe la entrada y la envía al lector de entrada para comenzar el proceso. Tambien funciona como controlador
     *  entre los distintos objetos de este proyecto.
     *  Si ocurre una excepción durante este proceso, terminará con gracia.
     *
     *  @param args   La entrada por archivo del programa. Solo puede ser un archivo. Es la entrada prioritaria
     *
     *  */
    public static void main(String[] args) {
        if (args.length>1)
            uso();

        InputStreamReader entradaEstandar = new InputStreamReader(System.in);
        File archivo = new File(args[0]);

        //Obteniendo input
        LectorEntrada lector = new LectorEntrada(entradaEstandar, archivo);

        String input = "";
        try{
            input = lector.leerEntradas();
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            uso();
        }

        //Formando peticion
        FormadorPeticiones formador = new FormadorPeticiones(input);
        try{
            Peticion peticion = formador.formarPeticion();
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            uso();
        }

        Graficador graficador = GraficadorFactory.crearGraficador(peticion);



    }

}
