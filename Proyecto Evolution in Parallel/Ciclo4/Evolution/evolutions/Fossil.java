package evolutions;
import shapes.*;
import javax.swing.*;
import java.util.*;
/**
 * La clase Fossil crea un fossil con una cadena determinada
 * 
 * @author Julian Devia - Hugo Alvarez
 * @version 002
 */
public class Fossil implements Comparable<Fossil>
{
    // instance variables - replace the example below with your own
    // variables del programa
    protected int x;
    protected int y;
    protected String genSequence;
    protected int sz;
    protected boolean isVisible;
    // variables de presentacion
    protected Triangle[] nucleotidos;
    protected Rectangle marco;
    public static final String[] colores = {"red","yellow","blue","green","magenta","black"};
    public static final char[] letras = {'A','C','M'};
    public static final List<String> TIPOS = Arrays.asList("normal","top","magical","destroyer","colonizador");

    /**
     * crea un fosil con la secuencia ADN que le ingresaron
     * A - green
     * C - black
     * M - red
     * @param  sequence, una secuencia de caracteres formada por A,C,M
     */
    public Fossil(String sequence)
    {
        x=0; 
        y=0;
        sz=30;
        isVisible = false;
        marco=new Rectangle();
        marco.changeColor("white");
        genSequence = soloValidas(sequence.toUpperCase());
        nucleotidos = new Triangle[getLength()];
        presentacion();
    }

    /**
     * Este metodo retorna el tamano de la cadena
     *
     * @return   el tamano de la cadena
     */
    public int getLength()
    {
        return getSequence().length();
    }

    /**
     * Este metodo retorna la secuencia del fosil
     *
     * @return    genSequence, que es la secuencia del fosil
     */
    public String getSequence()
    {
        return genSequence;
    }

    /**
     * determina si un fosil se puede agregar a un path en una posicion determinada
     * @param   path, la linea evolutiva a la que se quiere agregar el fosil
     * @param   org, el organismo del plantea al que se quiere agregar el fosil
     * @param   pos, la posicion del path en la que se quiere agregar el fosil
     * @return true si se puede agregar al camino, false dlc.
     */
    public boolean isAddable(ArrayList<Fossil> path, Organismo org, int pos){
        boolean puede = this.checkSequence(org);
        for(int i=0;i<path.size() && puede;i++){
            Fossil f=path.get(i);
            if(f.getLength()<getLength()){
                puede=f.checkSequence(this) && i<pos;
            }else if(f.getLength()==getLength()){
                puede=false;
            }else{
                puede=this.checkSequence(f) && i>=pos;
            }
        }
        return puede;
    }
    
    /**
     * Este metodo realiza la presentacion
     *
     */
    private void presentacion()
    {
        marco.changeSize(sz,sz*getLength());
        marco.moveHorizontal(x+6);
        marco.moveVertical(y);
        for(int i = 0; i < getLength(); i++){
            nucleotidos[i] = new Triangle();
            nucleotidos[i].changeColor(colores[(genSequence.charAt(i)%15)-2]);
            nucleotidos[i].changeSize(sz,sz);
            nucleotidos[i].moveHorizontal(sz*i+(sz-30)/2);
        }
    }

    /**
     * Este metodo reinicia la posicion del objeto en el canvas
     *
     */
    public void reset()
    {
        move(-x,-y);
    }

    /**
     * Este metodo hace visible los fosiles
     * 
     */
    public void makeVisible()
    {
        if(!isVisible){
            marco.makeVisible();
            isVisible = true;
            for(int i = 0; i < getLength(); i++){
                nucleotidos[i].makeVisible();
            }
        }

    }
    
    /**
     * Este metodo hace invisible los fosiles
     * 
     */
    public void makeInvisible()
    {
        if(isVisible){
            marco.makeInvisible();
            isVisible = false;
            for(int i = 0; i < getLength(); i++){
                nucleotidos[i].makeInvisible();
            }
        }

    }
    
