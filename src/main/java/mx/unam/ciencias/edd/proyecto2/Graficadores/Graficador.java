package mx.unam.ciencias.edd.proyecto2.Graficadores;

/**
 * Interface que modela el comportamiento que debe tener un graficador de este proyecto
 *
 * A grandes rasgos, lo unico que debe estar modelado es que debe retornar una hoja SVG con la gráfica de la estructura
 */
public interface Graficador {

    public HojaSVG darHoja();
}
