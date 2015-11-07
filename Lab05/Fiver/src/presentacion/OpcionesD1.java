package presentacion;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import aplicacion.*;

public class OpcionesD1 extends JDialog{
	private FiverGUI fiver;
	// pestanas
	private JPanel color1;
	private JPanel color2;
	private JPanel tamano;
  private JColorChooser c1;
  private JColorChooser c2;
  private JTextField mensaje;
	// botones
	private JButton aceptar;
	private JButton cancelar;

	public OpcionesD1(FiverGUI frame){
		super(frame,"Opciones",true);
		fiver=frame;
		prepareElementos();
		prepareAcciones();
	}
	private void prepareElementos(){
		setTitle("Opciones");
		Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(720,400);
		add(prepareElementosBotones(),BorderLayout.SOUTH);
		add(prepareElementosPestanas(),BorderLayout.CENTER);
	}
	private JTabbedPane prepareElementosPestanas(){
		JTabbedPane tp = new JTabbedPane();
		color1 = new JPanel();
    c1 = new JColorChooser();
    c2 = new JColorChooser();
		color2 = new JPanel();
		tamano = new JPanel();
		mensaje = new JTextField();
		mensaje.setColumns(7);
    color1.add(c1);
    color2.add(c2);
    tamano.add(new JLabel("Inserte el nuevo tamaño"),BorderLayout.WEST);
    tamano.add(mensaje,BorderLayout.EAST);
		tp.add(color1,"Color 1");
		tp.add(color2,"Color 2");
		tp.add(tamano,"Tamaño");
		return tp;
	}
	private JPanel prepareElementosBotones(){
		JPanel sur = new JPanel();
		aceptar = new JButton("Aceptar");
		cancelar = new JButton("Cancelar");
		sur.setLayout(new GridLayout(1,2));
		sur.add(aceptar);
		sur.add(cancelar);
		return sur;
	}
	public void prepareAcciones(){
		addWindowListener(new WindowAdapter() {
		 	public void windowClosing(WindowEvent ev){
				salga();
			}
		});
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				aceptar();
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				salga();
			}
		});
	}
	private void salga(){
		dispose();
	}

	private void aceptar(){
		if(mensaje.getText().trim().length()>0){
			try{
				int n = Integer.parseInt(mensaje.getText().trim());
				if(n<3 || n>38) JOptionPane.showMessageDialog(this,FiverExcepcion.TAMANO_INVALIDO);
				else{
					fiver.cambieTamano(n);
					dispose();
				}
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(this,"el tamaño debe ser un numero");
			}
		}
		if(!c1.getColor().equals(Color.WHITE) || !c2.getColor().equals(Color.WHITE)){
			if(c1.getColor().equals(c2.getColor()))
			 JOptionPane.showMessageDialog(this,"Los dos colores no pueden ser iguales");
			else{
				fiver.cambieColor(c1.getColor(),c2.getColor());
				dispose();
			}
		}
	}
}
