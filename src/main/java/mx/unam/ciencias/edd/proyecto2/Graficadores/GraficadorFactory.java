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

                break;

            case "ArbolBinarioCompleto":

                break;

            case "ArbolBinarioOrdenado":

                break;

            case "ArbolRojinegro":

                break;

            case "Arreglo":

                break;

            case "Cola":

                break;

            case "Grafica":

                break;

            case "Lista":

                break;

            case "MonticuloArreglo":

                break;

            case "MonticuloMinimo":

                break;

            case "Pila":

                break;

            default:
                throw new EstructuraNoEncontradaException("La estructura declarada en la petición no cuenta con un constructor.");
        }

        return retorno;

    }
}
