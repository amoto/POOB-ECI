package presentacion;

import aplicacion.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Implementa la GUI del catalogo de masacres de arte
 * @version ECI 2012
 */
public class RutasConflictoGUI extends JFrame{

    private static final int ANCHO_PREFERIDO = 450;
    private static final int ALTO_PREFERIDO= 450;
    private static final Dimension DIMENSION_PREFERIDA =
              new Dimension(ANCHO_PREFERIDO,ALTO_PREFERIDO);

    private RutasConflicto masacres;

    /*Panel botonListar*/
    private JButton botonListar;
    private JButton botonReiniciarListar;
    private JTextArea textoDetalles;
    
    /*Panel botonAdicionar*/
    private JTextField textoNombre;   
    private JTextField textoGrupo;
    private JTextField textoLugar;
    private JTextField textoA_o;
    private JTextArea textoDescripcion;
    
    private JButton botonAdicionar;
    private JButton botonReiniciarAdicionar;
    
    /*Panel buscar*/
    private JTextField busquedaTexto;
    private JTextArea resultadosTexto;
    
    /**
     * Create un marco para el catalogo de masacres de arte
     */
    
    
    private RutasConflictoGUI()throws RutasConflictoExcepcion{
        masacres=new RutasConflicto();
        masacres.adicioneTres();
        prepareElementos();
        prepareAcciones();
    }


    private void prepareElementos(){
        setTitle("Rutas del Conflicto. Memoria Historica.");
        textoNombre = new JTextField(50);
        textoGrupo = new JTextField(50);
        textoLugar = new JTextField(50);
        textoA_o = new JTextField(4);
        textoDescripcion = new JTextArea(10, 50);
        textoDescripcion.setLineWrap(true);
        textoDescripcion.setWrapStyleWord(true);
        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar",   prepareAreaListar());
        etiquetas.add("Adicionar",  prepareAreaAdicionar());
        etiquetas.add("Buscar", prepareAreaBuscar());
        getContentPane().add(etiquetas);
        setSize(DIMENSION_PREFERIDA);
        
    }


