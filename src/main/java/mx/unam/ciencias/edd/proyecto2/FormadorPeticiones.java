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

    /**
     *  Ordena el recorrido de la entrada con el metodo correspondiente, y luego crea la peticion correspondiente con los datos
     *  obtenidos de la misma.
     *
     *  @throws MasDeUnaEstructuraDeclaradaException  si se encuentran más de dos declaraciones de estructura en el archivo,
     *  pues se espera que solo se declare una estructura por archivo
     *  @throws EstructuraVaciaException   si no se encuentran elementos para agregar a la estructura
     *  */
    public Peticion formarPeticion() throws IOException, MasDeUnaEstructuraDeclaradaException, EstructuraVaciaException{
        recorrerEntrada();
        if(elementos.esVacia())
            throw new EstructuraVaciaException("La estructura dada por la entrada no contiene elementos");

        return new Peticion(estructura, elementos);
    }

    private void recorrerEntrada() throws IOException, MasDeUnaEstructuraDeclaradaException{
        String strlinea;
        char[] linea;
        //Iteracion por linea
        while((strlinea = lector.readLine()) != null){
            linea = strlinea.toCharArray();
            //Iteracion por caracter
            int i = 0;
            String numero = "";
            String preEstructura = "";
            while(i<linea.length){
                char caracter = linea[i];
                if(caracter == '#') // Para ignorar la documentacion de la entrada
                    break;
                if(caracter == ' ') //Detectamos un espacio significa que el objeto a capturar ha terminado
                    if(!numero.isEmpty()){
                        elementos.agrega(Integer.valueOf(numero));
                        numero = "";
                    }else if(!preEstructura.isEmpty())
                        if(estructura.isEmpty())
                            estructura = preEstructura;
                        else
                            throw new MasDeUnaEstructuraDeclaradaException("La entrada recibida tiene declaradas dos o más estructuras para formar, verifique su entrada");

                if(Character.isDigit(caracter)){ //Si detecta un elemento, lo empieza a capturar
                    numero += caracter;
                }

                if(Character.isAlphabetic(caracter)){ //Si detecta un caracter, es el inicio del nombre de la estructura, idem
                    preEstructura += caracter;
                }

                i++;
            } //Ultima verificacion, por si se nos queda un elemento pendiente
            if(!numero.isEmpty()){
                elementos.agrega(Integer.valueOf(numero));
                numero = "";
            }else if(!preEstructura.isEmpty())
                if(estructura.isEmpty())
                    estructura = preEstructura;
                else
                    throw new MasDeUnaEstructuraDeclaradaException("La entrada recibida tiene declaradas dos o más estructuras para formar, verifique su entrada");
        }
    }

}
