package pruebas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import modelo.AbstractFraccion;
import modelo.Fraccion;
import modelo.SolucionadorSimplex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yordan Jiménez
 */
@RunWith(Parameterized.class)
public class AgregarUnosTest {
    AbstractFraccion[][] matriz;
    ArrayList<Integer> filas;
    int inicio;
    boolean positivo;
    AbstractFraccion[][] valorCorrecto;

    public AgregarUnosTest(AbstractFraccion[][] matriz, ArrayList<Integer> filas,
            int inicio, boolean positivo, AbstractFraccion[][] valorCorrecto) {
        this.matriz = matriz;
        this.filas = filas;
        this.inicio = inicio;
        this.positivo = positivo;
        this.valorCorrecto = valorCorrecto;
    }
    
    @Test
    public void test() {
        Object[] argumentos = new Object[]{UtilPruebas.clonarMatriz(matriz),filas,positivo,inicio};
        Class<?>[] tipoArgumentos = new Class<?>[]{AbstractFraccion[][].class,ArrayList.class,boolean.class,int.class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "agregarUnos";
        Object solucionador = new SolucionadorSimplex();
        AbstractFraccion[][] resultadoGenerado = 
                (AbstractFraccion[][])UtilPruebas.ingresarMetodoPrivado(argumentos,tipoArgumentos,clase,nombreMetodo,solucionador);
        assertTrue(UtilPruebas.matricesIguales(resultadoGenerado, valorCorrecto));
    }
    
    @Parameterized.Parameters
    public static Collection parametros() {
        AbstractFraccion[][] matriz1 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(4)
            },
            new AbstractFraccion[]{
                new Fraccion(5),
                new Fraccion(6),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(7)
            }
        };
        
        AbstractFraccion[][] matriz2 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1),
                new Fraccion(-1),
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(5),
                new Fraccion(6),
                new Fraccion(),
                new Fraccion()
            }
        };
        
        AbstractFraccion[][] matriz3 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4)
            },
            new AbstractFraccion[]{
                new Fraccion(1)
            },
            new AbstractFraccion[]{
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(5)
            },
            new AbstractFraccion[]{
                new Fraccion(2,8)
            }
        };
        
        AbstractFraccion[][] matriz4 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(5),
                new Fraccion(6),
                new Fraccion()
            }
        };
        return Arrays.asList(new Object[][]{
            {matriz1, new ArrayList<>(Arrays.asList(0,3)), 1, true, 
                new AbstractFraccion[][]{
                new AbstractFraccion[]{
                    new Fraccion(1),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(1),
                    new Fraccion(1),
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(15, 4),
                    new Fraccion(10),
                    new Fraccion(-1),
                    new Fraccion(-1),
                    new Fraccion(-1),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(1),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(-1),
                    new Fraccion(1),
                    new Fraccion(),
                    new Fraccion(2)
                },
                new AbstractFraccion[]{
                    new Fraccion(),
                    new Fraccion(1),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(1),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(4)
                },
                new AbstractFraccion[]{
                    new Fraccion(5),
                    new Fraccion(6),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion(1),
                    new Fraccion(7)
                }
        }
            },
            {matriz2, new ArrayList<>(Arrays.asList(2)), 2, false, 
                new AbstractFraccion[][]{
                    new AbstractFraccion[]{
                        new Fraccion(),
                        new Fraccion(),
                        new Fraccion(),
                        new Fraccion(),
                    },
                    new AbstractFraccion[]{
                        new Fraccion(15, 4),
                        new Fraccion(10),
                        new Fraccion(-1),
                        new Fraccion(-1),
                    },
                    new AbstractFraccion[]{
                        new Fraccion(1),
                        new Fraccion(-1),
                        new Fraccion(),
                        new Fraccion()
                    },
                    new AbstractFraccion[]{
                        new Fraccion(),
                        new Fraccion(3),
                        new Fraccion(),
                        new Fraccion()
                    },
                    new AbstractFraccion[]{
                        new Fraccion(5),
                        new Fraccion(6),
                        new Fraccion(),
                        new Fraccion()
                    }
        }
            },
            {matriz3, new ArrayList<>(Arrays.asList(4)), 1, true,
            new AbstractFraccion[][]{
                new AbstractFraccion[]{
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(15, 4)
                },
                new AbstractFraccion[]{
                    new Fraccion(1)
                },
                new AbstractFraccion[]{
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(1)
                },
                new AbstractFraccion[]{
                    new Fraccion(2,8)
                }
            }
            },
            {matriz4, new ArrayList<>(Arrays.asList(2,3,4)), 1, false, 
            new AbstractFraccion[][]{
                new AbstractFraccion[]{
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(15, 4),
                    new Fraccion(10),
                    new Fraccion(-1)
                },
                new AbstractFraccion[]{
                    new Fraccion(-1),
                    new Fraccion(),
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(),
                    new Fraccion(-1),
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(5),
                    new Fraccion(6),
                    new Fraccion(-1)
                }
            }
            },
            {matriz4, new ArrayList<>(Arrays.asList(4)), 1, true, 
            new AbstractFraccion[][]{
                new AbstractFraccion[]{
                    new Fraccion(),
                    new Fraccion(),
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(15, 4),
                    new Fraccion(10),
                    new Fraccion(-1)
                },
                new AbstractFraccion[]{
                    new Fraccion(1),
                    new Fraccion(),
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(),
                    new Fraccion(3),
                    new Fraccion()
                },
                new AbstractFraccion[]{
                    new Fraccion(1),
                    new Fraccion(6),
                    new Fraccion()
                }
            }
            }
        });
    }
}
