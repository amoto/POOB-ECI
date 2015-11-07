package aplicacion;

import java.awt.Color;
import java.util.*;


/**
 * Write a description of class CelulaKamikaze here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaKamikaze extends Celula
{
    public static final int [] dc = {0,-1,1,0};
    public static final int [] dr = {-1,0,0,1};
    private boolean[] existen;
    private boolean explota;
    
     /**
     * crea una celula kamikaze en el automata dado, en la posicion dada.
     * @param ac automata celular en el que se va a ubicar la nueva célula
     * @param fila fila en el automata celular
     * @param columna columna en el automata celula
     */
    public CelulaKamikaze(AutomataCelular ac,int fila,int columna)
    {
        super(ac,fila,columna);
        color=Color.blue;
        existen=new boolean[4];
        Arrays.fill(existen,false);
        explota=false;
    }
    
    public void decida(){
        if(edad()>=5 && estaViva()){
            for(int i=0;i<dc.length;i++){
                int filaRev=getFila()+dr[i];
                int columnaRev=getColumma()+dc[i];
                if(filaRev<automata.LONGITUD && columnaRev<automata.LONGITUD && filaRev>0 && columnaRev>0){
                    if(automata.getCelula(filaRev,columnaRev)!=null && automata.getCelula(filaRev,columnaRev).estaViva()){
                        existen[i]=true;
                        explota=true;
                    }
                }
            }
        }else{
            explota=false;
        }
    }
    
    public void cambie(){
        edad++;
        estadoActual=estadoSiguiente;
        if(explota){
            muera();
            for(int i=0;i<dc.length;i++){
                int filaRev=getFila()+dr[i];
                int columnaRev=getColumma()+dc[i];
                if(existen[i]){
                    automata.getCelula(filaRev,columnaRev).muera();
                }
            }
        }
    }
}
