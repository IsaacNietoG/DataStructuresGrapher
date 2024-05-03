package mx.unam.ciencias.edd.proyecto2.Graficadores;

import mx.unam.ciencias.edd.proyecto2.excepciones.EstructuraNoEncontradaException;

/**
 * Factory de los graficadores que usará el proyecto para cada una de las estructuras de datos.
 * Recibe una petición y retorna el graficador adecuado a la misma, ya con la información necesaria para que el mismo
 * pueda trabajar en nuestro metodo main
 *
 *
 */
public class GraficadorFactory {


    public static Graficador crearGraficador(Peticion peticion) throws EstructuraNoEncontradaException{
        Graficador retorno;
        switch(peticion.estructura){
            case "ArbolAVL":
                retorno = new GraficadorArbolAVL(peticion.cuerpo);
                break;

            case "ArbolBinarioCompleto":
                retorno = new GraficadorArbolCompleto(peticion.cuerpo);
                break;

            case "ArbolBinarioOrdenado":
                retorno = new GraficadorArbolOrdenado(peticion.cuerpo);
                break;

            case "ArbolRojinegro":
                retorno = new GraficadorArbolRojinegro(peticion.cuerpo);
                break;

            case "Arreglo":
                retorno = null;
                break;

            case "Cola":
                retorno = null;
                break;

            case "Grafica":
                retorno = null;
                break;

            case "Lista":
                retorno = null;
                break;

            case "MonticuloArreglo":
                retorno = null;
                break;

            case "MonticuloMinimo":
                retorno = null;
                break;

            case "Pila":
                retorno = null;
                break;

            default:
                throw new EstructuraNoEncontradaException("La estructura declarada en la petición no cuenta con un constructor.");
        }

        return retorno;

    }
}
