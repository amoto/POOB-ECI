package aplicacion;

import java.awt.Color;

/**
 * Write a description of class CelulaPobladora here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CelulaPobladora extends Celula
{
    private boolean norte,este;
     /**
     * crea una celula pobladora en el automata dado, en la posicion dada.
     * @param ac automata celular en el que se va a ubicar la nueva célula
     * @param fila fila en el automata celular
     * @param columna columna en el automata celula
     */
    public CelulaPobladora(AutomataCelular ac,int fila,int columna)
    {
        super(ac,fila,columna);
        color=Color.orange;
        norte=false;
        este=false;
    }

    public void decida(){
        if(edad()>=3 && estaViva()){
            if(getColumma()<automata.LONGITUD-1 && (automata.getCelula(getFila(),getColumma()+1)==null || automata.getCelula(getFila(),getColumma()+1).estaMuerta())){
                estadoSiguiente=MUERTA;
                este=true;
            }
            if(getFila()>0 && (automata.getCelula(getFila()-1,getColumma())==null || automata.getCelula(getFila()-1,getColumma()).estaMuerta())){            
                estadoSiguiente=MUERTA;
                norte=true;
            }
        }
    }
    
    public void cambie(){
        edad++;
        if(norte && estaViva()){
            automata.setCelula(getFila()-1,getColumma(),new CelulaPobladora(automata,getFila()-1,getColumma()));
        }
        if(este && estaViva()){
            automata.setCelula(getFila(),getColumma()+1,new CelulaIzquierdoza(automata,getFila(),getColumma()+1));
        }
        estadoActual=estadoSiguiente;
    }
}