    /**
     * Este metodo mueve el fosil en el canvas
     *
     * @param  horizontal, que es el movimiento en el eje x
     * @param  vertical, que es el movimiento en el eje y
     */
    public void move(int horizontal ,int vertical)
    {
        if(horizontal>0){
            for (int i=0;i<horizontal;i++){
                marco.moveHorizontal(1);
                for (int j=0;j<getLength();j++)
                    nucleotidos[j].moveHorizontal(1);
                }
        }else{
            for (int i=0;i>horizontal;i--){
                marco.moveHorizontal(-1);
                for (int j=0;j<getLength();j++)
                    nucleotidos[j].moveHorizontal(-1);
                }
        }
        if (vertical>0){
            for (int i=0;i<vertical;i++){
                marco.moveVertical(1);
                for (int j=0;j<getLength();j++)
                    nucleotidos[j].moveVertical(1);
                }
        }else{
            for (int i=0;i>vertical;i--){
                marco.moveVertical(-1);
                for (int j=0;j<getLength();j++)
                    nucleotidos[j].moveVertical(-1);
                }
        }
        x+=horizontal;
        y+=vertical;
    }

    /**
     * Este metodo es el comparador de los fosiles
     *
     * @param  Fossil a comparar
     * @return     1 si es mayor al actual o -1 si es menor al actual
     */
    public int compareTo(Fossil f)
    {
        if(getLength()>f.getLength()){
            return 1;
        }else{
            return -1;
        }
    }

    /**
     * Este metodo hace el equivalente entre dos fosiles
     *
     * @return     true si cumple las condiciones, false si no.
     */
    public boolean equals(Fossil f)
    {
        return getLength()==f.getLength() && genSequence.equals(f.getSequence());
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
        if(sign=='+'){
            sz++;
        }else{
            sz--;
        }
        marco.moveHorizontal(-6);
        reset();
        presentacion();
        if(tmp){
            makeVisible();
        }
    }
    
    /**
     * Verifica que la sequencia determinada contenga los elementos de la secuencia genetica de este fosil
     *
     * @param  fo, la sequencia de nucleotidos del fosil
     * @return     true si la sequencia sigue el patrón de este organismo
     */
    public boolean checkSequence(Fossil fo)
    {
        int k=0;
        String seq = fo.getSequence();
        boolean isAble=getLength()<seq.length();
        for(int i=0;i<getLength() && isAble;i++){
            while(k<seq.length() && seq.charAt(k)!=genSequence.charAt(i)){
                k++;
            }
            if(k==seq.length()){
                isAble=false;
            }
            k++;
        }
        return isAble;
    }
    
    /**
     * Valida las letras de la sequencia y deja solo las que sean validas
     * segun las que los fosiles permitan tener
     * 
     * @param sequence, la sequencia a validar
     * @return la sequencia conteniendo solo los caracteres validos
     */
    public String soloValidas(String sequence)
    {
        String realSeq="";
        for(int i=0;i<sequence.length();i++){
            for (int j=0;j<Fossil.letras.length;j++){
                if(sequence.charAt(i)==Fossil.letras[j]){
                    realSeq+=Fossil.letras[j];
                }
            }
        }
        return realSeq;
    }

    /**
     * situa el fosil en la linea de la evolucion dada en la posicion dada si es posible
     *
     * @param  path, la linea de evolucion
     * @param  position,  la posicion dada
     * @param  org, el organismo vivo actual del planeta
     * @return     la linea de evolucion con o sin el fosil agregado
     */
    public ArrayList<Fossil> situar(ArrayList<Fossil> path, int position, Fossil org)
    {
        boolean check=true;
        if(position-1>=0 && position-1<path.size()){
            check = path.get(position-1).checkSequence(this);
        }
        if(check && position>=0 && position<path.size()){
            check = this.checkSequence(path.get(position));
        }
        if(check){
            path.add(position,this);
        }
        return path;
    }
}
