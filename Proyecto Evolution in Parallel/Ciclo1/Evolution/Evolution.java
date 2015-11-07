import java.util.*;
import javax.swing.*;
/**
 * Esta clase trata de crear una solucion al problema E de la maraton mundial 2015
 * 
 * @author Julian Devia - Hugo Alvarez
 * @version 002
 */
public class Evolution
{
    //variables de programa
    private String genSequence;
    private int len;
    private int x;
    private int y;
    private boolean isVisible;
    private static final String[] colores = {"red","yellow","blue","green","magenta","black"};
    private static final char[] letras = {'A','C','M'};
    private ArrayList<Fossil> fosiles;
    private ArrayList<Path> lineas;
    private ArrayList<Fossil> totalFosiles;
    private HashMap<Character,Integer> inGenSequence;
    private int nLineas;
    private int nFosilesLibres;
    private int nFosiles;
    private boolean isOk;
    //variables de presentacion
    private Circle[] nucleotidos;
    /**
     * Construrtor de la clase Evolution, que realiza el inicio a la clase
     * 
     * @param sequence, que es la cadena de nucleotidos
     * <ol>
     *  <li> Precondiciones: </li>
     *  <ul>
     *      <li> sequence debe ser una cadena que contiene los caracteres A, C, M. </li>
     *      <li> la cadena no puede ser vacia </li>
     *      <li> el tamaño de la cadena menor a 4000 caracteres </li>
     *  </ul>
     *  <li> Poscondiciones: </li>
     *  <ul>
     *      <li> Realizar el objeto con los metodos apropiados </li>
     *  </ul>
     * </ol>
     */
    public Evolution(String sequence)
    {
        isOk=true;
        inGenSequence=new HashMap<Character,Integer>();
        String realSequence=correctSeq(sequence.toUpperCase());
        if(realSequence.length()==0){
            isOk=false;
            JOptionPane.showMessageDialog(null,"Ningun caracter que introdujo es valido.");
        }
        if(isOk){
            genSequence=realSequence;
            len=realSequence.length();
            countChars();
            nucleotidos=new Circle[len];
            isVisible=false;
            for(int i=0;i<len;i++){
                nucleotidos[i]=new Circle();
                nucleotidos[i].changeColor(colores[(genSequence.charAt(i)%15)-2]);
                nucleotidos[i].moveHorizontal(30*i);
            }
            x = 0;
            y = 0;
            nLineas=0;
            nFosilesLibres=0;
            nFosiles=0;
            fosiles = new ArrayList<Fossil>();
            lineas = new ArrayList<Path>();
            totalFosiles= new ArrayList<Fossil>();
            isOk=true;
            if(len>63) {
                JOptionPane.showMessageDialog(null,"No se va a hacer visible");
            }
        }
    }

    /**
     * Cuenta las repeticiones de cada letra en la sequencia genética del planeta
     *
     */
    private void countChars()
    {
        for(int i=0;i<genSequence.length();i++){
            if(!inGenSequence.containsKey(genSequence.charAt(i))){
                inGenSequence.put(genSequence.charAt(i),1);
            }else{
                inGenSequence.put(genSequence.charAt(i),inGenSequence.get(genSequence.charAt(i))+1);
            }
        }
    }

    /**
     * Corrige la sequencia dejando solo los elementos permitidos
     *
     * @param  seq,la sequencia a verificar
     * @return     realSeq, la sequencia corregida
     */
    private String correctSeq(String seq)
    {
        String realSeq="";
        for(int i=0;i<seq.length();i++){
            for (int j=0;j<letras.length;j++){
                if(seq.charAt(i)==letras[j]){
                    realSeq+=letras[j];

                }
            }
        }
        return realSeq;
    }

