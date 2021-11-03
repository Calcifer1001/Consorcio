package modelo.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import entidades.Expensas;

public interface ExpensasDAOI {
	void crearTabla() throws SQLException;
	int crearExpensas(Expensas p) throws SQLException, ParseException;
	boolean eliminarExpensas(Expensas p) throws SQLException;
	int editarExpensas(Expensas p) throws SQLException;
	
	ArrayList<Expensas> listarTodos() throws SQLException;
}
