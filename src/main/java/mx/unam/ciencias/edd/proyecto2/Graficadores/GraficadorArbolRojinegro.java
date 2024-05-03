package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.Color;

/**
 * Graficador para arboles ordenados
 * Implementa los metodos necesarios para graficar los nodos de este tipo de arbol y llamar al constructor adecuado para el mismo
 *
 */
public class GraficadorArbolRojinegro extends GraficadorArbol{

    public GraficadorArbolRojinegro(Lista<Integer> cuerpo){
        construirArbol(cuerpo);
    }

    @Override
    protected void construirArbol(Lista<Integer> cuerpo) {
        arbol = new ArbolRojinegro<>(cuerpo);
    }

    @Override
    protected String graficarVertice(VerticeArbolBinario<Integer> vertice, int centroX, int centroY, int radio) {
        Color color = ((ArbolRojinegro)arbol).getColor(vertice);
        String colorString = color == Color.NEGRO ? "black" : "red";
        return String.format("<circle cx='%d' cy='%d' r='%d' stroke='%s' stroke-width='3' fill='%s' />",
                centroX, centroY, radio, "black", colorString) +
            String.format("<text x='%d' y='%d' text-anchor='middle'" +
                " font-family='sans-serif' font-size='%d' fill='%s'>%s</text>",
                centroX, centroY + 5, 15, "white", vertice.get().toString());
    }


}
