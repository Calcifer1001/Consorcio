package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Departamento;
import excepciones.DAOException;
import excepciones.DAOException;

public interface DepartamentoDAOI {
	void crearTabla() throws SQLException;
	int crearDepartamento(Departamento d) throws SQLException;
	void eliminarDepartamento(Departamento d) throws SQLException;
	int editarDeparatmento(Departamento d) throws SQLException;
	int registrarPagoDeparatmento(Departamento d) throws SQLException;
	
	ArrayList<Departamento> listarTodos() throws SQLException;

}
