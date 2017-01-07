package modelo;

import modelo.AbstractFraccion;

/**
 *
 * @author Yordan Jiménez
 */
public class Fraccion extends AbstractFraccion {

    /**
     * Crea una fraccion nueva con valor 0
     */
    public Fraccion() {
        super(0, 1);
    }

    /**
     * Crea una fraccion nueva con los parametros enviados
     *
     * @param numerador numerador de la fraccion
     * @param denominador denominador de la fraccion
     */
    public Fraccion(double numerador, double denominador) {
        super(numerador, denominador);
    }

    /**
     * Crea una fracción con el numerador indicado. Se encarga de convertir
     * dicho numero decimal a fraccionario.
     *
     * @param numerador numerador de la fraccion
     */
    public Fraccion(double numerador) {
        super(numerador);
    }

    @Override
    public AbstractFraccion sumar(AbstractFraccion operando) {
        double numerador = 0;
        double denominador = 0;
        numerador = (this.getNumerador() * operando.getDenominador())
                + (this.getDenominador() * operando.getNumerador());

        denominador = this.getDenominador() * operando.getDenominador();
        return new Fraccion(numerador, denominador);
    }

    @Override
    public AbstractFraccion restar(AbstractFraccion operando) {
        double numerador = 0;
        double denominador = 0;
        numerador = (this.getNumerador() * operando.getDenominador())
                - (this.getDenominador() * operando.getNumerador());

        denominador = this.getDenominador() * operando.getDenominador();
        return new Fraccion(numerador, denominador);
    }

    @Override
    public AbstractFraccion multiplicar(AbstractFraccion operando) {
        double numerador = 0;
        double denominador = 0;
        numerador = this.getNumerador() * operando.getNumerador();
        denominador = this.getDenominador() * operando.getDenominador();
        return new Fraccion(numerador, denominador);
    }

    @Override
    public AbstractFraccion dividir(AbstractFraccion operando) {
        double numerador = 0;
        double denominador = 0;
        if (esCero(operando)) {
            throw new ArithmeticException("División entre 0 no permitida.");
        }
        numerador = this.getNumerador() * operando.getDenominador();
        denominador = this.getDenominador() * operando.getNumerador();
        return new Fraccion(numerador, denominador);
    }

    @Override
    protected int obtenerMayorDivisorComun(double numero1, double numero2) {
        double valorTemporal;
        while (numero2 != 0) {
            valorTemporal = numero2;
            numero2 = numero1 % numero2;
            numero1 = valorTemporal;
        }
        return (int) numero1;
    }

    /**
     * Valida si la fracción indicada es cero.
     *
     * @param fraccion Fracción a validar si es cero.
     * @return Valor booleano que indica si es cero.
     */
    private boolean esCero(AbstractFraccion fraccion) {
        AbstractFraccion cero = new Fraccion(0, 1);
        return fraccion.iguales(cero);
    }

    @Override
    public AbstractFraccion clonar() {
        return new Fraccion(getNumerador(), getDenominador());
    }

    @Override
    public String toString() {
        return (int) getNumerador() + "/" + (int) getDenominador();
    }

    @Override
    public AbstractFraccion obtenerInverso() {
        return new Fraccion(getDenominador(), getNumerador());
    }

    @Override
    public String toString(boolean fraccional) {
        if (fraccional) {
            if (getNumerador() == 0) {
                return "0";
            } else {
                if (getDenominador() == 1) {
                    return String.valueOf((int) getNumerador());
                } else {
                    return toString();
                }
            }
        } else {
            return String.format("%.2f", (getNumerador() / getDenominador()));
        }
    }
}
