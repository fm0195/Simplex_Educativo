package Numeracion;

/**
 *
 * @author Yordan Jiménez
 */
public abstract class AbstractFraccion {

    private int _numerador;
    private int _denominador = 1;

    /**
     * Inicializa una instancia de la clase fracción con ambos valores
     * indicados.
     *
     * @param numerador Valor a insertar en el atributo numerador.
     * @param denominador Valor a insertar en el atributo denominador.
     */
    public AbstractFraccion(int numerador, int denominador) {
        int divisorComun = obtenerMayorDivisorComun(numerador, denominador);
        this._numerador = numerador / divisorComun;
        this._denominador = denominador / divisorComun;
    }

    /**
     * Inicializa una instancia de la clase fracción con el valor del numerador
     * indicado pero el denominador indicado como 0.
     *
     * @param numerador
     */
    public AbstractFraccion(int numerador) {
        this._numerador = numerador;
    }

    /**
     * Suma dos fracciones devolviendo una nueva instancia de fracción.
     *
     * @param operando Fracción del segundo operando con el cual se realizará la
     * operación.
     * @return Fracción con el nuevo resultado después de operar ambas
     * fracciones.
     */
    public abstract AbstractFraccion sumar(AbstractFraccion operando);

    /**
     * Resta dos fracciones devolviendo una nueva instancia de fracción.
     *
     * @param operando Fracción del segundo operando con el cual se realizará la
     * operación.
     * @return Fracción con el nuevo resultado después de operar ambas
     * fracciones.
     */
    public abstract AbstractFraccion restar(AbstractFraccion operando);

    /**
     * Multiplica dos fracciones devolviendo una nueva instancia de fracción.
     *
     * @param operando Fracción del segundo operando con el cual se realizará la
     * operación.
     * @return Fracción con el nuevo resultado después de operar ambas
     * fracciones.
     */
    public abstract AbstractFraccion multiplicar(AbstractFraccion operando);

    /**
     * Divide dos fracciones devolviendo una nueva instancia de fracción.
     *
     * @param operando Fracción del segundo operando con el cual se realizará la
     * operación.
     * @return Fracción con el nuevo resultado después de operar ambas
     * fracciones.
     */
    public abstract AbstractFraccion dividir(AbstractFraccion operando);

    /**
     * Obtiene el divosor mayor existente entre los números indicados por
     * parámetro, utilizando el algoritmo de euclides.
     *
     * @param numero1 Valor numérico para comparar.
     * @param numero2 Valor numérico para comparar.
     * @return Volor numérico que representa el divisor mayor existente.
     * @see https://en.wikipedia.org/wiki/Euclidean_algorithm
     */
    protected abstract int obtenerMayorDivisorComun(int numero1, int numero2);

    
    /**
     * Compara la igualdad entre dos fracciones.
     *
     * @param fraccion2 Fraccion con la que se va a comparar.
     * @return Valor booleano que indica si son iguales o no.
     */
    public boolean iguales(AbstractFraccion fraccion2) {
        return this._numerador == fraccion2.getNumerador()
                && this._denominador == fraccion2.getDenominador();
    }
    
    /**
     * Retorna el valor del numerador de la fracción.
     *
     * @return Numerador de la fracción.
     */
    public int getNumerador() {
        return _numerador;
    }

    /**
     * Retorna el valor del denominador de la fracción.
     *
     * @return Denominador de la fracción.
     */
    public int getDenominador() {
        return _denominador;
    }

}
