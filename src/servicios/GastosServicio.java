package servicios;

import entidades.Expensas;
import entidades.Gasto;
import modelo.dao.GastosDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GastosServicio {
	
	private GastosDAO gastoDao;
	
	public GastosServicio() {
		this.gastoDao = new GastosDAO();
	}
	
	
	public List<Gasto> listarTodos() throws SQLException {
		
		return this.gastoDao.listarTodos();
	}
	
	
	public int crearGastos(Gasto gasto) throws SQLException, ParseException {
		return this.gastoDao.crearGastos(gasto);
		
	}
	
	
	public boolean eliminarGastos(Gasto gasto) throws SQLException {
		return this.gastoDao.eliminarGastos(gasto);
		
	}
	
	public int editarGastos(Gasto gasto) throws SQLException {
		return this.gastoDao.editarGastos(gasto);
		
	}
	
	public float getGastosMesPasado() throws SQLException {
		return this.gastoDao.getGastosMesPasado();
	}
	
	
	
	

}

