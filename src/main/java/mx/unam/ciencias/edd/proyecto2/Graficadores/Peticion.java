package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.Lista;

/**
 * Peticion para la factory de graficadores, contiene los siguientes datos:
 *
 * - El nombre de la estructura a graficar
 * - Una lista de Integers, que son el cuerpo de la estructura a graficar
 *
 * Este objeto no posee comportamiento, al ser un paso intermedio de simplemente procesar la entrada y mandarla a la factory de
 * graficadores
 */
public class Peticion {

    public String estructura;
    public Lista<Integer> cuerpo;

    public Peticion(String estructura, Lista<Integer> cuerpo){
        this.estructura = estructura;
        this.cuerpo = cuerpo;
    }
}
