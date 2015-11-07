package evolutions;
import shapes.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/**
 * La clase Path realiza la linea de evolucion
 * 
 * @author Julian Devia - Hugo Alvarez
 * @version 2.0
 */
public class Path implements Comparable<Path>
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private int maximo;
    private int sz;
    private boolean isVisible;
    private ArrayList<Fossil> fosiles;
    private ArrayList<Integer> longitudes;
    private Rectangle marco;
    /**
     * El constructor de Path crea una linea de evolucion
     */
    public Path()
    {
        x = 0;
        y = 0;
        maximo = 40;
        sz=30;
        isVisible = false;
        marco = new Rectangle();
        marco.changeSize(sz+10, sz+10);
        marco.changeColor("white");
        fosiles = new ArrayList<Fossil>();
        longitudes = new ArrayList<Integer>();
    }
    
    /**
     * Este metodo agrega un fosil junto con su posicion a la linea de evolucion
     *
     * @param  fosil, que es la cadena de caracteres a agregar
     * @param  position, que es la posicion en la linea de evolucion
     * @param  org, el organismo viviente del planeta
     * @return true si se agrego el fosil al path, false d.l.c.
     */
    public boolean addFossil(Fossil fosil, int position, Fossil org)
    {
        Fossil[] antes=new Fossil[fosiles.size()];
        fosiles.toArray(antes);
        fosiles = fosil.situar(fosiles, position-1,org);
        Fossil[] despues= new Fossil[fosiles.size()];
        fosiles.toArray(despues);
        longitudes = new ArrayList<Integer>();
        for(int i = 0; i < fosiles.size(); i++){
            longitudes.add(fosiles.get(i).getLength());
        }
        recalcMaximo();
        if(isVisible){
            makeInvisible();
            apariencia();
            makeVisible();
        }
        else{
            apariencia();        
        }
        boolean situo=compare(antes,despues);
        return !situo;
    }
    
    /**
     * Calcula el maximo de la longitud del path
     */
    public void recalcMaximo()
    {
        maximo=sz;
        for (int i = 0 ; i < fosiles.size() ; i++){
            if(fosiles.get(i).getLength()*sz>maximo){
                maximo = fosiles.get(i).getLength()*sz;
            }
        }
    }

    
    /**
     * compara dos arreglos de fosiles y determina si son iguales o no
     * @param  a, el primer arreglo de fosiles a comparar
     * @param  b, el segundo arreglo de fosiles a comparar
     * @return  true si los dos arreglos de fosiles son iguales, false d.l.c.
     */
    private boolean compare(Fossil[] a,Fossil[] b){
        boolean res=a.length==b.length;
        for(int i=0;i<a.length && res;i++){
            res=a[i].equals(b[i]);
        }
        return res;
    }
    
    /**
     * Revisa que un fosil pueda entrar en una linea de evolucion en una posicion determinada 
     *
     * @param  fosil, es el fosil que se va a evaluar si puede entrar en la linea
     * @param  org, el organismo vivo del planeta
     * @param  pos, la posicion en la que se desea agregar el fosil en el path
     * @return     true si es posible agregar el fosil en la linea, de lo contrario false
     */
    public boolean isAddable(Fossil fosil,Organismo org,int pos)
    {
        return fosil.isAddable(fosiles, org,pos);
    }

    /**
     * Este metodo realiza la apariencia en el canvas
     *
     */
    private void apariencia()
    {
        if(fosiles.size()==0){
            marco.changeSize((sz+10),(sz+10));
        }
        else{
            marco.changeSize((sz+10) * fosiles.size(), maximo + 10);
            for(int i = 0; i < fosiles.size(); i++){
                fosiles.get(i).reset();
                fosiles.get(i).move(0 + x, 5 + (sz+10) * (fosiles.size() - i - 1) + y);
            }
        }
    }

    /**
     * Este metodo revisa si es posible mesclar este path con otro, si es posible hace la mezcla
     *
     * @param  p2, que es la linea de evolucion a mezclar
     * @param  org, el organismo vivo del planeta
     * @return     true, si hizo la mezcla, false si no.
     */
    public boolean mergeWith(Path p2, Organismo org)
    {
        boolean check = true;
        ArrayList<Fossil> tmp=new ArrayList<Fossil>();
        for(int i=0;i<getLength();i++) tmp.add(getFossil(i));
        for(int i=0;i<p2.getLength()    && check;i++){
            int pos=tmp.size();
            Fossil[] antes=new Fossil[tmp.size()];
            tmp.toArray(antes);
            boolean check2=false;
            Fossil f=p2.getFossil(i);
            for(int j=0;j<tmp.size() && !check2;j++)
                if(f.getLength()<tmp.get(j).getLength()){
                    check2=true;
                    pos=j;
                }
            ArrayList<Fossil> nuevo=f.situar(tmp, pos, org);
            Fossil[] despues=new Fossil[nuevo.size()];
            nuevo.toArray(despues);
            check=!compare(antes,despues);
            if(check) tmp=nuevo;
            for(int j=0;j<tmp.size() && !check;j++) f.situar(tmp, j, org);
        }
        if(check){
            helpMerge(p2,org);
        }
        return check;
    }

    /**
     * Si es posible situa los fosiles del otro path en este path
     *
     * @param  p2, el path que se quiere mezclar con este
     * @param  org, el organismo viviente del planeta
     */
    private void helpMerge(Path p2,Organismo org)
    {
        boolean check=true;
        for(int i=0;i<p2.getLength() && check;i++){
            int pos=getLength();
            Fossil[] antes=new Fossil[getLength()];
            fosiles.toArray(antes);
            boolean check2=false;
            Fossil f=p2.getFossil(i);
            for(int j=0;j<getLength() && !check2;j++)
                if(f.getLength()<getFossil(j).getLength()){
                    check2=true;
                    pos=j;
                }
            check=addFossil(f, pos+1,org);
            for(int j=0;j<getLength() && !check;j++) addFossil(f, j+1,org);
        }
    }

    /**
     * Este metodo realiza el movimiento de la linea de evolucion
     * 
     * @param  horizontal, que es el movimiento en el eje x
     * @param  vertical, que es el movimiento en el eje y
     */
    public void move(int horizontal, int vertical)
    {
        if(horizontal > 0){
            for (int i = 0; i < horizontal; i++){
                marco.moveHorizontal(1);
                for (int j = 0; j < fosiles.size(); j++){
                    fosiles.get(j).move(1,0);
                }
            }
        }else{
            for (int i = 0; i > horizontal; i--){
                marco.moveHorizontal(-1);
                for (int j = 0; j < fosiles.size(); j++){
                    fosiles.get(j).move(-1,0);
                }
            }
        }
        if (vertical > 0){
            for (int i = 0; i < vertical; i++){
                marco.moveVertical(1);
                for (int j = 0; j < fosiles.size(); j++){
                    fosiles.get(j).move(0,1);
                }
            }
        }else{
            for (int i = 0; i > vertical; i--){
                marco.moveVertical(-1);
                for (int j = 0; j < fosiles.size(); j++){
                    fosiles.get(j).move(0,-1);
                }
            }
        }
        x += horizontal;
        y += vertical;
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
        return fosiles.size();
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
     * Retorna las cadenas genéticas de los fósiles del camino
     *
     * @return     todas las cadenas de los fósiles del path
     */
    public String[] consult()
    {
        String[] consulta=new String[getLength()];
        for(int i=0;i<getLength();i++){
            consulta[i]=getFossil(i).getSequence();
        }
        return consulta;
    }


    /**
     * Este metodo retorna el maximo de la linea de evolucion
     *
     * @return     el tamano de la linea de evolucion
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
     */
    public void makeVisible()
    {
        if(!isVisible){
            isVisible = true;
            marco.makeVisible();
            for(int i = 0; i < fosiles.size(); i++){
                fosiles.get(i).makeVisible();
            }
        }

    }

    /**
     * Este metodo hace invisibles los objetos en el canvas
     *
     */
    public void makeInvisible()
    {
        if(isVisible){
            isVisible = false;
            marco.makeInvisible();
            for(int i = 0; i < fosiles.size(); i++){
                fosiles.get(i).makeInvisible();
            }
        }

    }
    
    /**
     * Este metodo es el comparador de las lineas de evolucion
     *
     * @param  linea a comparar
     * @return     1 si es mayor al actual o -1 si es menor al actual
     */
    public int compareTo(Path p)
    {
        int cmp = 0;
        if(fosiles.size() < p.getLength()){
            cmp = -1;
        }else if(fosiles.size() > p.getLength()){
            cmp = 1;
        }
        else{
            String fCompare = "",sCompare = "";
            for(int i = 0; i < fosiles.size(); i++){
                fCompare += fosiles.get(i).getSequence();
                sCompare += p.getFossil(i).getSequence();
            }
            if(fCompare.length() < sCompare.length()){
                cmp = -1;
            }
            else if(fCompare.length() == sCompare.length()){
                 cmp = fCompare.compareTo(sCompare);
            }
            else{
                cmp = 1;
            }
        }
        return cmp;
    }
    
    /**
     * acerca o aleja la vista del fosil
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
        maximo/=sz;
        if(sign == '+'){
            sz++;
        }else if(sign == '-'){
            sz--;
        }
        maximo*=sz;
        for(int i=0;i<fosiles.size();i++){
            fosiles.get(i).zoom(sign);
        }
        apariencia();
        if(tmp){
            makeVisible();
        }
    }

}
