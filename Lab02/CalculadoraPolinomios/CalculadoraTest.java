import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Las pruebas para la clase Calculadora   
 *
 * @author  Hugo Alvarez, Julian Devia
 * @version 1.0
 */
public class CalculadoraTest
{
    //Pruebas Ciclo1
    
    //Pruebas Adicionar Operando, consultar
    @Test
    public void deberiaAdicionarYConsultar()
    {
        Calculadora c1=new Calculadora();
        int [][] tmp = {{1},{2,3},{0}};
        c1.adicione(tmp);
        assertEquals("1/1 +2/3 x",c1.consulte());
        int [][] tmp1 = {{5},{0},{-2,3}};
        c1.adicione(tmp1);
        assertEquals("5/1 -2/3 x**2",c1.consulte());
        int [][] tmp2 = {{0}};
        c1.adicione(tmp2);
        assertEquals("",c1.consulte());
    }

    //Pruebas borrar
    @Test
    public void deberiaPermitirBorrar()
    {
        Calculadora c1=new Calculadora();
        int [][] tmp = {{1},{2,3},{0}};
        c1.adicione(tmp);
        int [][] tmp1 = {{5},{0},{-2,3}};
        c1.adicione(tmp1);
        int [][] tmp2 = {{0}};
        c1.adicione(tmp2);
        c1.borre();
        assertEquals("",c1.consulte());
        c1.adicione(tmp);
        assertEquals("1/1 +2/3 x",c1.consulte());
        c1.adicione(tmp1);
        c1.borre();
        assertEquals("",c1.consulte());
    }
    @Test
    public void deberiaSumar()
    {
        Calculadora c1=new Calculadora();
        int [][] tmp = {{1},{2,3},{0}};
        c1.adicione(tmp);
        int [][] tmp1 = {{5},{0},{-2,3}};
        c1.adicione(tmp1);
        int [][] tmp2 = {{0}};
        c1.adicione(tmp2);
        c1.sume();
        assertEquals("5/1 -2/3 x**2",c1.consulte());
        c1.sume();
        assertEquals("6/1 +2/3 x -2/3 x**2",c1.consulte());
    }
    @Test
    public void deberiaRestar()
    {
        Calculadora c1=new Calculadora();
        int [][] tmp = {{1},{2,3},{0}};
        c1.adicione(tmp);
        int [][] tmp1 = {{5},{0},{-2,3}};
        c1.adicione(tmp1);
        int [][] tmp2 = {{0}};
        c1.adicione(tmp2);
        c1.reste();
        assertEquals("-5/1 +2/3 x**2",c1.consulte());
        c1.reste();
        assertEquals("-6/1 -2/3 x +2/3 x**2",c1.consulte());
    }
    @Test
    public void deberiaMultiplicar()
    {
        Calculadora c1=new Calculadora();
        int[][] m = {{6},{5,4},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m3 = {{3},{4,3},{-1,2,5},{10,1,10},{-9,6,4}};
        c1.adicione(m);
        c1.adicione(m1);
        c1.multiplique();
        assertEquals("-10/1 -12 1/12 x -49 7/12 x**2 -48 3/4 x**3 -50/1 x**4 -12 1/2 x**5 -3 1/3 x**6",c1.consulte());
        c1.adicione(m1);
        c1.adicione(m3);
        c1.multiplique();
        assertEquals("-5/1 -7 2/9 x -4 8/9 x**2 -16 13/18 x**3 +3/1 x**4 +2/3 x**5 +17 1/2 x**6",c1.consulte());
    }
    @Test
    public void deberiaDividir()
    {
        Calculadora c1=new Calculadora();
        int[][] m = {{6},{5,4},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m4 = {{-42},{0},{-12},{1}};
        int[][] m5 = {{-3},{1}};
        c1.adicione(m);
        c1.adicione(m1);
        c1.divide();
        assertEquals("-10 1/5 -2 1/10 x -1 1/5 x**2",c1.consulte());
        c1.adicione(m4);
        c1.adicione(m5);
        c1.divide();
        assertEquals("-27/1 -9/1 x +1/1 x**2",c1.consulte());
    }
    @Test
    public void deberiaDerivar()
    {
        Calculadora c1=new Calculadora();
        int[][] m = {{6},{5,4},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m4 = {{-42},{0},{-12},{1}};
        int[][] m5 = {{-3},{1}};
        c1.adicione(m);
        c1.derive();
        assertEquals("1 1/4 +45/1 x +16 1/2 x**2 +8/1 x**3",c1.consulte());
        c1.adicione(m1);
        c1.derive();
        assertEquals("-1 2/3 -3 1/3 x",c1.consulte());
        c1.adicione(m4);
        c1.derive();
        assertEquals("-24/1 x +3/1 x**2",c1.consulte());
        c1.adicione(m5);
        c1.derive();
        assertEquals("1/1",c1.consulte());
    }
    @Test
    public void deberiaIntegrar()
    {
        Calculadora c1=new Calculadora();
        int[][] m = {{6},{5,4},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m4 = {{-42},{0},{-12},{1}};
        int[][] m5 = {{-3},{1}};
        c1.adicione(m);
        c1.integre();
        assertEquals("6/1 x +5/8 x**2 +7 1/2 x**3 +1 3/8 x**4 +2/5 x**5",c1.consulte());
        c1.adicione(m1);
        c1.integre();
        assertEquals("-1 2/3 x -5/6 x**2 -5/9 x**3",c1.consulte());
        c1.adicione(m4);
        c1.integre();
        assertEquals("-42/1 x -4/1 x**3 +1/4 x**4",c1.consulte());
        c1.adicione(m5);
        c1.integre();
        assertEquals("-3/1 x +1/2 x**2",c1.consulte());
    }
}
