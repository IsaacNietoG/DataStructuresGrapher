package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.MonticuloMinimo;
import mx.unam.ciencias.edd.ValorIndexable;

/**
 * Graficador para monticulos minimo
 *
 * A grosso modo, son simplemente dos graficadores envueltos, el graficador de Arreglos y el graficador
 * de arboles completos.
 *
 * Construimos la estructura monticulo minimo y recorremos su arreglo para construir una lista que enviaremos
 * al graficador de arreglos y al graficador de arboles completos, cada uno nos regresará la representación SVG,
 * y solamente las juntaremos en una sola hoja y ajustaremos las dimensiones correspondientes
 */
public class GraficadorMonticuloMinimo implements Graficador{

    MonticuloMinimo<ValorIndexable<Integer>> monticulo;
    GraficadorArreglo graficadorArreglo;
    GraficadorArbolCompleto graficadorArbolCompleto;

    public GraficadorMonticuloMinimo(Lista<Integer> cuerpo){
        //Convertimos nuestros Integers a valores indexables
        Lista<ValorIndexable<Integer>> listaAdaptada = new Lista<>();
        for(Integer integer : cuerpo){
            listaAdaptada.agrega(new ValorIndexable<Integer>(integer, integer.doubleValue()));
        }

        //Construimos el monticulo y el arbol completo
        monticulo = new MonticuloMinimo<>(listaAdaptada);

        Lista<Integer> arreglo = new Lista<>();

        //Lo vaciamos a una lista compatible con nuestros graficadores
        for(ValorIndexable<Integer> integer : monticulo){
            arreglo.agrega(integer.getElemento());
        }
        //Creamos los graficadores
        graficadorArbolCompleto = new GraficadorArbolCompleto(arreglo);
        graficadorArreglo = new GraficadorArreglo(arreglo);
    }

    @Override
    public HojaSVG darHoja() {
        //Recibe las hojas SVG de cada uno de los graficadores
        HojaSVG hojaArbol = graficadorArbolCompleto.darHoja();
        HojaSVG hojaArreglo = graficadorArreglo.darHoja();

        //Dimensiones de la hoja final.
        //El alto simplemente se preserva del arbol, se preserva el ancho de la más grande
        int alto = hojaArbol.getAlto();
        int ancho = hojaArbol.getAncho() > hojaArreglo.getAncho() ? hojaArbol.getAncho() : hojaArreglo.getAncho();

        //Construccion de la hoja final
        HojaSVG hojaFinal = new HojaSVG(alto, ancho);
        for(String elemento : hojaArbol.getElementos())
            hojaFinal.agregaElementoFinal(elemento);
        for(String elemento : hojaArreglo.getElementos())
            hojaFinal.agregaElementoFinal(elemento);

        return hojaFinal;
    }
}
