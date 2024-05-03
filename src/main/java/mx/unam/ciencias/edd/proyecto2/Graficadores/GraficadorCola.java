
package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;

/**
 * Graficador para Colas
 *
 * Representa los nodos de la cola como rectángulos conectados por lineas, las cuales simbolizan la
 * dirección de la cola.
 * Debido a la naturaleza tan simple de dicha estructura, nos ahorramos crear una Cola, y en su lugar
 * considero que:
 * 1. Debido a la naturaleza first-in first-out de la Cola, el orden de los elementos de una Cola creada con
 * la lista que recibe el constructor preservaría el orden de la misma lista.
 * 2. Nos podemos ahorrar una vuelta para fabricar la cola considerando lo previo, si simplemente recorremos
 * la lista y vamos fabricando los nodos en ese orden.
 */
public class GraficadorCola implements Graficador{

    Lista<Integer> cola;
    public final String defFlecha = "<defs>\n<marker id='arrow' markerWidth='3' markerHeight='6' refX='3' refY='3' orient='auto'>\n<path d='M 0 0 L 3 3 L 0 6 z' fill='black' />\n</marker>\n</defs>";

    public GraficadorCola(Lista<Integer> cuerpo){
        cola = cuerpo;
    }

    @Override
    public HojaSVG darHoja() {
        HojaSVG retorno = new HojaSVG(60, (cola.getLongitud() * 50)-20);
        retorno.agregaElementoInicio(defFlecha);
        int cambioX = 0;
        IteradorLista<Integer> iterador = cola.iteradorLista();
        //Agregamos cada nodo
        while(iterador.hasNext()){
            retorno.agregaElementoFinal(crearSlot(iterador.next(), cambioX, 0));
            cambioX += 30;
            if(iterador.hasNext()){
                retorno.agregaElementoFinal(crearFlecha(cambioX, 35, cambioX+20, 35));
                cambioX += 20;
            }
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

    private String crearFlecha(int origenX, int origenY, int destinoX, int destinoY){
        return String.format("<line x1='%d' y1='%d' x2='%d' y2='%d' stroke='black' stroke-width='2' marker-end='url(#arrow)' />",
                             origenX, origenY, destinoX, destinoY);
    }
}
