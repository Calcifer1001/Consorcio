package modelo.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import entidades.Gasto;

public interface GastosDAOI {
	void crearTabla() throws SQLException;
	int crearGastos(Gasto g) throws SQLException, ParseException;
	boolean eliminarGastos(Gasto g) throws SQLException;
	int editarGastos(Gasto g) throws SQLException;
	
	ArrayList<Gasto> listarTodos() throws SQLException;
}
