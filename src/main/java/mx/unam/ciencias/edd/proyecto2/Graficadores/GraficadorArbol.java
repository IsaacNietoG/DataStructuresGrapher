package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.Pila;

/**
 * Clase abstracta que modela todos los comportamientos en comun que tienen los graficadores de arboles. Asi mismo, tiene
 * el metodo comun que desencadena todo el proceso para comenzar a graficar la estructura. (Siendo darHoja)
 * Sigue el patron de diseñó Template
 *
 */
public abstract class GraficadorArbol implements Graficador{

    /**
     * Par de coordenadas para uso interno rapido de las coordenadas de los vertices */
    private class ParCoord{
        public int x;
        public int y;

        public ParCoord(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    ArbolBinario<Integer> arbol;
    Lista<Integer> fuente;
    HojaSVG resultado;
    Pila<VerticeArbolBinario<Integer>> pilaVertices = new Pila<>();
    Pila<Integer> pilaNivel = new Pila<>();
    Pila<ParCoord> pilaCoords = new Pila<>();

    /**
     *  Metemos toda la rama izquierda del vertice en las dos pilas correspondientes. Esto para seguir el recorrido DFS que usaremos
     *  para graficar el arbol
     *
     *  @param vertice   el vertice del que queremos meter su rama izquierda
     *  @param nivel     el nivel de dicho vertice
     *  */
    private void meteRamaIzquierda(VerticeArbolBinario<Integer> vertice, int nivel){
        if(vertice == null)
            return;

        VerticeArbolBinario<Integer> target = vertice;
        int nivelTarget = nivel;

        while(target.hayIzquierdo()){
            pilaVertices.mete(target);
            pilaNivel.mete(++nivelTarget);
            target = target.izquierdo();
        }

    }

    //Auxiliar para verificar si un vertice es hijo derecho
    protected boolean esDerecho(VerticeArbolBinario<Integer> vertice){
        if (vertice == arbol.raiz())
            return false;

        if (!vertice.padre().hayDerecho())
            return false;

        return vertice.padre().derecho() == vertice;
    }

    //Auxiliar para verificar si un vertice es hijo izquierdo
    protected boolean esIzquierdo(VerticeArbolBinario<Integer> vertice){
        if (vertice == arbol.raiz())
            return false;

        if (!vertice.padre().hayIzquierdo())
            return false;

        return vertice.padre().izquierdo() == vertice;
    }

    /**

     *  Desencadena el proceso de graficado de la hoja SVG del arbol. Este es el metodo principal que desencadena todo el proceso
     *  segun el patron de diseño Template. A su vez, es el metodo en comun que tiene este graficador con el resto de los graficadores
     *
     *
     *  */
    public HojaSVG darHoja(){
        resultado = new HojaSVG(calcularAlto(), calcularAncho());

        //Recorrido DFS porque casi chillo por intentar hacerlo BFS
        VerticeArbolBinario<Integer> target = arbol.raiz();
        meteRamaIzquierda(target, 0);

        int coordX = 0;
        //Desplazamiento despues de cada insercion
        int cambioAltura = 25 * 2 + 50;
        int cambioAncho = 25 * 2 + 30;

        while(!pilaVertices.esVacia()){
            target = pilaVertices.saca();
            int nivel = pilaNivel.saca();

            int coordY = nivel * cambioAltura + 25;

            resultado.agregaElementoFinal(graficarVertice(target, coordX, coordY, 25));
            coordX += cambioAncho;

            if(target.hayDerecho())
                meteRamaIzquierda(target.derecho(), nivel+1);

            resultado.agregaElementoInicio(conectaVertice(target, coordX, coordY));
        }


        return resultado;
    }

    /**
     *  Conecta un vertice con sus vecinos inmediatos, aprovechamos que el recorrido DFS nos permite hacer esto, ya que
     *  siempre tendremos cerca en la pila a los vecinos de un vertice que acabamos de sacar
     *
     *  @param vertice   el vertice a conectar
     *  @param coordX    la coordX de la representacion grafica del vertice a conectar
     *  @param coordY    la coordY de la representacion grafica del vertice a conectar
     *  */
    protected String conectaVertice(VerticeArbolBinario<Integer> vertice, int coordX, int coordY){

        String lineas = "";

        //Como es DFS in-order, sabemos que su hijo izquierdo fue graficado inmediatamente antes
        if(vertice.hayIzquierdo()){
            ParCoord hijoIzq = pilaCoords.saca();
            lineas += graficarLinea(hijoIzq.x, hijoIzq.y, coordX, coordY);
        }

        //Si es hijo derecho de otro vertice, ese otro vertice tambien fue graficado antes, justamente antes del caso ya cubierto
        if(esDerecho(vertice)){
            ParCoord padre = pilaCoords.saca();
            lineas += graficarLinea(padre.x, padre.y, coordX, coordY);
        }

        //Preparacion para la siguiente ronda de conexiones
        //El hijo derecho del vértice actual es el que se encarga de la conexion entre ambos, guardamos las coordenadas del actual
        //para este paso
        if(vertice.hayDerecho())
            pilaCoords.mete(new ParCoord(coordX, coordY));

        //Si es izquierdo de otro, ese otro será graficado inmediatamente despues, asi que nos guardamos en la pila.
        if(esIzquierdo(vertice))
            pilaCoords.mete(new ParCoord(coordX, coordY));

        return lineas;
    }

    /**
     *  Hook para especficar en las clases concretas cuál constructor de cuál tipo de arbol queremos utilizar para crear el arbol
     *  binario que graficaremos
     *
     *  @param cuerpo   una lista de los elementos que contendrá el arbol binario
     *  */
    protected abstract void construirArbol(Lista<Integer> cuerpo);

    //Metodo hook para especificar como queremos dibujar cada vertice segun el arbol binario. Aqui agregamos los extras de cada
    //tipo de vertice posible
    protected abstract String graficarVertice(VerticeArbolBinario<Integer> vertice, int centroX, int centroY, int radio);

    /**
     *  Calcula el alto necesario de la hoja SVG para albergar todos los elementos del arbol, esto en base a la altura del mismo
     *
     *  @return el alto en pixeles de la hoja SVG
     *  */
    protected int calcularAlto(){
        return arbol.altura() * 80;
    }

    /**
     *  Calcula el ancho necesario de la hoja SVG para albergar todos los elementos del arbol, esto en base al ancho del ultimo
     *  nivel del mismo, que es el que en teoría debe albergar el doble de elementos que el resto del arbol
     *
     *  @return el ancho en pixeles de la hoja SVG
     *  */
    protected int calcularAncho(){
        return (arbol.getElementos() / 2) * 50;
    }

    /**
     * Grafica una linea entre dos vertices del arbol, dados sus centros
     *  */
    protected String graficarLinea(int origenX, int origenY, int destinoX, int destinoY){
        return String.format("<line x1='%d' y1='%d' x2='%d' y2='%d'" +
                " stroke='%s' stroke-width='3' />",
                origenX, origenY, destinoX, destinoY, "black");
    }

}
