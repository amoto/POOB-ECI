package aplicacion;

import java.awt.Color;


/**
 * Write a description of class CelulaIzquierdoza here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaIzquierdoza extends Celula
{

    /**
     * crea una celula izquierdoza en el automata dado, en la posicion dada.
     * @param ac automata celular en el que se va a ubicar la nueva célula
     * @param fila fila en el automata celular
     * @param columna columna en el automata celula
     */
    public CelulaIzquierdoza(AutomataCelular ac,int fila,int columna)
    {
        super(ac,fila,columna);
        color=Color.red;
    }

    public void decida()
    {
        if(edad>=1 && (getColumma()==automata.LONGITUD-1 || automata.getCelula(getFila(),getColumma()+1)==null || automata.getCelula(getFila(),getColumma()+1).estaMuerta())){
            estadoSiguiente=MUERTA;
        }
    }
}