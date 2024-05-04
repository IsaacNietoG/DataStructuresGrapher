package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.IteradorLista;
/**
 * Graficador para listas
 *
 * Representa los nodos y sus dobles conexiones, al ser listas doblemente ligadas
 *
 * Como desde un inicio, la estructura de datos que formamos y que integra nuestra Peticion para el
 * GraficadorFactory es una lista, no necesitamos crear la estructura de datos subyacente.
 *
 */
public class GraficadorLista implements Graficador{
    Lista<Integer> lista;
    //Definicion para el triangulo de la punta de la flecha
    public final String defFlecha = "<defs>\n<marker id='arrow' markerWidth='3' markerHeight='6' refX='3' refY='3' orient='auto'>\n<path d='M 0 0 L 3 3 L 0 6 z' fill='black' />\n</marker>\n</defs>";

    public GraficadorLista(Lista<Integer> cuerpo){
        lista = cuerpo;
    }

    @Override
    public HojaSVG darHoja() {
        HojaSVG retorno = new HojaSVG(60, (lista.getLongitud() * 50)-20);
        retorno.agregaElementoInicio(defFlecha);
        int cambioX = 0;
        IteradorLista<Integer> iterador = lista.iteradorLista();
        //Agregamos cada nodo
        while(iterador.hasNext()){
            retorno.agregaElementoFinal(crearSlot(iterador.next(), cambioX, 0));
            cambioX += 30;
            if(iterador.hasNext()){
                retorno.agregaElementoFinal(crearFlecha(cambioX, 25, cambioX+20, 25));
                retorno.agregaElementoFinal(crearFlecha(cambioX+20, 35, cambioX, 35));
                cambioX += 20;
            }
        }

        return retorno;
    }

    /**
     *  Crea el rectangulo que representa el nodo de la lista con su elemento
     *  */
    private String crearSlot(Integer elemento, int esquinaX, int esquinaY){
        return String.format("<text x='%d' y='%d' text-anchor='middle'" +
                             " font-family='sans-serif' font-size='%d' fill='%s'>%s</text>",
                             esquinaX + 15, esquinaY + 35, 15, "black", elemento) +
            String.format("<rect width='%d' height='%d' x='%d'  y='%d' style='stroke-width:2;stroke:black;fill:white;fill-opacity:0.01'/>",
                          30, 60, esquinaX, esquinaY);
    }

    /**
     *  Crea una flecha
     *  */
    private String crearFlecha(int origenX, int origenY, int destinoX, int destinoY){
        return String.format("<line x1='%d' y1='%d' x2='%d' y2='%d' stroke='black' stroke-width='2' marker-end='url(#arrow)' />",
                             origenX, origenY, destinoX, destinoY);
    }

}
