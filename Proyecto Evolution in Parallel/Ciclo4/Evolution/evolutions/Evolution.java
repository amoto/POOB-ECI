package evolutions;

import shapes.*;
import java.util.*;
import javax.swing.*;
/**
 * Esta clase trata de crear una solucion al problema E de la maraton mundial 2015
 * 
 * @author Julian Devia - Hugo Alvarez
 * @version 004
 */
public class Evolution
{
    //variables de programa
    public static final int MAXH=1400;
    public static final int MAXV=600;
    private int sz;
    private Organismo actual;
    private int x;
    private int y;
    private boolean isVisible;
    private ArrayList<Fossil> fosiles;
    private ArrayList<Path> lineas;
    private boolean isOk;
    /**
     * Construrtor de la clase Evolution, que realiza el inicio a la clase
     * 
     * @param sequence, que es la cadena de nucleotidos
     */
    public Evolution(String sequence)
    {
        isOk=true;
        actual=new Organismo(sequence);
        if(actual.getLength()==0){
            isOk=false;
            JOptionPane.showMessageDialog(null,"Ningun caracter que introdujo es valido.");
        }
        if(isOk){
            isVisible=false;
            x = 0;
            y = 0;
            sz=30;
            fosiles = new ArrayList<Fossil>();
            lineas = new ArrayList<Path>();
            isOk=true;
            if(actual.getLength()>(MAXH/sz)-1) {
                JOptionPane.showMessageDialog(null,"No se va a hacer visible");
            }
        }
    }

    /**
     * Este constructor anade el organismo y fosiles al planeta
     *
     * @param  organism, es la cadena de evolucion actual
     * @param fossils, son los fosiles que se agregan al planeta
     */
    public Evolution(String organism,String[] fossils)
    {
        isOk=true;
        actual=new Organismo(organism);
        if(actual.getLength()==0){
            isOk=false;
            JOptionPane.showMessageDialog(null,"Ningun caracter que introdujo es valido.");
        }
        if(isOk){
            isVisible=false;
            x = 0;
            y = 0;
            sz=30;
            fosiles = new ArrayList<Fossil>();
            lineas = new ArrayList<Path>();
            isOk=true;
            for(int i=0;i<fossils.length;i++){
                addFossil(fossils[i],"normal");
            }
            if(actual.getLength()>(MAXH/sz)-1 || fosiles.size()>(Math.ceil(MAXV/3.28)-2*sz)/sz+1) {
                JOptionPane.showMessageDialog(null,"No se va a hacer visible");
            }
        }
    }

    /**
     * Este constructor anade el organismo y fosiles al planeta
     *
     * @param  organism, es la cadena de evolucion actual
     * @param fossils, son los fosiles que se agregan al planeta
     * @param types, son los tipos de los fosiles
     */
    public Evolution(String organism,String[] fossils,String[] types)
    {
        isOk=true;
        actual=new Organismo(organism);
        if(actual.getLength()==0){
            isOk=false;
            JOptionPane.showMessageDialog(null,"Ningun caracter que introdujo es valido.");
        }
        if(isOk && fossils.length!=types.length){
            isOk=false;
            JOptionPane.showMessageDialog(null,"No hay suficientes tipos o fosiles.");
            finish();
        }
        if(isOk){
            isVisible=false;
            x = 0;
            y = 0;
            sz=30;
            fosiles = new ArrayList<Fossil>();
            lineas = new ArrayList<Path>();
            isOk=true;
            for(int i=0;i<fossils.length;i++){
                addFossil(fossils[i],types[i]);
            }
            if(actual.getLength()>(MAXH/sz)-1 || fosiles.size()>(Math.ceil(MAXV/3.28)-2*sz)/sz+1) {
                JOptionPane.showMessageDialog(null,"No se va a hacer visible");
            }
        }
    }

    /**
     * Este metodo determina si es posible visualizar el simulador
     *
     * @return     True si se puede hacer visible, d.l.c false
     */
    private boolean isPosible()
    {
        boolean isP = true;
        if(fosiles.size() < (Math.ceil(MAXV/3.28)-2*sz)/sz+1 && actual.getLength() < (MAXH/sz)-1){
            if(lineas.size()>0  && lineas.get(lineas.size()-1).getEnd()+5 < MAXH){
                boolean fosilesVisibles = true;
                for(int i = 0; i < lineas.size() && fosilesVisibles; i++){
                    fosilesVisibles = lineas.get(i).getLength()*(sz+10) < MAXV-(Math.ceil(MAXV/3.28)+sz);
                }
                isP = fosilesVisibles;
            }
            else if(lineas.size()>0  && lineas.get(lineas.size()-1).getEnd()+5 > MAXH){
                isP = false;
            }
        }else{
            isP = false;
        }
        return isP;
    }

