package presentacion.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entidades.Departamento;
import entidades.Expensas;
import entidades.Gasto;
import modelo.dao.GastosDAO;
import modelo.dao.GastosDAOI;
import presentacion.tablemodel.DepartamentoTableModel;
import presentacion.tablemodel.GastosTableModel;

public class DetalleExpensas extends JFrame {

	
	private JScrollPane scrollPaneGastos;
	public static JTable tablaDatosGastos;
	private JButton buttonClose= new JButton("Cerrar");
	
	private GastosTableModel modelo;
	private static GastosDAO gastosDAO = new GastosDAO();
	
	private Expensas expensa;
	
	public DetalleExpensas(Expensas expensa) throws SQLException {
		this.expensa = expensa;
		this.armarPanel();
		
	}
	
	private void armarPanel() throws SQLException {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				
//		panel.add(lblMonto);
		this.armarGastosTable();
		panel.add(scrollPaneGastos);
		panel.add(buttonClose);
		
		buttonClose.addActionListener(this.getCloseButtonListener());
		
		this.getContentPane().add(panel);
		this.pack();
		this.setVisible(true);
	}
	
	private void armarGastosTable() throws SQLException {
		modelo= new GastosTableModel();
		tablaDatosGastos= new JTable(modelo); 
		scrollPaneGastos = new JScrollPane(tablaDatosGastos);
		this.add(scrollPaneGastos);		

		ArrayList<Gasto> ld = gastosDAO.getGastos(this.expensa.getPeriodo());

		modelo.setContenido(ld);

		modelo.fireTableDataChanged();
		
		// scrollPaneGastos.setVisible(false);
	}
	
	private ActionListener getCloseButtonListener() {
		JFrame frame = this;
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};
	}

}