    /**
     * Este metodo determina si es posible visualizar el simulador
     *
     * @return     iss, True si se puede hacer visible, d.l.c false
     */
    private boolean isPosible()
    {
        boolean iss = true;
        if(nFosilesLibres < 9 && len < 64){
            if(nLineas>0  && lineas.get(nLineas-1).getEnd() < 1930){
                boolean ban = true;
                for(int i = 0; i < nLineas && ban; i++){
                    ban = lineas.get(i).getLength() < 13;
                }
                iss = ban;
            }
        }else{
            iss = false;
        }
        return iss;
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
            fosiles.get(i).move(0,250-(i*30));
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
        for (int i=1;i<lineas.size();i++){
            Path tmp=lineas.get(i-1);
            lineas.get(i).makeInvisible();
            lineas.get(i).reset();
            lineas.get(i).move(tmp.getEnd()+15,280);
            if(isVisible){
                lineas.get(i).makeVisible();
            }
        }
    }

    /**
     * Este metodo añade una nueva linea de evolucion sin fosiles
     *
     */
    public void addPath()
    {
        isOk=true;
        if(isVisible && nLineas>=1 && lineas.get(nLineas-1).getEnd()+45>1930){
            isOk=false;
            JOptionPane.showMessageDialog(null,"No se puede crear por limite de elementos en pantalla");
        }
        if(isOk){
            lineas.add(new Path());
            lineas.get(nLineas).move(0,280);
            nLineas++;
            if(isVisible){
                lineas.get(nLineas-1).makeVisible();
            }
            ubicarLinea();
            isOk=true;
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
        isOk=true;
        if(sequence.length()>len){
            JOptionPane.showMessageDialog(null,"No se puede agregar porque es mayor que la cadena actual del planeta");
            isOk=false;
        }
        if(isVisible && isOk && (nFosilesLibres > 7 || sequence.length()>63)){
            isOk = false;
            JOptionPane.showMessageDialog(null,"No se puede agregar fosiles porque hay un limite para la pantalla");
        }
        for(int i=0;i<nFosiles && isOk;i++){
            if(totalFosiles.get(i).getSequence().equals(sequence)){
                isOk=false;
                JOptionPane.showMessageDialog(null,"El fosil ya existe.");
            }
        }
        String realSequence=correctSeq(sequence.toUpperCase());
        if(isOk && realSequence.length()==0){
            isOk=false;
            JOptionPane.showMessageDialog(null,"Ningun caracter que introdujo es valido.");
        }
        if(isOk){
            fosiles.add(new Fossil(realSequence));
            totalFosiles.add(new Fossil(realSequence));
            nFosiles++;    
            if(isVisible){
                fosiles.get(nFosilesLibres).makeVisible();
            }
            nFosilesLibres++;
            ubicarFosil();
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
        if(isOk && (fossil <1 || fossil> nFosilesLibres || path < 1 || path > nLineas || position > lineas.get(path-1).getLength()+1)){
            JOptionPane.showMessageDialog(null,"Los datos ingresados sin incorrectos");
            isOk=false;
        }
        if(isVisible&& isOk && lineas.get(path-1).getLength()>13){
            isOk = false;
            JOptionPane.showMessageDialog(null,"no se puede agregar fosil, limite de pantalla excedido");
        }
        int max=0;
        if(isOk && lineas.get(path-1).getLength()>0){
            max=lineas.get(path-1).getFossil(lineas.get(path-1).getLength()-1).getLength();
        }
        if(isVisible && isOk && fosiles.get(fossil-1).getSequence().length()>max && lineas.get(nLineas-1).getEnd()+(fosiles.get(fossil-1).getSequence().length()-max)*30>1930){
            isOk=false;
            JOptionPane.showMessageDialog(null,"no se puede agregar fosil, limite de pantalla excedido");
        }
        if(isOk){
            isOk = lineas.get(path-1).checkPosition(fosiles.get(fossil-1).getLength(), position) && lineas.get(path-1).isAddable(fosiles.get(fossil-1));
            if (!isOk){
                JOptionPane.showMessageDialog(null,"no se puede agregar fosil");
            }
        }
        if(isOk){
            Fossil tmp=fosiles.remove(fossil-1);
            String tmpSequence=tmp.getSequence();
            checkSequence(tmpSequence);
        
            if (isOk){
                lineas.get(path-1).addFossil(tmp, position);
                nFosilesLibres--;
                ubicarLinea();
                ubicarFosil();
            }else{
                fosiles.add(tmp);
                ubicarFosil();
            }
        }
    }
    
    /**
     * Verifica que la sequencia determinada contenga los elementos de la secuencia genetica de los seres del planeta
     *
     * @param  seq, la sequencia de nucleotidos del fosil
     * @return     the sum of x and y
     */
    private void checkSequence(String seq)
    {
        HashMap<Character,Integer> inSequence=new HashMap<Character,Integer>();
        ArrayList<Character> tmp=new ArrayList<Character>();
        for (int i=0;i<seq.length() && isOk;i++){
            boolean flag=false;
            for(int j=0;j<genSequence.length() && !flag; j++){
                if(genSequence.charAt(j)==seq.charAt(i)){
                    if(!inSequence.containsKey(seq.charAt(i))){
                        inSequence.put(seq.charAt(i),1);
                        tmp.add(seq.charAt(i));
                    }else{
                        inSequence.put(seq.charAt(i),inSequence.get(seq.charAt(i))+1);
                    }
                    flag=true;
                }
            }
            if (!flag){
                isOk=false;
                JOptionPane.showMessageDialog(null,"El fosil no cumple el patron de la linea de evolucion.");
            }
        }
        for(int i=0;i<tmp.size();i++){
            isOk= isOk && (inSequence.get(tmp.get(i))<=inGenSequence.get(tmp.get(i)));
        }
        isOk=isOk && !seq.equals(genSequence);
    }

    /**
     * Este metodo mezcla dos posibles lineas de evolucion siempre y cuando se cumpla las condiciones requeridas
     *
     * @param  first, que es la linea de evolucion destino
     * @param  second que es la linea de evolucion origen
     */
    public void mergePath(int first,int second)
    {
        isOk = first>0 && second >0 && first <= nLineas && second <=nLineas;
        if(isOk){
            isOk = lineas.get(first-1).mergeWith(lineas.get(second-1));
        }else{
            JOptionPane.showMessageDialog(null,"Uno de los caminos no existe.");
        }
        if(isOk){
            lineas.get(second-1).makeInvisible();
            lineas.remove(second-1);
            ubicarLinea();
            nLineas--;
        }
        else{
            JOptionPane.showMessageDialog(null,"Las dos lineas de evolucion no se pueden mezclar.");
        }
    }

    /**
     * Este metodo hace visible los fosiles, las lineas de evolucion y la cadena actual
     * 
     * @return  isVisible que es true para mostrar los objetos en el canvas
     */
    public boolean makeVisible()
    {
        if(isPosible()){
            if(!isVisible){
                isVisible=true;
                for(int i=0;i<len;i++){
                    nucleotidos[i].makeVisible();
                }
                for(int i = 0; i < fosiles.size();i++){
                    fosiles.get(i).makeVisible();
                }
                for(int i = 0; i < lineas.size();i++){
                    lineas.get(i).makeVisible();
                }
            }
            isOk=true;
        }
        else{
            JOptionPane.showMessageDialog(null,"No se puede hacer visible");
            isOk = false;
        }
        return isVisible;
    }
    
    /**
     * Este metodo oculta los elementos que son los fosiles, las lineas de evolucion y la cadena actual
     *
     * @return  isVisible que es false para ocultar los objetos en el canvas
     */
    public boolean makeInvisible()
    {
        if(isVisible){
            isVisible=false;
            for(int i=0;i<len;i++){
                nucleotidos[i].makeInvisible();
            }
            for(int i = 0; i < fosiles.size();i++){
                fosiles.get(i).makeInvisible();
            }
            for(int i = 0; i < lineas.size();i++){
                lineas.get(i).makeInvisible();
            }
        }
        isOk=true;
        return isVisible;
    }


    /**
     * Este metodo finaliza por completo el programa
     *
     */
    public void finish()
    {
       isOk=true;
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

}
