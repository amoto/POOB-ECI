import java.util.ArrayList;

public class Equipo{
    private ArrayList<Persona> personas = new ArrayList<Persona>();
    
    public Equipo(String [] nombres){
        personas= new ArrayList<Persona>();
        for (int i=0; i< nombres.length;i++){
            personas.add(new Persona(nombres[i]));
        }    
    }

    /**
     * Calcula el valor hora de un equipo
     * @throws EquipoExcepcion si el equipo esta vacio o hay una persona desconocida o no se conoce el valor hora de una persona 
     */
    public int valorHora() throws EquipoExcepcion{
        if(personas.size()==0) throw new EquipoExcepcion(EquipoExcepcion.EQUIPO_VACIO);
        int valor=0;
        for(int i=0;i<personas.size();i++)
            valor+=personas.get(i).valorHora();
        return valor;
    }
    
    /**
     * Calcula el valor hora estimado de un equipo.
     * El valor estimado de una persona a la que no se conoce su valor es el valor
     * promedio de sus compañeros
     * @return el valor hora del equipo
     * @throws EquipoException si en el equipo hay una persona desconocida
     * o no se tiene información para calular el valor promedio
     */
    public int valorHoraEstimado() throws EquipoExcepcion{
        if(personas.size()==0) throw new EquipoExcepcion(EquipoExcepcion.EQUIPO_VACIO);
        int valor=0;
        int valorPromedio=0;
        int noValor=0;
        for(int i=0;i<personas.size();i++){
            try{
                valor+=personas.get(i).valorHora();
            }catch(EquipoExcepcion a){
                if(a.getMessage().equals(EquipoExcepcion.VALOR_DESCONOCIDO))
                    noValor++;
                else
                    throw a;
            }
        }
        if(noValor==personas.size()) throw new EquipoExcepcion(EquipoExcepcion.VALOR_DESCONOCIDO);
        valorPromedio=valor/(personas.size()-noValor);
        valor+=(valorPromedio*noValor);
        return valor;
    }   
    
    

    
    
}
