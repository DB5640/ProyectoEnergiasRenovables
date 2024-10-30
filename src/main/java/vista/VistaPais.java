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
import controlador.RegionController;
import model.PaisModel;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class VistaPais extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textId;
	private JTextField textPais;
	private JTextField textCodigo;
	private JTextField textProduccion;
    private RegionController paisController;
    private Pais pais;
    DefaultTableModel tableModel= new DefaultTableModel();
	/**
	 * Launch the application.
	 */
    
   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPais frame = new VistaPais();
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
	public VistaPais() throws SQLException {
		
		paisController = new RegionController();
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
		panel_1.setBounds(183, 199, 557, 142);
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
		panel_2.setBounds(183, 66, 557, 122);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textId = new JTextField();
		textId.setBorder(null);
		textId.setBounds(54, 11, 46, 26);
		panel_2.add(textId);
		textId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(20, 17, 24, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PAIS");
		lblNewLabel_2.setBounds(20, 68, 46, 14);
		panel_2.add(lblNewLabel_2);
		
		textPais = new JTextField();
		textPais.setBorder(null);
		textPais.setBounds(54, 65, 86, 20);
		panel_2.add(textPais);
		textPais.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CODIGO");
		lblNewLabel_3.setBounds(150, 68, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		textCodigo = new JTextField();
		textCodigo.setBorder(null);
		textCodigo.setBounds(206, 65, 86, 20);
		panel_2.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("PRUDUCCIÓN");
		lblNewLabel_4.setBounds(302, 68, 75, 14);
		panel_2.add(lblNewLabel_4);
		
		textProduccion = new JTextField();
		textProduccion.setBorder(null);
		textProduccion.setBounds(387, 65, 86, 20);
		panel_2.add(textProduccion);
		textProduccion.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(284, 23, 314, 32);
		getContentPane().add(panel_3);
		
		JLabel lblNewLabel = new JLabel("FORMULARIO PAIS");
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
        String  nombre = textPais.getText();        
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
