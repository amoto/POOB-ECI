package pruebas;
import aplicacion.*;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AutomataTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AutomataTest
{
    @Test
    public void tercerClickCelulas()
    {
        AutomataCelular ac=new AutomataCelular();
        Celula c1=new Celula(ac,0,0);
        Celula c2=new Celula(ac,0,1);
        ac.setCelula(0,0,c1);
        ac.setCelula(0,1,c2);
        for(int i=0;i<3;i++){
            ac.ticTac();
        }
        assertTrue(ac.getCelula(0,0).estaMuerta());
        assertTrue(ac.getCelula(0,1).estaMuerta());
    }
 
    @Test
    public void tercerClickCelulasIzquierdozas()
    {
        AutomataCelular ac=new AutomataCelular();
        Celula c1=new CelulaIzquierdoza(ac,0,0);
        Celula c2=new CelulaIzquierdoza(ac,0,1);
        ac.setCelula(0,0,c1);
        ac.setCelula(0,1,c2);
        for(int i=0;i<3;i++){
            ac.ticTac();
        }
        assertTrue(ac.getCelula(0,0).estaMuerta());
        assertTrue(ac.getCelula(0,1).estaMuerta());
    }

    @Test
    public void tercerClickCelulasPobladoras()
    {
        AutomataCelular ac=new AutomataCelular();
        Celula c1=new CelulaPobladora(ac,1,0);
        Celula c2=new CelulaPobladora(ac,1,1);
        ac.setCelula(1,0,c1);
        ac.setCelula(1,1,c2);
        for(int i=0;i<3;i++){
            ac.ticTac();
        }
        assertTrue(ac.getCelula(1,0).estaViva());
        assertTrue(ac.getCelula(1,1).estaViva());
    }
    @Test
    public void tercerClickCelulasNecias()
    {
        AutomataCelular ac=new AutomataCelular();
        CelulaNecia c1=new CelulaNecia(ac,0,0);
        CelulaNecia c2=new CelulaNecia(ac,0,1);
        ac.setCelula(0,0,c1);
        ac.setCelula(0,1,c2);
        for(int i=0;i<3;i++){
            ac.ticTac();
        }
        assertTrue(ac.getCelula(0,0).estaMuerta());
        assertTrue(ac.getCelula(0,1).estaMuerta());
    }
    @Test
    public void sextoClickCelulasKamikaze()
    {
        AutomataCelular ac=new AutomataCelular();
        CelulaIzquierdoza c1 = new CelulaIzquierdoza(ac,1,1);
        CelulaKamikaze c2 = new CelulaKamikaze(ac,1,2);
        ac.setCelula(1,1,c1);
        ac.setCelula(1,2,c2);
        for(int i=0;i<6;i++){
            ac.ticTac();
            if(i<5){
                assertTrue(ac.getCelula(1,1).estaViva());
                assertTrue(ac.getCelula(1,2).estaViva());
            }
        }
        assertTrue(ac.getCelula(1,1).estaMuerta());
        assertTrue(ac.getCelula(1,2).estaMuerta());
    }
    @Test
    public void sextoClickCelulasConway()
    {
        AutomataCelular ac=new AutomataCelular();
        CelulaConway c1 = new CelulaConway(ac,1,1);
        CelulaConway c2 = new CelulaConway(ac,1,2);
        ac.setCelula(1,1,c1);
        ac.setCelula(1,2,c2);
        for(int i=0;i<3;i++){
            ac.ticTac();
        }
        assertTrue(ac.getCelula(1,1).estaMuerta());
        assertTrue(ac.getCelula(1,2).estaMuerta());
    }
}
