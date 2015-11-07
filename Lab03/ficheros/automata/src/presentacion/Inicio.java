package presentacion;
import aplicacion.*;


public class Inicio {
    
    public static void main(String[] args) {
        
        AutomataCelular ac=new AutomataCelular();

        /**
        Para adicionar células
                new CelulaNecia(ac,1,1);
        **/      

        Celula daisy=new Celula(ac,1,1);
        Celula minnie=new Celula(ac,1,2);
        CelulaIzquierdoza marx= new CelulaIzquierdoza(ac,3,1);
        CelulaIzquierdoza hegel= new CelulaIzquierdoza(ac,2,2);
        CelulaPobladora felipito=new CelulaPobladora(ac,5,1);
        CelulaPobladora susanita=new CelulaPobladora(ac,5,2);
        CelulaNecia homero = new CelulaNecia(ac,5,5);
        CelulaNecia bart = new CelulaNecia(ac,5,6);
        CelulaKamikaze hugo = new CelulaKamikaze(ac,2,1);
        CelulaKamikaze julian = new CelulaKamikaze(ac,3,2);
        CelulaConway horton = new CelulaConway(ac,5,15);
        CelulaConway jhon   = new CelulaConway(ac,5,14);
        CelulaConway bloque1 = new CelulaConway(ac,19,0);
        CelulaConway bloque2 = new CelulaConway(ac,19,1);
        CelulaConway bloque3 = new CelulaConway(ac,18,0);
        CelulaConway bloque4 = new CelulaConway(ac,18,1);
        CelulaConway parpadeador1 = new CelulaConway(ac,18,8);
        CelulaConway parpadeador2 = new CelulaConway(ac,18,9);
        CelulaConway parpadeador3 = new CelulaConway(ac,18,10);
        CelulaConway planeador1 = new CelulaConway(ac,17,17);
        CelulaConway planeador2 = new CelulaConway(ac,17,18);
        CelulaConway planeador3 = new CelulaConway(ac,17,19);
        CelulaConway planeador4 = new CelulaConway(ac,18,17);
        CelulaConway planeador5 = new CelulaConway(ac,19,18);
        /*barco
        CelulaConway a = new CelulaConway(ac,4,7);
        CelulaConway c = new CelulaConway(ac,4,8);
        CelulaConway d = new CelulaConway(ac,5,7);
        CelulaConway e = new CelulaConway(ac,5,9);
        CelulaConway f = new CelulaConway(ac,6,8);*/
        /*sapudo
        CelulaConway a = new CelulaConway(ac,4,7);
        CelulaConway c = new CelulaConway(ac,4,8);
        CelulaConway d = new CelulaConway(ac,4,9);
        CelulaConway e = new CelulaConway(ac,5,6);
        CelulaConway f = new CelulaConway(ac,5,7);
        CelulaConway g = new CelulaConway(ac,5,8);*/
        /*planeador
        */
        /*naveLigera
        CelulaConway a = new CelulaConway(ac,4,7);
        CelulaConway c = new CelulaConway(ac,4,10);
        CelulaConway d = new CelulaConway(ac,5,6);
        CelulaConway e = new CelulaConway(ac,6,6);
        CelulaConway b = new CelulaConway(ac,6,10);
        CelulaConway f = new CelulaConway(ac,7,9);
        CelulaConway g = new CelulaConway(ac,7,8);
        CelulaConway h = new CelulaConway(ac,7,7);
        CelulaConway i = new CelulaConway(ac,7,6);*/
        /*Wikipedia
         CelulaConway a = new CelulaConway(ac,4,7);
        CelulaConway c = new CelulaConway(ac,4,8);
        CelulaConway d = new CelulaConway(ac,4,9);
        CelulaConway e = new CelulaConway(ac,3,7);
        CelulaConway b = new CelulaConway(ac,5,8);*/
        
        AutomataGUI gui=new AutomataGUI(ac);
        gui.setVisible(true);

    }

}