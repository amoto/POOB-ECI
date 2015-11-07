package aplicacion;

import javax.swing.*;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

/**
 * 
 */
public class Registro{
    public static String nombre="rutasConflicto";
    
    public static void registre(Exception e){
        try{
            Logger logger=Logger.getLogger(nombre);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(nombre+".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();

        }catch (Exception oe){
            oe.printStackTrace();
        }finally{
            if(!(e instanceof NullPointerException)){
                JOptionPane.showMessageDialog(null,"ha habido un problema, se guardara el registro para ser corregido por los desarrolladores y la aplicación terminará su ejecución");
                System.exit(0);
            }else JOptionPane.showMessageDialog(null,"ha habido un problema, se guardara el registro para ser corregido por los desarrolladores");
        }

    }
}       