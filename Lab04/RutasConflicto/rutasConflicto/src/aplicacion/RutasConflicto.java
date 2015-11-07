package aplicacion;

import java.util.LinkedList;
import java.util.ArrayList;

/**
 * RutasConflicto contiene información de las masacres en colombia
 * @author POOB 01 2015-02
 */

public class RutasConflicto{

    private LinkedList <Masacre> masacres;


    /**
     * Inicializa la informacion
     */
    public RutasConflicto(){
        masacres = new LinkedList<Masacre>();
    }
    
    /**
     * Consulta la información de una masacre
     * @param nombre
     * @return el masacre del nombre indicado, si existe. Sino, null.
     */
    public Masacre get(String nombre){
       Masacre p=null;
       for(int i=0;i<masacres.size() && p == null;i++){
           if (masacres.get(i).getNombre().compareToIgnoreCase(nombre)==0){
               p=masacres.get(i);
           }
       }
       return p;
    }


    /**
     * Adiciona un nuevo masacre a la base
     */
    public void adicione(String nombre, String grupo, String lugar, String a_o, String descripcion) throws RutasConflictoExcepcion{
       Masacre masacre= new Masacre(nombre,grupo,lugar,a_o,descripcion);
       if(masacres.contains(masacre)) throw new RutasConflictoExcepcion(RutasConflictoExcepcion.MASACRE_EXISTENTE);
       int i=0;
       while ((i<masacres.size()) && (masacres.get(i).getNombre().compareToIgnoreCase(masacre.getNombre())<0)){
            i++;
       }
       masacres.add(i,masacre);
    }
    

    /**
     * Adiciona tres masacres
     */    
    
    public void adicioneTres() throws RutasConflictoExcepcion{
        masacres.add(new Masacre("El Placer","Paramilitares del Bloque  Sur Putumayo", "El Placer","1999",
                 "A las nueve de la mañana del domingo 7 de noviembre de 1999, 38 paramilitares del Bloque Sur Putumayo "+
                 "llegaron a la vereda El Placer, en el municipio de Valle del Guamuez, Putumayo. Era el día de mercado "+
                 "y el lugar estaba muy concurrido, los 'paras' ordenaron que los presentes se tiraran al suelo y nadie "+
                 "corriera, pero luego les dispararon indiscriminadamente y asesinaron a 11 personas que trataron "+
                 "de huir."
                 ));
        masacres.add(new Masacre("La Rejoya","Paramilitares del Bloque Calima", "La Rejoya","2001",
                 "El 15 de enero de 2001, un grupo de paramilitares del Bloque Calima montó un retén ilegal sobre "+
                 "la carretera que conduce de la ciudad de Popayán a la vereda La Rejoya.  Los hombres armados pararon "+
                 "un bus que había salido de la plaza de mercado de la capital del Cauca con rumbo a Cajibío y asesinaron "+
                 "a 10 personas con tiros en la cabeza, señalándolos de ser supuestos guerrilleros. "+
                 "La masacre fue coordinada por José de Jesús Pérez, alias ‘Sancocho’."
                 ));

       masacres.add(new Masacre("El Tigre","Paramilitares del Bloque Sur Putumayo", "El Tigre","1999",
                 "Sobre las 11 de la noche del 9 de enero de 1999, un grupo de aproximadamente 150 paramilitares se "+
                 "tomaron  el caserío de El Tigre, en el municipio de Valle del Guamuez. Los ‘paras’ recorrieron la "+
                 "vereda, escribieron en algunas paredes “Auc presentes” y obligaron a los habitantes a reunirse en la "+
                 "calle principal. A algunos les ordenaron arrodillarse y permanecer en silencio, mientras decidían al "+
                 "azar a quienes asesinar. Según informes de la Fiscalía, también se presentaron casos de violencia sexual "+
                 "y  el aborto de dos mujeres, a causa de las agresiones físicas a las que fueron sometidas."
                 ));
    }
 
    
    /**
     * Consulta las masacres que inician con un prefijo
     * @param prefijo El prefijo a buscar
     * @return Los masacres encontrados
     */
    public ArrayList<Masacre> busque(String prefijo){
       ArrayList<Masacre> resultados= new ArrayList<Masacre>();
       prefijo=prefijo.toUpperCase();
       for(int i=0;i<masacres.size();i++){
           if(masacres.get(i).getNombre().toUpperCase().startsWith(prefijo)){
              resultados.add(masacres.get(i));
           }    
        }
        return resultados;
    }

    /**
     * Consulta el numero de masacres
     * @return Numero de masacres
     */
    public int numero(){
        return masacres.size();
    }


    /**
     * Consulta todos los masacres
     * @return Todas los masacres 
     */
    public String toString(){
        StringBuffer todas=new StringBuffer();
        for(Masacre masacre : masacres) {
            todas.append(masacre.toString().length()<=200? masacre:masacre.toString().substring(0,199)+"...");
            todas.append('\n');
            todas.append('\n');
        }
        return todas.toString();
    }
}
