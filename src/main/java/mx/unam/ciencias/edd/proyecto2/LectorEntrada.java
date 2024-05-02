package mx.unam.ciencias.edd.proyecto2;

import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import mx.unam.ciencias.edd.proyecto2.excepciones.EntradaInvalidaException;
import mx.unam.ciencias.edd.proyecto2.excepciones.MasDeUnaEntradaException;

/**
 * Clase encargada de leer los dos tipos de entrada que puede recibir el proyecto.
 *
 * Recibe en su constructor dos objetos, uno siendo el {@link java.io.InputStreamReader} que tiene
 * la entrada estándar del programa. (El system.in) y un objeto File.
 *
 * Intentará leer ambos tipos de entrada pero regresará una excepción si se puede leer de ambas a la vez.
 * Esto para preservar la unicidad de la entrada.
 *
 * Su unico método público retorna una string con la entrada recibida, o las excepciones correspondientes.
 */
public class LectorEntrada {

    private File archivo;
    private InputStreamReader standardIn;

    public LectorEntrada(InputStreamReader standardIn, File archivo){
        this.standardIn = standardIn;
        this.archivo = archivo;
    }

    /**
     *  Lee las entradas proporcionadas al objeto y retorna una String con lo recopilado
     *
     *  @throws MasDeUnaEntradaException si se reciben más de dos entradas válidas
     *  @throws EntradaInvalidaException si de ninguna de las dos entradas se puede obtener algo
     *
     *  @return Una string con la entrada recibida por cualquiera de los dos medios
     *  */
    public String leerEntradas() throws MasDeUnaEntradaException, EntradaInvalidaException{
        String resultado;

        StringBuilder entradaStandar = leerEntradaEstandar();
        StringBuilder entradaArchivo = leerArchivo();

        if(entradaArchivo.length()>0 && entradaStandar.length()>0)
            throw new MasDeUnaEntradaException("Más de una entrada ha sido recibida.");

        if(entradaArchivo.length()== 0 && entradaStandar.length() == 0)
            throw new EntradaInvalidaException("No se ha podido obtener nada de la entrada proporcionada");

        resultado = entradaArchivo.toString() + entradaStandar.toString();
        return resultado;
    }

    /**
     * Lee la entrada estandar del programa y vacia todo a un objeto StringBuilder
     *
     * @return el objeto StringBuilder con la entrada obtenida
     */
    private StringBuilder leerEntradaEstandar(){
        StringBuilder retorno = new StringBuilder();

        try{
            if(!standardIn.ready()){
                standardIn.close();
            }
        }catch (IOException e){
        }
        BufferedReader reader = new BufferedReader(standardIn);
        String renglon;
        try{
            while((renglon = reader.readLine()) != null){
                retorno.append(renglon);
            }
        reader.close();
        }catch (IOException e){
        }

        return retorno;
    }

    /**
     *  Lee el archivo recibido como entrada y vacia todo a un StringBuilder
     *
     *  @return el objeto StringBuilder con la entrada obtenida
     *  */
    private StringBuilder leerArchivo(){
        StringBuilder retorno = new StringBuilder();

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(archivo));
        }catch(FileNotFoundException e){
            reader = null;
        }
        String renglon;
        try{
            while((renglon = reader.readLine()) != null){
                retorno.append(renglon);
            }
            reader.close();
        }catch (IOException e){
        }

        return retorno;

    }

}
