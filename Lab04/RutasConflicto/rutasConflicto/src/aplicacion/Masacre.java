package aplicacion;


public class Masacre{
    private String nombre;
    private String grupo;
    private String lugar;
    private String a_o;
    private String descripcion;

    /**
     * Crea una neuva masacre
     */
    public Masacre(String nombre, String grupo, String lugar,String a_o, String descripcion) throws RutasConflictoExcepcion{
        if(nombre.length()==0) throw new RutasConflictoExcepcion(RutasConflictoExcepcion.MASACRE_SIN_NOMBRE);
        if(grupo.length()==0) throw new RutasConflictoExcepcion(RutasConflictoExcepcion.MASACRE_SIN_GRUPO);
        if(descripcion.length()==0) throw new RutasConflictoExcepcion(RutasConflictoExcepcion.MASACRE_SIN_DESCRIPCION);
        if(lugar.length()==0) throw new RutasConflictoExcepcion(RutasConflictoExcepcion.MASACRE_SIN_LUGAR);
        boolean aniovalido=true;
        for (int i=0;i<a_o.length() && aniovalido ;i++ ) {
            aniovalido=Character.isDigit(a_o.charAt(i));
        }
        if(!aniovalido) throw new RutasConflictoExcepcion(RutasConflictoExcepcion.ANIO_INVALIDO);
        this.nombre = nombre.trim();
        this.grupo= grupo.trim();
        this.lugar=lugar.trim();
        this.a_o=a_o;
        this.descripcion = descripcion.trim();
    }
    
    /**
     * @return 
     */
    public String getGrupo(){
        return grupo;
    }

    /**
     * @return 
     */
    public String getLugar(){
        return lugar;
    }

    /**
     * @return 
     */
    public String getA_o(){
        return a_o;
    }
    
    
    /**
     * @return 
     */
    public String getDescripcion(){
        return descripcion;
    }

    /**
     * @return 
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * @return 
     */
    public String toString(){
        return nombre + "\n" + grupo + "\n" + lugar + "\n" + a_o + "\n"+descripcion;
    }

    public  boolean equals(Object o){
        Masacre m=(Masacre)o;
        return toString().equals(m.toString());
    }
}
