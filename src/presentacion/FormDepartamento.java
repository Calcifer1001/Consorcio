package presentacion;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import entidades.Departamento;
import servicios.DepartamentoServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class FormDepartamento extends JPanel {
	
	
	private int id;
	private int index;
	
	private JLabel lblUnidad = new JLabel("Unidad:");
	
	private JTextField tfUnidad = new JTextField("");
	
	private JLabel lblPropietario = new JLabel("Propietario:");
	
	private JTextField tfPropietario = new JTextField("");
			
	private JLabel lblCopropietario = new JLabel("Copropietario:");
	
	private JTextField tfCopropietario = new JTextField("");
	
	private DepartamentoTableModel tablaPresentacion;
	
	
	public FormDepartamento(DepartamentoTableModel table) {
		this(table, null, -1);
	}
	
	public FormDepartamento(DepartamentoTableModel table, Departamento departamento, int index) {
		super();
		this.tablaPresentacion = table;
		this.id = departamento != null ? departamento.getId() : -1;
		this.index = index;
		armarPanel(departamento);
	}
	
	//Asociacion del JTabel con TableModel
	
	private void armarPanel(Departamento departamento) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
				
		this.add(lblUnidad);
		
		this.add(tfUnidad);
		
		this.add(lblPropietario);
		
		this.add(tfPropietario);		
		
		this.add(lblCopropietario);
		
		this.add(tfCopropietario);	

		if(departamento == null) {
			JButton crear = new JButton("Crear departamento");
			crear.addActionListener(this.getCreateActionListener());
			this.add(crear);
		} else {
			JButton editar = new JButton("Editar departamento");
			editar.addActionListener(this.getEditActionListener());
			this.add(editar);
			
			tfUnidad.setText(String.valueOf(departamento.getUnidad()));
			tfPropietario.setText(departamento.getNombreProp());
			tfCopropietario.setText(departamento.getNombreCop());
		}
		
	}

	private ActionListener getCreateActionListener() {
		final JPanel panel = this;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Departamento dep = getDepartamentoFromForm();
					DepartamentoServicio deptoServicio = new DepartamentoServicio();
					int id = deptoServicio.departamentoAgregar(dep);
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
		final JPanel panel = this;
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Departamento dep = getDepartamentoFromForm();
					DepartamentoServicio deptoServicio = new DepartamentoServicio();
					int rowsAffected = deptoServicio.departamentoModificar(dep);
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
		int id = this.id;
		int u = Integer.parseInt(tfUnidad.getText());
		String p = tfPropietario.getText();
		String cop = tfCopropietario.getText();
		
		return new Departamento(id, u,p,cop);
	}
	
	private void closeFrame(JPanel panel) {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
		frame.dispose();
	}


}
