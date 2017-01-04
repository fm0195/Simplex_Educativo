/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileReader;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;
import modelo.parser.IParser;
import modelo.parser.SimplexParser;

/**
 *
 * @author jfmolina
 */
public class Simplex_Educativo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        IParser parser = new SimplexParser();
        DtoSimplex res = parser.parse("(0) max z = -15/4 x1 + -10 x2 + x4 + x5 + x6\n" +
                        "(1) -1 x1        <= -2\n" +
                        "(2) -3     x2    >= -4\n" +
                        "(3) -5 x1 + -6 x2 = -7");
        System.out.println(res);
    }
    
}
