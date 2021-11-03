package presentacion.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Gasto;

public class GastosTableModel extends AbstractTableModel{
	
	//Indices columnas
		private static final int COLUMNA_NOMBRE_GASTO = 0;
		private static final int COLUMNA_MONTO = 1;
		
		//Nombre encabezados
		
		private String[] nombreColumnas = {"Descripción gasto", "Monto"};
		
		//Tipo de cada columna
		
		private Class[] tiposColumnas = {String.class, Float.class};
		
		private ArrayList<Gasto> contenido = new ArrayList<Gasto>();
		
		//Constructor vacio
		public GastosTableModel () {
			contenido = new ArrayList<Gasto>();
		}
		
		//Constructor contenido inicial
		
		public GastosTableModel (ArrayList<Gasto> contenidoInicial) {
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
			
			Gasto d = contenido.get(rowIndex);
			
			Object result = null;
			
			switch(columnIndex) {
				case COLUMNA_NOMBRE_GASTO:
					result = d.getNombreGasto();
					break;
				
				case COLUMNA_MONTO:
					result = d.getMontoGasto();
					break;
				default:
					result = new String("");
				
			}
			
			return result;
		}
		
		public ArrayList<Gasto> getContenido(){
			return contenido;
		}
		
		public void setContenido(ArrayList<Gasto> contenido) {
			this.contenido = contenido;
		}
		
		public void gastoAgregar(Gasto gasto) {
			this.contenido.add(gasto);
		}
		
		public void gastoEditar(int index, Gasto gasto) {
			this.contenido.set(index, gasto);
		}
		
		public void gastosEliminar(List<Gasto> listaAEliminar) {
			this.contenido.removeAll(listaAEliminar);
		}

}
