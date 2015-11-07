import java.io.*;
import java.util.*;
/**
 * La clase Path realiza la linea de evolucion
 * 
 * @author Julian Devia - Hugo Alvarez
 * @version 002
 */
public class Path
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private int maximo;
    private boolean isVisible;
    private ArrayList<Fossil> fosiles;
    private ArrayList<Integer> longitudes;
    private Rectangle marco;
    private int len;
    

    /**
     * El constructor de Path crea una linea de evolucion
     */
    public Path()
    {
        x = 0;
        y = 0;
        maximo = 40;
        isVisible = false;
        marco = new Rectangle();
        marco.changeSize(40,40);
        marco.changeColor("white");
        fosiles = new ArrayList<Fossil>();
        longitudes = new ArrayList<Integer>();
        len=0;
    }

    /**
     * Este metodo hace la presentacion para el canvas
     *
     */
    public void presentacion()
    {
        marco.changeSize(40,40);
    }

    /**
     * Este metodo agrega un fosil junto con su posicion a la linea de evolucion
     *
     * @param  fosil, que es la cadena de caracteres a agregar
     * @param  position, que es la posicion en la linea de evolucion
     */
    public void addFossil(Fossil fosil, int position)
    {
        len++;
        fosiles.add(fosil);
        longitudes.add(fosil.getLength());
        Collections.sort(longitudes);
        if(maximo<fosil.getLength()*30) 
            maximo = fosil.getLength()*30;
        Collections.sort(fosiles);
        if(isVisible){
            makeInvisible();
            apariencia();
            makeVisible();
        }
        else apariencia();        
    }

    /**
     * Revisa que un fosil pueda entrar en una linea de evolucion considerando los elementos de la sequencia genetica del fosil y los de los fosiles que hay en la linea
     * , teniendo en cuenta que en cada paso de la evolucion solo se pueden gregar nucleotidos
     *
     * @param  fosil es el fosil que se va a evaluar si puede entrar en la linea
     * @return     check con true si es posible agregar el fosil en la linea, de lo contrario false
     */
    public boolean isAddable(Fossil fosil)
    {
        String tmp=fosil.getSequence();
        boolean check=true;
        for(int i=0 ; i<len && check ; i++){
            boolean flag=true;
            String comparado=fosiles.get(i).getSequence();
            String smallSequence;
            String bigSequence;
            if(fosiles.get(i).getLength()>tmp.length()){
                smallSequence=tmp;
                bigSequence=comparado;
            }else{
                smallSequence=comparado;
                bigSequence=tmp;
            }
            int k=0;
            for(int j=0; j<smallSequence.length() && flag; j++){
                boolean isin=false;
                while(k<bigSequence.length() && !isin){
                    if(smallSequence.charAt(j)==bigSequence.charAt(k)){
                        isin=true;
                    }
                    k++;
                }
                if(!isin){
                    flag=false;
                }
            }
            if(!flag){
                check=false;
            }
        }
        return check;
    }

    /**
     * verifica que la cadena se pueda colocar en en la linea de evolución
     * en una posición determinada de acuerdo a su longitud
     * 
     *  @param l, es la longitud del fosil
     *  @param pos, es la posicion que desea agregar el fosil
     *  @return true si se puede colovar, false si no.
     */
    public boolean checkPosition(int l,int pos)
    {
        boolean check = true;
        int cont=0;
        for(int i = 0; i < fosiles.size() && check; i++){
            if(fosiles.get(i).getLength()<l){
                cont++;
            }
            check = fosiles.get(i).getLength()!=l;
        }
        if(check && cont+1!=pos){
            check=false;
        }
        return check;
    }

    /**
     * Este metodo realiza la apariencia en el canvas
     *
     */
    public void apariencia()
    {
        marco.changeSize(40*fosiles.size(),maximo+10);
        for(int i = 0; i < fosiles.size(); i++){
            fosiles.get(i).reset();
            fosiles.get(i).move(0+x,5+40*(fosiles.size()-i-1)+y);
        }
    }

    /**
     * Este metodo revisa si es posible, si es posible hace la mezcla
     *
     * @param  p2, que es la linea de evolucion a mezclar
     * @return     true, si hizo la mezcla, false si no.
     */
    public boolean mergeWith(Path p2)
    {
        boolean check = true;
        ArrayList<Integer> tmpl=p2.getLongitudes();
        for(int i = 0; i < len && check; i++){
            check=!(tmpl.contains(longitudes.get(i)));
        }
        if (check){
            for(int i=0;i<p2.getLength() && check;i++){
                check=isAddable(p2.getFossil(i));
            }
        }
        if(check){
            p2.makeInvisible();
            for(int i=0;i<p2.getLength();i++){
                Fossil tmp=p2.removeFosil(0);
                int j=0;
                while(j<getLength() && longitudes.get(j)<tmp.getLength() ){
                    j++;
                }
                addFossil(tmp, j+1);
            }
        }
        return check;
    }

    /**
     * Este metodo realiza el movimiento de la linea de evolucion
     * 
     * @param  horizontal, que es el movimiento en el eje x
     * @param  vertical, que es el movimiento en el eje y
     */
    public void move(int horizontal,int vertical)
    {
        if(horizontal>0){
            for (int i=0;i<horizontal;i++){
                marco.moveHorizontal(1);
                for (int j=0;j<len;j++){
                    fosiles.get(j).move(1,0);
                }
            }
        }else{
            for (int i=0;i>horizontal;i--){
                marco.moveHorizontal(-1);
                for (int j=0;j<len;j++){
                    fosiles.get(j).move(-1,0);
                }
            }
        }
        if (vertical>0){
            for (int i=0;i<vertical;i++){
                marco.moveVertical(1);
                for (int j=0;j<len;j++){
                    fosiles.get(j).move(0,1);
                }
            }
        }else{
            for (int i=0;i>vertical;i--){
                marco.moveVertical(-1);
                for (int j=0;j<len;j++){
                    fosiles.get(j).move(0,-1);
                }
            }
        }
        x+=horizontal;
        y+=vertical;
    }
    
    /**
     * Este metodo retorna la longitud de todos los fosiles agregados
     *
     * @return     longitudes, que es una lista de tamanos de todas las cadenas agregadas
     */
    public ArrayList<Integer> getLongitudes()
    {
        return longitudes;
    }

    /**
     * Este metodo retorna el numero de fosiles actuales
     *
     * @return     len, que es el numero de fosiles actuales
     */
    public int getLength()
    {
        return len;
    }

    /**
     * Este metodo retorna el fosil en la posicion i y lo elimina
     *
     * @param  i, que es la posicion del fosil a eliminar
     * @return     el fosil
     */
    public Fossil removeFosil(int i)
    {
        return fosiles.remove(i);
    }

    /**
     * Este metodo retorna el fosil en la posicion i
     *
     * @param  i, que es la posicion del fosil
     * @return     el fosil
     */
    public Fossil getFossil(int i)
    {
        return fosiles.get(i);
    }

    /**
     * Este metodo retorna el maximo de la linea de evolucion
     *
     * @return     maximo+x, que es el tamano de la linea de evolucion
     */
    public int getEnd()
    {
        return maximo+x;
    }

    /**
     * Este metodo reincia la posicion de la linea de evolucion
     *
     */
    public void reset()
    {
        move(-x,-y);
    }
    
    /**
     * Este metodo hace visible los objetos en el canvas
     *
     * @return     true si esta visible
     */
    public boolean makeVisible()
    {
        if(!isVisible){
            isVisible = true;
            marco.makeVisible();
            for(int i = 0; i < fosiles.size(); i++){
                fosiles.get(i).makeVisible();
            }
        }
        return  isVisible;
    }

    /**
     * Este metodo hace invisibles los objetos en el canvas
     *
     * @return     false si no esta visible
     */
    public boolean makeInvisible()
    {
        if(isVisible){
            isVisible = false;
            marco.makeInvisible();
            for(int i = 0; i < fosiles.size(); i++){
                fosiles.get(i).makeInvisible();
            }
        }
        return  isVisible;
    }
    
}
