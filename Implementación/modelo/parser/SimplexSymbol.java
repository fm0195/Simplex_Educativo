package modelo.parser;

/**
 *
 * @author Fernando Molina
 * Una clase que representa los posibles tokens para el parseo de un problema de
 * programacion lineal. 
 */
public class SimplexSymbol extends java_cup.runtime.Symbol {
  private int line;
  private int column;

    /**
     *
     * @param type el tipo de token. Un valor entero de la clase sym que 
     * representa el tipo de token encontrado. 
     * @param line linea donde se encuentra en el string original
     * @param column columna donde se encuentra en el string original
     */
    public SimplexSymbol(int type, int line, int column) {
    this(type, line, column, null);
  }

    /**
     *
     * @param type el tipo de token. Un valor entero de la clase sym que 
     * representa el tipo de token encontrado. 
     * @param line linea donde se encuentra en el string original
     * @param column columna donde se encuentra en el string original
     * @param value un valor que posee el token. Puede ser un valor numerico
     * en el caso de coeficientes o un string en el caso de variables. 
     */
    public SimplexSymbol(int type, int line, int column, Object value) {
    super(type, -1, -1, value);
    this.line = line;
    this.column = column;
  }

    /**
     * Convierte un simbolo a su representacion textual. 
     * @return representacion del simbolo en forma de string.
     */
    public String toString() {   
    return "Linea "+line+", Columna "+column+", Sym: "+sym+(value == null ? "" : (", Valor: '"+value+"'"));
  }
}
