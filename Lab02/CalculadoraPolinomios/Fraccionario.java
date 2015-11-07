import java.util.*;
/**
 * Fraccionario
 * Esta clase implementa el tipo de dato Fraccionario; es decir, un número racional que se pueden escribir de la forma p/q, donde p y q son enteros, con q <> 0
 * La implemantación se hace mediante objetos inmutables
 * INV: El fraccionario se encuentra representado en forma irreductible.
 * @author E.C.I.
 *
 */
public class Fraccionario {
    private int n;
    private int d;
    
    /**Calcula el máximo común divisor de dos enteros
     * Lo implementaremos mediante el algoritmo recursivo
     * @param a primer entero
     * @param b segundo entero
     * @return el Máximo Común Divisor de a y b
     */
    public static int mcd(int a, int b) {
        int ans;
        if(b==0) ans = Math.abs(a);
        else ans = mcd(b,a%b);
        return ans;
    }   
    

    
    /**Crea un nuevo fraccionario, dado el numerador y el denominador
     * @param numerador
     * @param denominador. denominador <> 0
     */
    public Fraccionario (int numerador, int denominador) {
        n = numerador;
        d = denominador;
        ciclo();
        if(d<0){
            n*=-1;
            d*=-1;
        }
    }
    
    /**Crea un fraccionario correspondiente a un entero
     * @param entero el entero a crear
     */
    public Fraccionario (int entero) {
        n = entero;
        d = 1;
    }

     /**Crea un fraccionario, a partir de su representacion mixta. 
     * El numero creado es enteroMixto + numeradorMixto/denominadorMixto
     * @param enteroMixto la parte entera del numero
     * @param numeradorMixto el numerador de la parte fraccionaria
     * @param denominadorMixto el denominador de la parte fraccionaria. denominadorMixto<>0
     */
    public Fraccionario (int enteroMixto, int numeradorMixto, int denominadorMixto) {
        if(enteroMixto < 0){
            enteroMixto*=-1;
            denominadorMixto*=-1;
        }
        if(numeradorMixto < 0){
            numeradorMixto*=-1;
            denominadorMixto*=-1;
        }
        if(denominadorMixto<0){
            d=denominadorMixto*-1;
            n=enteroMixto*d+numeradorMixto;
            n*=-1;
            ciclo();
        }else{
            n = enteroMixto*denominadorMixto+numeradorMixto;
            d = denominadorMixto;
            ciclo();
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    private void ciclo()
    {
        while(mcd(n,d)!=1){
            int mc = mcd(n,d);
            n/=mc;
            d/=mc;
        }
    }


    
    /**
     * Un fraccionario p/q está escrito en forma simplificada si q es un entero positivo y 
     * el máximo común divisor de p y q es 1.
     * @return El numerador simplificado del fraccionario
     */
    public int numeradorSimplificado() {
        return n;
    }
    
    /**
     * Un fraccionario p/q está escrito en forma simplificada si q es un entero Positivo y 
     * el máximo común divisor de p y q es 1.
     * @return el denominador simplificado del fraccionario
     */
    public int denominadorSimplificado() {
        return d;
    }
    
    /**Suma este fraccionario con otro fraccionario
     * @param otro es otro fraccionario
     * @return este+otro
     */
    public Fraccionario sume (Fraccionario otro) {
        int num=(n*otro.denominadorSimplificado())+(otro.numeradorSimplificado()*d);
        int den=d*otro.denominadorSimplificado();
        return new Fraccionario(num,den);
    }
    
    /**Multiplica este fraccionario con otro fraccionario
     * @param otro El otro fraccionario
     * @return este * otro
     */
    public Fraccionario multiplique (Fraccionario otro) {
        return new Fraccionario(n*otro.numeradorSimplificado(),d*otro.denominadorSimplificado());
    }
    
    /**Calcula el inverso multiplicativo de este fracionario, si no es cero
     * @return El inverso multiplicativo de este fraccionario (i.e. 1/este)
     */
    public Fraccionario inverso () {
        if(Math.abs(n)>0){
            return new Fraccionario(d,n);
        }
        return null;
    }
    
    /** Calcula el reciproco aditivo de este fraccionario
     * @return El recíproco aditivo de este fraccionario (i.e. (-1) * este)
     */
    public Fraccionario reciproco () {
        return new Fraccionario(-n,d);
    }
    
    /**Compara este fraccionario con otro fraccionario
     * @param otro el otro fraccionario
     * @return true si este fraccionario es igual matemáticamente al otro fraccionario, False d.l.c.
     */
    @Override
    public boolean equals (Object otro) {
        Fraccionario other=(Fraccionario) otro;
        return n==other.numeradorSimplificado() && d == other.denominadorSimplificado();
    }


    /** Calcula la representacion en cadena de un fraccionario en formato mixto simplificado
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString() {
        int entero=n/d;
        int num=n-(entero*d);
        String cadena;
        if(Math.abs(entero)>0 && d>1){
            cadena=Integer.toString(entero)+" "+Integer.toString(Math.abs(num))+"/"+Integer.toString(d);
        }else{
            cadena=Integer.toString(n)+"/"+Integer.toString(d);
        }
        return cadena;
    }

}
