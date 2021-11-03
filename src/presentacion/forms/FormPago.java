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
import modelo.dao.DepartamentoDAO;
import presentacion.tablemodel.DepartamentoTableModel;
import presentacion.tablemodel.GastosTableModel;

public class FormPago extends JFrame {
	
	private Departamento departamento;
	
	private JLabel lblMonto = new JLabel("Monto:");
	
	private JTextField tfMonto = new JTextField("");
	
	private JButton buttonPagar = new JButton("Pagar");
	
	private DepartamentoTableModel modelo;
	private int index;


	
	public FormPago(DepartamentoTableModel modelo, Departamento departamento, int index) {
		this.departamento = departamento;
		this.modelo = modelo;
		this.index = index;
		this.armarPanel();
	}
	
	private void armarPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				
		panel.add(lblMonto);
		
		panel.add(tfMonto);
		
		panel.add(buttonPagar);
		
		buttonPagar.addActionListener(this.getPagarActionListener());
		
		this.getContentPane().add(panel);
		this.pack();
		this.setVisible(true);
	}
	
	private ActionListener getPagarActionListener() {
		final JFrame panel = this;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Float monto = Float.valueOf(tfMonto.getText());
					if(monto <= 0) {
						JOptionPane.showMessageDialog(null, "Se debe pagar una cantidad positiva");
						return;
					}
					DepartamentoDAO depDAO = new DepartamentoDAO();
					departamento.registrarPago(monto);
					depDAO.registrarPagoDeparatmento(departamento);
					panel.dispose();
					modelo.departamentoEditar(index, departamento);
					modelo.fireTableDataChanged();
					
					JOptionPane.showMessageDialog(null, "Pago registrado satisfactoriamente");
				} catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}

}
