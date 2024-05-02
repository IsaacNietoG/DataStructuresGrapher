package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.Graficadores.Peticion;
import mx.unam.ciencias.edd.proyecto2.excepciones.MasDeUnaEstructuraDeclaradaException;
import mx.unam.ciencias.edd.proyecto2.excepciones.EstructuraVaciaException;

/**
 * Formador de peticiones para la factory de Graficadores
 *
 * Recibe la string obtenida de la entrada del usuario y la convierte en una Peticion, que será la que luego pasará a la Factory de
 * graficadores.
 *
 * Al ser el objeto encargado de formar la petición, es el encargado de detectar si existe algo mal con la misma y lanzar la
 * excepción correspondiente.
 */
public class FormadorPeticiones {

    private BufferedReader lector;
    private Lista<Integer> elementos;
    private String estructura;

    public FormadorPeticiones(String input){
        lector = new BufferedReader(new StringReader(input));
        elementos = new Lista<>();
        estructura = "";
    }

    public Peticion formarPeticion() throws IOException, MasDeUnaEstructuraDeclaradaException, EstructuraVaciaException{
        recorrerEntrada();
        if(elementos.esVacia())
            throw new EstructuraVaciaException("La estructura dada por la entrada no contiene elementos");

        return new Peticion(estructura, elementos);
    }

    private void recorrerEntrada() throws IOException, MasDeUnaEstructuraDeclaradaException{
        char[] linea;
        //Iteracion por linea
        while((linea = lector.readLine().toCharArray()) != null){
            //Iteracion por caracter
            for(int i = 0; i<linea.length; i++){
                char caracter = linea[i];
                if(caracter == '#') // Para ignorar la documentacion de la entrada
                    break;

                else if(Character.isDigit(caracter)){ //Si detecta un elemento, lo empieza a capturar
                    String numero = "";
                    while(Character.isDigit(caracter)){
                        numero += caracter;
                        caracter = linea[i++];
                    }
                    elementos.agrega(Integer.parseInt(numero));
                }

                else if(Character.isAlphabetic(caracter)){ //Si detecta un caracter, es el inicio del nombre de la estructura, idem
                    if(!estructura.isEmpty()) //Si ya fue capturada una estructura, lanza la excepcion correspondiente
                        throw new MasDeUnaEstructuraDeclaradaException("La entrada recibida tiene declaradas dos o más estructuras para formar, verifique su entrada");

                    String preEstructura = "";
                    while(Character.isAlphabetic(caracter)){
                        preEstructura += caracter;
                        caracter = linea[i++];
                    }
                    estructura = preEstructura;
                }
            }
        }
    }

}
