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

import clases.TipoEnergia;
import clases.Planta;
import clases.TipoEnergia;
import controlador.TipoEnergiaController;
import controlador.TipoEnergiaController;

import javax.swing.JTextField;
import javax.swing.plaf.synth.Region;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class VistaTipoEnergia extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textIdTipoEnergia;
	private JTextField textEnergia;
    private TipoEnergiaController tipoEnergiaController;
    private TipoEnergia tipoEnergia;
    private JButton btnInsertar, btnActualizar,btnEliminar,btnBuscar;
    DefaultTableModel tableModel= new DefaultTableModel();
    private JTextField textFuente;
	/**
	 * Launch the application.
	 */
    
   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaTipoEnergia frame = new VistaTipoEnergia();
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
	public VistaTipoEnergia() throws SQLException {
		
		tipoEnergiaController = new TipoEnergiaController();
		tipoEnergia = new TipoEnergia();
		
		
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
				insertar();
			}
		});
		
		btnInsertar.setBounds(10, 27, 121, 54);
		panel.add(btnInsertar);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  actualizar();
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
		
		String[] titulostabla =new String[] {"ID","TIPO ENERGIA","FUENTE DE ENERGIA"};
		tableModel.setColumnIdentifiers(titulostabla);
		table.setModel(tableModel);
		vistaListarTipoEnergia();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(183, 66, 557, 69);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textIdTipoEnergia = new JTextField();
		textIdTipoEnergia.setBorder(null);
		textIdTipoEnergia.setBounds(54, 11, 38, 26);
		textIdTipoEnergia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnBuscar.setEnabled(true);
				btnActualizar.setEnabled(false);
	            btnEliminar.setEnabled(false);
	            btnInsertar.setEnabled(false);
			}
		});
		panel_2.add(textIdTipoEnergia);
		textIdTipoEnergia.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(20, 17, 24, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("TIPO ENERGIA");
		lblNewLabel_2.setBounds(102, 17, 80, 14);
		panel_2.add(lblNewLabel_2);
		
		textEnergia = new JTextField();
		textEnergia.setBorder(null);
		textEnergia.setBounds(192, 14, 89, 20);
		panel_2.add(textEnergia);
		textEnergia.setColumns(10);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarFormulario();
			}
		});
		btnLimpiar.setBounds(458, 13, 89, 23);
		panel_2.add(btnLimpiar);
		
		JLabel lblNewLabel_2_1 = new JLabel("FUENTE");
		lblNewLabel_2_1.setBounds(291, 17, 50, 14);
		panel_2.add(lblNewLabel_2_1);
		
		textFuente = new JTextField();
		textFuente.setColumns(10);
		textFuente.setBorder(null);
		textFuente.setBounds(342, 14, 89, 20);
		panel_2.add(textFuente);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(284, 23, 314, 32);
		getContentPane().add(panel_3);
		
		JLabel lblNewLabel = new JLabel("FORMULARIO TIPO ENERGIA");
		panel_3.add(lblNewLabel);
        
		inicializarBotonera();
	}
	
	public void vistaListarTipoEnergia() throws SQLException{ 
        tableModel.setRowCount(0); // Limpiar la tabla
        
        List<TipoEnergia> listado = tipoEnergiaController.listarTipoEnergia();
        listado.forEach((TipoEnergia) -> { 
            tableModel.addRow(new Object[]{TipoEnergia.getId_tipoEnergia(),TipoEnergia.getNombreEnergia(),TipoEnergia.getFuente()});
        }); 
    }
	
	private void insertar() {
        String nombreTipoEnergia = textEnergia.getText();
        String fuente = textFuente.getText();
        TipoEnergia tipoEnergia= new TipoEnergia(0,nombreTipoEnergia,fuente);
        
        try {
            tipoEnergiaController.agregarTipoEnergia(tipoEnergia);
            JOptionPane.showMessageDialog(this, "El Tipo de Energia fue agregado");
            vistaListarTipoEnergia();
            limpiarFormulario(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
	private void actualizar() {
        int id = Integer.parseInt(textIdTipoEnergia.getText());
        String nombre= textEnergia.getText();
        String fuente= textFuente.getText();
        TipoEnergia tipoEnergia = new TipoEnergia(id,nombre,fuente);
        try {
            tipoEnergiaController.actualizarTipoEnergia(tipoEnergia);
            JOptionPane.showMessageDialog(this, "TipoEnergia Actualizado");
            vistaListarTipoEnergia();
            limpiarFormulario();
            inicializarBotonera();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	private void consultar() throws SQLException {        
        int  id = Integer.parseInt(textIdTipoEnergia.getText());        
        tipoEnergia = tipoEnergiaController.consultarTipoEnergia(id);
        if (tipoEnergia  == null) {
            JOptionPane.showMessageDialog(this, "TIPO DE ENERGIA NO ENCONTRADO");
        } else {
            textEnergia.setText(tipoEnergia.getNombreEnergia());
            textFuente.setText(tipoEnergia.getFuente());
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnInsertar.setEnabled(false);
        }
    }
	private void eliminar() throws SQLException {
        int id = Integer.parseInt(textIdTipoEnergia.getText());
        tipoEnergiaController.eliminarTipoEnergia(id);        
        this.limpiarFormulario();
        vistaListarTipoEnergia();
        JOptionPane.showMessageDialog(this, "Registro eliminado exitosamente");
    }


	private boolean validarVacios() {
	       boolean validado = false;
	       if (!textIdTipoEnergia.getText().trim().isEmpty())
	               validado = true;
	       return validado;
	    }
	private void limpiarFormulario(){
		textIdTipoEnergia.setText("");
		textEnergia.setText("");
		textFuente.setText("");
	    inicializarBotonera();
    } 
	
	private void inicializarBotonera(){       
		btnInsertar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(false);
    }
}
