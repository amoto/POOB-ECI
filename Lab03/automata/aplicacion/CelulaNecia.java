package aplicacion;

import java.awt.Color;

/**
 * Write a description of class CelulaNecia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaNecia extends Celula
{
     /**
     * crea una celula necia en el automata dado, en la posicion dada.
     * @param ac automata celular en el que se va a ubicar la nueva célula
     * @param fila fila en el automata celular
     * @param columna columna en el automata celula
     */
    public CelulaNecia(AutomataCelular ac,int fila,int columna)
    {
        super(ac,fila,columna);
        color=Color.pink;
    }
    
    public void decida(){
        super.cambie();
    }
    
    public void cambie(){
        super.decida();
    }
}
