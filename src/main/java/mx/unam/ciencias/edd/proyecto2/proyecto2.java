package mx.unam.ciencias.edd.proyecto2;

import java.io.InputStreamReader;

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
 * De un formador de estructuras, que convierte la entrada recibida a una instancia de la estructura de datos recibida, es
 * el encargado de verificar las posibles excepciones que puedan ocurrir de recibir una entrada no convertible a una EDD
 *
 * Una vez formada la estructura, se llamará a uno de los graficadores existentes, uno para cada estructura, que será el encargado
 * de formar los objetos SVG que representarán la estructura
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
    }

}
