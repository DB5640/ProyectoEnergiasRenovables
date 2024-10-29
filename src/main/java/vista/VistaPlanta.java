package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Pais;
import clases.Planta;
import clases.Region;
import clases.TipoEnergia;
import controlador.PlantaController;
import controlador.TipoEnergiaController;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaPlanta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textIdPlanta;
	private JTextField textPais;
	private JTextField textCapacidad;
	private JButton btnInsertar, btnActualizar,btnEliminar,btnBuscar;
	private JComboBox<TipoEnergia> combxTipoEnergia;
    private PlantaController plantaController;
    private Planta planta;
    DefaultTableModel tableModel= new DefaultTableModel();
	/**
	 * Launch the application.
	 */
    private TipoEnergiaController tipoEnergiaController;
    private static TipoEnergia energia = new TipoEnergia(); 
    private List<TipoEnergia> listaenergia;    
   
    // Primero, un Map para almacenar los objetos por su PK
    Map<Integer, TipoEnergia> comboMap = new HashMap<>();
    private JTextField textAño;

   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPlanta frame = new VistaPlanta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VistaPlanta() throws SQLException {
       

		tipoEnergiaController = new TipoEnergiaController();
		 plantaController= new PlantaController();

		
		setBounds(0, 0, 778, 400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 23, 151, 318);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnInsertar = new JButton("CREAR");
		btnInsertar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        if (! validarVacios()) {
		            JOptionPane.showInputDialog(this, "Los campos con (*) son obligados");
		          }
		          else{
		            insertar();
		          }

			
			}
		});
		                                        

		btnInsertar.setBounds(10, 27, 121, 54);
		panel.add(btnInsertar);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizar();
				limpiarFormulario();
			}
		});
		btnActualizar.setBounds(10, 102, 121, 54);
		panel.add(btnActualizar);
		
	    btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					eliminar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(10, 243, 121, 54);
		panel.add(btnEliminar);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consultar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(10, 167, 121, 54);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(183, 210, 557, 131);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 11, 537, 120);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"comlum1", "colum2", "colum3", "colum4","colum5"
			}
		));
		table.setBounds(0, 0, 537,120);
		
		String[] titulostabla =new String[] {"ID PLANTA","TIPO ENERGIA","CAPACIDAD","AÑO","PAIS"};
		tableModel.setColumnIdentifiers(titulostabla);
		table.setModel(tableModel);
		
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(205, 54, 557, 145);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textIdPlanta = new JTextField();
		textIdPlanta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnBuscar.setEnabled(true);
				btnActualizar.setEnabled(true);
	            btnEliminar.setEnabled(true);
	            btnInsertar.setEnabled(false);
			}
		});
		textIdPlanta.setBorder(null);
		textIdPlanta.setBounds(104, 5, 46, 26);
		panel_2.add(textIdPlanta);
		textIdPlanta.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(34, 11, 24, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("TIPO ENERGIA");
		lblNewLabel_2.setBounds(210, 37, 80, 25);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PAIS");
		lblNewLabel_3.setBounds(34, 48, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		textPais = new JTextField();
		textPais.setBorder(null);
		textPais.setBounds(104, 45, 86, 20);
		panel_2.add(textPais);
		textPais.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("CAPACIDAD");
		lblNewLabel_4.setBounds(34, 91, 75, 14);
		panel_2.add(lblNewLabel_4);
		
		textCapacidad = new JTextField();
		textCapacidad.setBorder(null);
		textCapacidad.setBounds(104, 88, 86, 20);
		panel_2.add(textCapacidad);
		textCapacidad.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("FECHA INICIAL");
		lblNewLabel_5.setBounds(439, 8, 86, 20);
		panel_2.add(lblNewLabel_5);
		
		JList<?> list = new JList<Object>();
		list.setBounds(439, 76, 86, 20);
		panel_2.add(list);
		
		JLabel lblNewLabel_6 = new JLabel("FECHA FINAL");
		lblNewLabel_6.setBounds(439, 57, 86, 20);
		panel_2.add(lblNewLabel_6);
		
		JList<?> list_1 = new JList<Object>();
		list_1.setBounds(439, 28, 86, 20);
		panel_2.add(list_1);
		
		JButton btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setBounds(436, 111, 89, 23);
		panel_2.add(btnFiltrar);
		
	    combxTipoEnergia = new JComboBox<>();
	    combxTipoEnergia.setToolTipText("");
		combxTipoEnergia.setBounds(302, 38, 75, 22);
		panel_2.add(combxTipoEnergia);
		
	
		inicializarBotonera();
		
		llenarCombos();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(284, 11, 314, 32);
		getContentPane().add(panel_3);
		
		JLabel lblNewLabel = new JLabel("FORMULARIO PLANTA");
		panel_3.add(lblNewLabel);
		vistaListarPlanta();
	}
	
	public void vistaListarPlanta() throws SQLException{ 
        tableModel.setRowCount(0); // Limpiar la tabla
        
        List<Object[]> listado = plantaController.listarPlantaTabla();
        for (Object[]fila : listado) {
        	tableModel.addRow(fila);
        }
    }
	
	private void consultar() throws SQLException {        
        int  id =Integer.parseInt(textIdPlanta.getText());        
        planta = plantaController.consultarPlanta(id);
        if (planta  == null) {
            JOptionPane.showMessageDialog(this, "ID NO ENCONTRADO");
        } else {
            textCapacidad.setText(String.valueOf(planta.getCapacidad()));
            textPais.setText(String.valueOf(planta.getId_tipoEnergia()));
            
            combxTipoEnergia.setSelectedItem(planta.getId_tipoEnergia());
            //System.out.println(tipoEnergia.getId_tipoEnergia()+"combo");
            TipoEnergia item= comboMap.get(planta.getId_tipoEnergia());
            System.out.println(item);
            if (item != null) {
            	
            	combxTipoEnergia.setSelectedItem(item);
            	
            }else {
            	System.out.print(" es null");
            }
            
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnInsertar.setEnabled(false);
        }
    }
	
	
	
	private void llenarCombos() throws SQLException {        
		 List<TipoEnergia> listado = tipoEnergiaController.listarTipoEnergia();
        for (TipoEnergia item : listado) {
        	combxTipoEnergia.addItem(item);
            comboMap.put(item.getId_tipoEnergia(), item);
        }
       
    }
	
	private void insertar() {
        double capacidad = Double.parseDouble(textCapacidad.getText());
        Date date= new Date (System.currentTimeMillis());
        java.sql.Date año = new java.sql.Date(date.getTime());
        TipoEnergia fkSelecionada=(TipoEnergia)combxTipoEnergia.getSelectedItem();
        int tipoEnergia = fkSelecionada.getId_tipoEnergia();
        int pais= Integer.parseInt(textPais.getText());
        Planta planta= new Planta(0, capacidad,año,tipoEnergia,null);
        Region region= new Region(pais);
        try {
            plantaController.agregarPlanta(planta, region);
            JOptionPane.showMessageDialog(this, "la Planta fue agregado");
            vistaListarPlanta();
            limpiarFormulario(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
	private void actualizar() {
        int id = Integer.parseInt(textIdPlanta.getText());
        double capacidad =Double.parseDouble(textCapacidad.getText()) ;
        Planta planta = new Planta(id, capacidad,null,0,null);
        try {
            plantaController.actualizarPlanta(planta);
            JOptionPane.showMessageDialog(this, "Capacidad Actualizado");
            vistaListarPlanta();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

	private void eliminar() throws SQLException {
        int id = Integer.parseInt(textIdPlanta.getText());
        plantaController.eliminarPlanta(id);        
        this.limpiarFormulario();
        vistaListarPlanta();
        JOptionPane.showMessageDialog(this, "Registro eliminado exitosamente");
    }


	private boolean validarVacios() {
	       boolean validado = false;
	       if (!textCapacidad.getText().trim().isEmpty())
	               validado = true;
	       return validado;
	    }
	private void limpiarFormulario(){
		textIdPlanta.setText("");
		textPais.setText("");
		textCapacidad.setText("");
        inicializarBotonera();
    } 
	
	private void inicializarBotonera(){       
		btnInsertar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(false);
    }


}
