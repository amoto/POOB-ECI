package evolutions;

import java.util.*;

/**
 * Los fosiles Destroyer eliminan los fosiles que les impiden situarse en un path
 * 
 * @author Hugo Alvarez - Julian Devia
 * @version 2.0
 */
public class Destroyer extends Fossil
{

    /**
     * Crea un fosil destroyer
     * 
     * @param  sequence, la sequencia genetica del fosil
     */
    public Destroyer(String sequence)
    {
        super(sequence);
        marco.changeColor(colores[4]);
    }
    
    public ArrayList<Fossil> situar(ArrayList<Fossil> path, int position, Fossil org)
    {
        boolean check=true;
        int i=0;
        if(position>0)
            i=position-1;
        while (i<path.size()){
            Fossil f=path.get(i);
            if(f.getLength()<getLength()){
                if(!f.checkSequence(this)){
                    path.remove(f);
                }else{
                    i++;
                }
            }else if(f.getLength()==getLength()){
                path.remove(f);
            }else{
                if(!this.checkSequence(f)){
                    path.remove(f);
                }else{
                    i++;
                }
            }
        }
        path.add(this);
        Collections.sort(path);
        return path;
    }
    
    public boolean isAddable(ArrayList<Fossil> path, Organismo org,int pos){
        boolean puede=(new Fossil(getSequence())).checkSequence(org);
         for(int i=0;i<pos && puede;i++){
            Fossil f=path.get(i);
            if(f.getLength()<getLength()){
                puede=f.checkSequence(this);
            }
        }
        return puede;
    }
}
