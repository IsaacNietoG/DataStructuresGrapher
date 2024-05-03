package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeArbolBinario;

/**
 * Graficador para arboles AVL
 *
 * Solamente realiza la modifcacion de colocar una etiqueta de texto aclarando el balance y altura del vertice
 */
public class GraficadorArbolAVL extends GraficadorArbol{

    public GraficadorArbolAVL(Lista<Integer> cuerpo){
        construirArbol(cuerpo);
    }

    @Override
    protected void construirArbol(Lista<Integer> cuerpo) {
        arbol = new ArbolAVL<>(cuerpo);

    }

    /**
     *  Crea un nodo simple, pero le concatena una etiqueta en la parte superior con el balance y altura del mismo
     *  */
    @Override
    protected String graficarVertice(VerticeArbolBinario<Integer> vertice, int centroX, int centroY, int radio) {
        String balance = String.valueOf(vertice.izquierdo().altura() - vertice.derecho().altura());

        return String.format("<circle cx='%d' cy='%d' r='%d' stroke='%s' stroke-width='3' fill='%s' />",
                centroX, centroY, radio, "black", "white") +
            String.format("<text x='%d' y='%d' text-anchor='middle'" +
                " font-family='sans-serif' font-size='%d' fill='%s'>%s</text>",
                centroX, centroY + 5, 15, "black", vertice.get().toString()) +
            String.format("<text x='%d' y='%d' text-anchor='middle'" +
                " font-family='sans-serif' font-size='%d' fill='%s'>%s</text>",
                centroX, centroY + 20, 9, "black", "(" + vertice.altura() + "/" + balance + ")");
    }
}
