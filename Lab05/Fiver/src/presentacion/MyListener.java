package presentacion;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import aplicacion.*;

public class MyListener implements ActionListener{
  private int f,c;
  private FiverGUI fiver;
  public void actionPerformed(ActionEvent e){
    fiver.jugar(f,c);
  }
  public MyListener(int pos, int tam, FiverGUI fi){
    f = pos/tam;
    c = pos%tam;
    fiver = fi;
  }
}
