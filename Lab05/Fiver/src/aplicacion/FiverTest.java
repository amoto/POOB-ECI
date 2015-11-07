package aplicacion;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class FiverTest{
  @Test
  public void deberiaLanzarExcepcionTamano(){
    try{
      Fiver f = new Fiver(1);
    }
    catch(FiverExcepcion e){
      assertEquals(e.getMessage(),FiverExcepcion.TAMANO_INVALIDO);
    }
  }
  @Test
  public void deberiaLanzarExcepcionFueraRangoJugar(){
    try{
      Fiver f = new Fiver(8);
      f.jugar(8,8);
    }
    catch(FiverExcepcion e){
      assertEquals(e.getMessage(),FiverExcepcion.FUERA_RANGO);
    }
  }
  @Test
  public void deberiaLanzarExcepcionLimiteTurnoJugar(){
    try{
      Fiver f = new Fiver(5);
      for(int i =0; i<100; i++){
        f.jugar(0,0);
      }
    }
    catch(FiverExcepcion e){
      assertEquals(e.getMessage(),FiverExcepcion.LIMITE_TURNO);
    }
  }
  @Test
	/**
	* @throws FiverExcepcion
	*/
  public void deberiaJugar() throws FiverExcepcion{
    Fiver f = new Fiver(6);
    boolean ck[]= new boolean[15];
    ck[0] = f.get(0,1);
    ck[1] = f.get(1,0);
    ck[2] = f.get(1,1);
    ck[3] = f.get(1,2);
    ck[4] = f.get(2,1);
    f.jugar(1,1);
    ck[5] = f.get(0,1);
    ck[6] = f.get(1,0);
    ck[7] = f.get(1,1);
    ck[8] = f.get(1,2);
    ck[9] = f.get(2,1);
    assertTrue(ck[0] != ck[5] && ck[1] != ck[6] && ck[2] != ck[7] && ck[3] != ck[8] && ck[4] != ck[9]);
  }
}
