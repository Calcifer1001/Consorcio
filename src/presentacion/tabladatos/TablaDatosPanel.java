package presentacion.tabladatos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import entidades.Departamento;
import entidades.Expensas;
import entidades.Gasto;
import modelo.dao.DepartamentoDAO;
import modelo.dao.DepartamentoDAOI;
import modelo.dao.ExpensasDAO;
import modelo.dao.ExpensasDAOI;
import modelo.dao.GastosDAO;
import modelo.dao.GastosDAOI;
import presentacion.tablemodel.DepartamentoTableModel;
import presentacion.tablemodel.ExpensasTableModel;
import presentacion.tablemodel.GastosTableModel;

import java.awt.*;
import java.sql.SQLException;
import java.util.*;

public class TablaDatosPanel extends JPanel {

	private JScrollPane scrollPaneDepartamentos;
	public static JTable tablaDatosDepartamentos;
	public static  DepartamentoTableModel modeloDepartamentos;
	private static DepartamentoDAOI depDAO = new DepartamentoDAO();
	
	private JScrollPane scrollPaneExpensas;
	public static JTable tablaDatosExpensas;
	public static ExpensasTableModel modeloExpensas;
	private static ExpensasDAOI expDAO = new ExpensasDAO();
	
	private JScrollPane scrollPaneGastos;
	public static JTable tablaDatosGastos;
	public static GastosTableModel modeloGastos;
	private static GastosDAOI gastosDAO = new GastosDAO();

	public static AbstractTableModel modelo;


	public TablaDatosPanel() {
		super();
		armarPanel();
	}
	
	public int[] getSelectedRows() {
		return this.tablaDatosDepartamentos.getSelectedRows();
		
	}

	//Asociacion del JTabel con TableModel

	private void armarPanel() {
		try {
			this.setLayout(new FlowLayout());

			this.armarDepartamentosTable();
			this.armarExpensasTable();
			this.armarGastosTable();
			//this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, e.getMessage());
		}

	}

	public DepartamentoTableModel getModelo() {
		return this.modeloDepartamentos;
	}
	
	private void armarDepartamentosTable() throws SQLException {
		modeloDepartamentos = new DepartamentoTableModel();
		tablaDatosDepartamentos = new JTable (modeloDepartamentos); 
		scrollPaneDepartamentos = new JScrollPane(tablaDatosDepartamentos);
		this.add(scrollPaneDepartamentos);		

		ArrayList<Departamento> ld = depDAO.listarTodos();

		modeloDepartamentos.setContenido(ld);

		modeloDepartamentos.fireTableDataChanged();
		
		scrollPaneDepartamentos.setVisible(false);
	}
	
	public static void resetDepartamentosTable() throws SQLException {
		ArrayList<Departamento> ld = depDAO.listarTodos();

		modeloDepartamentos.setContenido(ld);

		modeloDepartamentos.fireTableDataChanged();

		
	}
	
	public static void resetExpensasTable() throws SQLException {
		ArrayList<Expensas> ld = expDAO.listarTodos();

		modeloExpensas.setContenido(ld);

		modeloExpensas.fireTableDataChanged();
	}

	
	private void armarExpensasTable() throws SQLException {
		modeloExpensas = new ExpensasTableModel();
		tablaDatosExpensas = new JTable (modeloExpensas); 
		scrollPaneExpensas = new JScrollPane(tablaDatosExpensas);
		this.add(scrollPaneExpensas);		

		ArrayList<Expensas> ld = expDAO.listarTodos();

		modeloExpensas.setContenido(ld);

		modeloExpensas.fireTableDataChanged();
		
		scrollPaneExpensas.setVisible(false);
	}
	
	private void armarGastosTable() throws SQLException {
		modeloGastos= new GastosTableModel();
		tablaDatosGastos= new JTable(modeloGastos); 
		scrollPaneGastos = new JScrollPane(tablaDatosGastos);
		this.add(scrollPaneGastos);		

		ArrayList<Gasto> ld = gastosDAO.listarTodos();

		modeloGastos.setContenido(ld);

		modeloGastos.fireTableDataChanged();
		
		scrollPaneGastos.setVisible(false);
	}
	
	public void hideTablas() {
		this.scrollPaneDepartamentos.setVisible(false);
		this.scrollPaneExpensas.setVisible(false);
		this.scrollPaneGastos.setVisible(false);
	}
	
	public void showDepartamentos() {
		this.hideTablas();
		this.scrollPaneDepartamentos.setVisible(true);
		modelo = modeloDepartamentos;
	}
	
	public void showExpensas() {
		this.hideTablas();
		this.scrollPaneExpensas.setVisible(true);
		modelo = modeloExpensas;
	}
	
	public void showGastos() {
		this.hideTablas();
		this.scrollPaneGastos.setVisible(true);
		modelo = modeloGastos;
	}
}
