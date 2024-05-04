package mx.unam.ciencias.edd.proyecto2.Graficadores;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;

/**
 * Graficador para Pilas
 *
 * Las representa como si fueran una pila de nodos, siguiendo la regla de first-in last-out de colas.
 * Siendo asi que el elemento hasta arriba de la pila es justamente el proximo elemento a sacar de la misma
 *
 * Para optimizar, no creamos una pila en este graficador, en su lugar la lista que recibimos como cuerpo
 * la recorremos de manera inversa, aprovechando que nuestra implementación de lista es doblemente ligada
 * y que nuestro IteradorLista permite un recorrimiento al revés
 */
public class GraficadorPila implements Graficador{
    Lista<Integer> pila;

    public GraficadorPila(Lista<Integer> cuerpo){
        this.pila = cuerpo;
    }

    @Override
    public HojaSVG darHoja() {
        HojaSVG retorno = new HojaSVG(pila.getLongitud()*30, 60);
        int cambioY = 0;
        IteradorLista<Integer> iterador = pila.iteradorLista();
        iterador.end();
        while(iterador.hasPrevious()){
            retorno.agregaElementoFinal(crearSlot(iterador.previous(), 0, cambioY));
            cambioY += 30;
        }

        return retorno;
    }

    /**
     *  Crea un rectangulo de 30x60 que representa un nodo del arreglo
     *
     *  @param elemento    el elemento que contiene el nodo
     *  @param esquinaX    la esquina
     *  */
    private String crearSlot(Integer elemento, int esquinaX, int esquinaY){
        return String.format("<text x='%d' y='%d' text-anchor='middle'" +
                             " font-family='sans-serif' font-size='%d' fill='%s'>%s</text>",
                             esquinaX + 28, esquinaY + 20, 15, "black", elemento) +
            String.format("<rect width='%d' height='%d' x='%d'  y='%d' style='stroke-width:2;stroke:black;fill:white;fill-opacity:0.01'/>",
                          60, 30, esquinaX, esquinaY);
    }
}
