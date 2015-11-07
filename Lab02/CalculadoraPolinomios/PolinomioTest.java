

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PolinomioTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PolinomioTest
{
    
    @Test
    public void crearPolinomios()
    {
        int[][] m = {{6},{5},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m3 = {{0},{0},{0},{0},{0}};
        int[][] m2 = {{0},{2},{0},{1},{0}};
        
        Polinomio p = new Polinomio(m);
        Polinomio p1 = new Polinomio(m1);
        Polinomio p2 = new Polinomio(m2);
        Polinomio p3 = new Polinomio(m3);
        assertEquals("",p3.toString());
        assertEquals("6/1 +5/1 x +22 1/2 x**2 +5 1/2 x**3 +2/1 x**4",p.toString());
        assertEquals("-1 2/3 -1 2/3 x -1 2/3 x**2",p1.toString());
        assertEquals("2/1 x +1/1 x**3",p2.toString());
    }
    @Test
    public void sumarPolinomios()
    {
        int[][] m = {{6},{5},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m3 = {{0},{0},{0},{0},{0}};
        int[][] m2 = {{0},{2},{0},{1},{0}};
        Polinomio p = new Polinomio(m);
        Polinomio p1 = new Polinomio(m1);
        Polinomio p2 = new Polinomio(m2);
        Polinomio p3 = new Polinomio(m3);
        assertEquals("4 1/3 +3 1/3 x +20 5/6 x**2 +5 1/2 x**3 +2/1 x**4",p.sume(p1).toString());
        assertEquals("-1 2/3 -1 2/3 x -1 2/3 x**2",p1.sume(p3).toString());
        assertEquals("6/1 +7/1 x +22 1/2 x**2 +6 1/2 x**3 +2/1 x**4",p.sume(p2).toString());
    }
    @Test
    public void restarPolinomios()
    {
        int[][] m = {{6},{5},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m3 = {{0},{0},{0},{0},{0}};
        int[][] m2 = {{0},{2},{0},{1},{0}};
        Polinomio p = new Polinomio(m);
        Polinomio p1 = new Polinomio(m1);
        Polinomio p2 = new Polinomio(m2);
        Polinomio p3 = new Polinomio(m3);
        assertEquals("7 2/3 +6 2/3 x +24 1/6 x**2 +5 1/2 x**3 +2/1 x**4",p.reste(p1).toString());
        assertEquals("-1 2/3 -1 2/3 x -1 2/3 x**2",p1.reste(p3).toString());
        assertEquals("6/1 +3/1 x +22 1/2 x**2 +4 1/2 x**3 +2/1 x**4",p.reste(p2).toString());
    }
    @Test
    public void multiplicarPolinomios()
    {
        int[][] m = {{6},{5,4},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m3 = {{3},{4,3},{-1,2,5},{10,1,10},{-9,6,4}};
        int[][] m2 = {{0},{2},{0},{1},{0}};
        Polinomio p = new Polinomio(m);
        Polinomio p1 = new Polinomio(m1);
        Polinomio p2 = new Polinomio(m2);
        Polinomio p3 = new Polinomio(m3);
        assertEquals("-10/1 -12 1/12 x -49 7/12 x**2 -48 3/4 x**3 -50/1 x**4 -12 1/2 x**5 -3 1/3 x**6",p.multiplique(p1).toString());
        assertEquals("-5/1 -7 2/9 x -4 8/9 x**2 -16 13/18 x**3 +3/1 x**4 +2/3 x**5 +17 1/2 x**6",p1.multiplique(p3).toString());
        assertEquals("12/1 x +2 1/2 x**2 +51/1 x**3 +12 1/4 x**4 +26 1/2 x**5 +5 1/2 x**6 +2/1 x**7",p.multiplique(p2).toString());
    }
    @Test
    public void dividaPolinomios()
    {
        int[][] m = {{6},{5,4},{45,2},{4,3,2},{2}};
        int[][] m1 = {{1,-2,3},{-1,2,3},{1,2,-3}};
        int[][] m3 = {{3},{4,3},{-1,2,5},{10,1,10},{-9,6,4}};
        int[][] m2 = {{0},{2},{0},{1},{0}};
        int[][] m4 = {{-42},{0},{-12},{1}};
        int[][] m5 = {{-3},{1}};
        Polinomio p = new Polinomio(m);
        Polinomio p1 = new Polinomio(m1);
        Polinomio p2 = new Polinomio(m2);
        Polinomio p3 = new Polinomio(m3);
        Polinomio p4 = new Polinomio(m4);
        Polinomio p5 = new Polinomio(m5);
        assertEquals("-27/1 -9/1 x +1/1 x**2",p4.divida(p5).toString());
        assertEquals("-10 1/5 -2 1/10 x -1 1/5 x**2",p.divida(p1).toString());
        assertEquals("6 9/10 -12 9/25 x +6 3/10 x**2",p3.divida(p1).toString());
        assertEquals("5 1/2 +2/1 x",p.divida(p2).toString());
    }
}