    /**
     * Este metodo ubica el fosil en el canvas
     *
     */
    private void ubicarFosil()
    {
        for(int i = 0; i < fosiles.size(); i++){
            fosiles.get(i).makeInvisible();
            fosiles.get(i).reset();
            fosiles.get(i).move(0,(int)(Math.ceil(MAXV/3.28)-(i*sz)));
            if(isVisible){
                fosiles.get(i).makeVisible();
            }
        }
    }

    /**
     * Este metodo ubica la linea de evolucion en el canvas
     *
     */
    private void ubicarLinea()
    {
        if(lineas.size()>0){
            lineas.get(0).makeInvisible();
            lineas.get(0).reset();
            lineas.get(0).move(0,(int)(Math.ceil(MAXV/3.28)+sz));
        }
        if(isVisible){
            lineas.get(0).makeVisible();
        }
        for (int i=1;i<lineas.size();i++){
            Path tmp=lineas.get(i-1);
            lineas.get(i).makeInvisible();
            lineas.get(i).reset();
            lineas.get(i).move(tmp.getEnd()+15,(int)(Math.ceil(MAXV/3.28)+sz));
            if(isVisible){
                lineas.get(i).makeVisible();
            }
        }
    }


    /**
     * retorna todas las cadenas de los fosiles en lineas de evolucion
     *
     * @return     las cadenas geneticas de los fosiles de las lineas de evolucion
     */
    public String[][] consult()
    {
        int maxFosiles=0;
        isOk=true;
        String[][] consulta=new String[lineas.size()][0];
        for(int i=0;i<lineas.size();i++){
            consulta[i]=lineas.get(i).consult();
        }
        return consulta;
    }

    /**
     * Este metodo añade una nueva linea de evolucion sin fosiles
     *
     */
    public void addPath()
    {
        isOk=true;
        if(isVisible && lineas.size()>=1 && lineas.get(lineas.size()-1).getEnd()+3*15>MAXH){
            mensajeUsuario("el simulador se hará invisible por el limite de la pantalla");
            makeInvisible();
        }
        lineas.add(new Path());
        lineas.get(lineas.size()-1).move(0,(int)(Math.ceil(MAXV/3.28)+30));
        checkZoom(lineas.get(lineas.size()-1));
        if(isVisible){
            lineas.get(lineas.size()-1).makeVisible();
        }
        ubicarLinea();
        isOk=true;
    }


    /**
     * Este metodo ayuda al anadir fosil
     *
     * @param  sequence, que es la cadena a revisar si se puede agregar o no
     */
    private void helpFossil(String sequence)
    {
        String realSequence=actual.soloValidas(sequence.toUpperCase());
        if(realSequence.length()>actual.getLength()){
            mensajeUsuario("No se puede agregar porque es mayor que la cadena actual del planeta");
            isOk=false;
        }
        if(isVisible && isOk && (fosiles.size() > (Math.ceil(MAXV/3.28)-2*sz)/sz+1 || sequence.length()>(MAXH/sz)-1)){
            mensajeUsuario("el simulador se hará invisible por el limite de la pantalla");
            makeInvisible();
        }
        for(int i=0;i<fosiles.size() && isOk;i++){
            if(fosiles.get(i).getSequence().equals(sequence) || fosiles.get(i).getSequence().equals(realSequence)){
                isOk=false;
                mensajeUsuario("El fosil ya existe.");
            }
        }
        for(int i=0;i<lineas.size() && isOk;i++){
            for(int j=0;j<lineas.get(i).getLength() && isOk;j++){
                if(lineas.get(i).getFossil(j).getSequence().equals(sequence) || lineas.get(i).getFossil(j).getSequence().equals(realSequence)){
                    isOk=false;
                    mensajeUsuario("El fosil ya existe.");
                }
            }
        }
    }

