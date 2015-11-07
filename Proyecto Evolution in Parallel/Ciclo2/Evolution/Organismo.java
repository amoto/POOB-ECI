import java.util.*;
/**
 * Write a description of class Organismo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Organismo
{
    // variables de programa
    private int x;
    private int y;
    private String genSequence;
    private boolean isVisible;
    private int sz;
    // variables de presentacion
    private Circle[] nucleotidos;

    /**
     * Constructor for objects of class Organismo
     */
    public Organismo(String sequence)
    {
        genSequence=soloValidas(sequence.toUpperCase());
        nucleotidos=new Circle[genSequence.length()];
        x=0;
        y=0;
        sz=30;
        presentacion();
        isVisible=false;
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
     * retorna la sequenca genetica del organismo
     *
     * @return     la sequencia genetica del organismo
     */
    public String getSequence()
    {
        return genSequence;
    }

    /**
     * retorna la longitud de la cadena genetica del organismo
     *
     * @return     la longitud de la cadena genetica del organismo
     */
    public int getLen()
    {
        return genSequence.length();
    }

    /**
     * hace visible el organismo
     */
    public void makeVisible()
    {
        if(!isVisible){
            isVisible=true;
            for(int i=0;i<getLen();i++){
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
        for(int i=0;i<getLen();i++){
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

    /**
     * Verifica que la sequencia determinada contenga los elementos de la secuencia genetica de los seres del planeta
     *
     * @param  @param  seq, la sequencia de nucleotidos del fosil
     * @return     true si la sequencia sigue el patrón de este organismo
     */
    public boolean checkSequence(String seq)
    {
        int k=0;
        boolean isAble=seq.length()<getLen();
        for(int i=0;i<seq.length() && isAble;i++){
            while(k<getLen() && seq.charAt(i)!=genSequence.charAt(k)){
                k++;
            }
            if(k==getLen()){
                isAble=false;
            }
            k++;
        }
        return isAble;
    }

}
