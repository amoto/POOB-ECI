package Evolutions;
import Shapes.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/**
 * La clase Path realiza la linea de evolucion
 * 
 * @author Julian Devia - Hugo Alvarez
 * @version 002
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
     */
    public void addFossil(Fossil fosil, int position)
    {
        fosiles.add(fosil);
        longitudes.add(fosil.getLength());
        Collections.sort(longitudes);
        if(maximo < fosil.getLength() * sz){
            maximo = fosil.getLength() * sz;
        }
        Collections.sort(fosiles);
        if(isVisible){
            makeInvisible();
            apariencia();
            makeVisible();
        }
        else{
            apariencia();        
        }
    }
    
    /**
     * Este metodo agrega un fosil junto con su posicion a la linea de evolucion
     *
     * @param  fosil, que es la cadena de caracteres a agregar
     */
    public void addFossil(Fossil fosil)
    {
        fosiles.add(fosil);
        longitudes.add(fosil.getLength());
        Collections.sort(longitudes);
        if(maximo < fosil.getLength() * sz){
            maximo = fosil.getLength() * sz;
        }
        Collections.sort(fosiles);
        if(isVisible){
            makeInvisible();
            apariencia();
            makeVisible();
        }
        else{
            apariencia();        
        }
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
        boolean check=true;
        for(int i=0 ; i < fosiles.size() && check ; i++){
            if(fosiles.get(i).getLength() > fosil.getLength()){
                check = fosiles.get(i).checkSequence(fosil);
            }else{
                check = fosil.checkSequence(fosiles.get(i));
            }
        }
        return check;
    }

    /**
     * verifica que la cadena se pueda colocar en en la linea de evolución
     * en una posición determinada de acuerdo a su longitud
     * 
     *  @param lenFosil, es la longitud del fosil
     *  @param pos, es la posicion que desea agregar el fosil
     *  @return true si se puede colovar, false si no.
     */
    public boolean checkPosition(int lenFosil, int pos)
    {
        boolean check = true;
        int cont = 0;
        for(int i = 0; i < fosiles.size() && check; i++){
            if(fosiles.get(i).getLength() < lenFosil){
                cont++;
            }
            check = fosiles.get(i).getLength() != lenFosil;
        }
        if(check && cont + 1 != pos){
            check = false;
        }
        return check;
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
     * Este metodo revisa si es posible, si es posible hace la mezcla
     *
     * @param  p2, que es la linea de evolucion a mezclar
     * @return     true, si hizo la mezcla, false si no.
     */
    public boolean mergeWith(Path p2)
    {
        boolean check = true;
        ArrayList<Integer> tmpl = p2.getLongitudes();
        for(int i = 0; i < fosiles.size() && check; i++){
            check = !(tmpl.contains(longitudes.get(i)));
        }
        if (check){
            for(int i = 0; i < p2.getLength() && check; i++){
                check = isAddable(p2.getFossil(i));
            }
        }
        if(check){
            p2.makeInvisible();
            int p2Len=p2.getLength();
            for(int i = 0; i < p2Len; i++){
                Fossil tmp = p2.removeFosil(0);
                int j = 0;
                while(j < getLength() && longitudes.get(j) < tmp.getLength()){
                    j++;
                }
                addFossil(tmp, j + 1);
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
     * @return     false si no esta visible
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
