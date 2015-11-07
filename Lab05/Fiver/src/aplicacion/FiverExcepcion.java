package aplicacion;

import java.util.*;
import java.io.*;

public class FiverExcepcion extends Exception{
  public static final String TAMANO_INVALIDO = "El tamaño es demasiado pequeño o grande";
  public static final String FUERA_RANGO = "Esa posicion no existe";
  public static final String LIMITE_TURNO = "Los turnos se han agotado";
  public FiverExcepcion(String mensaje){
    super(mensaje);
  }
}
