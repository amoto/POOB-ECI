package presentacion;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import aplicacion.*;

public class FiverGUI extends JFrame {

	//Menu
	private JMenu menu;
	private JMenuItem opciones;
	private JMenuItem guardar;
	private JMenuItem cargar;
	private JMenuItem reiniciar;
	private JMenuItem salir;

	//Principal
	private JPanel principal;
	private JButton[] botones;
	private Color c1;
	private Color c2;
	private int tamano;

	//persistencia
	private JFileChooser fc;
	private String ext;
  private Fiver fiver;

	//Movimientos
	private JLabel restantes;

	private FiverGUI(){
		super("Fiver");
		prepareElementos();
		prepareAcciones();
	}

	private void prepareElementos(){
		tamano=8;
		try{
      fiver = new Fiver(tamano);
    }catch(FiverExcepcion e){
      JOptionPane.showMessageDialog(this,e.getMessage());
    }
		restantes=new JLabel("Le quedan "+Integer.toString(fiver.restantes())+" movimientos");
		ext=".game";
		c2 = Color.BLACK;
		c1 = Color.WHITE;
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("GAME files", "game");
		fc.setFileFilter(filter);
		Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(sz.width/2,sz.height/2);
		prepareElementosTablero();
		add(prepareElementosMenu(),BorderLayout.NORTH);
		add(principal,BorderLayout.CENTER);
		add(restantes,BorderLayout.SOUTH);
	}

	private JMenuBar prepareElementosMenu(){
		JMenuBar barMenu= new JMenuBar();
		menu = new JMenu("Menu");
		salir = new JMenuItem("Salir");
		opciones = new JMenuItem("Opciones");
		guardar = new JMenuItem("Guardar");
		cargar = new JMenuItem("Cargar");
		reiniciar = new JMenuItem("Reiniciar");
		menu.add(opciones);
		menu.add(guardar);
		menu.add(cargar);
		menu.add(reiniciar);
		menu.add(salir);
		barMenu.add(menu);
		return barMenu;

	}

	private void prepareElementosTablero(){
		principal = new JPanel();
		principal.setLayout(new GridLayout(tamano,tamano));
		botones = new JButton[tamano*tamano];
		for(int i = 0; i < botones.length; i++){
			botones[i] = new JButton();
			principal.add(botones[i]);
		}
		refresque();
	}

	private void refresque(){
    for(int j = 0; j < tamano; j++)
      for(int i = 0; i < tamano; i++){
        try{
          if(fiver.get(j,i))
            botones[(j*tamano)+i].setBackground(c2);
          else
            botones[(j*tamano)+i].setBackground(c1);
        }catch(FiverExcepcion e){
          JOptionPane.showMessageDialog(this,e.getMessage());
        }
      }
		restantes.setText("Le quedan "+Integer.toString(fiver.restantes())+" movimientos");
	}
	private void crearAccionesBotones(){
		for(int i = 0; i < botones.length; i++){
      botones[i].addActionListener(new MyListener(i, tamano,this));
		}
	}
	public void prepareAcciones(){
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent ev){
        salga();
      }
    });
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				salga();
			}
		});
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				guarda();
			}
		});
		cargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				carga();
			}
		});
		opciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				opciones();
			}
		});
		crearAccionesBotones();
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				reinicie();
			}
		});
	}
	

	private void salga(){
		int n = JOptionPane.showConfirmDialog(this,"Seguro desea salir?","Salir",JOptionPane.YES_NO_OPTION);
		if(n == 0){
        	setVisible(false);
			System.exit(0);
		}
		else
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	private void guarda(){
		int n = fc.showSaveDialog(this);
		if(n == fc.APPROVE_OPTION){
			JOptionPane.showMessageDialog(this,"La funcionalidad guardar esta en construccion, archivo: "+fc.getSelectedFile().getName());
		}
	}
	private void carga(){
		int n = fc.showOpenDialog(this);
		if(n == fc.APPROVE_OPTION){
			JOptionPane.showMessageDialog(this,"La funcionalidad cargar esta en construccion, archivo: "+fc.getSelectedFile().getName());
		}
	}
	private void opciones(){
		OpcionesD1 opd = new OpcionesD1(this);
		opd.setVisible(true);
	}
	public void cambieColor(Color c1x, Color c2x){
		c1=c1x;
		c2=c2x;
		refresque();
	}
	public void cambieTamano(int n){
		tamano = n;
		//quitarAcciones();
		try{
      fiver = new Fiver(tamano);
    }catch(FiverExcepcion e){
      JOptionPane.showMessageDialog(this,e.getMessage());
    }
		remove(principal);
		prepareElementosTablero();
		add(principal,BorderLayout.CENTER);
		revalidate();
		crearAccionesBotones();
		refresque();
	}
	protected void jugar(int f, int c){
    boolean ck = false;
    try{
      ck = fiver.jugar(f,c);
      refresque();
			
      if(ck){
        JOptionPane.showMessageDialog(this,"Ha ganado");
      }
    }catch(FiverExcepcion e){
      if(e.getMessage().equals(FiverExcepcion.LIMITE_TURNO)){
        JOptionPane.showMessageDialog(this,"Ha perdido");
      }
    }
	}

	private void reinicie(){
    try{
      fiver = new Fiver(tamano);
    }catch(FiverExcepcion e){
      JOptionPane.showMessageDialog(this,e.getMessage());
    }
		refresque();
	}

	public static void main(String[] args) {
		FiverGUI fi = new FiverGUI();
		fi.setVisible(true);
	}

}
