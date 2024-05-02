package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.Lista;

/**
 *  Abstrae la creacion de la hoja SVG con su propia clase que representa esto.
 *  En su creacion se define el alto y ancho de la hoja, y posee metodos para agregar elementos a la misma
 *
 *  Su toString es el codigo final de la hoja SVG
 *  */
public class HojaSVG {

    private static final String inicioSVG =
        "<?xml version='1.0' encoding='UTF-8' ?>\n";

    private String dimensiones;
    private Lista<String> elementos;
    private static final String finSVG = "</g></svg>";

    public HojaSVG(int alto, int ancho){
        dimensiones = String.format("<svg width='%d' height='%d'><g>", ancho, alto);

    }

    /**
     *  Agrega un elemento al inicio de la lista, esto nos sirve para darle menor prioridad sobre otros elementos a la hora del
     *  dibujado
     *
     *  @param elemento    la representacion en texto del elemento que queremos agregar
     *  */
    public void agregaElementoInicio(String elemento){
        elementos.agregaInicio(elemento);
    }

    /**
     *  Agrega un elemento al final de la lista, esto nos sirve por si  queremos darle jerarquia sobre otros elementos
     *  de la hoja
     *
     *  @param elemento   la representacion en texto del elemento que queremos agregar
     *  */
    public void agregaElementoFinal(String elemento){
        elementos.agregaFinal(elemento);
    }

    /**
     *  Crea la representacion en texto de la hoja que hemos estado creando.
     *  */
    @Override
    public String toString(){
        StringBuffer string = new StringBuffer();
        string.append(inicioSVG);
        string.append(dimensiones);
        for(String elemento : elementos){
            string.append(elemento);
            string.append("\n");
        }
        string.append(finSVG);
        return string.toString();
    }
}
