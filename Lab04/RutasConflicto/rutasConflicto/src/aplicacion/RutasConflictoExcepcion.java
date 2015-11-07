package aplicacion;


public class RutasConflictoExcepcion extends Exception{
    public static final String MASACRE_SIN_NOMBRE = "no se puede agregar una masacre sin nombre";
    public static final String ANIO_INVALIDO = "el anio debe ser un numero";
    public static final String MASACRE_SIN_GRUPO = "no se puede agregar una masacre sin grupo armado";
    public static final String MASACRE_SIN_DESCRIPCION = "no se puede agregar una masacre sin descripcion";
    public static final String MASACRE_SIN_LUGAR = "no se puede agregar una masacre sin lugar";
    public static final String MASACRE_EXISTENTE = "la masacre ya existe y no se puede repetir";
    
    public RutasConflictoExcepcion(String mensaje){
	super(mensaje);
    }

}