    /**
     * Este metodo agrega un fosil libre
     *
     * @param  sequence, que es una cadena de caracteres que sea menor a la cadena de evolucion
     * y tenga los caracteres C,A,M.
     * @param  type, el tipo del fosil que se va a agregar
     */
    public void addFossil(String sequence, String type)
    {
        isOk=true;
        helpFossil(sequence);
        String realSequence=actual.soloValidas(sequence.toUpperCase());
        if(isOk && realSequence.length()==0){
            isOk=false;
            mensajeUsuario("Ningun caracter que introdujo es valido.");
        }
        if(isOk && !verificaTipo(type)){
            isOk=false;
            mensajeUsuario("El tipo que ingreso es incorrecto.");
        }
        if(isOk){
            decide(type,realSequence);
            checkZoom(fosiles.get(fosiles.size()-1));
            if(isVisible){
                fosiles.get(fosiles.size()-1).makeVisible();
            }
            ubicarFosil();
        }
   }

   /**
     * Este metodo agrega un fosil libre
     *
     * @param  sequence, que es una cadena de caracteres que sea menor a la cadena de evolucion
     * y tenga los caracteres C,A,M.
     */
    public void addFossil(String sequence)
    {
        addFossil(sequence,"normal");
   }

   /**
    * decide que tipo de fosil crear con el tipo dado y lo crea con la sequencia dada
    *
    * @param  type, el tipo de fosil que debe crear
    * @param  sequence, la sequencia genetica con la que se creara el fosil
    */
   private void decide(String type,String sequence)
   {
        String typeD=type.toLowerCase();
        if(typeD.equals("normal")){
            fosiles.add(new Fossil(sequence));
        }else if(typeD.equals("top")){
            fosiles.add(new Top(sequence));
        }else if(typeD.equals("magical")){
            fosiles.add(new Magical(sequence));
        }else if(typeD.equals("destroyer")){
            fosiles.add(new Destroyer(sequence));
        }else if(typeD.equals("colonizador")){
            fosiles.add(new Colonizador(sequence));
        }
   }


    /**
     * Verifica si el tipo determinado puede ser un tipo de fosil
     *
     * @param  type, el tipo que se quiere verificar
     * @return     true, si el tipo pertenece a los tipos de fosiles, flase d.l.c.
     */
    private boolean verificaTipo(String type)
    {
        String typeD=type.toLowerCase();
        boolean valido=false;
        if(Fossil.TIPOS.contains(typeD)){
            valido=true;
        }
        return valido;
    }

    /**
     * Este metodo agranda o disminuye el tamano de las cosas antes de hacerlas visibles
     *
     * @param  p, que es la linea a agrandar o disminuir
     */
    private void checkZoom(Path p)
    {
        if(sz>30){
            for(int i = 0; i < sz-30; i++){
                p.zoom('+');
            }
        }
        else if(sz<30){
            for(int i = 0; i > sz-30; i--){
                p.zoom('-');
            }
        }
    }

    /**
     * Este metodo agranda o disminuye el tamano de las cosas antes de hacerlas visibles
     *
     * @param  f, que es el fosil a agrandar o disminuir
     */
    private void checkZoom(Fossil f)
    {
        if(sz>30){
            for(int i = 0; i < sz-30; i++){
                f.zoom('+');
            }
        }
        else if(sz<30){
            for(int i = 0; i > sz-30; i--){
                f.zoom('-');
            }
        }
    }


    /**
     * Este metodo ayuda a el situate(fossil,path,position)
     *
     * @param  fossil, el fosil a agregar
     * @param path, la linea donde va a ser agregado el fosil
     * @param position, la posicion en la que va a ser agregado el fosil
     */
    private void helpSituate(int fossil, int path, int position)
    {
        if(isOk && (fossil <1 || fossil> fosiles.size() || path < 1 || path > lineas.size() || position > lineas.get(path-1).getLength()+1)){
            mensajeUsuario("Los datos ingresados sin incorrectos");
            isOk=false;
        }
        int max=0;
        if(isOk && lineas.get(path-1).getLength()>0){
            max=lineas.get(path-1).getFossil(lineas.get(path-1).getLength()-1).getLength();
        }
        if(isVisible && isOk && ((fosiles.get(fossil-1).getSequence().length()>max && lineas.get(lineas.size()-1).getEnd()+(fosiles.get(fossil-1).getSequence().length()-max)*sz>MAXH) || lineas.get(path-1).getLength()*(sz+10)>MAXV-Math.ceil(MAXV/3.28)-sz)){
            mensajeUsuario("el simulador se hará invisible por el limite de la pantalla");
            makeInvisible();
        }
        if(isOk){
            isOk = lineas.get(path-1).isAddable(fosiles.get(fossil-1),actual,position-1);
            if (!isOk){
                mensajeUsuario("no se puede agregar fosil");
            }
        }
    }

