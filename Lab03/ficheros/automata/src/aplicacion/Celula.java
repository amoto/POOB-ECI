package aplicacion;

import java.awt.Color;

/**Informacion sobre una célula<br>
<b>(COLOR,automata,fila,columna,estadoActual,estadoSigiente)</b><br>
Las celulas conocen su color, el automata en la que viven, la posición en la que están en ese autómata,su estado actual y el estado que van a tomar en el siguiente instante.<br>
Todas las células son de color azul<br>
Los posibles estados de una célula son tres: viva, muerta o latente<br>
*/
public class Celula{



    protected final static char VIVA='v', MUERTA='m';

    
    protected AutomataCelular automata;
    
    private int fila,columna;
    
    protected char estadoActual,estadoSiguiente;
    
    protected Color color;
    
    protected int edad;


    /**Crea una celula, viva o latente, en la posicion (<b>fila,columna</b>) del automata <b>ac</b>.Toda nueva celula va a estar viva en el estado siguiente.
    @param ac automata celular en el que se va a ubicar la nueva celula
    @param fila fila en el automata celular
    @param columna columna en el automata celula
    */
    public Celula(AutomataCelular ac,int fila, int columna){
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        estadoActual=' ';
        estadoSiguiente=VIVA;
        edad=0;
        automata.setCelula(fila,columna,this);  
        color=Color.black;
    }
    
    /**Retorna la fila del automata en que se encuentra 
    @return la fila donde se encuentra la celula
    */

    public final int getFila(){
        return fila;
    }

    /**Retorna la columna del automata en que se encuentra
    @return la columna donde se encuentra la celula
    */
    public final int getColumma(){
        return columna;
    }

   /**Retorna el color de  la celula
    @return el color de la celula
    */
    public final Color getColor(){
        return color;
    }

    /**Retorna si esta viva
    @return true, si la celula esta viva flase d.l.c.
    */
    public final boolean estaViva(){
        return (estadoActual == VIVA) ;
    }
    
    /**Retorna si esta muerta
    @return  true, si la celula esta muerta, false d.l.c.
    */
    public final boolean estaMuerta(){
        return (estadoActual == MUERTA) ;
    }    


    /**Retorna la edad de la célula
    @return la edad de la celula
    */
    public final int edad(){
        return (edad) ;
    }
    
    /**Decide cual va a ser su  siguiente estado considerando las reglas del juego de la vida
    */
    public void decida(){
       if (edad>=2 && estaViva()){
           estadoSiguiente=MUERTA;
       }   
    }

    /**Actualiza su estado actual considerando lo definido como siguiente estado
    */
    public void cambie(){
        edad++;
        estadoActual=estadoSiguiente;
    }
    
    /**
     * Asesina a la celula
     */
    public void muera()
    {
        estadoActual=MUERTA;
        estadoSiguiente=MUERTA;
    }


}
