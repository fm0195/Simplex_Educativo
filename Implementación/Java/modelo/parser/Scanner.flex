package simplex_educativo;
import java_cup.runtime.*;
import modelo.Fraccion;

%%
%public
%class Scanner
%unicode
%line
%column
%cup
%cupdebug
%{
  private Symbol symbol(int type) {
    return new SimplexSymbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new SimplexSymbol(type, yyline+1, yycolumn+1, value);
  }
%}

CambioLinea = \r|\n|\r\n

EspacioBlanco = [ \t\f]

Variable = [:jletter:][:jletterdigit:]*

NumRestriccion = \([0-9]+\)

NumeroEntero = 0 | [1-9][0-9]*
    
NumeroDecimal  = [0-9]+ \. [0-9]* 

NumeroFraccionario  = {NumeroEntero} \/ {NumeroEntero}

%%

<YYINITIAL> {
  "+"                   { return symbol(sym.MAS); }
  "-"                   { return symbol(sym.MENOS); }
  ">="                  { return symbol(sym.MAYORIGUAL); }
  "<="                  { return symbol(sym.MENORIGUAL); }
  "="                   { return symbol(sym.IGUAL); }
  "max"                 { return symbol(sym.MAX); }
  "min"                 { return symbol(sym.MIN); }  
  {CambioLinea}         { return symbol(sym.CAMBIOLINEA); }
  {EspacioBlanco}       { /* Ignorar */ }
  {Variable}            { return symbol(sym.VARIABLE, yytext()); }
  {NumRestriccion}      { return symbol(sym.NUMRESTRICCION, yytext()); }
  {NumeroEntero}        { return symbol(sym.COEFICIENTE, new Fraccion(Integer.parseInt(yytext()))); }
  {NumeroDecimal}       { return symbol(sym.COEFICIENTE, new Fraccion(Double.parseDouble(yytext()))); }
  {NumeroFraccionario}  {   
                        String[] split = yytext().split("/");
                        int numerador = Integer.parseInt(split[0]);   
                        int denominador = Integer.parseInt(split[1]);
                        return symbol(sym.COEFICIENTE, new Fraccion(numerador, denominador)); 
                        }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(sym.EOF); }