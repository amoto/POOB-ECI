package evolutions;
import shapes.*;
import java.util.*;
/**
 * Los fosiles Colonizador solo se pueden agregar si son los primeros en una linea evolutiva
 * 
 * @author Hugo Alvarez - Julian Devia
 * @version 1.0
 */
public class Colonizador extends Fossil
{

    /**
     * Constructor for objects of class Colonizador
     * 
     * @param sequence, la sequencia genetica del fosil
     */
    public Colonizador(String sequence)
    {
        super(sequence);
        marco.changeColor("orange");
    }

    public boolean isAddable(ArrayList<Fossil> path, Organismo org,int pos){
        boolean puede=(new Fossil(getSequence())).checkSequence(org) && path.size()==0;
        return puede;
    }
}
