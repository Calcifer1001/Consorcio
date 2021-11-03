package presentacion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentacion.tabladatos.TablaDatosPanel;

public class Navbar extends JPanel {
	
	private JButton departamentosButton;
	private JButton gastosButton;
	private JButton expensasButton;
	
	private ActionPanel actionSection;
	private TablaDatosPanel tableSection;
	
	public Navbar(ActionPanel actionSection, TablaDatosPanel tableSection) {
		super();
		this.actionSection = actionSection;
		this.tableSection = tableSection;
		armarPanel();
		
	}
	
	private void armarPanel() {
		try {
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			this.departamentosButton = new JButton("Departamentos");
			this.gastosButton = new JButton("Gastos");
			this.expensasButton = new JButton("Expensas");
			
			departamentosButton.addActionListener(this.getDepartamentoClickListener());
			gastosButton.addActionListener(this.getGastosClickListener());
			expensasButton.addActionListener(this.getExpensasClickListener());
			
			this.add(departamentosButton);
			this.add(gastosButton);
			this.add(expensasButton);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, e.getMessage());
		}
	}
	
	private AbstractAction getDepartamentoClickListener() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableSection.showDepartamentos();
					actionSection.showDepartamentos();
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Hubo un problema al mostrar la seccion de departamentos");
				}
			}
		};
	}
	
	private AbstractAction getGastosClickListener() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableSection.showGastos();
				actionSection.showGastos();
			}
		};
	}
	
	private AbstractAction getExpensasClickListener() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableSection.showExpensas();
				actionSection.showExpensas();
			}
		};
	}
	

}
