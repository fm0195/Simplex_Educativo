package Modelo;

/**
 *
 * @author Yordan Jiménez
 */
public class SolucionadorSimplex extends AbstractSolucionadorSimplex{

    public SolucionadorSimplex() {
    }
    
    @Override
    public DtoSimplex[] solucionar(DtoSimplex dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DtoSimplex SiguientePaso(DtoSimplex dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractFraccion[] calcularRadio(DtoSimplex dto, int columna) {
        return obtenerRadios(dto.getFraccion(), columna);
    }
    
    /**
     * Obtiene el índice del valor menor que se encuentre dentro de la lista de 
     * fracciones.
     * @param valores Arreglo con las fracciones donde se va a buscar el valor 
     * menor.
     * @return Valor entro que representa el índice del valor menor del arreglo.
     */
    private int obtenerIndiceDelValorMenor(AbstractFraccion[] valores){
        int indice = 0;
        AbstractFraccion valorMenor = valores[0];
        AbstractFraccion valor;
        for (int contador = 1; contador < valores.length; contador++) {
            valor = valores[contador];
            if(valor.menorQue(valorMenor)){
                valorMenor = valor;
                indice = contador;
            }
        }
        return indice;
    }
    
    /**
     * * Aplica la división entre el lado derecho de un problema con una columna, 
     * la operación se realiza dividiendo posiciones correspondientes entre los 
     * índices de las listas.
     * @param fracciones Valores de las filas y columnas que representan a un 
     * problema de programación lineal.
     * @param columna Indice de la columna qcon la cual se generará los radio.
     * @return Arreglo de valores que representan los radios.
     */
    private AbstractFraccion[] obtenerRadios(AbstractFraccion[][] fracciones, 
            int columna){
        AbstractFraccion[] resultado = new Fraccion[fracciones.length];
        AbstractFraccion elementoLadoDerecho;
        AbstractFraccion elementoColumna;
        int ladoDerecho = fracciones[0].length;
        for (int contador = 0; contador < resultado.length; contador++) {
            elementoLadoDerecho = fracciones[contador][ladoDerecho];
            elementoColumna = fracciones[contador][columna];
            resultado[contador] = generarRadio(elementoLadoDerecho, elementoColumna);
        }
        return resultado;
    }
    
    /**
     * Genera el radio entre dos elementos, el lado derecho y la columna, si el 
     * elemento de la columna es menor o igual a cero, retorna una fracción con 
     * el valor máximo del tipo de dato Double que representa nuestro inifinito 
     * positivo.
     * @param ladoDerecho Fracción del lado derecho.
     * @param columna Fracción de la columna.
     * @return Fracción que representa el radio.
     */
    private AbstractFraccion generarRadio(AbstractFraccion ladoDerecho, 
            AbstractFraccion columna){
        AbstractFraccion cero = new Fraccion(0, 1);//Este valor representa al valor entero 0. 
        if (ladoDerecho.menorIgualQue(cero) ){
            return new Fraccion(Double.MAX_VALUE);
        } else{
            return ladoDerecho.dividir(columna);
        }
    }
}
