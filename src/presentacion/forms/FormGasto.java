package presentacion.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.Departamento;
import entidades.Gasto;
import modelo.dao.GastosDAO;
import presentacion.tablemodel.DepartamentoTableModel;
import presentacion.tablemodel.GastosTableModel;
import servicios.GastosServicio;

public class FormGasto extends JFrame  {
	
	// id del gasto en la base de datos
	private int id;
	// Indice del gasto en la tabla que se muestra
	private int index;
	
	private JLabel lblDescripcion = new JLabel("Descripción gasto:");
	
	private JTextField tfDescripcion = new JTextField("");
	
	private JLabel lblMonto = new JLabel("Monto:");
	
	private JTextField tfMonto = new JTextField("");
	
	private GastosTableModel modelo;
	
	public FormGasto(GastosTableModel table) {
		this(table, null, -1);
	}
	
	public FormGasto(GastosTableModel table, Gasto gasto, int index) {
		super();
		this.modelo = table;
		this.id = gasto != null ? gasto.getIdGasto() : -1;
		this.index = index;
		armarPanel(gasto);
	}
	
	//Asociacion del JTabel con TableModel
	private void armarPanel(Gasto gasto) {
//		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(lblDescripcion);
		
		panel.add(tfDescripcion);
		
		panel.add(lblMonto);
		
		panel.add(tfMonto);		
		
		if(gasto == null) {
			JButton crear = new JButton("Crear gasto");
			crear.addActionListener(this.getCreateActionListener());
			panel.add(crear);
		} else {
			JButton editar = new JButton("Editar gasto");
			editar.addActionListener(this.getEditActionListener());
			panel.add(editar);
			
			tfDescripcion.setText(gasto.getNombreGasto());
			tfMonto.setText(String.valueOf(gasto.getMontoGasto()));
		}
		this.getContentPane().add(panel);
		this.pack();
		this.setVisible(true);
	}
	
	private ActionListener getCreateActionListener() {
		JFrame panel = this;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Gasto gasto = getGastoFromForm();
					GastosServicio gastoServicio = new GastosServicio();
					int id = gastoServicio.crearGastos(gasto);
					
					JOptionPane.showMessageDialog(null, "Gasto creado satisfactoriamente");
					closeFrame(panel);
					modelo.gastoAgregar(gasto);
					modelo.fireTableDataChanged();
					
				} catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};	
	}
	
	private ActionListener getEditActionListener() {
		final JFrame panel = this;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Gasto gasto = getGastoFromForm();
					GastosServicio gastoServicio = new GastosServicio();
					int rowsAffected = gastoServicio.editarGastos(gasto);
				
					JOptionPane.showMessageDialog(null, "Gasto editado satisfactoriamente");
					modelo.gastoEditar(index, gasto);
					closeFrame(panel);
					modelo.fireTableDataChanged();
					
				} catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};	
	}
	
	private Gasto getGastoFromForm() throws Exception {
		int id = this.id;
		String descripcion = tfDescripcion.getText();
		float monto = 0f;
		try {
			monto = Float.valueOf(tfMonto.getText());
		} catch (Exception e) {
			throw new Exception("El monto tiene que ser un número");
		}
		
		
		return new Gasto(id, descripcion, monto);
	}
	
	private void closeFrame(JFrame panel) {
		panel.dispose();
	}

}
