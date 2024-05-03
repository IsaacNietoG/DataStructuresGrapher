package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeArbolBinario;

/**
 * Graficador para arboles ordenados
 * Implementa los metodos necesarios para graficar los nodos de este tipo de arbol y llamar al constructor adecuado para el mismo
 *
 */
public class GraficadorArbolOrdenado extends GraficadorArbol{

    public GraficadorArbolOrdenado(Lista<Integer> cuerpo){
        construirArbol(cuerpo);
    }

    @Override
    protected void construirArbol(Lista<Integer> cuerpo) {
        arbol = new ArbolBinarioOrdenado<>(cuerpo);
    }

    @Override
    protected String graficarVertice(VerticeArbolBinario<Integer> vertice, int centroX, int centroY, int radio) {
        return String.format("<circle cx='%d' cy='%d' r='%d' stroke='%s' stroke-width='3' fill='%s' />",
                centroX, centroY, radio, "black", "white") +
            String.format("<text x='%d' y='%d' text-anchor='middle'" +
                " font-family='sans-serif' font-size='%d' fill='%s'>%s</text>",
                centroX, centroY + 5, 15, "black", vertice.get().toString());
    }


}
