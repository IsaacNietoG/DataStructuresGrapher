package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.Lista;

/**
 * Graficador para arboles completos
 *
 * Como clase concreta de GraficadorArbol, solamente implementa los metodos necesarios para graficar a esta estructura de datos
 * siendo:
 *
 * 1. El constructor, que llama a la construccion del arbol con el cuerpo correspondiente
 * 2. construirArbol() que utiliza el constructor de ArbolBinarioCompleto que recibe una colección siendo este el cuerpo recibido
 * 3. graficarVertice, el cual crea un nodo sencillo, solamente especificando el elemento el interior del mismo, pues un arbol
 * completo no posee más información que esto en sus nodos.
 */
public class GraficadorArbolCompleto extends GraficadorArbol{

    public GraficadorArbolCompleto(Lista<Integer> cuerpo){
        construirArbol(cuerpo);
    }

    @Override
    protected void construirArbol(Lista<Integer> cuerpo) {
        arbol = new ArbolBinarioCompleto<>(cuerpo);
    }

    /**
     *  Crea un nodo simple, sin información adicional sobre el mismo.
     *  */
    @Override
    protected String graficarVertice(VerticeArbolBinario<Integer> vertice, int centroX, int centroY, int radio) {
        return String.format("<circle cx='%d' cy='%d' r='%d' stroke='%s' stroke-width='3' fill='%s' />",
                centroX, centroY, radio, "black", "white") +
            String.format("<text x='%d' y='%d' text-anchor='middle'" +
                " font-family='sans-serif' font-size='%d' fill='%s'>%s</text>",
                centroX, centroY + 5, 15, "black", vertice.get().toString());
    }
}
