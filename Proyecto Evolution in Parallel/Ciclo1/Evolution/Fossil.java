
import javax.swing.*;
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
    private int x;
    private int y;
    private String genSequence;
    private int len;
    private boolean isVisible;
    private static int nfosiles = 0;
    // variables de presentacion
    private Triangle[] nucleotidos;
    private static final String[] colores = {"red","yellow","blue","green","magenta","black"};

    /**
     * crea un fosil con la secuencia ADN que le ingresaron
     * A - green
     * C - black
     * M - red
     * @param  sequence, una secuencia de caracteres formada por A,C,M
     */
    public Fossil(String sequence)
    {
        len = sequence.length();
        x=0;
        y=0;
        isVisible = false;
        genSequence = sequence.toUpperCase();
        nucleotidos = new Triangle[len];
        presentacion();
        nfosiles++;
    }

    /**
     * Este metodo retorna el tamano de la cadena
     *
     * @return     len, que es el tamano de la cadena
     */
    public int getLength()
    {
        return len;
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
     * Este metodo realiza la presentacion
     *
     */
    private void presentacion()
    {
        for(int i = 0; i < len; i++){
            nucleotidos[i] = new Triangle();
            nucleotidos[i].changeColor(colores[(genSequence.charAt(i)%15)-2]);
            nucleotidos[i].changeSize(30,30);
            nucleotidos[i].moveHorizontal(30*i);
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
     * @return isVisible que nos idica true al mostrar los objetos en el canvas
     */
    public boolean makeVisible()
    {
        if(!isVisible){
            isVisible = true;
            for(int i = 0; i < len; i++){
                nucleotidos[i].makeVisible();
            }
        }
        return isVisible;
    }
    
    /**
     * Este metodo hace invisible los fosiles
     * 
     * @return isVisible que nos idica false al ocultar los objetos en el canvas
     */
    public boolean makeInvisible()
    {
        if(isVisible){
            isVisible = false;
            for(int i = 0; i < len; i++){
                nucleotidos[i].makeInvisible();
            }
        }
        return isVisible;
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
            for (int i=0;i<horizontal;i++)
                for (int j=0;j<len;j++)
                    nucleotidos[j].moveHorizontal(1);
        }else{
            for (int i=0;i>horizontal;i--)
                for (int j=0;j<len;j++)
                    nucleotidos[j].moveHorizontal(-1);
        }
        if (vertical>0){
            for (int i=0;i<vertical;i++)
                for (int j=0;j<len;j++)
                    nucleotidos[j].moveVertical(1);
        }else{
            for (int i=0;i>vertical;i--)
                for (int j=0;j<len;j++)
                    nucleotidos[j].moveVertical(-1);
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
        if(len>f.getLength()){
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
        return len==f.getLength() && genSequence.equals(f.getSequence());
    }

}
