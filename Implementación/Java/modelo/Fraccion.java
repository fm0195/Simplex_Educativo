package modelo;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 *
 * @author Yordan Jiménez
 */
public class Fraccion extends AbstractFraccion implements Serializable {

    public Fraccion() {
        super(0, 1);
    }

    public Fraccion(int numerador, int denominador) {
        super(numerador, denominador);
    }

    public Fraccion(double numerador) {
        super(numerador);
    }

    @Override
    public AbstractFraccion sumar(AbstractFraccion operando) {
        int numerador = 0;
        int denominador = 0;
        numerador = (this.getNumerador() * operando.getDenominador())
                + (this.getDenominador() * operando.getNumerador());

        denominador = this.getDenominador() * operando.getDenominador();
        return new Fraccion(numerador, denominador);
    }

    @Override
    public AbstractFraccion restar(AbstractFraccion operando) {
        int numerador = 0;
        int denominador = 0;
        numerador = (this.getNumerador() * operando.getDenominador())
                - (this.getDenominador() * operando.getNumerador());

        denominador = this.getDenominador() * operando.getDenominador();
        return new Fraccion(numerador, denominador);
    }

    @Override
    public AbstractFraccion multiplicar(AbstractFraccion operando) {
        int numerador = 0;
        int denominador = 0;
        numerador = this.getNumerador() * operando.getNumerador();
        denominador = this.getDenominador() * operando.getDenominador();
        return new Fraccion(numerador, denominador);
    }

    @Override
    public AbstractFraccion dividir(AbstractFraccion operando) {
        int numerador = 0;
        int denominador = 0;
        if (esCero(operando)) {
            throw new ArithmeticException("División entre 0 no permitida.");
        }
        numerador = this.getNumerador() * operando.getDenominador();
        denominador = this.getDenominador() * operando.getNumerador();
        return new Fraccion(numerador, denominador);
    }

    @Override
    protected int obtenerMayorDivisorComun(int numero1, int numero2) {
        int valorTemporal;
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
        return getNumerador() + "/" + getDenominador();
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
            if (getNumerador() == 0) {
                return "0";
            } else {
                if (getDenominador() == 1) {
                    return String.valueOf((int) getNumerador());
                } else {
                    return new DecimalFormat("0.00").format(((double) getNumerador() / (double) getDenominador()));
                }
            }
        }
    }

    @Override
    public AbstractFraccion obtenerParteDecimal() {
        int nuevoNumerador = getNumerador() % getDenominador();
        return new Fraccion(nuevoNumerador, getDenominador());
    }

    @Override
    public AbstractFraccion obtenerParteEntera() {
        double monto = ((double) getNumerador()) / ((double) getDenominador());
        AbstractFraccion resultado = new Fraccion((int) Math.floor(monto));
        return resultado;
    }
}
