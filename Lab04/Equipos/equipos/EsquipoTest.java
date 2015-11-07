

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EsquipoTest{
   
    private static String NOEXCEPCION="No se espera una excepcion";
    private static String EXCEPCION="Se espera una excepcion";
    
    public EsquipoTest(){
    }


    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }
    
    @Test
    public void deberiaCalcularElValorDeUnEquipo(){
        String [] nombres={"Pedro","Judas"};
        Equipo eq= new Equipo(nombres);
        try {
           assertEquals(60000,eq.valorHora());
        } catch (EquipoExcepcion e){
            fail("Lanzó excepcion");
        }    
    }    
    
    @Test
    public void deberiaLanzarExcepcionSiElEquipoNoTienePersonas(){
        String [] nombres={};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanzó excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.EQUIPO_VACIO,e.getMessage());
        }    
    }    
    
    
   @Test
    public void deberiaLanzarExcepcionSiNoSeConoceElValorDeUnaPersona(){
        String [] nombres={"Pedro","Garcia","Juan"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.VALOR_DESCONOCIDO,e.getMessage());
        }    
   }     
    
   @Test
    public void deberiaLanzarExcepcionSiNoSeConoceUnaPersona(){
        String [] nombres={"Pedro","Carlos","Juan"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.PERSONA_DESCONOCIDA,e.getMessage());
        }    
    }  
    
   @Test
    public void deberiaLanzarExcepcionSiNoSeConoceUnaPersona2(){
        String [] nombres={"Pedro","Carlos","Juan"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHoraEstimado();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.PERSONA_DESCONOCIDA,e.getMessage());
        }    
   }
   @Test
    public void deberiaLanzarExcepcionSiNoSeConoceElValorDeNadie(){
        String [] nombres={"Ospina","Garcia","Guarin"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHoraEstimado();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.VALOR_DESCONOCIDO,e.getMessage());
        }    
   }
   @Test
    public void deberiaHallarPromedio(){
        String [] nombres={"Judas","Marcos","Guarin","Ospina"};
        Equipo eq= new Equipo(nombres);
        try {
           assertEquals(160000,eq.valorHoraEstimado());
        } catch (EquipoExcepcion e){
            fail("Lanzó excepcion");
        }   
   }
}
