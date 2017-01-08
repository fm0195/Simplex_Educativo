package modelo;

/**
 *
 * @author Yordan Jiménez
 */
public abstract class AbstractFraccion {

    private int numerador;
    private int denominador = 1;

    /**
     * Inicializa una instancia de la clase fracción con ambos valores
     * indicados.
     *
     * @param numerador Valor a insertar en el atributo numerador.
     * @param denominador Valor a insertar en el atributo denominador.
     */
    public AbstractFraccion(int numerador, int denominador) {
        if (denominador == 0) {
            throw new ArithmeticException("División entre 0 no permitida.");
        }
        int divisorComun = obtenerMayorDivisorComun(numerador, denominador);
        this.numerador = numerador / divisorComun;
        this.denominador = denominador / divisorComun;
        validarSignos();
    }

    /**
     * Inicializa una instancia de la clase fracción con el valor del numerador
     * indicado pero el denominador indicado como 0.
     *
     * @param numerador
     */
    public AbstractFraccion(double numerador) {
        if(numerador == Double.MAX_VALUE) {
            this.numerador = Integer.MAX_VALUE;
            this.denominador = 1;
            return;
        }
        String s = String.valueOf(numerador);
        int digitsDec = s.length() - 1 - s.indexOf('.');
        int denom = 1;
        for (int i = 0; i < digitsDec; i++) {
            numerador *= 10;    
            denom *= 10;
        }
        int divisorComun = obtenerMayorDivisorComun((int)(numerador), denom);
        this.numerador = ((int)(numerador)) / divisorComun;
        this.denominador = denom / divisorComun;
        validarSignos();
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
        double numerador1 = this.numerador;
        double denominador1 = this.denominador;
        double numerador2 = fraccion2.getNumerador();
        int denominador2 = fraccion2.getDenominador();
        return numerador1 / denominador1
                ==  numerador2 / denominador2;
    }

    /**
     * Compara si esta fracción es menor que la fracción 2 indicada por
     * parámetro.
     *
     * @param fraccion2 Fraccion con la que se va a comparar.
     * @return Valor booleano que indica si es menor o no.
     */
    public boolean menorQue(AbstractFraccion fraccion2) {
        double numerador1 = this.numerador;
        double denominador1 = this.denominador;
        double numerador2 = fraccion2.getNumerador();
        int denominador2 = fraccion2.getDenominador();
        return numerador1 / denominador1
                <  numerador2 / denominador2;
    }

    /**
     * Compara si esta fracción es mayor que la fracción 2 indicada por
     * parámetro.
     *
     * @param fraccion2 Fraccion con la que se va a comparar.
     * @return Valor booleano que indica si es mayor o no.
     */
    public boolean mayorQue(AbstractFraccion fraccion2) {
        double numerador1 = this.numerador;
        double denominador1 = this.denominador;
        double numerador2 = fraccion2.getNumerador();
        int denominador2 = fraccion2.getDenominador();
        return numerador1 / denominador1
                >  numerador2 / denominador2;
    }

    /**
     * Compara si esta fracción es mayor o igual que la fracción 2 indicada por
     * parámetro.
     *
     * @param fraccion2 Fraccion con la que se va a comparar.
     * @return Valor booleano que indica si es mayor igual o no.
     */
    public boolean mayorIgualQue(AbstractFraccion fraccion2) {
        double numerador1 = this.numerador;
        double denominador1 = this.denominador;
        double numerador2 = fraccion2.getNumerador();
        int denominador2 = fraccion2.getDenominador();
        return numerador1 / denominador1
                >=  numerador2 / denominador2;
    }

    /**
     * Compara si esta fracción es menor o igual que la fracción 2 indicada por
     * parámetro.
     *
     * @param fraccion2 Fraccion con la que se va a comparar.
     * @return Valor booleano que indica si es mwnor igual o no.
     */
    public boolean menorIgualQue(AbstractFraccion fraccion2) {
        double numerador1 = this.numerador;
        double denominador1 = this.denominador;
        double numerador2 = fraccion2.getNumerador();
        int denominador2 = fraccion2.getDenominador();
        return numerador1 / denominador1
                <=  numerador2 / denominador2;
    }

    /**
     * Clona la fracción, crea un nuevo objeto AbstractFraccion identico al
     * original.
     *
     * @return Clon del objeto.
     */
    public abstract AbstractFraccion clonar();

    /**
     * Niega el valorde una fracción,cambiando el signo que posee.
     */
    public void hacerNegativa() {
        if (numerador != 0) {
            this.numerador *= -1;
        }
    }

    /**
     * Retorna el valor del numerador de la fracción.
     *
     * @return Numerador de la fracción.
     */
    public int getNumerador() {
        return numerador;
    }

    /**
     * Retorna el valor del denominador de la fracción.
     *
     * @return Denominador de la fracción.
     */
    public int getDenominador() {
        return denominador;
    }
    /**
     * Genera el inverso multiplicativo de una fracción.
     * @return Fracción con numerador y denominador intercambiados.
     */
    public abstract AbstractFraccion obtenerInverso();
    
    /**
     * Separa la parte decimal de la parte entera de una fracción.
     * @return Fraccion con el valor de la parte entera.
     */
    public abstract AbstractFraccion obtenerParteEntera();
    
    /**
     * Separa la parte decimal de la parte entera de una fracción.
     * @return Fraccion con el valor de la parte decimal.
     */
    public abstract AbstractFraccion obtenerParteDecimal();

    /**
     * Valida los signos dejando el signo negativo en el numerador.
     */
    private void validarSignos() {
        if (this.numerador < 0 && this.denominador < 0
                || this.numerador > 0 && this.denominador < 0) {
            this.numerador *= -1;
            this.denominador *= -1;
        }
    }

    /**
     * Restorn el string con el siguiente formato.
     *          nuemrador/denominador
     * @return String con formato Fraccional.
     */
    public abstract String toString();
    
    /**
     * Retorna el String de la fracción indicando si desea el formato decimal o 
     * el fraccional
     * @param fraccional Indica el formato de salida.
     * @return String con el formato indicado
     */
    public abstract String toString(boolean fraccional);

}
