package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;

/**
 * Graficador para arreglos
 *
 * Aunque tecnicamente no es una clase implementada durante el curso, me interesa realizar un graficador de arreglos para luego
 * poder graficar más comodamente los monticulos minimos en su representacion de arreglo y los monticulos arreglo
 */
public class GraficadorArreglo implements Graficador{
    Integer[] arreglo;

    public GraficadorArreglo(Lista<Integer> cuerpo){
        arreglo = new Integer[cuerpo.getElementos()];
        IteradorLista<Integer> iterador = cuerpo.iteradorLista();
        for(int i = 0; i<arreglo.length ; i++){
            arreglo[i] = iterador.next();
        }
    }

    @Override
    public HojaSVG darHoja() {
        HojaSVG retorno = new HojaSVG(60, arreglo.length * 30);
        int cambioX = 0;
        for(Integer i : arreglo){
            retorno.agregaElementoFinal(crearSlot(i, cambioX, 0));
            cambioX += 30;
        }

        return retorno;
    }

    private String crearSlot(Integer elemento, int esquinaX, int esquinaY){
        return String.format("<text x='%d' y='%d' text-anchor='middle'" +
                             " font-family='sans-serif' font-size='%d' fill='%s'>%s</text>",
                             esquinaX + 15, esquinaY + 35, 15, "black", elemento) +
            String.format("<rect width='%d' height='%d' x='%d'  y='%d' style='stroke-width:2;stroke:black;fill:white;fill-opacity:0.01'/>",
                          30, 60, esquinaX, esquinaY);
    }

}
