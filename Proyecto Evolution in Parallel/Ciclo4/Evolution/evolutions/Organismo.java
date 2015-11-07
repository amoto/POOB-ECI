package evolutions;
import shapes.*;
import java.util.*;
/**
 * El organismo es la cadena de adn del ser vivo actualmente en el planeta
 * 
 * @author Hugo Alvarez - Julian Devia
 * @version 2.0
 */
public class Organismo extends Fossil
{
    // variables de presentacion
    private Circle[] nucleotidos;
    /**
     * Crea un organismo
     * 
     * @param sequence, la sequencia genetica del fosil
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

    public void makeVisible()
    {
        if(!isVisible){
            isVisible=true;
            for(int i=0;i<getLength();i++){
                nucleotidos[i].makeVisible();
            }
        }
    }

    public void makeInvisible()
    {
        isVisible=false;
        for(int i=0;i<getLength();i++){
            nucleotidos[i].makeInvisible();
        }
    }

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
