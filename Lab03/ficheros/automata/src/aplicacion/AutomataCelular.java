package aplicacion;

import java.util.*;


public class AutomataCelular{

    public static final int LONGITUD=20;
    private Celula[][] celulas;

        
    /**
     * crea un automata celular vacio
     */
   public AutomataCelular() {
        celulas=new Celula[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                celulas[f][c]=null;
            }
        }
    }

    /**
     * retorna la celula, en la posicion dada del automata
     * @param  f, es la fila dada del automata
     * @param  c, es la columna dada del automata
     * @return la celula de la posicion dada
     */
    public Celula getCelula(int f,int c){
        return celulas[f][c];
    }
    
    /**
     * ubica una celula nueva en la posicion dada
     * @param  f, es la fila dada del automata
     * @param  c, es la columna dada del automata
     * @param nueva, la celula a ubicar en la posicion dada
     */
    public void setCelula(int f, int c, Celula nueva){
        celulas[f][c]=nueva;
    }

    /**
     * hacen que realicen los cambios necesarios en el automata y sus celulas conforme pasa el tiempo
     */
    public void ticTac(){
        for(int i=0;i<celulas.length;i++){
            for(int j=celulas[i].length-1;j>-1;j--){
                Celula c=getCelula(i,j);
                if(c!=null){
                    c.decida();
                }
            }
        }
        for(int i=0;i<celulas.length;i++){
            for(int j=celulas[i].length-1;j>-1;j--){
                Celula c=getCelula(i,j);
                if(c!=null){
                    c.cambie();
                }
            }
        }
        for(int i=0;i<celulas.length;i++){
            for(int j=0;j<celulas[i].length;j++){
                if(getCelula(i,j)!=null && getCelula(i,j) instanceof CelulaConway){
                    CelulaConway c= (CelulaConway) getCelula(i,j);
                    c.nuevas();
                }
            }
        }
    }
}
