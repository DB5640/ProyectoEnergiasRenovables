package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Pais;
import controlador.RegionController;

import javax.swing.JTextField;
import javax.swing.plaf.synth.Region;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class VistaRegion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textId;
	private JTextField textPais;
    private RegionController regionController;
    private Pais pais;
    private JButton btnInsertar, btnActualizar,btnEliminar,btnBuscar;
    DefaultTableModel tableModel= new DefaultTableModel();
	/**
	 * Launch the application.
	 */
    
   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegion frame = new VistaRegion();
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
	public VistaRegion() throws SQLException {
		
		regionController = new RegionController();
		pais = new Pais();
		
		
		setBounds(0, 0, 778, 400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 23, 151, 318);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnInsertar = new JButton("CREAR");
		
		btnInsertar.setBounds(10, 27, 121, 54);
		panel.add(btnInsertar);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setBounds(10, 102, 121, 54);
		panel.add(btnActualizar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		panel_1.setBounds(183, 146, 557, 195);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 11, 537, 173);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"comlum1", "colum2", "colum3", "colum4"
			}
		));
		table.setBounds(0, 0, 537,120);
		
		String[] titulostabla =new String[] {"ID","PAIS"};
		tableModel.setColumnIdentifiers(titulostabla);
		table.setModel(tableModel);
		vistaListarPais();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(183, 66, 557, 69);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textId = new JTextField();
		textId.setBorder(null);
		textId.setBounds(54, 11, 46, 26);
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnBuscar.setEnabled(true);
				btnActualizar.setEnabled(true);
	            btnEliminar.setEnabled(true);
	            btnInsertar.setEnabled(false);
			}
		});
		panel_2.add(textId);
		textId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(20, 17, 24, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PAIS");
		lblNewLabel_2.setBounds(163, 17, 46, 14);
		panel_2.add(lblNewLabel_2);
		
		textPais = new JTextField();
		textPais.setBorder(null);
		textPais.setBounds(219, 14, 130, 20);
		panel_2.add(textPais);
		textPais.setColumns(10);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarFormulario();
			}
		});
		btnLimpiar.setBounds(428, 13, 89, 23);
		panel_2.add(btnLimpiar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(284, 23, 314, 32);
		getContentPane().add(panel_3);
		
		JLabel lblNewLabel = new JLabel("FORMULARIO PAIS");
		panel_3.add(lblNewLabel);
        
		inicializarBotonera();
	}
	
	public void vistaListarPais() throws SQLException{ 
        tableModel.setRowCount(0); // Limpiar la tabla
        
        List<Pais> listado = regionController.listarRegion();
        listado.forEach((Pais) -> { 
            tableModel.addRow(new Object[]{Pais.getIdpais(),Pais.getNombre()});
        }); 
    }
	
	private void consultar() throws SQLException {        
        int  id = Integer.parseInt(textId.getText());        
        pais = regionController.consultarPais(id);
        if (pais  == null) {
            JOptionPane.showMessageDialog(this, "PAIS NO ENCONTRADO");
        } else {
            textPais.setText(String.valueOf(pais.getNombre()));
            
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnInsertar.setEnabled(false);
        }
    }
	private void eliminar() throws SQLException {
        int id = Integer.parseInt(textId.getText());
        regionController.eliminarPais(id);        
        this.limpiarFormulario();
        vistaListarPais();
        JOptionPane.showMessageDialog(this, "Registro eliminado exitosamente");
    }


	private boolean validarVacios() {
	       boolean validado = false;
	       if (!textId.getText().trim().isEmpty())
	               validado = true;
	       return validado;
	    }
	private void limpiarFormulario(){
		textId.setText("");
		textPais.setText("");
	    inicializarBotonera();
    } 
	
	private void inicializarBotonera(){       
		btnInsertar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(false);
    }
}
