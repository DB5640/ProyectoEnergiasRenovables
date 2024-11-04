package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import clases.Pais;
import clases.Planta;
import clases.TipoEnergia;
import controlador.GraficasController;
import controlador.PlantaController;
import controlador.RegionController;

import javax.swing.JTextField;
import javax.swing.plaf.synth.Region;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class VistaGraficas extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
    private PlantaController plantaController;
    private GraficasController graficasController;
    private JButton btnCircular, btnBarras;
    private JPanel pnlGraficas;
    private JFreeChart chart;
    DefaultTableModel tableModel= new DefaultTableModel();
	/**
	 * Launch the application.
	 */
    
   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGraficas frame = new VistaGraficas();
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
	public VistaGraficas() throws SQLException {
		
		plantaController = new PlantaController();
		
		graficasController= new GraficasController();
		
		
		
		setBounds(0, 0, 778, 400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 108, 135, 177);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnCircular = new JButton("CIRCULAR");
		btnCircular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					graficarPastel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btnCircular.setBounds(10, 27, 121, 54);
		panel.add(btnCircular);
		
		btnBarras = new JButton("BARRAS");
		btnBarras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					graficarHistograma();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBarras.setBounds(10, 102, 121, 54);
		panel.add(btnBarras);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(183, 271, 557, 88);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 535, 77);
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
		table.setModel(tableModel);
		table.setModel(tableModel); 
	
		 pnlGraficas = new JPanel();
		 pnlGraficas.setBounds(167, 11, 573, 249);
		 getContentPane().add( pnlGraficas);
		 pnlGraficas.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 119, 24);
		getContentPane().add(panel_3);
		
		JLabel lblNewLabel = new JLabel("GRAFICAS");
		panel_3.add(lblNewLabel);
		
		String[] titulostabla =new String[] {"ID PLANTA","TIPO ENERGIA","CAPACIDAD","AÑO","PAIS"};
		tableModel.setColumnIdentifiers(titulostabla);
		graficarPastel();
		vistaListarPlanta();
	}
	
	public void vistaListarPlanta() throws SQLException{ 
        tableModel.setRowCount(0); // Limpiar la tabla
        
        List<Object[]> listado = plantaController.listarPlantaTabla();
        for (Object[]fila : listado) {
        	tableModel.addRow(fila);
        }
    }
	
	private void graficarPastel() throws SQLException {
        List<Object[]> listaDatos = graficasController.listarTipoEnergia();
        DefaultPieDataset dataset = new DefaultPieDataset();
        listaDatos.forEach((registro) -> {
                String tipoFigura = (String) registro[0];
                Number cantidad = (Number) registro[1];
            dataset.setValue(tipoFigura, cantidad); //eje X y eje Y
        });
        chart = ChartFactory.createPieChart("GRAFICA POR TIPO DE ENERGIA", dataset, true, true, true);
         ChartPanel panel_2 = new ChartPanel(chart);
		 panel_2.setBounds(0, 0, 563, 249);
		 pnlGraficas.add(panel_2);
		 panel_2.setMouseWheelEnabled(true);
        pnlGraficas.validate();
    } //fin método que dibuja la gráfica
	
	private void graficarHistograma() throws SQLException{
        String chartTitle = "Cantidad de Figuras por Tipo"; 
        String categoryAxisLabel = "Tipo de Figura"; 
        String valueAxisLabel = "Cantidad"; 
        List<Object[]> listaDatos = graficasController.listarTipoEnergia();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);
        for (Object[] registro : listaDatos) {
            if (registro != null && registro[0] != null && registro[1] != null) {
                String tipoFigura = (String) registro[0];
                Number cantidad = (Number) registro[1];
                dataset.addValue(cantidad, "Cantidad", tipoFigura);
            } else {
                System.out.println("Registro nulo o incompleto: " + registro);
            }
        };         
        JFreeChart barChart = ChartFactory.createBarChart(
                "Distribución de Figuras",     // Título del gráfico
                "Tipo de Figura",              // Etiqueta del eje X
                "Cantidad",                    // Etiqueta del eje Y
                dataset                        // Datos
        );
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE); // Color para la serie 1        
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        pnlGraficas.setLayout(new java.awt.BorderLayout());
        pnlGraficas.add(panel, BorderLayout.CENTER);
        pnlGraficas.validate();
    } //fin método que dibuja la gráfica

}
