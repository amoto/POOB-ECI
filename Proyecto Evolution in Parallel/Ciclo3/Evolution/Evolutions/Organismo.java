package Evolutions;
import Shapes.*;
import java.util.*;
/**
 * Write a description of class Organismo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Organismo extends Fossil
{
    // variables de presentacion
    private Circle[] nucleotidos;
    /**
     * Constructor for objects of class Organismo
     */
    public Organismo(String sequence)
    {
        super(sequence);
        genSequence=soloValidas(sequence.toUpperCase());
        nucleotidos=new Circle[genSequence.length()];
        x=0;
        y=0;
        sz=30;
        presentacion();
        isVisible=false;
    }
    
    /**
     * ubica los elementos visuales del organismo
     */
    private void presentacion()
    {
        for(int i=0;i<genSequence.length();i++){
            nucleotidos[i]=new Circle();
            nucleotidos[i].changeColor(Fossil.colores[(genSequence.charAt(i)%15)-2]);
            nucleotidos[i].changeSize(sz);
            nucleotidos[i].moveHorizontal(sz*i);
        }     
    }

    /**
     * hace visible el organismo
     */
    public void makeVisible()
    {
        if(!isVisible){
            isVisible=true;
            for(int i=0;i<getLength();i++){
                nucleotidos[i].makeVisible();
            }
        }
    }

    /**
     * hace invisible el organismo
     */
    public void makeInvisible()
    {
        isVisible=false;
        for(int i=0;i<getLength();i++){
            nucleotidos[i].makeInvisible();
        }
    }

    /**
     * acerca o aleja la vista del organizmo
     *
     * @param  sign el signo ('+' o '-') que determina si se agrega o 
     * disminuye la unidad al diametro
     */
    public void zoom(char sign)
    {
        boolean tmp=isVisible;
        if(tmp){
            makeInvisible();
        }
        if(sign=='+'){
            sz++;
        }else{
            sz--;
        }
        presentacion();
        if(tmp){
            makeVisible();
        }
    }

}
