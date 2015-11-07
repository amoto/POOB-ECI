package aplicacion;

import java.awt.Color;
import java.util.*;

/**
 * Write a description of class CelulaConway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaConway extends Celula
{
    public static final int [] DC = {-1,0,1,-1,1,-1,0,1};
    public static final int [] DR = {-1,-1,-1,0,0,1,1,1};

     /**
     * crea una celula conway en el automata dado, en la posicion dada.
     * @param ac automata celular en el que se va a ubicar la nueva célula
     * @param fila fila en el automata celular
     * @param columna columna en el automata celula
     */
    public CelulaConway(AutomataCelular ac,int fila,int columna)
    {
        super(ac,fila,columna);
        color=Color.green;
    }
    
    /**
     * cuenta las celulas vivas adyacentes, de una posicion dada del automata
     *
     * @param  f, es la fila dada del automata
     * @param  c, es la columna dada del automata
     * @return   el numero de celulas vivas
     */
    private int cuenteVivas(int f,int c)
    {
        int contador = 0;
        for(int i = 0; i < DR.length; i++){
            int filaRev = f+DR[i];
            int columnaRev = c+DC[i];
            if(filaRev<automata.LONGITUD && columnaRev<automata.LONGITUD && filaRev>=0 && columnaRev>=0){
                if(automata.getCelula(filaRev,columnaRev)!=null && automata.getCelula(filaRev,columnaRev).estaViva()){
                    contador++;
                }
            }
        }
        return contador;
    }

    /**
     * determina si esta celula debe revivir
     */
    public void revivir()
    {
        if(estaMuerta()){
            int contador = cuenteVivas(getFila(),getColumma());
            if(contador == 3){
                estadoSiguiente = VIVA;
            }
        }
    }

    /**
     * decide si esta celula debe mantenerse viva
     */
    public void vecinas()
    {
        if(estaViva()){
            int contador = cuenteVivas(getFila(),getColumma());
            if(contador == 2 || contador == 3){
                estadoSiguiente = VIVA;
            }
        }
    }

    /**
     * decide si esta celula muere o permanece muerta
     */
    public void muerte()
    {
        int contador = cuenteVivas(getFila(),getColumma());
        if(contador <= 1 || contador >3){
            estadoSiguiente = MUERTA;
        }
    }

    /**
     * busca alrededor de esta celula, las casillas vacias para forma una nueva celula
     */
    public void nuevas()
    {
        for(int i = 0; i < DR.length; i++){
            int filaRev = getFila()+DR[i];
            int columnaRev = getColumma()+DC[i];
            if(filaRev<automata.LONGITUD && columnaRev<automata.LONGITUD && filaRev>=0 && columnaRev>=0){
                if(automata.getCelula(filaRev,columnaRev)==null){
                    int contador = cuenteVivas(filaRev,columnaRev);
                    if(contador == 3){
                        automata.setCelula(filaRev,columnaRev,new CelulaConway(automata,filaRev,columnaRev));
                    }
                }
            }
            
        }
    }

    public void decida()
    {
        if(edad>=1){
            revivir();
            vecinas();
            muerte();
        }
    }

}