    /**
     * Este metodo situa un fosil a una linea de evolucion
     *
     * @param  fossil, que es el fosil libre a agregar
     * @param  path, es la linea de evolucion que desea agregarlo
     * @param  position, es la posicion correspondiente en el camino, este altera por la cantidad y orden de los
     * elementos
     */
    public void situate(int fossil,int path, int position)
    {
        isOk = true;
        helpSituate(fossil,path,position);
        if(isOk){
            Fossil tmp=fosiles.get(fossil-1);
            if (isOk){
                boolean situo= lineas.get(path-1).addFossil(tmp, position,actual);
                if(situo){
                    fosiles.remove(fossil-1);
                }else{
                    mensajeUsuario("no se puede agregar fosil en la posicion dada");
                    isOk=false;
                }
                ubicarLinea();
            }
            ubicarFosil();
        }
    }


    /**
     * Este metodo ayuda a el situate(fossil,path)
     *
     * @param  fossil, el fosil a agregar
     * @param path, la linea donde va a ser agregado el fosil
     */
    private void helpSituate(int fossil,int path)
    {
        if(isOk && (fossil <1 || fossil> fosiles.size() || path < 1 || path > lineas.size())){
            mensajeUsuario("Los datos ingresados sin incorrectos");
            isOk=false;
        }
        int max=0;
        if(isOk && lineas.get(path-1).getLength()>0){
            max=lineas.get(path-1).getFossil(lineas.get(path-1).getLength()-1).getLength();
        }
        if(isVisible && isOk && ((fosiles.get(fossil-1).getSequence().length()>max && lineas.get(lineas.size()-1).getEnd()+(fosiles.get(fossil-1).getSequence().length()-max)*sz>MAXH) || lineas.get(path-1).getLength()*(sz+10)>MAXV-Math.ceil(MAXV/3.28)-sz)){
            mensajeUsuario("el simulador se hará invisible por el limite de la pantalla");
            makeInvisible();
        }
        if(isOk){
            isOk=false;
            for(int i=0;i<lineas.get(path-1).getLength()+1 && !isOk;i++){
                isOk = isOk || lineas.get(path-1).isAddable(fosiles.get(fossil-1),actual,i);
            }
            if (!isOk){
                mensajeUsuario("no se puede agregar fosil");
            }
        }
    }

    /**
     * Este metodo situa un fosil a una linea de evolucion
     *
     * @param  fossil, que es el fosil libre a agregar
     * @param  path, es la linea de evolucion que desea agregarlo
     */
    public void situate(int fossil,int path)
    {
        isOk = true;
        helpSituate(fossil,path);
        if(isOk){
            boolean situado=false;
            boolean tmp = isVisible;
            boolean posicion = false;
            int pos=lineas.get(path-1).getLength();
             if(isVisible){
                makeInvisible();
            }
            for(int i=0;i<lineas.get(path-1).getLength() && !posicion;i++){
                if(lineas.get(path-1).getFossil(i).getLength()>fosiles.get(fossil-1).getLength()){
                    posicion=true;
                    pos=i;
                }
            }
            situate(fossil,path,pos+1);
            situado=ok();
            for (int i=0;i<=lineas.get(path-1).getLength() && !situado;i++){
                situate(fossil,path,i+1);
                situado = ok();
            }
            if(tmp){
                makeVisible();
            }
            if(!situado){
                mensajeUsuario( "no se puede agregar fosil a ninguna linea de evolucion");
            }
        }
    }

    /**
     * Este metodo situa un fosil a una linea de evolucion
     *
     * @param  fossil, que es el fosil libre a agregar
     */
    public void situate(int fossil)
    {
        isOk = true;
        if(isOk && (fossil <1 || fossil> fosiles.size() || lineas.size()==0)){
            mensajeUsuario("Los datos ingresados sin incorrectos");
            isOk=false;
        }
        if(isOk){
            boolean situado=false;
            boolean tmp = isVisible;
            if(isVisible){
                makeInvisible();
            }
            for (int i=0;i<lineas.size() && !situado;i++){
                situate(fossil,i+1);
                situado = ok();
            }
            if(tmp){
                makeVisible();
            }
            if(!situado){
                mensajeUsuario( "no se puede agregar fosil a ninguna linea de evolucion");
            }
        }
    }
    
