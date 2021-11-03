package presentacion;

import javax.swing.table.AbstractTableModel;

import entidades.Departamento;

import java.util.*;

public class DepartamentoTableModel extends AbstractTableModel{
	
	//Indices columnas
	
	private static final int COLUMNA_NUM_UNIDAD = 0;
	private static final int COLUMNA_NOMBRE_PROP = 1;
	private static final int COLUMNA_NOMBRE_COPROP = 2;
	private static final int COLUMNA_SALDO_ACTUAL = 3;
	private static final int COLUMNA_PAGO_ANT = 4;
	private static final int COLUMNA_INTERES = 5;
	private static final int COLUMNA_TOTAL = 6;
	
	//Nombre encabezados
	
	private String[] nombreColumnas = {"Unidad", "Nombre Propietario", "Nombre CoPropietario", "Saldo Actual", "Intereses", "Total a Pagar"};
	
	//Tipo de cada columna
	
	private Class[] tiposColumnas = {Integer.class, String.class, String.class, Float.class, Float.class, Float.class};
	
	private List<Departamento> contenido = new ArrayList<Departamento>();
	
	
	//Constructor vacio
	public DepartamentoTableModel () {
		contenido = new ArrayList<Departamento>();
	}
	
	//Constructor contenido inicial
	
	public DepartamentoTableModel (ArrayList<Departamento> contenidoInicial) {
		contenido = contenidoInicial;
	}
	
	//Metodos a pisar (columnas) 
	
	public int getColumnCount() {
		return nombreColumnas.length;
	}
	
	//Metodo a pisar (filas)
	
	public int getRowCount() {
		return contenido.size();
		
	}
	
	//Metodo a pisar (encabezados)
	@Override
	public String getColumnName (int col) {
		return nombreColumnas[col];
	
	}
	
	@Override
	public Class getColumnClass(int col) {
		return tiposColumnas[col];
	}

	
	public Object getValueAt (int rowIndex, int columnIndex) {
		
		Departamento d = contenido.get(rowIndex);
		
		Object result = null;
		
		switch(columnIndex) {
			case COLUMNA_NUM_UNIDAD:
				result = d.getUnidad();
				break;
			
			case COLUMNA_NOMBRE_PROP:
				result = d.getNombreProp();
				break;
				
			case COLUMNA_NOMBRE_COPROP:
				result = d.getNombreCop();
				break;
			
			case COLUMNA_SALDO_ACTUAL:
				result = d.getSaldoActual();
				break;
				
			case COLUMNA_PAGO_ANT:
				result = d.getSaldoActual();
				break;
				
		//	case COLUMNA_INTERES:
		//		result = d.getIntereses();
		//		break;
				
			case COLUMNA_TOTAL:
				result = 111111;
				break;
			
			default:
				result = new String("");
			
		}
		
		return result;
	}
	
	//Getter Setter de Contenido.
	
	public List<Departamento> getContenido(){
		return contenido;
	}
	
	public void setContenido(List<Departamento> contenido) {
		this.contenido = contenido;
	}
	
	public void departamentoAgregar(Departamento dpto) {
		this.contenido.add(dpto);
	}
	
	public void departamentoEditar(int index, Departamento dpto) {
		this.contenido.set(index, dpto);
	}
	
	public void departamentosEliminar(List<Departamento> listaAEliminar) {
		this.contenido.removeAll(listaAEliminar);
	}


}
