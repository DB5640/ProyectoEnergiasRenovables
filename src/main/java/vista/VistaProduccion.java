package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
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
import clases.Produccion;
import clases.TipoEnergia;
import controlador.RegionController;
import controlador.PlantaController;
import controlador.ProduccionController;
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

public class VistaProduccion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textIdPlanta;
	private JTextField textCapacidad;
	private JButton btnCalProd, btnLimpiar,btnProduPais;
	private JList <Pais>listPais;
	private JComboBox<TipoEnergia> combxTipoEnergia;
    private PlantaController plantaController;
    private Planta planta;
    private Pais pais;
    DefaultTableModel tableModel= new DefaultTableModel();
    private RegionController PaisController;
    private Produccion produccion;
    private TipoEnergiaController tipoEnergiaController;
    private ProduccionController produccionController;
    private static TipoEnergia energia = new TipoEnergia(); 
    private List<TipoEnergia> listaenergia;    
   
    // Primero, un Map para almacenar los objetos por su PK
    Map<Integer, TipoEnergia> comboMap = new HashMap<>();
    Map<Integer, Pais> comboMapPais = new HashMap<>();
    private JTextField textAño;
    private JTextField textProducMensual;
    private JTextField textProducAnual;
    private JTextField textCapaPais;

   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaProduccion frame = new VistaProduccion();
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
	public VistaProduccion() throws SQLException {
       
        produccionController = new ProduccionController();
		tipoEnergiaController = new TipoEnergiaController();
		plantaController= new PlantaController();
        PaisController = new RegionController();
		pais= new Pais();
		produccion = new Produccion();
		setBounds(0, 0, 778, 400);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(102, 186, 638, 117);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 11, 618, 95);
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
		panel_2.setBounds(10, 54, 742, 132);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textIdPlanta = new JTextField();
		textIdPlanta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnProduPais.setEnabled(true);
				btnLimpiar.setEnabled(true);
	            
	            btnCalProd.setEnabled(true);
			}
		});
		textIdPlanta.setBorder(null);
		textIdPlanta.setBounds(114, 5, 46, 20);
		panel_2.add(textIdPlanta);
		textIdPlanta.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(34, 11, 24, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("TIPO ENERGIA");
		lblNewLabel_2.setBounds(34, 36, 80, 25);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PAIS");
		lblNewLabel_3.setBounds(284, 11, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CAPACIDAD");
		lblNewLabel_4.setBounds(34, 72, 75, 14);
		panel_2.add(lblNewLabel_4);
		
		textCapacidad = new JTextField();
		textCapacidad.setBorder(null);
		textCapacidad.setBounds(114, 72, 86, 20);
		panel_2.add(textCapacidad);
		textCapacidad.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("PRODUCCION MENSUAL");
		lblNewLabel_5.setBounds(415, 8, 151, 20);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("PRODUCCION ANUAL");
		lblNewLabel_6.setBounds(415, 69, 151, 20);
		panel_2.add(lblNewLabel_6);
		
	    combxTipoEnergia = new JComboBox<>();
	    combxTipoEnergia.setToolTipText("");
		combxTipoEnergia.setBounds(114, 37, 75, 22);
		panel_2.add(combxTipoEnergia);
		
		
	    listPais = new JList();
	    listPais.setBounds(104, 42, 51, 90);
	    JScrollPane scrollLista = new JScrollPane();
		scrollLista.setBounds(260, 36, 110, 84);
		//panel_2.add(listPais);
		scrollLista.setViewportView(listPais);
		panel_2.add(scrollLista);
		
		textProducMensual = new JTextField();
		textProducMensual.setBounds(415, 38, 86, 20);
		panel_2.add(textProducMensual);
		textProducMensual.setColumns(10);
		
		textProducAnual = new JTextField();
		textProducAnual.setBounds(415, 100, 86, 20);
		panel_2.add(textProducAnual);
		textProducAnual.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("CAPACIDAD POR PAIS");
		lblNewLabel_7.setBounds(576, 8, 137, 14);
		panel_2.add(lblNewLabel_7);
		
		textCapaPais = new JTextField();
		textCapaPais.setBounds(576, 38, 86, 20);
		panel_2.add(textCapaPais);
		textCapaPais.setColumns(10);
		
		llenarLista();
		//inicializarBotonera();
		
		llenarCombos();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(231, 11, 314, 32);
		getContentPane().add(panel_3);
		
		JLabel lblNewLabel = new JLabel("FORMULARIO PRODUCCIÓN");
		panel_3.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(102, 297, 638, 62);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnCalProd = new JButton("CALCULAR PRUDUCCION");
		btnCalProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        
		            try {
						calcularProduccion();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		          }

			
			
		});
		

		btnCalProd.setBounds(10, 11, 147, 40);
		panel.add(btnCalProd);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				limpiarFormulario();
			}
		});
		btnLimpiar.setBounds(356, 11, 121, 40);
		panel.add(btnLimpiar);
		
	   
	    
	    btnProduPais = new JButton("PRODUC * PAIS");
	    btnProduPais.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					calcularProduccionPais();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    btnProduPais.setBounds(183, 11, 135, 40);
	    panel.add(btnProduPais);
		vistaListarPlanta();
	}
	
	public void vistaListarPlanta() throws SQLException{ 
        tableModel.setRowCount(0); // Limpiar la tabla
        
        List<Object[]> listado = plantaController.listarPlantaTabla();
        for (Object[]fila : listado) {
        	tableModel.addRow(fila);
        }
    }
	
	private void calcularProduccion() throws SQLException {
		 int  id =Integer.parseInt(textIdPlanta.getText());  
		 produccion = produccionController.producionAnual(id);
	        
	        if (produccion  == null) {
	            JOptionPane.showMessageDialog(this, "ID NO ENCONTRADO");
	        } else {
	            textCapacidad.setText(String.valueOf(produccion.getCapacidad()));
	            listPais.setSelectedValue(pais.getIdpais(), closable);
	            combxTipoEnergia.setSelectedItem(produccion.getId_tipoEnergia());
	            textProducAnual.setText(String.valueOf(produccion.produccionAnual(id)));
	            textProducMensual.setText(String.valueOf(produccion.produccionMensual(id)));
	            TipoEnergia item= comboMap.get(produccion.getId_tipoEnergia());
	            Pais itemPais= comboMapPais.get(pais.getIdpais());
	            
	           
	            if (item != null) {
	            	
	            	combxTipoEnergia.setSelectedItem(item);
	        	
	            }
	            
	           if (itemPais != null) {
	            	
	            	listPais.setSelectedValue(itemPais, closable);
	            	System.out.print(listPais);
	        	
	            }else { 
	            	System.out.print("es null");
	            }
	            
	            btnLimpiar.setEnabled(true);
	           
	            btnCalProd.setEnabled(true);
	        }
		
	}
	
	private void calcularProduccionPais() throws SQLException {
		// int  id =Integer.parseInt(textIdPlanta.getText()); 
		 Pais fkpais=(Pais) listPais.getSelectedValue();
		 if (fkpais  == null) {
	            JOptionPane.showMessageDialog(this, "ELIGE UN PAIS");
	        } else {
		 int idPais= fkpais.getIdpais();
		 
	     produccion = produccionController.producionPais(idPais);
	     System.out.println(produccion ); 
	        
	       
	        	textCapaPais.setText(String.valueOf(produccion.getCapacidad()));
	            listPais.setSelectedValue(pais.getIdpais(), closable);
	            combxTipoEnergia.setSelectedItem(produccion.getId_tipoEnergia());
	            textProducAnual.setText(String.valueOf(produccion.produccionAnual(idPais)));
	            textProducMensual.setText(String.valueOf(produccion.produccionMensual(idPais)));
	            TipoEnergia item= comboMap.get(produccion.getId_tipoEnergia());
	            Pais itemPais= comboMapPais.get(pais.getIdpais());
	            
	           
	            if (item != null) {
	            	
	            	combxTipoEnergia.setSelectedItem(item);
	        	
	            }
	            
	           if (itemPais != null) {
	            	
	            	listPais.setSelectedValue(itemPais, closable);
	            	System.out.print(listPais);
	        	
	            }else { 
	            	System.out.print("es null");
	            }
	            
	            btnLimpiar.setEnabled(true);
	           
	            btnCalProd.setEnabled(true);
	        }
		
	}


	
	
	private void llenarCombos() throws SQLException {        
		 List<TipoEnergia> listado = tipoEnergiaController.listarTipoEnergia();
        for (TipoEnergia item : listado) {
        	combxTipoEnergia.addItem(item);
            comboMap.put(item.getId_tipoEnergia(), item);
        }
       
    }
	
	private void llenarLista() throws SQLException {        
		 List<Pais> listado = PaisController.listarRegion();
		 DefaultListModel modelo = new DefaultListModel();
       
		 for (Pais item : listado) {
    	   modelo.addElement(item);
    	   listPais.setModel(modelo);
    	   comboMapPais.put(item.getIdpais(), item);
       }
      
   }
	
	



	private boolean validarVacios() {
	       boolean validado = false;
	       if (!textCapacidad.getText().trim().isEmpty())
	               validado = true;
	       return validado;
	    }
	private void limpiarFormulario(){
		textIdPlanta.setText("");
		textCapacidad.setText("");
		textProducMensual.setText("");
		textProducAnual.setText("");
        
    } 
	
	
}
