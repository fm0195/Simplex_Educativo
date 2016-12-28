package Numeracion;

/**
 *
 * @author Yordan Jiménez
 */
public class Fraccion extends AbstractFraccion {

    public Fraccion(int numerador, int denominador) {
        super(numerador, denominador);
    }

    public Fraccion(int numerador) {
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
        return numero1;
    }
}
