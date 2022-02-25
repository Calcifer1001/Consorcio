package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import entidades.Departamento;
import entidades.Expensas;
import entidades.Gasto;
import presentacion.forms.FormDepartamento;
import presentacion.forms.FormGasto;
import presentacion.forms.FormPago;
import presentacion.popup.DetalleExpensas;
import presentacion.tabladatos.TablaDatosPanel;
import presentacion.tablemodel.DepartamentoTableModel;
import presentacion.tablemodel.ExpensasTableModel;
import presentacion.tablemodel.GastosTableModel;
import servicios.DepartamentoServicio;
import servicios.ExpensaServicio;
import servicios.GastosServicio;
import utils.DateUtils;

public class ActionPanel extends JPanel {
	
	private JButton departamentoCrearButton;
	private JButton departamentoEditarButton;
	private JButton departamentoEliminarButton;
	private JButton departamentoPagarButton;
	
	private JButton expensasDetalleButton;
	
	private JButton gastosCrearButton;
	private JButton gastosEditarButton;
	private JButton gastosEliminarButton;
	private JButton gastosGenerarButton;
	
	public ActionPanel() {
		super();
		this.armarPanel();
	}

	private void armarPanel() {
		try {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			
			this.departamentoCrearButton = new JButton("Crear");
			this.departamentoEditarButton = new JButton("Editar");
			this.departamentoEliminarButton = new JButton("Eliminar");
			this.departamentoPagarButton = new JButton("Pagar");
			
			this.expensasDetalleButton = new JButton("Detalle");
			
			this.gastosCrearButton = new JButton("Crear");
			this.gastosEditarButton = new JButton("Editar");
			this.gastosEliminarButton = new JButton("Eliminar");
			this.gastosGenerarButton = new JButton("Generar");
			
			this.departamentoCrearButton.addActionListener(this.getCrearDepartamentoClickListener());
			this.departamentoEditarButton.addActionListener(this.getEditarDepartamentoClickListener());
			this.departamentoEliminarButton.addActionListener(this.getEliminarDepartamentoClickedListener());
			this.departamentoPagarButton.addActionListener(this.getRegistrarPagoDepartamentoClickListener());
			
			this.expensasDetalleButton.addActionListener(this.getDetalleExpensaClickListener());

			
			this.add(departamentoCrearButton);
			this.add(departamentoEditarButton);
			this.add(departamentoEliminarButton);
			this.add(departamentoPagarButton);
			
			this.add(expensasDetalleButton);
			
			this.add(gastosCrearButton);
			this.add(gastosEditarButton);
			this.add(gastosEliminarButton);
			this.add(gastosGenerarButton);
			this.gastosCrearButton.addActionListener(this.getCrearGastoClickListener());
			this.gastosEditarButton.addActionListener(this.getEditarGastoClickListener());
			this.gastosEliminarButton.addActionListener(this.getEliminarGastoClickedListener());
			this.gastosGenerarButton.addActionListener(this.getGenerarExpensaClickedListener());
					
			hidePanel();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, e.getMessage());
		}
	}
	
	private void hidePanel() {
		departamentoCrearButton.setVisible(false);
		departamentoEditarButton.setVisible(false);
		departamentoEliminarButton.setVisible(false);
		departamentoPagarButton.setVisible(false);
		
		expensasDetalleButton.setVisible(false);
		
		gastosCrearButton.setVisible(false);
		gastosEditarButton.setVisible(false);
		gastosEliminarButton.setVisible(false);
		gastosGenerarButton.setVisible(false);
	}
	
	public void showDepartamentos() {
		hidePanel();
		departamentoCrearButton.setVisible(true);
		departamentoEditarButton.setVisible(true);
		departamentoEliminarButton.setVisible(true);
		departamentoPagarButton.setVisible(true);
	}
	
	public void showExpensas() {
		hidePanel();
		expensasDetalleButton.setVisible(true);
	}
	
	public void showGastos() {
		hidePanel();
		gastosCrearButton.setVisible(true);
		gastosEditarButton.setVisible(true);
		gastosEliminarButton.setVisible(true);
		gastosGenerarButton.setVisible(true);
	}
	
	private ActionListener getCrearDepartamentoClickListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new FormDepartamento((DepartamentoTableModel) TablaDatosPanel.modelo);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}
	
	private ActionListener getEditarDepartamentoClickListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DepartamentoTableModel model = (DepartamentoTableModel) TablaDatosPanel.modelo;
					int[] selectedIndexes = TablaDatosPanel.tablaDatosDepartamentos.getSelectedRows();
					if(selectedIndexes.length != 1) {
						throw new Exception("Debe seleccionar una unica fila");
					}
					int index  = selectedIndexes[0];
					Departamento dptoSeleccionado = model.getContenido().get(index);
					new FormDepartamento(model, dptoSeleccionado, index);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}
	
	private ActionListener getEliminarDepartamentoClickedListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				try {
					int[] seleccionados = TablaDatosPanel.tablaDatosDepartamentos.getSelectedRows();
					if(seleccionados.length == 0) {
						throw new Exception("Debe seleccionar una unica fila");
					}
					DepartamentoTableModel model = (DepartamentoTableModel) TablaDatosPanel.modelo;
					List<Departamento> listaAEliminar = new ArrayList<Departamento>();
					// Mover el DAO a la capa de servicio
					DepartamentoServicio departamentoServicio = new DepartamentoServicio();
					for (int indice : seleccionados) {
						Departamento dpto = model.getContenido().get(indice);
						departamentoServicio.departamentoEliminar(dpto.getId());
						listaAEliminar.add(dpto);
					}
					model.departamentosEliminar(listaAEliminar);
					model.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Todos los departamentos fueron eliminados satisfactoriamente");
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}
	
	private ActionListener getRegistrarPagoDepartamentoClickListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DepartamentoTableModel model = (DepartamentoTableModel) TablaDatosPanel.modelo;
					int[] selectedIndexes = TablaDatosPanel.tablaDatosDepartamentos.getSelectedRows();
					if(selectedIndexes.length != 1) {
						throw new Exception("Debe seleccionar una unica fila");
					}
					int index  = selectedIndexes[0];
					Departamento dptoSeleccionado = model.getContenido().get(index);
					new FormPago(model, dptoSeleccionado, index);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}
	
	private ActionListener getDetalleExpensaClickListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ExpensasTableModel model = (ExpensasTableModel) TablaDatosPanel.modelo;
					int[] selectedIndexes = TablaDatosPanel.tablaDatosExpensas.getSelectedRows();
					if(selectedIndexes.length != 1) {
						throw new Exception("Debe seleccionar una unica fila");
					}
					int index  = selectedIndexes[0];
					Expensas expensaSeleccionada = model.getContenido().get(index);
					
					new DetalleExpensas(expensaSeleccionada);					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}
	
	private ActionListener getCrearGastoClickListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new FormGasto((GastosTableModel) TablaDatosPanel.modelo);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}
	
	private ActionListener getEditarGastoClickListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GastosTableModel model = (GastosTableModel) TablaDatosPanel.modelo;
					int[] selectedIndexes = TablaDatosPanel.tablaDatosGastos.getSelectedRows();
					if(selectedIndexes.length != 1) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una unica fila");
						return;
					}
					int index  = selectedIndexes[0];
					Gasto dptoSeleccionado = model.getContenido().get(index);
					new FormGasto(model, dptoSeleccionado, index);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
	}
	
	private ActionListener getEliminarGastoClickedListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				try {
					int[] seleccionados = TablaDatosPanel.tablaDatosGastos.getSelectedRows();
					if(seleccionados.length == 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una fila");
						return;
					}
					GastosTableModel model = (GastosTableModel) TablaDatosPanel.modelo;
					List<Gasto> listaAEliminar = new ArrayList<Gasto>();
					// Mover el DAO a la capa de servicio
					GastosServicio gastosServicio = new GastosServicio();
					for (int indice : seleccionados) {
						Gasto gasto = model.getContenido().get(indice);
						gastosServicio.eliminarGastos(gasto);
						listaAEliminar.add(gasto);
					}
					model.gastosEliminar(listaAEliminar);
					model.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Todos los gastos fueron eliminados satisfactoriamente");
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		};
	}
	
	private ActionListener getGenerarExpensaClickedListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				try {
					// Mover el DAO a la capa de servicio
					GastosServicio gastosServicio = new GastosServicio();

					// Mover el DAO a la capa de servicio
					DepartamentoServicio departamentoServicio = new DepartamentoServicio();
					
					// Mover el DAO a la capa de servicio
					ExpensaServicio expensaServicio = new ExpensaServicio();
					
					float gastosTotales = gastosServicio.getGastosMesPasado();
					List<Departamento> departamentos = departamentoServicio.departamentosListar();
					
					float montoPorDepartamento = gastosTotales / departamentos.size();
					Date periodo = DateUtils.formatAnoMes(DateUtils.obtenerMes(-1)[0]);
					for (Departamento departamento : departamentos) {
						Expensas expensa = new Expensas(-1, departamento.getId(), -1, periodo, montoPorDepartamento, null);
						expensaServicio.crearExpensas(expensa);
						
						departamentoServicio.departamentoExpensaRegistrar(departamento, montoPorDepartamento);
					}
					
					
					TablaDatosPanel.resetDepartamentosTable();
					TablaDatosPanel.resetExpensasTable();

					JOptionPane.showMessageDialog(null, "La expensa se genero satisfactoriamente");
				} catch(JdbcSQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "Ya se genero la expensa en este periodo y no se puede volver a generar. " + ex.getMessage());
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido inesperado. Por favor reintente nuevamente o contacte al administrador");
				}
			}
		};
	}
	

}