    /**
     * Este metodo mezcla dos posibles lineas de evolucion siempre y cuando se cumpla las condiciones requeridas
     *
     * @param  first, que es la linea de evolucion destino
     * @param  second que es la linea de evolucion origen
     */
    public void mergePaths(int first,int second)
    {
        isOk = (first > 0) && (second > 0) && (first <= lineas.size()) && (second <= lineas.size());
        boolean firstM=false;
        boolean secondM=false;
        boolean isV=isVisible;
        if(isOk){
            if (isV) makeInvisible();
            isOk = lineas.get(first - 1).mergeWith(lineas.get(second - 1),actual);
            firstM=isOk;
            if(!isOk){
                isOk = lineas.get(second - 1).mergeWith(lineas.get(first - 1),actual);
                secondM=isOk;
            }
        }else{
            mensajeUsuario( "Uno de los caminos no existe.");
        }
        if(isOk){
            if(firstM)
                lineas.remove(second - 1);
            else if(secondM)
                lineas.remove(first - 1);
            ubicarLinea();
            if(isV) makeVisible();
        }
        else{
            if(isV) makeVisible();
            mensajeUsuario( "Las dos lineas de evolucion no se pueden mezclar.");
        }
        
    }

    /**
     * Este metodo hace visible los fosiles, las lineas de evolucion y la cadena actual
     * 
     * @return  isVisible que es true para mostrar los objetos en el canvas
     */
    public void makeVisible()
    {
        if(isPosible()){
            if(!isVisible){
                isVisible = true;
                actual.makeVisible();
                for(int i = 0; i < fosiles.size(); i++){
                    fosiles.get(i).makeVisible();
                }
                for(int i = 0; i < lineas.size(); i++){
                    lineas.get(i).makeVisible();
                }
            }
            isOk = true;
        }
        else{
            mensajeUsuario( "No se puede hacer visible");
            isOk = false;
        }
    }

    /**
     * Este metodo ordena las lineas de evolucion dependiendo de:
     * El numero de Fosiles
     * la longitud de los fosiles unidos
     * El orden lexicografico de los fosiles unidos
     *
     */
    public void sort()
    {
        isOk=lineas.size()>0;
        if(lineas.size()>0){
            Collections.sort(lineas);
            ubicarLinea();
        }
    }

    /**
     * Este metodo agranda o disminuye el tamano de todo el mundo
     *
     * @param  sign, que es '+' o '-' para agrendar o disminuir respectivamente
     */
    public void zoom(char sign)
    {
        actual.zoom(sign);
        boolean tmp = isVisible;
        if(tmp){
            makeInvisible();
        }
        for(int i = 0; i < fosiles.size(); i++){
            fosiles.get(i).zoom(sign);
        }
        for(int i = 0; i < lineas.size(); i++){
            lineas.get(i).zoom(sign);
        }
        if(sign == '+'){
            sz++;
        }else if (sign == '-'){
            sz--;
        }
        ubicarLinea();
        ubicarFosil();
        if(tmp){
            makeVisible();
        }
    }

    /**
     * Este metodo oculta los elementos que son los fosiles, las lineas de evolucion y la cadena actual
     *
     * @return  isVisible que es false para ocultar los objetos en el canvas
     */
    public void makeInvisible()
    {
        if(isVisible){
            isVisible = false;
            actual.makeInvisible();
            for(int i = 0; i < fosiles.size(); i++){
                fosiles.get(i).makeInvisible();
            }
            for(int i = 0; i < lineas.size(); i++){
                lineas.get(i).makeInvisible();
            }
        }
        isOk = true;
    }

    /**
     * Este metodo finaliza por completo el programa
     *
     */
    public void finish()
    {
       isOk = true;
       System.exit(0);
    }

    /**
     * Este metodo revisa que el ultimo metodo ejecutado se realizo bien
     *
     * @return  isOk que es la variable para saber si ejecuto bien el ultimo metodo
     */
    public boolean ok()
    {
        return isOk;
    }

    /**
     * Muestra un mensaje al usuario
     *
     * @param  mensaje   el mensaje que se le mostrara al usuario
     */
    private void mensajeUsuario(String mensaje)
    {
        if(isVisible){
            JOptionPane.showMessageDialog(null,mensaje);
        }
    }

}