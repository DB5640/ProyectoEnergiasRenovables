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
import controlador.PaisController;
import model.PaisModel;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JList;

public class VistaPlanta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textCodigo;
	private JTextField textProduccion;
    private PaisController paisController;
    private Pais pais;
    DefaultTableModel tableModel= new DefaultTableModel();
	/**
	 * Launch the application.
	 */
    
   
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
		
		paisController = new PaisController();
		pais = new Pais();
		
		
		setBounds(0, 0, 778, 400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 23, 151, 318);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCrear = new JButton("CREAR");
		
		btnCrear.setBounds(10, 27, 121, 54);
		panel.add(btnCrear);
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setBounds(10, 102, 121, 54);
		panel.add(btnActualizar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(10, 243, 121, 54);
		panel.add(btnEliminar);
		
		JButton btnBuscar = new JButton("BUSCAR");
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
		
		String[] titulostabla =new String[] {"ID","CODIGO","PAIS"};
		tableModel.setColumnIdentifiers(titulostabla);
		table.setModel(tableModel);
		vistaListarPais();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(205, 54, 557, 145);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textId = new JTextField();
		textId.setBorder(null);
		textId.setBounds(93, 42, 46, 26);
		panel_2.add(textId);
		textId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(42, 48, 24, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NOMBRE");
		lblNewLabel_2.setBounds(20, 96, 46, 14);
		panel_2.add(lblNewLabel_2);
		
		textNombre = new JTextField();
		textNombre.setBorder(null);
		textNombre.setBounds(93, 93, 86, 20);
		panel_2.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("PAIS");
		lblNewLabel_3.setBounds(189, 96, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		textCodigo = new JTextField();
		textCodigo.setBorder(null);
		textCodigo.setBounds(245, 93, 86, 20);
		panel_2.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("CAPACIDAD");
		lblNewLabel_4.setBounds(341, 96, 75, 14);
		panel_2.add(lblNewLabel_4);
		
		textProduccion = new JTextField();
		textProduccion.setBorder(null);
		textProduccion.setBounds(426, 93, 86, 20);
		panel_2.add(textProduccion);
		textProduccion.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("FECHA INICIAL");
		lblNewLabel_5.setBounds(160, 48, 86, 20);
		panel_2.add(lblNewLabel_5);
		
		JList list = new JList();
		list.setBounds(256, 47, 86, 20);
		panel_2.add(list);
		
		JLabel lblNewLabel_6 = new JLabel("FECHA FINAL");
		lblNewLabel_6.setBounds(343, 48, 86, 20);
		panel_2.add(lblNewLabel_6);
		
		JList list_1 = new JList();
		list_1.setBounds(426, 47, 86, 20);
		panel_2.add(list_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(284, 11, 314, 32);
		getContentPane().add(panel_3);
		
		JLabel lblNewLabel = new JLabel("FORMULARIO PLANTA");
		panel_3.add(lblNewLabel);

	}
	
	public void vistaListarPais() throws SQLException{ 
        tableModel.setRowCount(0); // Limpiar la tabla
        
        List<Pais> listado = paisController.listarPais();
        listado.forEach((Pais) -> { 
            tableModel.addRow(new Object[]{Pais.getId(), Pais.getCodigo(),Pais.getPais()});
        }); 
    }
	
	private void consultar() throws SQLException {        
        String  nombre = textNombre.getText();        
        pais = paisController.consultarPais(nombre);
        if (pais  == null) {
            JOptionPane.showMessageDialog(this, "PAIS NO ENCONTRADO");
        } else {
            textId.setText(String.valueOf(pais.getId()));
            textCodigo.setText(pais.getCodigo());
           /* btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnInsertar.setEnabled(false);*/
        }
    }
}
