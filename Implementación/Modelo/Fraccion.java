package Modelo;

/**
 *
 * @author Yordan Jiménez
 */
public class Fraccion extends AbstractFraccion {

    public Fraccion(double numerador, double denominador) {
        super(numerador, denominador);
    }

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
     * @param fraccion Fracción a validar si es cero.
     * @return Valor booleano que indica si es cero.
     */
    private boolean esCero(AbstractFraccion fraccion) {
        AbstractFraccion cero = new Fraccion(0, 1);
        return fraccion.iguales(cero);
    }
}
