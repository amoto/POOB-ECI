package Pruebas;
import evolutionContest.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

/**
 * The test class PruebaAceptacion.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PruebaAceptacion
{
    @Test
    public void segunADnoDeberíaSituarImposible() throws InterruptedException{
        String[][] res={{"CM","CCCMMM"},{"AA","MAA","CCMAA"}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"AA","CM","CCMAA","CCCMMM","MAA"};
        String[][] ans;
        ans=ec.solve("CCCCMMMMAAAA",fos);
        ec.simulate(true);
        int respuesta = JOptionPane.showConfirmDialog(null,"le parece correcto?");
        if(respuesta == 0){
           assertTrue(true);
        }else{
           assertTrue(false);
        }
        //assertArrayEquals(ans,res);
        
    }
    @Test
    public void segunADdeberíaSituar() throws InterruptedException{
        String[][] res={{"AM"},{"MA"}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"MA","AM"};
        String[][] ans;
        ans=ec.solve("AMA",fos);
        ec.simulate(true);
        int respuesta = JOptionPane.showConfirmDialog(null,"le parece correcto?");
        if(respuesta == 0){
           assertTrue(true);
        }else{
           assertTrue(false);
        }
        //assertArrayEquals(ans,res); 
    }
}
