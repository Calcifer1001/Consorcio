package presentacion;

import javax.swing.*;

import entidades.Departamento;
import servicios.DepartamentoServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TablaDatos extends JPanel implements ActionListener{

	private JTable tablaDatos;
	private  DepartamentoTableModel modelo;
	private JScrollPane scrollPaneParaTabla;
	private JButton botonIngreso;
	private JButton botonNuevo;
	private JButton botonEgreso;
	private JButton botonEditar;
	private JButton botonBorrar;
	private JButton botonGastos;


	public TablaDatos() {
		super();
		armarPanel();
	}
	
	public int[] getSelectedRows() {
		return this.tablaDatos.getSelectedRows();
		
	}

	//Asociacion del JTabel con TableModel

	private void armarPanel() {
		try {
			this.setLayout(new FlowLayout());

			modelo = new DepartamentoTableModel();
			tablaDatos = new JTable (modelo);
			JFrame parentWindow = (JFrame)SwingUtilities.windowForComponent(this); 
			scrollPaneParaTabla = new JScrollPane(tablaDatos);
			this.add(scrollPaneParaTabla);		
			JButton botonNuevo = new JButton( new AbstractAction("Nuevo depto") {
				@Override
				public void actionPerformed( ActionEvent e ) {
					JFrame j2 = new JFrame("Nuevo Departamento");
					departamentoFormDraw(j2);
				}
			});
			this.add(botonNuevo);
			botonIngreso = new JButton("Ingreso $");
			botonIngreso.addActionListener(this);
			this.add(botonIngreso);
			botonIngreso.addActionListener(this);
			botonEgreso = new JButton("Egreso $");
			botonEgreso.addActionListener(this);
			this.add(botonEgreso);


			botonEditar = new JButton("Editar");
			botonEditar.addActionListener(this.getEditarClickedListener());
			this.add(botonEditar);

			botonBorrar = new JButton(this.getBorrarClickedListener());

			this.add(botonBorrar);

			botonGastos = new JButton ("Gastos");
			botonGastos.addActionListener(this);
			this.add(botonGastos);
			DepartamentoServicio depServicio = new DepartamentoServicio();
			List<Departamento> ld = depServicio.departamentosListar();

			modelo.setContenido(ld);

			modelo.fireTableDataChanged();
			//this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, e.getMessage());
		}

	}
	
	private AbstractAction getEditarClickedListener() {
		return new AbstractAction("Editar departamento") {
			@Override
			public void actionPerformed( ActionEvent e ) {
				int[] seleccionados = getSelectedRows();
				if(seleccionados.length != 1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar solo una fila");
					return;
				}
				
				int seleccionado = seleccionados[0];
				Departamento dptoSeleccionado = modelo.getContenido().get(seleccionado);
				JFrame j2 = new JFrame("Editar Departamento");
				departamentoFormDraw(j2, dptoSeleccionado, seleccionado);

			}
		};
	}
	
	private AbstractAction getBorrarClickedListener() {
		return new AbstractAction("Borrar") {
			@Override
			public void actionPerformed( ActionEvent e ) {
				try {
					int[] seleccionados = getSelectedRows();
					if(seleccionados.length == 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una fila");
						return;
					}
					List<Departamento> listaAEliminar = new ArrayList<Departamento>();
					DepartamentoServicio depServicio = new DepartamentoServicio();
					for (int indice : seleccionados) {
						Departamento dpto = modelo.getContenido().get(indice);
						depServicio.departamentoEliminar(dpto.getId());
						listaAEliminar.add(dpto);
					}
					modelo.departamentosEliminar(listaAEliminar);
					modelo.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Todos los departamentos fueron eliminados satisfactoriamente");
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}
	
	private void departamentoFormDraw(JFrame j2) {
		this.departamentoFormDraw(j2, null, -1);
	}
	
	private void departamentoFormDraw(JFrame j2, Departamento dpto, int index) {
		j2.getContentPane().add(new FormDepartamento(modelo, dpto, index));
		j2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		j2.pack();
		j2.setVisible(true);
	}

	public DepartamentoTableModel getModelo() {
		return this.modelo;
	}
	@Override
	public void actionPerformed(ActionEvent e) {


	}


}
