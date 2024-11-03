package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JDesktopPane escritorio = new JDesktopPane();
		escritorio.setBounds(10, 43, 778, 400);
		escritorio.setBackground(new Color(255, 255, 255));
		contentPane.add(escritorio);
		escritorio.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Alexandra\\Desktop\\mayra\\ProyectoEnergiasRenovables-master\\imagenes\\imagen.jpg"));
		lblNewLabel.setBounds(10, 11, 758, 378);
		escritorio.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(30, 15, 80, 25);
		menuBar.setEnabled(false);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Energias");
		menuBar.add(mnNewMenu);
		
		JMenuItem item1= new JMenuItem("FORMULARIO PAIS");
		
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaRegion vistaPais= null;
				try {
					vistaPais = new VistaRegion();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				escritorio.add(vistaPais);
				vistaPais.show();
				
			}
		});
		mnNewMenu.add(item1);
		
       JMenuItem item2= new JMenuItem("FORMULARIO PLANTA");
		
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaPlanta vistaPlanta= null;
				try {
					vistaPlanta = new VistaPlanta();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				escritorio.add(vistaPlanta);
				vistaPlanta.show();
				
				
			}
		});
		mnNewMenu.add(item2);
		
		  JMenuItem item3= new JMenuItem("FORMULARIO TIPO ENERGIA");
			
			item3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTipoEnergia vistaTipoEnergia= null;
					try {
						vistaTipoEnergia = new VistaTipoEnergia();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					escritorio.add(vistaTipoEnergia);
					vistaTipoEnergia.show();
				}
			});
			mnNewMenu.add(item3);
			
			  JMenuItem item4= new JMenuItem("FORMULARIO PRODUCCION");
				
				item4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VistaProduccion vistaProduccion= null;
						try {
							vistaProduccion = new VistaProduccion();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						escritorio.add(vistaProduccion);
						vistaProduccion.show();
					}
				});
				mnNewMenu.add(item4);
				
			
		
	}
}
