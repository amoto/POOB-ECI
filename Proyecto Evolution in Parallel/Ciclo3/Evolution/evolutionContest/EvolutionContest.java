package evolutionContest;
import Evolutions.*;
import java.util.*;
import java.lang.*;
/**
 * Resuelve el problema de la maraton
 * 
 * @author Julian Devia / Hugo Alvarez
 * @version 06/10/2015
 */
public class EvolutionContest
{
    private static String[] fosiles;
    private static String[] linea1;
    private static String[] linea2;
    private static String organismo;
    private static Evolution ev;

    /**
     * simula la ultima solucion realizada
     * 
     * @param  slow, true si desea que sea lenta y visible la solucion, false que solo sea visible la solucion al finalizar la simulacion
     */
    public static void simulate(boolean slow) throws InterruptedException
    {
        ev = new Evolution(organismo,fosiles);
        if(slow){
            ev.makeVisible();
        }
        if (slow){
            Thread.sleep(2000);
        }
        ev.addPath();
        if (slow){
            Thread.sleep(2000);
        }
        ev.addPath();
        for(int i = 0; i < fosiles.length; i++){
            if (slow){
                Thread.sleep(2000);
            }
            ev.situate(1);
        }
        if (slow){
            Thread.sleep(2000);
        }
        ev.sort();
        if(!slow){
            ev.makeVisible();
        }
        Thread.sleep(5000);
        ev.makeInvisible();
    }
    
    /**
     * Soluciona el problema de la maraton
     *
     * @param  organism, el organismo de la solucion
     * @param  fossils, el arreglo de fosiles de la solucion
     * @return     una matriz donde muestra la solucion del problema
     */
    public static String[][] solve(String organism, String[] fossils)
    {
        organismo = organism;
        fosiles = fossils;
        linea1 = new String[0];
        linea2 = new String[0];
        StringBuffer ans1 = new StringBuffer("");
        StringBuffer ans2 = new StringBuffer("");
        ArrayList<String> path1 = new ArrayList<String>();
        ArrayList<String> path2 = new ArrayList<String>();
        for (int i = 0; i < fossils.length; i++) {
            String fosil = fossils[i];
            boolean cm = check(fosil,organism);
            if(cm){
                boolean intent = intento(path1,fosil);
                if(!intent){
                    intent = intento(path2,fosil);
                    if (intent) ans2.append(fosil);
                }else ans1.append(fosil);
            }
        }
        ordenamiento(path1,path2,ans1,ans2);
        String [][] strd = new String[2][0];
        strd[0] = linea1;
        strd[1] = linea2;
        return strd;
    }
    
    /**
     * Decide cual linea va primero o va despues, de acuerdo a su longitud y orden lexicografico
     *
     * @param  path1,   la primera linea evolutiva
     * @param  path2,   la segunda linea evolutiva
     * @param  ans1,    la concatenacion de los elementos de path1
     * @param  ans2,    la concatenacion de los elementos de path2
     */
    private static void ordenamiento(ArrayList<String> path1, ArrayList<String> path2, StringBuffer ans1, StringBuffer ans2)
    {
        boolean primero=false;
        if(path1.size() + path2.size() == fosiles.length){
            if(path1.size()<path2.size()){
                primero = true;
            }
            else if(path1.size()==path2.size()){
                if(ans1.toString().compareTo(ans2.toString())<0) primero = true;
            }
            if(primero){
                linea1 = path1.toArray(linea1);
                linea2 = path2.toArray(linea2);
            }else{
                linea1 = path2.toArray(linea1);
                linea2 = path1.toArray(linea2);
            }
        }else {linea1 = new String[0];linea2 = new String[0];}
    }
    
    /**
     * intenta ubicar la cadena fosil en el path p
     *
     * @param  p,   la linea evolutiva que se desea situar el fosil
     * @param  fosil,  el fosil a situar
     * @return true si lo pudo agregar, false d.l.c.
     */
    private static boolean intento(ArrayList<String> p, String fosil) {
        boolean flag=true;
        int place=0;
        boolean foundPlace=true;
        for(int i=0; i<p.size() && foundPlace ;i++){
            if(p.get(i).length()==fosil.length()){
                foundPlace=false;flag=false;
            }
            if(p.get(i).length() > fosil.length()){
                foundPlace=false;place=i;
            }
        }
        if (foundPlace) place=p.size();
        //System.out.println("Place: "+place+" tam: "+p.size());
        if(flag && p.size()>0 && place < p.size()) flag=check(fosil,p.get(place));
        if(flag && place>=1 && place <=p.size()) flag=check(p.get(place-1),fosil);
        if(flag && place < p.size()) p.add(place,fosil);
        else if(flag && place == p.size()) p.add(fosil);
        return flag;
    }

    /**
     * verifica su un fosil es evolucion del otro
     *
     * @param  s,   la cadena pequeña
     * @param  b,   la cadena grande
     * @return true si b es evolucion de s, false, d.l.c.
     */
    private static boolean check(String s, String b) {
        int k=0;
        int bl = b.length();
        boolean isAble=s.length()<bl;
        for(int i=0;i<s.length() && isAble;i++){
            while(k<bl && s.charAt(i)!=b.charAt(k)){
                k++;
            }
            if(k==bl){
                isAble=false;
            }
            k++;
        }
        return isAble;
    }

}
