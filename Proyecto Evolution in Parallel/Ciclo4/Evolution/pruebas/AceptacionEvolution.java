package pruebas;
import evolutions.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

/**
 * The test class AceptacionEvolution.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AceptacionEvolution
{
    @Test
    public void situarTodo() throws InterruptedException
    {
        String[] fossils={"a","aa","aaaa","mm","m"};
        String[] types={"colonizador","normal","top","magical","destroyer"};
        Evolution ev=new Evolution("aaaaaaamm",fossils,types);
        ev.makeVisible();
        ev.addPath();
        Thread.sleep(2000);
        ev.situate(1);
        Thread.sleep(2000);
        ev.situate(1);
        Thread.sleep(2000);
        ev.situate(1);
        Thread.sleep(2000);
        ev.situate(1);
        Thread.sleep(2000);
        ev.situate(1);
        Thread.sleep(2000);
        int respuesta = JOptionPane.showConfirmDialog(null,"le parece correcto?");
        if(respuesta == 0){
           assertTrue(true);
        }else{
           assertTrue(false);
        }
        ev.makeInvisible();
    }
    
    @Test
    public void situarDobleDestroyer() throws InterruptedException
    {
        String[] fossils={"a","c","m"};
        String[] types={"normal","destroyer","destroyer"};
        Evolution ev=new Evolution("acm",fossils,types);
        ev.makeVisible();
        ev.addPath();
        Thread.sleep(2000);
        ev.situate(1);
        Thread.sleep(2000);
        ev.situate(1);
        Thread.sleep(2000);
        ev.situate(1);
        Thread.sleep(2000);
        int respuesta = JOptionPane.showConfirmDialog(null,"le parece correcto?");
        if(respuesta == 0){
           assertTrue(true);
        }else{
           assertTrue(false);
        }
        ev.makeInvisible();
    }
}
