package evolutions;
import shapes.*;
import java.util.*;

/**
 * Los fosiles Magical se transforman en el fosil necesario para ubicarse en el path si es posible
 * 
 * @author Hugo Alvarez - Julian Devia
 * @version 2.0
 */
public class Magical extends Fossil
{

    /**
     * Crea un fosil Magical
     * 
     * @param fosil, la cadena genetica del fosil
     */
    public Magical(String fosil)
    {
        super(fosil);
        marco.changeColor(colores[2]);
    }

    /**
     * cambia la cadena evolutiva del fosil para que sea compatible con los dos fosiles uno menor y uno mayor
     * 
     * @param  fo1, el fosil menor
     * @param  fo2, el fosil mayor
     */
    private String changeBody(Fossil fo1, Fossil fo2)
    {
        String necad = "";
        boolean ban = false;
        String sfo1=fo1.getSequence(),sfo2=fo2.getSequence();
        for(int i = 0; i < sfo1.length() && !ban; i++){
            if(sfo1.charAt(i) == sfo2.charAt(i)){
                necad+=sfo1.charAt(i);
            }
            else if(sfo1.charAt(i) != sfo2.charAt(i)){
                necad+=sfo2.charAt(i)+sfo1.substring(i,sfo1.length());
                ban = true;
            }
        }
        if(!ban){
            necad+=sfo2.charAt(sfo2.length()-1);
        }
        return necad;
    }
    
    public ArrayList<Fossil> situar(ArrayList<Fossil> path, int position, Fossil org)
    {
        boolean check=checkSequence(org);
        Fossil tmp = new Fossil(this.getSequence());
        if(check && position-1>=0 && position-1<path.size())
            check = path.get(position-1).checkSequence(tmp);
        if(check && position>=0 && position<path.size())
            check = tmp.checkSequence(path.get(position));
        if(check){
            path.add(position,this);
        }
        else{
            boolean isVi = isVisible;
            String nSeq="";
            if(isVi)
                makeInvisible();
            int xt=x;
            int yt=y;
            reset();
            if(position==0 && path.get(0).getLength()>0)
                nSeq=path.get(0).getSequence().substring(0,path.get(0).getLength()-1);
            else if(position>0 && position < path.size())
                nSeq=changeBody(path.get(position-1),path.get(position));
            else if(position==path.size())
                nSeq=changeBody(path.get(position-1),new Fossil(org.getSequence().substring(0,org.getLength()-1)));
            if((new Fossil(nSeq)).isAddable(path,(Organismo)org,position) && nSeq.length()>0)
                genSequence=nSeq;
            cambioVisual();
            move(xt,yt);
            if((new Fossil(nSeq)).isAddable(path,(Organismo)org,position) && nSeq.length()>0)
                path.add(position,this);
            if(isVi)
                makeVisible();
        }
        return path;
    }
    
    /**
     * cambia la parte visual del fosil de acuerdo a la cadena genetica actual del fosil
     */
    private void cambioVisual(){
        marco.changeSize(sz,sz*getLength());
        marco.moveVertical(y);
        nucleotidos=new Triangle[getLength()];
        for(int i = 0; i < getLength(); i++){
            nucleotidos[i] = new Triangle();
            nucleotidos[i].changeColor(colores[(genSequence.charAt(i)%15)-2]);
            nucleotidos[i].changeSize(sz,sz);
            nucleotidos[i].moveHorizontal(sz*i+(sz-30)/2);
        }
    }
    
    public boolean isAddable(ArrayList<Fossil> path, Organismo org,int pos){
        boolean organismo=(new Fossil(getSequence())).checkSequence(org);
        boolean puede=path.size()!=0;
        if(pos-1>=0 && pos-1<path.size() && pos>=0 && pos<path.size()){
            puede=path.get(pos).getLength()-path.get(pos-1).getLength()>1;
            if(!puede) {
                organismo=false;
            }
        }
        if(pos==0 && path.size()>0){
            puede=path.get(0).getLength()>1;
            if(!puede) {
                organismo=false;
            }
        }
        return organismo || puede;
    }
    
}