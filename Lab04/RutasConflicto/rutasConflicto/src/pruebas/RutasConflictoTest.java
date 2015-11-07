package pruebas;

import aplicacion.*;
import java.util.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RutasConflictoTest{
    @Test
    public void deberiaAdicionar()throws RutasConflictoExcepcion{
        RutasConflicto rc = new RutasConflicto();
        rc.adicione("a","a","a","1","a");
        assertEquals("a\na\na\n1\na\n\n",rc.toString());
    }
    @Test
    public void deberiaListar() throws RutasConflictoExcepcion{
        RutasConflicto rc = new RutasConflicto();
        rc.adicione("a","a","a","1","a");
        rc.adicione("b","b","b","2","b");
        rc.adicione("c","c","c","3","c");
        assertEquals("a\na\na\n1\na\n\nb\nb\nb\n2\nb\n\nc\nc\nc\n3\nc\n\n",rc.toString());
    }
    @Test
    public void noDeberiaAdicionarSinNombre() {
        RutasConflicto rc = new RutasConflicto();
        try{
            rc.adicione("","a","a","1","a");
            fail("No lanzo excepcion");
        }catch (RutasConflictoExcepcion r){
            assertEquals(RutasConflictoExcepcion.MASACRE_SIN_NOMBRE,r.getMessage());
        }
    }
    @Test
    public void noDeberiaAdicionarMasacreExistente() {
        RutasConflicto rc = new RutasConflicto();
        try{
            rc.adicione("a","a","a","1","a");
            rc.adicione("a","a","a","1","a");
            fail("No lanzo excepcion");
        }catch (RutasConflictoExcepcion r){
            assertEquals(RutasConflictoExcepcion.MASACRE_EXISTENTE,r.getMessage());
        }
    }
    @Test
    public void noDeberiaAdicionarSinGrupo() {
        RutasConflicto rc = new RutasConflicto();
        try{
            rc.adicione("a","","a","1","a");
            fail("No lanzo excepcion");
        }catch (RutasConflictoExcepcion r){
            assertEquals(RutasConflictoExcepcion.MASACRE_SIN_GRUPO,r.getMessage());
        }
    }
    @Test
    public void noDeberiaAdicionarSinLugar() {
        RutasConflicto rc = new RutasConflicto();
        try{
            rc.adicione("a","a","","1","a");
            fail("No lanzo excepcion");
        }catch (RutasConflictoExcepcion r){
            assertEquals(RutasConflictoExcepcion.MASACRE_SIN_LUGAR,r.getMessage());
        }
    }
    @Test
    public void noDeberiaAdicionarConAnioInvalido() {
        RutasConflicto rc = new RutasConflicto();
        try{
            rc.adicione("a","a","a","a","a");
            fail("No lanzo excepcion");
        }catch (RutasConflictoExcepcion r){
            assertEquals(RutasConflictoExcepcion.ANIO_INVALIDO,r.getMessage());
        }
    }
    @Test
    public void noDeberiaAdicionarSinDescripcion() {
        RutasConflicto rc = new RutasConflicto();
        try{
            rc.adicione("a","a","a","1","");
            fail("No lanzo excepcion");
        }catch (RutasConflictoExcepcion r){
            assertEquals(RutasConflictoExcepcion.MASACRE_SIN_DESCRIPCION,r.getMessage());
        }
    }
    @Test
    public void DeberiaBuscar() throws RutasConflictoExcepcion{
        RutasConflicto rc = new RutasConflicto();
        rc.adicione("a","a","a","1","a");
        Masacre a=new Masacre("a","a","a","1","a");
        ArrayList<Masacre> busqueda=rc.busque("a");
        assertEquals(busqueda.get(0),a);
    }
}
