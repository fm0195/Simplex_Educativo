package pruebas;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(AgregarColumnasTest.class, 
              AgregarNombreVariablesTest.class,AgregarNombreWTest.class,
              AgregarNombresFilaTest.class, AgregarUnoMatrizTest.class,
              AgregarUnosTest.class, BuscarIndiceTest.class, 
              ConvertirDosFasesTest.class, CrearNombreFilaTest.class, 
              EsAcotadoTest.class, FraccionTest.class, ParserTest.class,
              SolucionadorSimplexCalcularRadiosTest.class, 
              SolucionadorSimplexCompletarProblemaTest.class,
              SolucionadorSimplexSiguientePasoTest.class, 
              SolucionadorSimplexSiguientesOperacionesTest.class,
              SolucionadorSimplexSolucionarTest.class,
              VerificarFactibilidadTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }
}  	
