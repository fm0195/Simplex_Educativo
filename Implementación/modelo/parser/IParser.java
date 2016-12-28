package modelo.parser;

import modelo.DtoSimplex;

/**
 *
 * @author Fernando Molina
 * Una interfaz para implementar el metodo de parseo de un string con el 
 * formato valido de un problema lineal utilizado por el profesor Jose Helo.
 */
public interface IParser {
    /**
     * Parsea un string y retorna un DTO con la informacion representada.
     * @param value el string que representa el problema lineal. 
     * @return un DTO con la informacion del string representada como matriz. 
     */
    public DtoSimplex parse(String value);
}