    /**
     * Prepara el panel para listar masacres
     * @return el panel para listar masacres
     */
    private JPanel prepareAreaListar(){

        textoDetalles = new JTextArea(10, 50);
        textoDetalles.setEditable(false);
        textoDetalles.setLineWrap(true);
        textoDetalles.setWrapStyleWord(true);
        JScrollPane scrollArea =
                new JScrollPane(textoDetalles,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                
        JPanel  botones = new JPanel();
        botonListar = new JButton("Listar");
        botonReiniciarListar = new JButton("Limpiar");
        botones.add(botonListar);
        botones.add(botonReiniciarListar);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
     }
     
    /**
     * Prepara el area de adición
     * @return El area de adición
     */
    private JPanel prepareAreaAdicionar(){
        
        Box textoNombreArea = Box.createHorizontalBox();
        textoNombreArea.add(new JLabel("Nombre", JLabel.LEFT));
        textoNombreArea.add(Box.createGlue());
        Box nombreArea = Box.createVerticalBox();
        nombreArea.add(textoNombreArea);
        nombreArea.add(textoNombre);

        Box textoGrupoArea = Box.createHorizontalBox();
        textoGrupoArea.add(new JLabel("Grupo", JLabel.LEFT));
        textoGrupoArea.add(Box.createGlue());
        Box grupoArea = Box.createVerticalBox();
        grupoArea.add(textoGrupoArea);
        grupoArea.add(textoGrupo);
        
        Box textoLugarArea = Box.createHorizontalBox();
        textoLugarArea.add(new JLabel("Lugar", JLabel.LEFT));
        textoLugarArea.add(Box.createGlue());
        Box lugarArea = Box.createVerticalBox();
        lugarArea.add(textoLugarArea);
        lugarArea.add(textoLugar);

        Box textoA_oArea = Box.createHorizontalBox();
        textoA_oArea.add(new JLabel("Año", JLabel.LEFT));
        textoA_oArea.add(Box.createGlue());
        Box a_oArea = Box.createVerticalBox();
        a_oArea.add(textoA_oArea);
        a_oArea.add(textoA_o);
        
        Box textoDescripcionArea = Box.createHorizontalBox();
        textoDescripcionArea.add(new JLabel("Descripción", JLabel.LEFT));
        textoDescripcionArea.add(Box.createGlue());
        Box descripcionArea = Box.createVerticalBox();
        descripcionArea.add(textoDescripcionArea);
        descripcionArea.add(textoDescripcion);
 
        Box singleLineFields = Box.createVerticalBox();
        singleLineFields.add(nombreArea);
        singleLineFields.add(grupoArea);
        singleLineFields.add(lugarArea);
        singleLineFields.add(a_oArea);        

        JPanel textoDetallesPanel = new JPanel();
        textoDetallesPanel.setLayout(new BorderLayout());
        textoDetallesPanel.add(singleLineFields, BorderLayout.NORTH);
        textoDetallesPanel.add(descripcionArea, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botonAdicionar = new JButton("Adicionar");
        botonReiniciarAdicionar = new JButton("Limpiar");

        botones.add(botonAdicionar);
        botones.add(botonReiniciarAdicionar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textoDetallesPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    



   /**
     * Prepara el area de liatar
     * @return El panel para buscar masacress
     */
    private JPanel prepareAreaBuscar(){

        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        busquedaTexto = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(busquedaTexto);
        
        resultadosTexto = new JTextArea(10,50);
        resultadosTexto.setEditable(false);
        resultadosTexto.setLineWrap(true);
        resultadosTexto.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(resultadosTexto,
                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel botonListarArea = new JPanel();
        botonListarArea.setLayout(new BorderLayout());
        botonListarArea.add(busquedaArea, BorderLayout.NORTH);
        botonListarArea.add(scrollArea, BorderLayout.CENTER);

        return botonListarArea;
    }


    public void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                setVisible(false);
                System.exit(0);
            }
        });
        
        /*Listar*/
        botonListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionListar();
            }
        });

        botonReiniciarListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                textoDetalles.setText("");
            }
        });
        
        /*Adicionar*/
        botonAdicionar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionAdicionar();                    
            }
        });
        
        botonReiniciarAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                textoNombre.setText("");
                textoGrupo.setText("");
                textoLugar.setText("");
                textoA_o.setText("");
                textoDescripcion.setText("");
            }
        });
        
        /*Buscarr*/
        busquedaTexto.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev){
                accionBuscar();

            }
           
            public void insertUpdate(DocumentEvent ev){
                accionBuscar();
            }
            
            public void removeUpdate(DocumentEvent ev){
                accionBuscar();
            }
            

        });
    }    

    
    private void accionListar(){
        textoDetalles.setText(masacres.toString());
    }
    
    private void  accionAdicionar() {
        try{
            masacres.adicione(textoNombre.getText(),textoGrupo.getText(),textoLugar.getText(), textoA_o.getText(),
            textoDescripcion.getText());
        }
        catch(RutasConflictoExcepcion e){
            if(e.getMessage().equals(RutasConflictoExcepcion.MASACRE_SIN_NOMBRE))
                JOptionPane.showMessageDialog(null,"Ingreso una masacre sin nombre que no podra ser agregada.");
            else if(e.getMessage().equals(RutasConflictoExcepcion.MASACRE_SIN_GRUPO))
                JOptionPane.showMessageDialog(null,"Ingreso una masacre sin grupo armado que no podra ser agregada.");
            else if(e.getMessage().equals(RutasConflictoExcepcion.MASACRE_SIN_LUGAR))
                JOptionPane.showMessageDialog(null,"Ingreso una masacre sin lugar que no podra ser agregada.");
            else if(e.getMessage().equals(RutasConflictoExcepcion.ANIO_INVALIDO))
                JOptionPane.showMessageDialog(null,"Ingreso una masacre con un anio invalido que no podra ser agregada.");
            else if(e.getMessage().equals(RutasConflictoExcepcion.MASACRE_SIN_DESCRIPCION))
                JOptionPane.showMessageDialog(null,"Ingreso una masacre sin descripcion que no podra ser agregada.");
            else if(e.getMessage().equals(RutasConflictoExcepcion.MASACRE_EXISTENTE))
                JOptionPane.showMessageDialog(null,"Ingreso una masacre aue ya existe y no podra ser agregada.");
        }
    }

    private void accionBuscar(){
        String patronBusqueda=busquedaTexto.getText();
        StringBuffer buffer = new StringBuffer();
        if(patronBusqueda.length() > 0) {
            try{
                ArrayList <Masacre> results = masacres.busque(patronBusqueda);
                for(int i = 0; i < results.size(); i++) {
                    buffer.append(results.get(i).toString());
                    buffer.append('\n');
                    buffer.append('\n');
                }
            }catch(Exception e){
                Registro.registre(e);
            }

        }
        resultadosTexto.setText(buffer.toString());
    } 
    
   public static void main(String args[]) throws RutasConflictoExcepcion{
        RutasConflictoGUI gui=new RutasConflictoGUI();
        gui.setVisible(true);
   }    
}
