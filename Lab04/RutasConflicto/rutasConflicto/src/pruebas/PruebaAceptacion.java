package pruebas;

import aplicacion.*;
import presentacion.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;


public class PruebaAceptacion{
    @Test
    public void aceptacion() throws RutasConflictoExcepcion{
        String nombre = JOptionPane.showInputDialog(null,"El nombre de la masacre:");
        String grupo = JOptionPane.showInputDialog(null,"El grupo de la masacre:");
        String lugar = JOptionPane.showInputDialog(null,"El lugar de la masacre:");
        String fecha = JOptionPane.showInputDialog(null,"La fecha de la masacre:");
        String desc = JOptionPane.showInputDialog(null,"la descripcion de la masacre:");
        RutasConflicto rc = new RutasConflicto();
        rc.adicione(nombre,grupo,lugar,fecha,desc);
        JOptionPane.showMessageDialog(null,rc.toString());
        int ans = JOptionPane.showConfirmDialog(null,"le parece correcto?");
        if(ans == 0){
           assertTrue(true);
        }else{
           assertTrue(false);
        }
    }
}
