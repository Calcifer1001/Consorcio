package presentacion.forms;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import entidades.Departamento;
import modelo.dao.DepartamentoDAO;
import presentacion.tablemodel.DepartamentoTableModel;
import servicios.DepartamentoServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class FormDepartamento extends JFrame {
	
	
	private Departamento departamento;
	private int index;
	
	private JLabel lblUnidad = new JLabel("Unidad:");
	
	private JTextField tfUnidad = new JTextField("");
	
	private JLabel lblPropietario = new JLabel("Propietario:");
	
	private JTextField tfPropietario = new JTextField("");
			
	private JLabel lblCopropietario = new JLabel("Copropietario:");
	
	private JTextField tfCopropietario = new JTextField("");
	private DepartamentoDAO depDAO = new DepartamentoDAO();
	
	private DepartamentoTableModel tablaPresentacion;
	
	
	public FormDepartamento(DepartamentoTableModel table) {
		this(table, null, -1);
	}
	
	public FormDepartamento(DepartamentoTableModel table, Departamento departamento, int index) {
		super();
		this.tablaPresentacion = table;
		this.departamento = departamento;
		this.index = index;
		armarPanel(departamento);
	}
	
	//Asociacion del JTabel con TableModel
	private void armarPanel(Departamento departamento) {
//		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				
		panel.add(lblUnidad);
		
		panel.add(tfUnidad);
		
		panel.add(lblPropietario);
		
		panel.add(tfPropietario);		
		
		panel.add(lblCopropietario);
		
		panel.add(tfCopropietario);	

		if(departamento == null) {
			JButton crear = new JButton("Crear departamento");
			crear.addActionListener(this.getCreateActionListener());
			panel.add(crear);
		} else {
			JButton editar = new JButton("Editar departamento");
			editar.addActionListener(this.getEditActionListener());
			panel.add(editar);
			
			tfUnidad.setText(String.valueOf(departamento.getUnidad()));
			tfPropietario.setText(departamento.getNombreProp());
			tfCopropietario.setText(departamento.getNombreCop());
		}
		this.getContentPane().add(panel);
		this.pack();
		this.setVisible(true);
	}

	private ActionListener getCreateActionListener() {
		final JFrame panel = this;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Departamento dep = getDepartamentoFromForm();
					DepartamentoServicio departamentoServicio = new DepartamentoServicio();
					int id = departamentoServicio.departamentoAgregar(dep);
					if(id != -1) {
						JOptionPane.showMessageDialog(null, "Departamento creado satisfactoriamente");
						closeFrame(panel);
						tablaPresentacion.departamentoAgregar(dep);
						tablaPresentacion.fireTableDataChanged();
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un problema al crear un departamento");
					}
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
					Departamento dep = getDepartamentoFromForm();
					DepartamentoServicio departamentoServicio = new DepartamentoServicio();
					int rowsAffected = departamentoServicio.departamentoModificar(dep);
					if(rowsAffected == 1) {
						JOptionPane.showMessageDialog(null, "Departamento editado satisfactoriamente");
						closeFrame(panel);
						tablaPresentacion.departamentoEditar(index, dep);
						tablaPresentacion.fireTableDataChanged();
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un problema al editar un departamento");
					}
				} catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};	
	}
	
	private Departamento getDepartamentoFromForm() {
		int id = -1;
		float saldoActual = 0;
		if(this.departamento != null) {
			id = this.departamento.getId();
			saldoActual = this.departamento.getSaldoActual();
		}
		
		int u = Integer.parseInt(tfUnidad.getText());
		String p = tfPropietario.getText();
		String cop = tfCopropietario.getText();
		
		return new Departamento(id, u,p,cop, saldoActual);
	}
	
	private void closeFrame(JFrame panel) {
		panel.dispose();
//		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
//		frame.dispose();
	}


}
