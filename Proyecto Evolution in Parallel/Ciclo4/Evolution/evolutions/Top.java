package evolutions;
import java.util.*;

/**
 * Los fosiles Top solo se pueden situar en la ultima posicion del path y no permiten que nada se situe por encima de ellos
 * 
 * @author Hugo Alvarez - Julian Devia
 * @version 2.0
 */
public class Top extends Fossil
{

    /**
     * Crea un fosil top
     * 
     * @param sequence, la sequencia genetica del fosil
     */
    public Top(String sequence)
    {
        super(sequence);
        marco.changeColor(Fossil.colores[1]);
    }

    public boolean checkSequence(Fossil fo){
        return false;
    }

    public ArrayList<Fossil> situar(ArrayList<Fossil> path, int position, Fossil org)
    {
        boolean check=position==path.size();
        if(check && position-1>=0){
            check = path.get(position-1).checkSequence(this);
        }
        if(check){
            path.add(position,this);
        }
        return path;
    }
    
    public boolean isAddable(ArrayList<Fossil> path, Organismo org,int pos){
        boolean puede=(new Fossil(getSequence())).checkSequence(org) && pos==path.size();;
        for(int i=0;i<path.size() && puede;i++){
            Fossil f=path.get(i);
            if(f.getLength()<getLength()){
                puede=f.checkSequence(this);
            }else {
                puede=false;
            }
        }
        return puede;
    }
}
