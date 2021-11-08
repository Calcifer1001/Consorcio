package presentacion.tablemodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Departamento;
import entidades.Expensas;

public class ExpensasTableModel extends AbstractTableModel {

	//Indices columnas
	private static final int COLUMNA_UNIDAD_DEPARTAMENTO = 0;
	private static final int COLUMNA_EXPENSA_PERIODO = 1;
	private static final int COLUMNA_EXPENSA_VALOR = 2;
	private static final int COLUMNA_EXPENSA_FECHA_PAGO = 3;
	
	//Nombre encabezados
	
	private String[] nombreColumnas = {"Unidad departamento", "Periodo", "Monto", "Fecha de pago"};
	
	//Tipo de cada columna
	
	private Class[] tiposColumnas = {Integer.class, Date.class, Float.class, Date.class};
	
	private ArrayList<Expensas> contenido = new ArrayList<Expensas>();
	
	//Constructor vacio
	public ExpensasTableModel () {
		contenido = new ArrayList<Expensas>();
	}
	
	//Constructor contenido inicial
	
	public ExpensasTableModel (ArrayList<Expensas> contenidoInicial) {
		contenido = contenidoInicial;
	}
	
	//Metodo a pisar (encabezados)
	@Override
	public String getColumnName (int col) {
		return nombreColumnas[col];
	
	}


	@Override
	public int getRowCount() {
		return contenido.size();
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public Object getValueAt (int rowIndex, int columnIndex) {
		
		Expensas d = contenido.get(rowIndex);
		
		Object result = null;
		
		switch(columnIndex) {
			case COLUMNA_UNIDAD_DEPARTAMENTO:
				result = d.getUnidad();
				break;
			case COLUMNA_EXPENSA_PERIODO:
				result = d.getPeriodo();
				break;
			
			case COLUMNA_EXPENSA_VALOR:
				result = d.getValor();
				break;
				
			case COLUMNA_EXPENSA_FECHA_PAGO:
				result = d.getFechaPago();
				break;
			default:
				result = new String("");
			
		}
		
		return result;
	}
	
	public ArrayList<Expensas> getContenido(){
		return contenido;
	}
	
	public void setContenido(ArrayList<Expensas> contenido) {
		this.contenido = contenido;
	}
	
	public void expensaAgregar(Expensas expensa) {
		this.contenido.add(expensa);
	}
	

}
